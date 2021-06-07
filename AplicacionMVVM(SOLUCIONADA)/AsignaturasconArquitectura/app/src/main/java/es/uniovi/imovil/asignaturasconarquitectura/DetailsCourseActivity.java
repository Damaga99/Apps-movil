package es.uniovi.imovil.asignaturasconarquitectura;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class DetailsCourseActivity extends AppCompatActivity {

    public static final String NAME_DETAIL = "es.uniovi.imovil.asignaturasconarquitectura.name_detail";
    CoursesListViewModel coursesListViewModel;
    TextView name, teacher, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_course);
        name = findViewById(R.id.details_name);
        teacher = findViewById(R.id.details_teacher);
        description = findViewById(R.id.details_description);
        Intent intent = getIntent();

        String nameCourse= intent.getStringExtra(NAME_DETAIL);

        coursesListViewModel = ViewModelProviders.of(this).get(CoursesListViewModel.class);
        coursesListViewModel.getCourse().observe(this, new Observer<Course>() {

            @Override
            public void onChanged(@Nullable Course course) {
                name.setText(course.getName());
                teacher.setText(course.getTeacher());
                description.setText(course.getDescription());
            }
        });
        coursesListViewModel.setName(nameCourse);

    }
}
