package es.uniovi.imovil.fragmentosmaterialresuelta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements CourseListFragment.Callbacks {

    private int mCourseCount = 0;
    CoursesAdapter adapter = null;
    boolean mTwoPanes;
    CourseDetailsFragment courseDetailsFragment = null;
    FragmentManager fragmentManager= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (findViewById(R.id.course_details_container) != null) {
            mTwoPanes = true;
            fragmentManager = getSupportFragmentManager();
            courseDetailsFragment = CourseDetailsFragment.newInstance("Pulsa una asignatura para obtener más información");
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.course_details_container, courseDetailsFragment).commit();

        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                fragmentManager = getSupportFragmentManager();
                CourseListFragment fragment = (CourseListFragment)
                        fragmentManager.findFragmentById(R.id.course_list_frag);

                String name = String.format(getString(R.string.default_course_format), ++mCourseCount);
                String teacher = String.format(getString(R.string.default_teacher_format),	mCourseCount);
                String description = "Descripción del curso";

                Course course = new Course(name, teacher, description);
                fragment.addCourse(course);
                //addCourse();
            }
        });
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
    public void onCourseSelected(Course course) {
        if (!mTwoPanes) {
            Intent intent = new Intent(this, CourseDetailsActivity.class);
            intent.putExtra(CourseDetailsActivity.DESCRIPTION, course.getDescription());
            startActivity(intent);
        }
        else {
            /*
            CourseDetailsFragment courseDetailsFragment = CourseDetailsFragment.newInstance(course.getDescription());
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.course_details_container, courseDetailsFragment).commit();
            */
            courseDetailsFragment.setDescription(course.getDescription());
        }
    }
}
