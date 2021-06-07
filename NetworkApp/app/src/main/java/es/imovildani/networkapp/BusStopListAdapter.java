package es.imovildani.networkapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.imovildani.networkapp.databinding.SimpleListItemBinding;

public class BusStopListAdapter extends RecyclerView.Adapter <BusStopHolder> {

    SimpleListItemBinding binding;
    // 9.1 Necesitamos un miembro privado en al adaptador que almacene la lista de paradas a mostrar
    //      por el RecyclerView. Declaralo con el siguiente código:
    private Llegadas mBusStops;


    // 9.3 El método onCreateViewHolder() tiene que inflar el layout de cada fila y luego invocar el constructor
    //      del ViewHolder pasando la clase vinculada ya inicializada. No es necesario añadir más código a la
    //      implementación suministrada
    @NonNull
    @Override
    public BusStopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SimpleListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BusStopHolder(binding);
    }


    // 9.4 En el método onBindViewHolder() hay que asignar a los elementos del ViewHolder los datos que tiene que
    //      presentar en pantalla. Hay que tener en cuenta que este método puede ser invocado cuando mBusStops aún
    //      es null, así que hay que tener prevista esa contingencia. En el caso de que no sea null, hay que llamar
    //      al método bind() del ViewHolder pasando la llegada correspondiente a la posición position. En el caso de
    //      que sea null, pasaremos un valor null al método bind() y dentro de él tendremos en cuenta esta circunstancia.
    @Override
    public void onBindViewHolder(@NonNull BusStopHolder holder, int position) {
        //holder.bind(.........);
        if(this.mBusStops != null)
        {
            holder.bind(this.mBusStops.getLlegada().get(position));
        }
        else
        {
            holder.bind(null);
        }
    }

    // 9.2 El método getItemCount() tiene que retornar el tamaño de la lista de paradas que contiene mBusStops.
    //      Hay que tener en cuenta que el adaptador puede ser llamado antes de que mBusStops esté inicializado
    //      por lo que sólo se debe devolver ese tamaño cuando mBusStops sea distinto de null. Si es null,
    //      devolver 1.
    @Override
    public int getItemCount() {
        if (this.mBusStops == null)
            return 1;
        else
            return this.mBusStops.getLlegada().size();
    }


    // 9.6 Queda por implementar el setter de mBusStops en el adaptador. Implementa un método público denominado
    //      setBusStops() que reciba como parámetro un objeto de tipo Llegadas y lo asigne a mBusStops. Hay que
    //      recordar que, como este método modifica el conjunto de datos que maneja el adaptador, tenemos que
    //      incluir una llamada al método del adaptador con el que se le notifica esta modificación.
    public void setBusStops(Llegadas llegadas)
    {
        this.mBusStops = llegadas;
        this.notifyDataSetChanged();
    }

}
