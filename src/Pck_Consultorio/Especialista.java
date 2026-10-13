/*
 * Desarrolladores:
 * Aban Miguel Angel
 * Cruz Ortega Alexis
 * López Licona Victor Hugo
 */

package Pck_Consultorio;

import java.io.Serializable;

public class Especialista extends Medico implements Serializable {

    private String especialidad;
    private String area;
    private String enfermera_o;

    public Especialista() {
        super();
        this.especialidad = "";
        this.area = "";
        this.enfermera_o = "";
    }

    public Especialista(String idEmpleado, String nombre,
            String direccion, String telefono,
            Fecha fechaNacimiento, Fecha fechaContratacion,
            String especialidad, String area, String enfermera_o) {

        super(idEmpleado, nombre, direccion, telefono,
                fechaNacimiento, fechaContratacion);

        this.especialidad = especialidad;
        this.area = area;
        this.enfermera_o = enfermera_o;
    }

    public Especialista(String idEmpleado, String nombre,
            String direccion, String telefono,
            int diaNac, int mesNac, int anioNac,
            int diaCon, int mesCon, int anioCon,
            String especialidad, String area, String enfermera_o) {

        super(idEmpleado, nombre, direccion, telefono,
                diaNac, mesNac, anioNac, diaCon, mesCon, anioCon);

        this.especialidad = especialidad;
        this.area = area;
        this.enfermera_o = enfermera_o;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEnfermera() {
        return enfermera_o;
    }

    public void setEnfermera(String enfermera_o) {
        this.enfermera_o = enfermera_o;
    }

    public String getEnfermera_o() {
        return enfermera_o;
    }

    public void setEnfermera_o(String enfermera_o) {
        this.enfermera_o = enfermera_o;
    }

    @Override
    public String getDatos() {
        return super.getDatos()
                + "\nEspecialidad: " + especialidad
                + "\nÁrea: " + area
                + "\nEnfermera(o): " + enfermera_o;
    }
}
