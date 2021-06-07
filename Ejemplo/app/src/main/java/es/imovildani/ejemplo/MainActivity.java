package es.imovildani.ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos una referencia al viewgroup SwipeLayout
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        //Indicamos que listener recogerá la retrollamada (callback), en este caso, será el metodo OnRefresh de esta clase.

        swipeLayout.setOnRefreshListener(this);
        //Podemos espeficar si queremos, un patron de colores diferente al patrón por defecto.
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //Obtenemos una referencia a nuestra lista.
        final ListView miLista = (ListView) findViewById(R.id.mi_lista);

        //Creamos una lista de elementos para mostrar
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 50; ++i) {
            list.add("Elemento "+i);
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
        miLista.setAdapter(adapter);
 /*Aquí esta el truco para que el refresco de una lista funcione correctamente.
 El problema del layout SwipeToRefresh es que deteca cuando hacemos scroll hacia arriba, e interpreta que debe invocar
 al callback para ejecutar el refresco. Por lo tanto, con la implementacion basica lo que obtenemos es una lista en la que no
 se puede hacer scroll hacia arriba ya que cada vez que lo intentamos, se ejecuta el callback. Por lo tanto lo que tenemos que hacer
 es comprobar que estamos en la parte superior de la lista, y solo en ese caso invocar el callback.
 */

        miLista.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int filaSuperior = (
                        miLista == null//Si la lista esta vacía ó
                                || miLista.getChildCount() == 0) ? 0 : miLista.getChildAt(0).getTop();//Estamos en el elemento superior
                swipeLayout.setEnabled(filaSuperior >= 0); //Activamos o desactivamos el swipe layout segun corresponda
            }

        });

    }

    @Override
    public void onRefresh() {
        //Aqui ejecutamos el codigo necesario para refrescar nuestra interfaz grafica.
        //Antes de ejecutarlo, indicamos al swipe layout que muestre la barra indeterminada de progreso.
        swipeLayout.setRefreshing(true);

        //Vamos a simular un refresco con un handle.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //Se supone que aqui hemos realizado las tareas necesarias de refresco, y que ya podemos ocultar la barra de progreso
                swipeLayout.setRefreshing(false);

            }
        }, 3000);

    }
}