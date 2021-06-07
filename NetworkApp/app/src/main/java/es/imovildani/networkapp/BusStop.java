package es.imovildani.networkapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusStop {

    @SerializedName("posiciones")
    @Expose
    private Posiciones posiciones;
    @SerializedName("llegadas")
    @Expose
    private Llegadas llegadas;

    public Posiciones getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(Posiciones posiciones) {
        this.posiciones = posiciones;
    }

    public Llegadas getLlegadas() {
        return llegadas;
    }

    public void setLlegadas(Llegadas llegadas) {
        this.llegadas = llegadas;
    }
}
