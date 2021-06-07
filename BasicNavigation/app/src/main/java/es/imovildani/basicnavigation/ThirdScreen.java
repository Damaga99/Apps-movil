package es.imovildani.basicnavigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdScreen newInstance(String param1, String param2) {
        ThirdScreen fragment = new ThirdScreen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_third_screen, container, false);
        String cadena;
        if(!ThirdScreenArgs.fromBundle(getArguments()).getOrigen().isEmpty()){
             cadena="Invocado desde " + ThirdScreenArgs.fromBundle(getArguments()).getOrigen();
        }
        else{
            cadena="Invocado desde " + ThirdScreenArgs.fromBundle(getArguments()).getOrigen();
        }
       // String cadena="Invocado desde " + ThirdScreenArgs.fromBundle(getArguments()).getOrigen();
        Toast.makeText(v.getContext(),cadena,Toast.LENGTH_LONG).show();
        return v;

    }
}