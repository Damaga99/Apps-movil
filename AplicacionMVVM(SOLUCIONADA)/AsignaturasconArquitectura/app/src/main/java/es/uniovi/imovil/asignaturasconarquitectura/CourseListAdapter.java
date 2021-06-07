package es.uniovi.imovil.asignaturasconarquitectura;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by arias on 16/03/2018.
 */

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder> {

    public class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;// teacher;

        public CourseViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textview_name);
            //teacher = (TextView) itemView.findViewById(R.id.textview_teacher);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listItemOnClickInterface.onItemClick(this.name.getText().toString());

        }
    }

    private final LayoutInflater mlayoutInflater;
    private List<String> mcourses;
    private ListItemOnClickInterface listItemOnClickInterface;

    public CourseListAdapter(Context context) {
        this.mlayoutInflater = LayoutInflater.from(context);
        if (context instanceof ListItemOnClickInterface)
            listItemOnClickInterface = (ListItemOnClickInterface) context;
    }

    public void setNames(List<String> mcourses) {
        this.mcourses = mcourses;
        notifyDataSetChanged();
    }

    @Override
    public CourseListAdapter.CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mlayoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseListAdapter.CourseViewHolder holder, int position) {
        if (mcourses != null) {

            holder.name.setText(mcourses.get(position));
            //holder.teacher.setText(current.getTeacher());
        }
        else
        {
            holder.name.setText("Sin asignatura");
            //holder.teacher.setText("No professor");
        }

    }

    @Override
    public int getItemCount() {
        if (mcourses != null)
            return mcourses.size();
        else
            return 1;
    }


}
