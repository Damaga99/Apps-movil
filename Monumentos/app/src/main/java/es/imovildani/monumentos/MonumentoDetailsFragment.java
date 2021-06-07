package es.imovildani.monumentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonumentoDetailsFragment extends Fragment {

    private static final String DESCRIPTION_ARG = "description";

    public static MonumentoDetailsFragment newInstance(String desc) {

        MonumentoDetailsFragment fragment = new MonumentoDetailsFragment();

        Bundle args = new Bundle();
        args.putString(DESCRIPTION_ARG, desc);
        fragment.setArguments(args);

        return fragment;
    }

    public MonumentoDetailsFragment() {
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
