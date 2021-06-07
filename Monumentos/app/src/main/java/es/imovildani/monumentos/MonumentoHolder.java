package es.imovildani.monumentos;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.imovildani.monumentos.databinding.SimpleListItemBinding;

public class MonumentoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    SimpleListItemBinding binding;

    private ListItemOnClickInterface listItemOnClickInterface;

   public MonumentoHolder(ListItemOnClickInterface interfaz, @NonNull SimpleListItemBinding binding) {
       super(binding.getRoot());
        this.binding = binding;
        this.listItemOnClickInterface = interfaz;
        binding.getRoot().setOnClickListener(this);
    }


    public void bind(Monumento String){

        System.out.println(String);
        if (String != null){
            binding.text1.setText(String.getTitulo());
            binding.text2.setText(String.getLineasBus());
            binding.text3.setText(String.getMunicipio());
        }
        else{
            binding.text1.setText("Aun no existen datos disponibles");}

    }

    @Override
    public void onClick(View v) {
        listItemOnClickInterface.onItemClick(binding.text1.getText().toString());

    }
}