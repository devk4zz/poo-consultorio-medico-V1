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
        this.idEmpleado = "SinId";
        this.nombre = "Sin nombre";
        this.direccion = "Sin dirección";
        this.telefono = "0000000000";
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

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public boolean setFechaContratacion(int dia, int mes, int anio) {
        Fecha fecha = new Fecha(dia, mes, anio);
        boolean correcta = fecha.fechaCorrecta();
        if (correcta) {
            this.fechaContratacion = fecha;
        }
        return correcta;
    }

    public void setFechaContratacion(Fecha fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getIdEmpleado() {
        return idEmpleado;
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

    public String getFechaContratacion() {
        return fechaContratacion.getFecha();
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
