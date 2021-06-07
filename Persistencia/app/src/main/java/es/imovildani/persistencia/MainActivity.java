package es.imovildani.persistencia;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import es.imovildani.persistencia.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    BookViewModel bookViewModel;
    NavController navController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        // 1. Declara un miembro en MainActivity de tipo BookViewModel. El código de instanciación,
        //    que incluiremos en onCreate(), tiene que utilizar un AndroidViewModelFactory ya que el
        //    ViewModel tiene un constructor no vacío. El código es el siguiente:
        bookViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(BookViewModel.class);


        // 2. En MainActivity es necesario acceder al controlador de navegación para conseguir que las
        //    pulsaciones en el FAB nos lleven a DataEntryFragment. PAra ello, necesitamos una referencia
        //    al NavHostFragment que contiene todos los destinos de navegación. La línea de código que lo
        //    permite es:
        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        // 3. A partir del NavHostFragment podemos acceder al controlador de navegación. Declararemos un
        //    miembro de la actividad para que contenga la referencia. El código es:
        navController = navHostFragment.getNavController();

        // 4. Ahora podemos asociar los eventos de pulsar en el FAB con el controlador.
        Navigation.setViewNavController(binding.fab, navController);

        // 5. Solo resta navegar a DataEntryFragment desde la implementación del evento de pulsación
        //    sobre el FAB.
        binding.fab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.DataEntryFragment));

        navHostFragment.getNavController().addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.ListFragment) {
                    if (binding.fab != null) {
                        binding.fab.setVisibility(View.VISIBLE);
                    }
                }
                // Código de gestión de otros destinos
                if (destination.getId() == R.id.DataEntryFragment) {
                    if (binding.fab != null) {
                        binding.fab.setVisibility(View.INVISIBLE);
                    }
                }

                // Código de gestión de otros destinos
                if (destination.getId() == R.id.action_settings ){
                    if (binding.fab != null) {
                        binding.fab.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "On Start", Toast.LENGTH_SHORT).show();
        bookViewModel.restoreBooksList();
    }

    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "On Pause", Toast.LENGTH_SHORT).show();
        bookViewModel.saveBooksList();
    }

   
}