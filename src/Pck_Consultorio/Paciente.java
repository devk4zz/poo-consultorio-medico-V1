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
        this.noPaciente = 0;
        this.nombre = "";
        this.direccion = "";
        this.telefono = "";
        this.fechaNacimiento = new Fecha();
        this.correo = "";
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

    public int getNoPaciente() {
        return noPaciente;
    }

    public void setNoPaciente(int noPaciente) {
        this.noPaciente = noPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean setFechaNacimiento(int dia, int mes, int anio) {
        Fecha f = new Fecha(dia, mes, anio);
        if (f.fechaCorrecta()) {
            this.fechaNacimiento = f;
            return true;
        }
        return false;
    }

    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.getFecha();
    }

    public Fecha getFechaNacimientoObj() {
        return fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDatos() {
        return "Número de paciente: " + noPaciente
                + "\nNombre: " + nombre
                + "\nDirección: " + direccion
                + "\nTeléfono: " + telefono
                + "\nFecha de nacimiento: " + fechaNacimiento.getFecha()
                + "\nCorreo: " + correo;
    }
}
