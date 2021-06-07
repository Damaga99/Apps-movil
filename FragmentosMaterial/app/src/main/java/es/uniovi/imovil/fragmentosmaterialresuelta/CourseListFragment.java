package es.uniovi.imovil.fragmentosmaterialresuelta;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseListFragment extends Fragment  {

    Callbacks mCallback;
    CoursesAdapter adapter = null;

    public interface Callbacks {
        public void onCourseSelected(Course course);
    }

    public static CourseListFragment newInstance() {

        CourseListFragment fragment = new CourseListFragment();
        return fragment;
    }

    public CourseListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        if (activity != null) {
            mCallback = (Callbacks) activity;
        }
        else {
            throw new ClassCastException(activity.toString() +
                    " must implement Callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView;
        rootView = inflater.inflate(R.layout.course_list_fragment, container, false);
        String [] courses = getResources().getStringArray(R.array.courses);
        String [] teachers = getResources().getStringArray(R.array.teachers);
        String [] descriptions = getResources().getStringArray(R.array.descriptions);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
        adapter = new CoursesAdapter(getContext(), mCallback);
        adapter.setCourses(createCourseList(courses, teachers, descriptions));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private List<Course> createCourseList(String[] names, String[] teachers,
                                          String[] descriptions) {

        // ...
        if (names.length != teachers.length || names.length != descriptions.length) {
            throw new IllegalStateException();
        }

        ArrayList<Course> courses = new ArrayList<Course>(names.length);
        for (int i = 0; i < names.length; i++) {
            courses.add(new Course(names[i], teachers[i], descriptions[i]));
        }
        return courses;
    }

    public void addCourse(Course course) {

        // ...
        adapter.addCourse(course);
    }
}
