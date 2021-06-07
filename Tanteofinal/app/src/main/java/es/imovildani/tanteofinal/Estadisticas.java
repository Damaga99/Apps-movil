package es.imovildani.tanteofinal;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import es.imovildani.tanteofinal.databinding.ActivityMain2Binding;
import es.imovildani.tanteofinal.databinding.ActivityMainBinding;


public class Estadisticas extends AppCompatActivity implements View.OnClickListener{

    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // Recibimos los datos
        Intent intent = getIntent();
        // Almacenamos los datos
        Bundle bundle = intent.getExtras();
        // Creamos la referencia al clase TeamPoints
        TeamPoints TeamLoc = bundle.getParcelable(MainActivity.LOCAL); //TeamPoints.CREATOR.createFromParcel(bundle.getParcelable(MainActivity.LOCAL));
        TeamPoints TeamVis = bundle.getParcelable(MainActivity.VISITANTE);



        // Asignamos los valores maximos a cada ProgressBar
        binding.PBloc1.setMax(TeamLoc.getPoints()); //pb_marc_1_loc.setMin(0);
        binding.PBvis1.setMax(TeamVis.getPoints()); //pb_marc_1_vis.setMin(0);
        binding.PBloc2.setMax(TeamLoc.getPoints()); //pb_marc_2_loc.setMin(0);
        binding.PBvis2.setMax(TeamVis.getPoints()); //pb_marc_2_vis.setMin(0);
        binding.PBloc3.setMax(TeamLoc.getPoints()); //pb_marc_3_loc.setMin(0);
        binding.PBvis3.setMax(TeamVis.getPoints()); //pb_marc_3_vis.setMin(0);

        // Asignamos el dato recibido al ProgressBar
        binding.PBloc1.setProgress(TeamLoc.getDe1()*1);
        binding.PBvis1.setProgress(TeamVis.getDe1()*1);
        binding.PBloc2.setProgress(TeamLoc.getDe2()*2);
        binding.PBvis2.setProgress(TeamVis.getDe2()*2);
        binding.PBloc3.setProgress(TeamLoc.getDe3()*3);
        binding.PBvis3.setProgress(TeamVis.getDe3()*3);

        // Asignamos el valor de cada submarcador a su etiqueta correspondiente
        binding.TVloc1.setText(String.valueOf(TeamLoc.getDe1()));
        binding.TVvis1.setText(String.valueOf(TeamVis.getDe1()));
        binding.TVloc2.setText(String.valueOf(TeamLoc.getDe2()));
        binding.TVvis2.setText(String.valueOf(TeamVis.getDe2()));
        binding.TVloc3.setText(String.valueOf(TeamLoc.getDe3()));
        binding.TVvis3.setText(String.valueOf(TeamVis.getDe3()));
    }


    @Override
    public void onClick(View v) {
        // Hacemos la operacion del boton volver
        if(v.getId() == R.id.Bvolver)
        {
            pasarAPartido(v);
        }
    }



    private void pasarAPartido(View v) {
        // Creamos el Intent para pasar a la otra activity
        Intent i = new Intent(this, MainActivity.class);
        // Comenzamos la segunda activity
        startActivity(i);
    }


}