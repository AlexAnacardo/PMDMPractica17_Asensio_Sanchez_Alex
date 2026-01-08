package es.ies.claudiomoyano.dam2.pmdm.practica17_asensio_sanchez_alex;

public class Alumno {

    private String nombre, apellidos, dni;
    private int idFoto;

    public Alumno(String nombre, String apellidos, String dni, int idFoto) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.idFoto = idFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }
}
