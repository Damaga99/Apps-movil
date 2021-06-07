package es.imovildani.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_nueva);

        button2= findViewById(R.id.button2);
        button3= findViewById(R.id.button3);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
       /* button.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView textview = (TextView) findViewById(R.id.textView3);
                textview.setVisibility(View.INVISIBLE);
            }


        });
        */
    }

    public void pulsacion(View v){
         TextView textview ;
         textview = (TextView) findViewById(R.id.textView3);
         textview.setVisibility(View.VISIBLE);

    }


    @Override
    public void onClick(View view) {
        if(view.getId() ==R.id.button2){
            TextView textview = (TextView) findViewById(R.id.textView3);
            textview.setVisibility(View.VISIBLE);
        }
        if(view.getId() == R.id.button3){
            TextView textview = (TextView) findViewById(R.id.textView3);
            textview.setVisibility(View.INVISIBLE);
        }


    }
}