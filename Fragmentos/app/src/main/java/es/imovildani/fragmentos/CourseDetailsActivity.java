package es.imovildani.fragmentos;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import es.uniovi.imovil.fragmentosmaterialresuelta.R;

public class CourseDetailsActivity extends AppCompatActivity {
    public static final String DESCRIPTION = "es.imovildani.user.courses.DESCRIPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details_activity);
        Intent intent = getIntent();
        String description = intent.getStringExtra(DESCRIPTION);
        if (findViewById(R.id.fragment_container) != null) {

            // Si estamos restaurando desde un estado previo no hacemos nada
            if (savedInstanceState != null) {
                return;
            }

            // Crear el fragmento pasándole el parámetro
            es.imovildani.fragmentos.CourseDetailsFragment fragment =
                    es.imovildani.fragmentos.CourseDetailsFragment.newInstance(description);

            // Añadir el fragmento al contenedor 'fragment_container'
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment).commit();
        }

    }
}
