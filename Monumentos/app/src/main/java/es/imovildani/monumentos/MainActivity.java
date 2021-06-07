package es.imovildani.monumentos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import es.imovildani.monumentos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MonumentoListFragment.Callbacks , ListItemOnClickInterface {
    private MonumentoAdapter MonumentoAdapter;
    private MonumentoViewModel MonumentoViewModel;
    ActivityMainBinding binding;

    public static final String NAME_REPLY = "NAME_REPLY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(R.layout.activity_main);



        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MonumentoAdapter MonumentoAdapter = new MonumentoAdapter(this);
        recyclerView.setAdapter(MonumentoAdapter);


        MonumentoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MonumentoViewModel.class);
        MonumentoViewModel.getMonumentoList().observe(this, new Observer<List<Monumento>>() {
            @Override
            public void onChanged(List<Monumento> String) {
                MonumentoAdapter.setMonumento(String);

            }
        });


        MonumentoViewModel.getCourseNames().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> courses) {

                MonumentoAdapter.setNames(courses);
            }
        });






    }
    @Override
    public void onItemClick(String name) {
        Intent intent = new Intent(this.getApplicationContext(),MonumentoDetailsActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString(NAME_REPLY,name);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    LiveData<List<Monumento>> lsitaMonumentos = MonumentoViewModel.getMonumentoList();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            case R.id.buscar:
                /*
                LiveData<List<Monumento>> lsitaMonumentos = MonumentoViewModel.getMonumentoList();

                ArrayList<List<Monumento>> filtrarLista = new LiveData<Monumento>();

                for(Monumento usuario : lsitaMonumentos) {
                    if(usuario.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                        filtrarLista.add(usuario);
                    }
                }

                MonumentoAdapter.filtrar(filtrarLista);
*/
                break;
            case R.id.mapa:

                LiveData<List<Monumento>> lista = MonumentoViewModel.getMonumentoList();
                ArrayList<String> lista2 = new ArrayList<>();
                ArrayList<String> lat = new ArrayList<>();
                ArrayList<String> lon = new ArrayList<>();
                ArrayList<String> nombres = new ArrayList<>();
                System.out.println(lista2.size());
                System.out.println("_---------------------------------------------------------------------");
                for(int i=0;i<lista.getValue().size() ;i++){
                    String l = lista.getValue().get(i).getLocalizacion();
                    String l2=  l.replaceAll("Lon: ","");
                    String l3 = l2.replaceAll(" Lat:","");
                    lista2.add(l3);
                    String[] parts= l3.split(", ");
                    lat.add(parts[1]);
                    lon.add(parts[0]);
                    nombres.add(lista.getValue().get(i).getTitulo());

                }

                Intent intent = new Intent(getApplicationContext() , MapsActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("nombres",nombres);
                startActivity(intent);

                break;

            case R.id.action_settings:
                MonumentoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MonumentoViewModel.class);


                break;
        }
        return true;

    }


    @Override
    public void onCourseSelected(Monumento monumento) {

    }
}