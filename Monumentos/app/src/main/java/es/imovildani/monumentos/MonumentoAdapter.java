package es.imovildani.monumentos;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.imovildani.monumentos.databinding.SimpleListItemBinding;

public class MonumentoAdapter extends RecyclerView.Adapter <MonumentoHolder> {


    SimpleListItemBinding binding;
    private List<Monumento> mMonumento;

    private List<String> ListMonumento = new ArrayList<String>();


    private final LayoutInflater mlayoutInflater;

    private ListItemOnClickInterface listItemOnClickInterface;

    MonumentoListFragment.Callbacks callbacks;

    public MonumentoAdapter(Context context, MonumentoListFragment.Callbacks callbacks) {

        this.mlayoutInflater = LayoutInflater.from(context);
        this.callbacks = callbacks;
    }


    @NonNull
    @Override
    public MonumentoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent, false);

        binding = SimpleListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MonumentoHolder(listItemOnClickInterface, binding);

    }



    @Override
    public void onBindViewHolder(@NonNull MonumentoHolder holder, int position) {
        //holder.bind(.........);
        if(this.mMonumento != null)
        {

            holder.bind(this.mMonumento.get(position));


        }
        else
        {
            holder.bind(null);
        }
    }


    @Override
    public int getItemCount() {
        if (this.mMonumento == null)
            return 1;
        else
            return this.mMonumento.size() ;
    }



    public void setMonumento(List<Monumento> String)
    {
        this.mMonumento = String;
        this.notifyDataSetChanged();
    }


    public MonumentoAdapter(Context context) {
        this.mlayoutInflater = LayoutInflater.from(context);
        if (context instanceof ListItemOnClickInterface)
            listItemOnClickInterface = (ListItemOnClickInterface) context;


    }


    public void setNames(List<String> mMonumento) {
        this.ListMonumento = mMonumento;
        notifyDataSetChanged();
    }

    public void filtrar(ArrayList<Monumento> filtroUsuarios) {
        this.mMonumento = filtroUsuarios;
        notifyDataSetChanged();
    }



}