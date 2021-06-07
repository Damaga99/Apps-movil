
package es.imovildani.networkapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Llegada {

    @SerializedName("idlinea")
    @Expose
    private Integer idlinea;
    @SerializedName("idtrayecto")
    @Expose
    private Integer idtrayecto;
    @SerializedName("idautobus")
    @Expose
    private String idautobus;
    @SerializedName("horaactualizacion")
    @Expose
    private String horaactualizacion;
    @SerializedName("fechaactualizacion")
    @Expose
    private String fechaactualizacion;
    @SerializedName("idparada")
    @Expose
    private Integer idparada;
    @SerializedName("minutos")
    @Expose
    private Integer minutos;
    @SerializedName("distancia")
    @Expose
    private Double distancia;
    @SerializedName("matricula")
    @Expose
    private String matricula;
    @SerializedName("modelo")
    @Expose
    private String modelo;

    public Integer getIdlinea() {
        return idlinea;
    }

    public void setIdlinea(Integer idlinea) {
        this.idlinea = idlinea;
    }

    public Integer getIdtrayecto() {
        return idtrayecto;
    }

    public void setIdtrayecto(Integer idtrayecto) {
        this.idtrayecto = idtrayecto;
    }

    public String getIdautobus() {
        return idautobus;
    }

    public void setIdautobus(String idautobus) {
        this.idautobus = idautobus;
    }

    public String getHoraactualizacion() {
        return horaactualizacion;
    }

    public void setHoraactualizacion(String horaactualizacion) {
        this.horaactualizacion = horaactualizacion;
    }

    public String getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(String fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

    public Integer getIdparada() {
        return idparada;
    }

    public void setIdparada(Integer idparada) {
        this.idparada = idparada;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}
