 package es.imovildani.monumentos;

 import android.os.Bundle;

 import androidx.fragment.app.FragmentActivity;
 import androidx.lifecycle.ViewModelProvider;

 import com.google.android.gms.maps.CameraUpdateFactory;
 import com.google.android.gms.maps.GoogleMap;
 import com.google.android.gms.maps.OnMapReadyCallback;
 import com.google.android.gms.maps.SupportMapFragment;
 import com.google.android.gms.maps.model.LatLng;
 import com.google.android.gms.maps.model.Marker;
 import com.google.android.gms.maps.model.MarkerOptions;

 import java.util.ArrayList;


 public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

     private GoogleMap mMap;
     private MonumentoViewModel MonumentoViewModel;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_maps);
         // Obtain the SupportMapFragment and get notified when the map is ready to be used.
         SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                 .findFragmentById(R.id.map);
         mapFragment.getMapAsync(this);

         MonumentoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MonumentoViewModel.class);

     }



     @Override
     public void onMapReady(GoogleMap googleMap) {
         mMap = googleMap;

         ArrayList<String> lista = (ArrayList<String>) getIntent().getSerializableExtra("lat");
         ArrayList<String> lista2 = (ArrayList<String>) getIntent().getSerializableExtra("lon");
         ArrayList<String> nombres = (ArrayList<String>) getIntent().getSerializableExtra("nombres");
         System.out.println("_----------------------------------------------------------------------------------------");

         System.out.println(lista.size());
         System.out.println(lista2.size());
         System.out.println(lista.get(1).toString());
         System.out.println(nombres.size());

         for(int i=0;i<lista.size();i++){
             Float l= Float.parseFloat(lista.get(i));

             Float l2= Float.parseFloat(lista2.get(i));

             LatLng a = new LatLng(l , l2);
             mMap.addMarker(new MarkerOptions().position(a).title(nombres.get(i)));

         }


         // Add a marker in Sydney and move the camera
         LatLng prueba = new LatLng(43.548032091977, -5.6386973055846);
         mMap.addMarker(new MarkerOptions().position(prueba).title("Gijon"));


         mMap.setMinZoomPreference(11);
         mMap.moveCamera(CameraUpdateFactory.newLatLng(prueba));

         googleMap.setOnInfoWindowClickListener(this);
     }

     @Override
     public void onInfoWindowClick(Marker marker) {

     }
 }