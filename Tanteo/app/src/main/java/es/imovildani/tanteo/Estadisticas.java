package es.imovildani.tanteo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Estadisticas extends AppCompatActivity {

    ProgressBar Barral1, Barral2, Barral3, Barrav1, Barrav2 , Barrav3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);


        TextView text= findViewById(R.id.local1);
        String text2 = getIntent().getStringExtra("textview4");
        Barral1 = findViewById(R.id.Barralocal1);
        Barral1.setProgress(Integer.parseInt(text2)+1);
        System.out.println(text2);
        text.setText(""+Integer.parseInt(text2));


        text= findViewById(R.id.local2);
        text2 = getIntent().getStringExtra("textview5");
        Barral1 = findViewById(R.id.Barralocal2);
        Barral1.setProgress(Integer.parseInt(text2)+1);
        System.out.println(text2);
        text.setText(""+Integer.parseInt(text2.toString()));

        text= findViewById(R.id.local3);
        text2 = getIntent().getStringExtra("textview6");
        Barral1 = findViewById(R.id.Barralocal3);
        Barral1.setProgress(Integer.parseInt(text2)+1);
        System.out.println(text2);
        text.setText(""+Integer.parseInt(text2.toString()));


        text= findViewById(R.id.visitante1);
        text2 = getIntent().getStringExtra("textview7");
        Barral1 = findViewById(R.id.Barravisitante1);
        Barral1.setProgress(Integer.parseInt(text2)+1);
        System.out.println(text2);
        text.setText(""+Integer.parseInt(text2.toString()));


        text= findViewById(R.id.visitante2);
        text2 = getIntent().getStringExtra("textview8");
        Barral1 = findViewById(R.id.Barravisitante2);
        Barral1.setProgress(Integer.parseInt(text2)+1);
        System.out.println(text2);
        text.setText(""+Integer.parseInt(text2.toString()));


        text= findViewById(R.id.visitante3);
        text2 = getIntent().getStringExtra("textview9");
        Barral1 = findViewById(R.id.Barravisitante3);
        Barral1.setProgress(Integer.parseInt(text2)+1);
        System.out.println(text2);
        text.setText(""+Integer.parseInt(text2.toString()));



    }


}