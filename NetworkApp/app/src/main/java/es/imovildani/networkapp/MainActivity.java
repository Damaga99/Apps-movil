package es.imovildani.networkapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.net.ssl.HandshakeCompletedEvent;

import es.imovildani.networkapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private BusStopListAdapter busStopListAdapter;
    private BusStopViewModel busStopViewModel;
    ActivityMainBinding binding;

    private SwipeRefreshLayout swipeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(R.layout.activity_main);

/**

         Button btnComprobarConexion = (Button) findViewById(R.id.Comprobarconexión);
         btnComprobarConexion.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
               /* // Si está conectado...
                if (isOnline() == true) {
                    // Lo notificamos por pantalla
                    Toast.makeText(v.getContext(), "Estás conectado a la red", Toast.LENGTH_SHORT).show();
                }
                // Si no está conectado...
                else {
                    // Lo notificamos por pantalla
                     Toast.makeText(v.getContext(), "NO estás conectado a la red", Toast.LENGTH_SHORT).show();
                }


                busStopViewModel.updateBusStops();
            }
        });

*/
        // 10.1 Asignar al RecyclerView de la clase vinculada al layout de la actividad un LinearLayoutManager.
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 10.2 Necesitamos un miembro privado (busStopListAdapter) en MainActivity que sea una instancia del
        //      RecyclerViewAdapter que hemos construído. Instáncialo y asígnalo al RecyclerView de la clase
        //      vinculada.
        BusStopListAdapter busStopListAdapter = new BusStopListAdapter();
        recyclerView.setAdapter(busStopListAdapter);

        // 10.3 También es necesario tener otro miembro privado que mantenga una referencia al ViewModel de la
        //      vista que controla MainActivity. Decláralo con el nombre busStopsViewModel e inicializarlo con
        //      el código:
        busStopViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(BusStopViewModel.class);


        busStopViewModel.getBusStopsList().observe(this, new Observer<Llegadas>() {
            @Override
            public void onChanged(Llegadas llegadas) {
                // 10.4.1 Realiza los cambios indicados

                // 10.4.2 Incluye en onChanged el código que modifica los datos del adaptador del RecyclerView llamando
                //          a setBusStops().
                busStopListAdapter.setBusStops(llegadas);


            }
        });
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipetorefresh);


        swipeLayout.setOnRefreshListener(this);
/**
        binding.swipetorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh() {
                //Aqui ejecutamos el codigo necesario para refrescar nuestra interfaz grafica.
                //Antes de ejecutarlo, indicamos al swipe layout que muestre la barra indeterminada de progreso.
                swipeLayout.setRefreshing(true);

                //Vamos a simular un refresco con un handle.
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        //Se supone que aqui hemos realizado las tareas necesarias de refresco, y que ya podemos ocultar la barra de progreso
                        swipeLayout.setRefreshing(false);

                    }
                }, 3000);
            }
        });
*/

}

    @Override
    public void onRefresh() {
        //Aqui ejecutamos el codigo necesario para refrescar nuestra interfaz grafica.
        //Antes de ejecutarlo, indicamos al swipe layout que muestre la barra indeterminada de progreso.
        swipeLayout.setRefreshing(true);

        //Vamos a simular un refresco con un handle.
        Handler handler = new Handler();
        busStopViewModel.updateBusStops();
        handler.postDelayed(new Runnable() {
            public void run() {
                //Se supone que aqui hemos realizado las tareas necesarias de refresco, y que ya podemos ocultar la barra de progreso
                Context context= getApplicationContext();

                Toast.makeText(context ,"Actualizado",Toast.LENGTH_SHORT).show();

                swipeLayout.setRefreshing(false);

            }
        }, 3000);

    }

    public enum AppStatus {
        ERROR, UPDATE, DOWNLOADING
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.refresh:
                busStopViewModel.updateBusStops();
                Context context= getApplicationContext();

                Toast.makeText(context ,"Actualizado",Toast.LENGTH_SHORT).show();


                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return true;
    }

    // 3.5 Añade la función isOnline() a la clase de la actividad principal.
    boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


}