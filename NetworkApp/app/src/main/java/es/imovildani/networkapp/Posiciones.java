
package es.imovildani.networkapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Posiciones {

    @SerializedName("posicion")
    @Expose
    private List<Posicion> posicion = null;

    public List<Posicion> getPosicion() {
        return posicion;
    }

    public void setPosicion(List<Posicion> posicion) {
        this.posicion = posicion;
    }

}
