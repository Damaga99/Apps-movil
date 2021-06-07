package es.imovildani.fragmentos;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.uniovi.imovil.fragmentosmaterialresuelta.R;

/**
 * Created by arias on 19/03/2018.
 */

public class CoursesAdapter extends RecyclerView.Adapter<es.imovildani.fragmentos.CoursesAdapter.MyViewHolder> {

    private List<Course> mcourses;
    private LayoutInflater layoutInflater;
    es.imovildani.fragmentos.CourseListFragment.Callbacks callbacks;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mCourseName;
        private TextView mTeacherName;

        public MyViewHolder(View itemView) {
            super(itemView);
            mCourseName = itemView.findViewById(R.id.nameTextView);
            mTeacherName = itemView.findViewById(R.id.teacherTextView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            callbacks.onCourseSelected(mcourses.get(getLayoutPosition()));
        }
    }

    public CoursesAdapter(Context context, es.imovildani.fragmentos.CourseListFragment.Callbacks callbacks) {

        this.layoutInflater = LayoutInflater.from(context);
        this.callbacks = callbacks;
    }

    public void setCourses(List<Course> mcourses) {
        this.mcourses = mcourses;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_course, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Course current = mcourses.get(position);
        holder.mCourseName.setText(current.getName());
        holder.mTeacherName.setText(current.getTeacher());

    }

    @Override
    public int getItemCount() {
        return mcourses.size();
    }

    public void addCourse(Course course) {

        if (course == null) {
            throw new IllegalArgumentException();
        }

        mcourses.add(course);

        // Importante: notificar que ha cambiado el dataset
        notifyDataSetChanged();
    }
}
