package es.imovildani.nine_patch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import es.imovildani.nine_patch.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    Button boton;
    ActivityMainBinding binding;
    TextView textview;

    Button boton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        boton = findViewById(R.id.button);
        textview = findViewById(R.id.textView);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               textview.setText(R.string.cadenalarga);


            }
        });

        boton2=findViewById(R.id.button3);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.textView);
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.appearance);
                textView.startAnimation(animation);
            }
        });



    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


}