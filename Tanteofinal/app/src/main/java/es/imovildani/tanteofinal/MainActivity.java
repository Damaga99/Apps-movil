package es.imovildani.tanteofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import es.imovildani.tanteofinal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String LOCAL = "PUNTOS_LOCAL";
    public static final String VISITANTE = "PUNTOS_VISITANTE";

    //TeamPoints teamLocal, teamVisit;
    ActivityMainBinding binding;
    MyViewModel myViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Creamos la referencia al clase ViewModel
        // (esta llamada siempre devuelve la misma instancia de myViewModel)
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        if (savedInstanceState != null) {
           binding.textView4.setText(String.valueOf(myViewModel.TeamLoc.getDe1()));
            binding.textView5.setText(String.valueOf(myViewModel.TeamLoc.getDe2()));
            binding.textView6.setText(String.valueOf(myViewModel.TeamLoc.getDe3()));
            binding.textView7.setText(String.valueOf(myViewModel.TeamVis.getDe1()));
            binding.textView8.setText(String.valueOf(myViewModel.TeamVis.getDe2()));
            binding.textView9.setText(String.valueOf(myViewModel.TeamVis.getDe3()));
            binding.Valorlocal.setText(String.valueOf(myViewModel.TeamLoc.getPoints()));
            binding.Valorvisitante.setText(String.valueOf(myViewModel.TeamVis.getPoints()));

        }



        binding.Blocal1.setOnClickListener(this);
        binding.Blocal2.setOnClickListener(this);
        binding.Blocal3.setOnClickListener(this);
        binding.Bvisitante1.setOnClickListener(this);
        binding.Bvisitante2.setOnClickListener(this);
        binding.Bvisitante3.setOnClickListener(this);

        binding.Bestadistica.setOnClickListener(this);



    }





        @Override
        public void onClick(View v){
            int id = v.getId();

                switch(id){
                    // En caso de sumar 1 canasta de 1 al LOCAL
                    case R.id.Blocal1:
                        // Obtenemos la referencia al valor convertido
                        myViewModel.TeamLoc.setDe1(myViewModel.TeamLoc.getDe1() + 1);
                        // Asignamos el valor calculado en formato string
                        binding.textView4.setText(String.valueOf(myViewModel.TeamLoc.getDe1()));
                        // Cerramos la operacion
                        break;


                    // En caso de sumar 1 canasta de 2 al LOCAL
                    case R.id.Blocal2:
                        // Obtenemos la referencia al valor convertido
                        myViewModel.TeamLoc.setDe2(myViewModel.TeamLoc.getDe2() + 1);
                        // Asignamos el valor calculado en formato string
                        binding.textView5.setText(String.valueOf(myViewModel.TeamLoc.getDe2()));
                        // Cerramos la operacion
                        break;


                    // En caso de sumar 1 canasta de 3 al LOCAL
                    case R.id.Blocal3:
                        // Obtenemos la referencia al valor convertido
                        myViewModel.TeamLoc.setDe3(myViewModel.TeamLoc.getDe3() + 1);
                        // Asignamos el valor calculado en formato string
                        binding.textView6.setText(String.valueOf(myViewModel.TeamLoc.getDe3()));
                        // Cerramos la operacion
                        break;


                    // En caso de sumar 1 canasta de 1 al VISITANTE
                    case R.id.Bvisitante1:
                        // Obtenemos la referencia al valor convertido
                        myViewModel.TeamVis.setDe1(myViewModel.TeamVis.getDe1() + 1);
                        // Asignamos el valor calculado en formato string
                        binding.textView7.setText(String.valueOf(myViewModel.TeamVis.getDe1()));
                        // Cerramos la operacion
                        break;


                    // En caso de sumar 1 canasta de 2 al VISITANTE
                    case R.id.Bvisitante2:
                        // Obtenemos la referencia al valor convertido
                        myViewModel.TeamVis.setDe2(myViewModel.TeamVis.getDe2() + 1);
                        // Asignamos el valor calculado en formato string
                        binding.textView8.setText(String.valueOf(myViewModel.TeamVis.getDe2()));
                        // Cerramos la operacion
                        break;


                    // En caso de sumar 1 canasta de 3 al VISITANTE
                    case R.id.Bvisitante3:
                        // Obtenemos la referencia al valor convertido
                        myViewModel.TeamVis.setDe3(myViewModel.TeamVis.getDe3() + 1);
                        // Asignamos el valor calculado en formato string
                        binding.textView9.setText(String.valueOf(myViewModel.TeamVis.getDe3()));
                        // Cerramos la operacion
                        break;

                    case R.id.Bestadistica:
                        Intent intent = new Intent(this, Estadisticas.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(LOCAL,myViewModel.getLocal());
                        bundle.putParcelable(VISITANTE, myViewModel.getVisitante());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;



                   }


                binding.Valorlocal.setText(String.valueOf(myViewModel.TeamLoc.getPoints()));
                binding.Valorvisitante.setText(String.valueOf(myViewModel.TeamVis.getPoints()));

                }




    }
