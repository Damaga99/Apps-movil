package es.imovildani.networkapp;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.imovildani.networkapp.databinding.SimpleListItemBinding;

public class BusStopHolder extends RecyclerView.ViewHolder {

    SimpleListItemBinding binding;

    public BusStopHolder(@NonNull SimpleListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Llegada llegada){
      // .............
        if (llegada != null)
            binding.text1.setText("Parada: " + llegada.getIdparada() +" -  Línea: " + llegada.getIdlinea() + " -  minutos: " + llegada.getMinutos());
        else
            binding.text1.setText("Aun no existen datos disponibles");

    }
}