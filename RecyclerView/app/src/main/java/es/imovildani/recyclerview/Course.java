package es.imovildani.recyclerview;

public class Course {
    String nombre;
    String profesor;

    public Course(){
        this.nombre = nombre;
        this.profesor = profesor;
    }


    public Course(String nombre, String profesor) {
        this.nombre = nombre;
        this.profesor = profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
