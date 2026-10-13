/*
 * Desarrolladores:
 * Aban Miguel Angel
 * Cruz Ortega Alexis
 * López Licona Victor Hugo
 */
package Pck_Consultorio;

public class General extends Medico {

    private String turno;
    private int consultorio;

    public General() {
        super();
        this.turno = "Matutino";
        this.consultorio = 1;
    }

    public General(String idEmpleado, String nombre,
            String direccion, String telefono,
            Fecha fechaNacimiento, Fecha fechaContratacion,
            String turno, int consultorio) {

        super(idEmpleado, nombre, direccion, telefono,
                fechaNacimiento, fechaContratacion);

        this.turno = turno;
        this.consultorio = consultorio;
    }

    public General(String idEmpleado, String nombre,
            String direccion, String telefono,
            int diaNac, int mesNac, int anioNac,
            int diaCon, int mesCon, int anioCon,
            String turno, int consultorio) {

        super(idEmpleado, nombre, direccion, telefono,
                diaNac, mesNac, anioNac, diaCon, mesCon, anioCon);

        this.turno = turno;
        this.consultorio = consultorio;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setConsultorio(int consultorio) {
        this.consultorio = consultorio;
    }

    public String getTurno() {
        return turno;
    }

    public int getConsultorio() {
        return consultorio;
    }

    @Override
    public String getDatos() {
        return super.getDatos()
                + "\nTurno: " + turno
                + "\nConsultorio: " + consultorio;
    }
}
