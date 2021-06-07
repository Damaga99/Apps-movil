
package es.imovildani.networkapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Llegadas {

    @SerializedName("llegada")
    @Expose
    private List<Llegada> llegada = null;

    public List<Llegada> getLlegada() {
        return llegada;
    }

    public void setLlegada(List<Llegada> llegada) {
        this.llegada = llegada;
    }

}
