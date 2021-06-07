package es.imovildani.recyclerview;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Utiliza este método para inicializar en MainActivity un objeto de la clase RecyclerViewAdapter
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(createCourseList());

        // Inicializamos el RecyclerView con el siguiente código
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
       fab.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
       //                 .setAction("Action", null).show();
               Course curso = new Course();
               ((RecyclerViewAdapter)recyclerView.getAdapter()).addCourse(curso);
            }
        });

       // RecyclerViewAdapter adapter = new RecyclerViewAdapter(createCourseList());

        //final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private List<Course> createCourseList() {

        List<Course> lista = new ArrayList<>(0);

        String [] courses = getResources().getStringArray(R.array.asignaturas);
        String [] teachers = getResources().getStringArray(R.array.profesores);
        for (int i = 0; i< courses.length; i++) {
            // crea una clase Course y la añade a la lista
            Course course = new Course(courses[i],teachers[i]);
            lista.add(course);
        }
        return lista;
    }

}