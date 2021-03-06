package es.uniovi.imovil.asignaturasconarquitectura;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListItemOnClickInterface{

    public static final int NEW_COURSE_REQUEST_CODE = 1;

    private CourseNamesViewModel courseNamesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CourseListAdapter courseListAdapter = new CourseListAdapter(this);
        recyclerView.setAdapter(courseListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseNamesViewModel = ViewModelProviders.of(this).get(CourseNamesViewModel.class);
        courseNamesViewModel.getCoursesNames().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> courses) {
                courseListAdapter.setNames(courses);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewCourseActivity.class);
                startActivityForResult(intent, NEW_COURSE_REQUEST_CODE);
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_COURSE_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Course newCourse = new Course(bundle.getString(NewCourseActivity.NAME_REPLY),
                    bundle.getString(NewCourseActivity.TEACHER_REPLY),
                    bundle.getString(NewCourseActivity.DESC_REPLY));
            courseNamesViewModel.insert(newCourse);

        }
        else {
            Toast.makeText(getApplicationContext(),"No se ha salvado los datos de la asignatura",Toast.LENGTH_LONG).show();
        }
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

    @Override
    public void onItemClick(String name) {
      /*  Snackbar.make(getCurrentFocus(), "cadena:  "+ name,Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
                */

        Intent intent = new Intent(this,DetailsCourseActivity.class);
        intent.putExtra(DetailsCourseActivity.NAME_DETAIL,name);
        startActivity(intent);
        /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();*/

    }
}
