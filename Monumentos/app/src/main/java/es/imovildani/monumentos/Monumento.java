
package es.imovildani.monumentos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Monumento {

    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("correo_electronico")
    @Expose
    private String correoElectronico;
    @SerializedName("codigo_postal")
    @Expose
    private String codigoPostal;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("distrito")
    @Expose
    private String distrito;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("googleplus")
    @Expose
    private String googleplus;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("linkedin")
    @Expose
    private String linkedin;
    @SerializedName("pinterest")
    @Expose
    private String pinterest;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("youtube")
    @Expose
    private String youtube;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("horario")
    @Expose
    private String horario;
    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("kml")
    @Expose
    private String kml;
    @SerializedName("localizacion")
    @Expose
    private String localizacion;
    @SerializedName("lineas_bus")
    @Expose
    private String lineasBus;
    @SerializedName("materias")
    @Expose
    private String materias;
    @SerializedName("municipio")
    @Expose
    private String municipio;
    @SerializedName("observaciones")
    @Expose
    private String observaciones;
    @SerializedName("pluscode")
    @Expose
    private String pluscode;
    @SerializedName("provincia")
    @Expose
    private String provincia;
    @SerializedName("imagenes_slider")
    @Expose
    private String imagenesSlider;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("idioma")
    @Expose
    private String idioma;
    @SerializedName("preguntas_frecuentes")
    @Expose
    private String preguntasFrecuentes;
    @SerializedName("etiquetas")
    @Expose
    private String etiquetas;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGoogleplus() {
        return googleplus;
    }

    public void setGoogleplus(String googleplus) {
        this.googleplus = googleplus;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getPinterest() {
        return pinterest;
    }

    public void setPinterest(String pinterest) {
        this.pinterest = pinterest;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getKml() {
        return kml;
    }

    public void setKml(String kml) {
        this.kml = kml;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getLineasBus() {
        return lineasBus;
    }

    public void setLineasBus(String lineasBus) {
        this.lineasBus = lineasBus;
    }

    public String getMaterias() {
        return materias;
    }

    public void setMaterias(String materias) {
        this.materias = materias;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPluscode() {
        return pluscode;
    }

    public void setPluscode(String pluscode) {
        this.pluscode = pluscode;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getImagenesSlider() {
        return imagenesSlider;
    }

    public void setImagenesSlider(String imagenesSlider) {
        this.imagenesSlider = imagenesSlider;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPreguntasFrecuentes() {
        return preguntasFrecuentes;
    }

    public void setPreguntasFrecuentes(String preguntasFrecuentes) {
        this.preguntasFrecuentes = preguntasFrecuentes;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

}
