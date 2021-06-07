package es.imovildani.tanteo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import es.imovildani.tanteo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Button button1l;
    Button button2l;
    Button button3l;
    Button button1v;
    Button button2v;
    Button button3v , Boton;
   TextView Valorlocal;
   TextView Valorvisitante;
    TextView textView4, textView5, textView6;
    TextView textView7, textView8, textView9;
    ProgressBar Barral1, Barral2, Barral3, Barrav1, Barrav2 , Barrav3;
    ActivityMainBinding binding ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);




        textView4 = (TextView)  findViewById(R.id.textView4); textView4.setText(""+0);

        textView5 = (TextView)  findViewById(R.id.textView5); textView5.setText(""+0);

        textView6 = (TextView)  findViewById(R.id.textView6); textView6.setText(""+0);

        textView7 = (TextView)  findViewById(R.id.textView7); textView7.setText(""+0);

        textView8 = (TextView)  findViewById(R.id.textView8); textView8.setText(""+0);

        textView9 = (TextView)  findViewById(R.id.textView9); textView9.setText(""+0);




        button1l= findViewById(R.id.Blocal1);
        button1l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Valorlocal= findViewById(R.id.Valorlocal);

                Integer num= Integer.parseInt(Valorlocal.getText().toString());
                textView4 = (TextView)  findViewById(R.id.textView4);

                Valorlocal.setText(""+(num+1));

                Integer num2= Integer.parseInt(textView4.getText().toString());
                textView4.setText(""+(num2+1));

            }
        });
        button2l = findViewById(R.id.Blocal2);
        button2l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Valorlocal= findViewById(R.id.Valorlocal);;

                Integer num= Integer.parseInt(Valorlocal.getText().toString());

                Valorlocal.setText(""+(num+2));

                textView5 = findViewById(R.id.textView5);
                Integer num2= Integer.parseInt(textView5.getText().toString());
                textView5.setText(""+(num2+1));
            }
        });

        button3l = findViewById(R.id.Blocal3);
        button3l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Valorlocal= findViewById(R.id.Valorlocal);;

                Integer num= Integer.parseInt(Valorlocal.getText().toString());

                Valorlocal.setText(""+(num+3));

                textView6 = findViewById(R.id.textView6);
                Integer num2= Integer.parseInt(textView6.getText().toString());
                textView6.setText(""+(num2+1));
            }
        });

        button1v = findViewById(R.id.Bvisitante1);
        button1v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Valorvisitante= findViewById(R.id.Valorvisitante);;

                Integer num= Integer.parseInt(Valorvisitante.getText().toString());

                Valorvisitante.setText(""+(num+1));

                textView7 = findViewById(R.id.textView7);
                Integer num2= Integer.parseInt(textView7.getText().toString());
                textView7.setText(""+(num2+1));
            }
        });

        button2v = findViewById(R.id.Bvisitante2);
        button2v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Valorvisitante= findViewById(R.id.Valorvisitante);;

                Integer num= Integer.parseInt(Valorvisitante.getText().toString());

                Valorvisitante.setText(""+(num+2));

                textView8 = findViewById(R.id.textView8);
                Integer num2= Integer.parseInt(textView8.getText().toString());
                textView8.setText(""+(num2+1));
            }
        });


        button3v = findViewById(R.id.Bvisitante3);
        button3v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Valorvisitante= findViewById(R.id.Valorvisitante);;

                Integer num= Integer.parseInt(Valorvisitante.getText().toString());

                Valorvisitante.setText(""+(num+3));

                textView9 = findViewById(R.id.textView9);
                Integer num2= Integer.parseInt(textView9.getText().toString());
                textView9.setText(""+(num2+1));
            }
        });

        Boton = findViewById(R.id.Bestadistica);
        Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity2Intent = new Intent(MainActivity.this,Estadisticas.class);
                System.out.println(textView4.getText().toString());

                    Activity2Intent.putExtra("textview4", textView4.getText().toString());
                    Activity2Intent.putExtra("textview5", textView5.getText().toString());
                    Activity2Intent.putExtra("textview6", textView6.getText().toString());
                    Activity2Intent.putExtra("textview7", textView7.getText().toString());
                    Activity2Intent.putExtra("textview8", textView8.getText().toString());
                    Activity2Intent.putExtra("textview9", textView9.getText().toString());

                startActivity(Activity2Intent);
            }
        });

    }
}