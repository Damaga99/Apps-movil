package es.uniovi.imovil.newrecyclerpractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    public List<Course> courses;

    public RecyclerViewAdapter(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public ViewHolder onCreateViewHolder
            (ViewGroup viewGroup, int type) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder
            (ViewHolder viewHolder, int i) {
        // Obtener el curso Actual
         Course actual= courses.get(i);
        // Colocar el nombre del curso y el profesor en el viewHolder

        viewHolder.courseName.setText(actual.getNombre());
        viewHolder.teacherName.setText(actual.getProfesor());

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    protected static class ViewHolder
            extends RecyclerView.ViewHolder {

        public TextView courseName;
        public TextView teacherName;

        public ViewHolder(View itemView) {
            super(itemView);
            // Inicializar los dos TextView
            // coursename = (TextView) itemView.findViewById(...).
            courseName= itemView.findViewById(R.id.courseText);
            teacherName=itemView.findViewById(R.id.TeacherText);
        }
    }

    public void addCourse(Course course) {
        courses.add(course);
        notifyDataSetChanged();
    }



}