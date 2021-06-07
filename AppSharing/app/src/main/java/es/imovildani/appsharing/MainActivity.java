package es.imovildani.appsharing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.List;

import es.imovildani.appsharing.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    static final int REQUEST_CAMERA =10;


    private EditText editText;

    ImageView imagen1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(R.layout.activity_main);


        editText =findViewById(R.id.message);
        imagen1 =(ImageView) findViewById(R.id.imageView);
        Button boton = findViewById(R.id.share_message);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                EditText editText =findViewById(R.id.message);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editText.getText().toString());

                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });


        Button boton2 = findViewById(R.id.send_email);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.fromParts("mailto", "damaga1999@gmail.com", // destinatario
                        null));

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Prueba desde AppSharing");
                EditText editText =findViewById(R.id.message);
                emailIntent.putExtra(Intent.EXTRA_TEXT, editText.getText().toString());
                startActivity(emailIntent);
            }
        });


        Button boton3 = findViewById(R.id.get_image);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               cameraAccess();

            }
        });


        Toast.makeText(this , getIntent().getAction().toString() ,Toast.LENGTH_SHORT).show();


        if(Intent.ACTION_SEND  == getIntent().getAction() ){
            editText.setText("");
            editText.setText(getIntent().getExtras().get(Intent.EXTRA_TEXT).toString());
        }



    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            Log.d("VISITA","PASAMOS POR if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK)");
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagen1.setImageBitmap(imageBitmap);
        }
    }


    private void cameraAccess() {

        // Comprueba si el permiso de acceso al almacenamiento externo está disponible
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // El permiso no ha sido concedido aún
            requestCameraPermission();
        } else {
            // El permiso está concedido. Accede a la cámara
            cameraDoWork();
        }
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            // Suministra una información adicional al usuario cuando el permiso no fue
            // concedido por primera vez para que tome una decisión informada

            // Muestra un SnackBar con un botón que lanza la solicitud del permiso
            Snackbar.make(binding.getRoot(), R.string.permission_camera_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                        }
                    })
                    .show();
        } else {
            // El permiso no ha sido concedido aún. Solicitarlo directamente
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA) {
            // Recibido el resultado de la solicitud de permiso de acceso
            // a la cámara

            // Comprueba si el permiso ha sido concedido
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido. El trabajo puede hacerse
                cameraDoWork();
            } else {
                // Permiso no concedido
                // Realizar alguna acción como informar al usuario
                // con un Toast
                Context context= getApplicationContext();

                Toast.makeText(context ,"Permiso no concedido",Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void cameraDoWork(){

        Intent intento1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        startActivityForResult(intento1,REQUEST_CAMERA);


    }


}