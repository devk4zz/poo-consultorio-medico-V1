/*
 * Desarrolladores:
 * Aban Miguel Angel
 * Cruz Ortega Alexis
 * López Licona Victor Hugo
 */
package Pck_Consultorio;

import java.io.Serializable;

public class Paciente implements Serializable {

    private int noPaciente;
    private String nombre;
    private String direccion;
    private String telefono;
    private Fecha fechaNacimiento;
    private String correo;

    public Paciente() {
        this.noPaciente = 1;
        this.nombre = "Sin nombre";
        this.direccion = "Sin dirección";
        this.telefono = "0000000000";
        this.fechaNacimiento = new Fecha();
        this.correo = "sin@correo.com";
    }

    public Paciente(int noPaciente, String nombre,
            String direccion, String telefono,
            Fecha fechaNacimiento, String correo) {

        this.noPaciente = noPaciente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
    }

    public Paciente(int noPaciente, String nombre,
            String direccion, String telefono,
            int diaNac, int mesNac, int anioNac,
            String correo) {

        this.noPaciente = noPaciente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = new Fecha(diaNac, mesNac, anioNac);
        this.correo = correo;
    }

    public void setNoPaciente(int noPaciente) {
        this.noPaciente = noPaciente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean setFechaNacimiento(int dia, int mes, int anio) {
        Fecha fecha = new Fecha(dia, mes, anio);
        boolean correcta = fecha.fechaCorrecta();
        if (correcta) {
            this.fechaNacimiento = fecha;
        }
        return correcta;
    }

    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getNoPaciente() {
        return noPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.getFecha();
    }

    public String getCorreo() {
        return correo;
    }

    public String getDatos() {
        return "No. Paciente: " + noPaciente
                + "\nNombre: " + nombre
                + "\nDirección: " + direccion
                + "\nTeléfono: " + telefono
                + "\nFecha de nacimiento: " + fechaNacimiento.getFecha()
                + "\nCorreo: " + correo;
    }
}
