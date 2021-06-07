package es.imovildani.practica2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG="Actividad 2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG,"Estoy en onCreate()");
        if(savedInstanceState ==null){
            Log.d(TAG,"Bundle es null");
        }
        else{
            Log.d(TAG,"Bundle no es null");
        }
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