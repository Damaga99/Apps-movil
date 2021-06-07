package es.imovildani.monumentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonumentoListFragment extends Fragment {

    Callbacks mCallback;
    MonumentoAdapter adapter = null;

    public interface Callbacks {
        public void onCourseSelected(Monumento monumento);
    }

    public MonumentoListFragment() {
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
        rootView = inflater.inflate(R.layout.monumento_list_fragment, container, false);
        String [] courses = getResources().getStringArray(R.array.courses);
        String [] teachers = getResources().getStringArray(R.array.teachers);
        String [] descriptions = getResources().getStringArray(R.array.descriptions);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
        adapter = new MonumentoAdapter(getContext(), mCallback);
        adapter.setMonumento(createCourseList(courses, teachers, descriptions));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private List<Monumento> createCourseList(String[] names, String[] teachers,
                                          String[] descriptions) {

        // ...
        if (names.length != teachers.length || names.length != descriptions.length) {
            throw new IllegalStateException();
        }

        List<Monumento> courses = new List<Monumento>(names.length);
        for (int i = 0; i < names.length; i++) {
            courses.add(new Monumento(names[i], teachers[i], descriptions[i]));
        }
        return monumentos;
    }

    public void addCourse(List<Monumento> monumento) {

        // ...
        adapter.setMonumento(monumento);
    }

}
