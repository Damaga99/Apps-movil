package es.uniovi.imovil.fragmentosmaterialresuelta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseDetailsFragment extends Fragment {

    private static final String DESCRIPTION_ARG = "description";

    public static CourseDetailsFragment newInstance(String desc) {

        CourseDetailsFragment fragment = new CourseDetailsFragment();

        Bundle args = new Bundle();
        args.putString(DESCRIPTION_ARG, desc);
        fragment.setArguments(args);

        return fragment;
    }

    public CourseDetailsFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewgroup = inflater.inflate(R.layout.course_details_fragment, container,false);
        String desc = "Vacia";
        Bundle args = getArguments();
        if (args != null) {
            desc = args.getString(DESCRIPTION_ARG);
        }
        TextView textView = (TextView) viewgroup.findViewById(R.id.description);
        textView.setText(desc);

        return viewgroup;
    }

    public void setDescription(String description) {
        TextView textView = (TextView) this.getView().findViewById(R.id.description);
        textView.setText(description);
    }

}
