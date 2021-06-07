package es.imovildani.persistencia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import es.imovildani.persistencia.databinding.FragmentListBinding;

public class ListFragment extends Fragment {

    FragmentListBinding binding;
    Adapter adapter;
    BookViewModel bookViewModel;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false);
        // Inicialización de los elementos del layout
        // 2. Para inicializar los elementos del layout (solo tenemos el RecyclerView) lo haremos a través
        //    de la clase vinculada. Necesitamos asignar un LinearLayoutManager al RecyclerView con el
        //    siguiente código:

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        // 3. Tenemos que asignarle una instancia del adaptador al RecyclerView para lo que declararemos un
        //    miembro del fragmento que sea de tipo Adapter. Instancialo y asignalo al adaptador.
        adapter = new Adapter();
        binding.recyclerview.setAdapter(adapter);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      /*  view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ListFragment.this)
                        .navigate(R.id.action_List_to_Entry);
            }
        });*/
    }
    public void onStart() {
        super.onStart();
        bookViewModel = new ViewModelProvider(getActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(BookViewModel.class);
        adapter.setBookViewModel(bookViewModel);
    }

}