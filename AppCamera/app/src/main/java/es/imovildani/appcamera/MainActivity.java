package es.imovildani.appcamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.imovildani.appcamera.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 10;
    private static final String AUTHORITY_NAME = "nombre";
    ActivityMainBinding binding;
    static final int REQUEST_CAMERA =10;

    Button boton;
    File fichimagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(R.layout.activity_main);

        boton = findViewById(R.id.button);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);

                cameraAccess();
            }
        });

    }


    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            // Suministra una información adicional al usuario cuando el permiso no fue
            // concedido por primera vez para que tome una decisión informada

            // Muestra un SnackBar con requn botón que lanza la solicitud del permiso
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
    private void cameraDoWork() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fichimagen = crearFichero();
        if (fichimagen != null) {
            try {
                Uri uriFile = FileProvider.getUriForFile(this, AUTHORITY_NAME, fichimagen);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriFile);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }


    private File crearFichero() {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = "Imagen_" + timeStamp + ".jpg";
        File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(directory, filename);
        if (file.exists())
            file.delete();
        else {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            mostrarImagen();
        }
    }

    private void mostrarImagen() {
        int anchoVisor = binding.imageView.getWidth();
        int altoVisor = binding.imageView.getHeight();

        System.out.println(anchoVisor);
        System.out.println(altoVisor);

        BitmapFactory.Options bmop = new BitmapFactory.Options();
        bmop.inJustDecodeBounds  = true;
        BitmapFactory.decodeFile(fichimagen.getAbsolutePath(), bmop);

        int anchoImagen = bmop.outWidth;
        int altoImagen = bmop.outHeight;

        int escala = Math.min(anchoImagen/anchoVisor, altoImagen/altoVisor);
        bmop.inJustDecodeBounds = false;
        bmop.inSampleSize = escala;
        bmop.inDensity = anchoImagen;
        bmop.inTargetDensity = anchoVisor * escala;

        Bitmap imagen = BitmapFactory.decodeFile(fichimagen.getAbsolutePath(), bmop);
        binding.imageView.setImageBitmap(imagen);
        binding.imageView.setVisibility(View.VISIBLE);
    }

}