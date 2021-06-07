package es.imovildani.servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentServicio;
        intentServicio = new Intent(getApplicationContext(), LocalMusicService.class);

           .......
        
        public void onClick(View v) {
            startService(intentServicio);
        }

           .......
        public void onClick(View v) {
            stopService(intentServicio);
        }
          .......

    }
}