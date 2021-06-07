package es.imovildani.drawablesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button boton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boton =findViewById(R.id.button);
       // Drawable rect = ContextCompat.getDrawable(getApplicationContext(), R.drawable.rectangulo);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton.setBackgroundResource(R.drawable.rectangulo);
            }
        });


    }
}