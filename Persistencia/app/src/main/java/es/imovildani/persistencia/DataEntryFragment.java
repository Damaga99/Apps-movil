package es.imovildani.persistencia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import es.imovildani.persistencia.databinding.ItemEditBinding;

public class DataEntryFragment extends Fragment {
    ItemEditBinding binding;
    Adapter adapter;
    BookViewModel bookViewModel;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_data_entry, container, false);

        binding = ItemEditBinding.inflate(inflater, container, false);
        // Inicialización de los elementos del layout
        // .....................
        // 2. Para inicializar los elementos del layout (solo tenemos el RecyclerView) lo haremos a través
        //    de la clase vinculada. Necesitamos asignar un LinearLayoutManager al RecyclerView con el
        //    siguiente código:
        //binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        // 3. Tenemos que asignarle una instancia del adaptador al RecyclerView para lo que declararemos un
        //    miembro del fragmento que sea de tipo Adapter. Instancialo y asignalo al adaptador.
        adapter = new Adapter();
        //binding.recyclerview.setAdapter(adapter);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // NavHostFragment.findNavController(DataEntryFragment.this)
               //         .navigate(R.id.action_Entry_to_List);
                bookViewModel.addBook(getData());
                Navigation.findNavController(view).popBackStack();


            }
        });
    }

    public void onStart() {
        super.onStart();
        bookViewModel = new ViewModelProvider(getActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(BookViewModel.class);
        adapter.setBookViewModel(bookViewModel);
    }

    private Book getData() {
        String title, author, isbn, editorial;
        Float prix;
        //......................
        title = binding.titleEdit.getText().toString();
        author = binding.authorEdit.getText().toString();
        isbn = binding.isbn.getText().toString();
        editorial = binding.editorEdit.getText().toString();
        prix = Float.parseFloat(binding.price.getText().toString());
        // Completar el método
        //..................
        return new Book(title, author, isbn, editorial, prix);

    }

}