package es.imovildani.practica2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="Actividad 1";
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"Estoy en onCreate()");
        if(savedInstanceState ==null){
            Log.d(TAG,"Bundle es null");
        }
        else{
            Log.d(TAG,"Bundle no es null");
        }
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity2Intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(Activity2Intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Estoy en onStart()");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Estoy en onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Estoy en onDestroy()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Estoy en onResumed()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Estoy en onPause()");
        long fin=System.currentTimeMillis() + 6*1000;
        while (System.currentTimeMillis() < fin) {
            try {
                wait(fin - System.currentTimeMillis());
            } catch (Exception e){}
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"Estoy en onRestart()");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"Estoy en onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"Estoy en onRestoreInstanceState()");
    }
}