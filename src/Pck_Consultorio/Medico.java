/*
 * Desarrolladores:
 * Aban Miguel Angel
 * Cruz Ortega Alexis
 * López Licona Victor Hugo
 */

package Pck_Consultorio;

import java.io.Serializable;

public class Medico implements Serializable {

    protected String idEmpleado;
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected Fecha fechaNacimiento;
    protected Fecha fechaContratacion;

    public Medico() {
        this.idEmpleado = "";
        this.nombre = "";
        this.direccion = "";
        this.telefono = "";
        this.fechaNacimiento = new Fecha();
        this.fechaContratacion = new Fecha();
    }

    public Medico(String idEmpleado, String nombre,
            String direccion, String telefono,
            Fecha fechaNacimiento, Fecha fechaContratacion) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContratacion = fechaContratacion;
    }

    public Medico(String idEmpleado, String nombre,
            String direccion, String telefono,
            int diaNac, int mesNac, int anioNac,
            int diaCon, int mesCon, int anioCon) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = new Fecha(diaNac, mesNac, anioNac);
        this.fechaContratacion = new Fecha(diaCon, mesCon, anioCon);
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public boolean setFechaContratacion(int dia, int mes, int anio) {
        Fecha f = new Fecha(dia, mes, anio);
        if (f.fechaCorrecta()) {
            this.fechaContratacion = f;
            return true;
        }
        return false;
    }

    public void setFechaContratacion(Fecha fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.getFecha();
    }

    public String getFechaContratacion() {
        return fechaContratacion.getFecha();
    }

    public Fecha getFechaNacimientoObj() {
        return fechaNacimiento;
    }

    public Fecha getFechaContratacionObj() {
        return fechaContratacion;
    }

    public String getDatos() {
        return "Id Empleado: " + idEmpleado
                + "\nNombre: " + nombre
                + "\nDirección: " + direccion
                + "\nTeléfono: " + telefono
                + "\nFecha de nacimiento: " + fechaNacimiento.getFecha()
                + "\nFecha de contratación: " + fechaContratacion.getFecha();
    }
}
