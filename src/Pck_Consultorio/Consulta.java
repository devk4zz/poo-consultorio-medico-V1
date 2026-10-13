/*
 * Desarrolladores:
 * Aban Miguel Angel
 * Cruz Ortega Alexis
 * López Licona Victor Hugo
 */
package Pck_Consultorio;

import java.io.Serializable;

public class Consulta implements Serializable {

    private int noConsulta;
    private String idEmpleado;
    private int noPaciente;
    private Fecha fechaConsulta;
    private String diagnostico;

    public Consulta() {
        this.noConsulta = 1;
        this.idEmpleado = "SinId";
        this.noPaciente = 1;
        this.fechaConsulta = new Fecha();
        this.diagnostico = "Sin diagnóstico";
    }

    public Consulta(int noConsulta, String idEmpleado,
            int noPaciente, Fecha fechaConsulta,
            String diagnostico) {

        this.noConsulta = noConsulta;
        this.idEmpleado = idEmpleado;
        this.noPaciente = noPaciente;
        this.fechaConsulta = fechaConsulta;
        this.diagnostico = diagnostico;
    }

    public Consulta(int noConsulta, String idEmpleado,
            int noPaciente, int diaCon, int mesCon, int anioCon,
            String diagnostico) {

        this.noConsulta = noConsulta;
        this.idEmpleado = idEmpleado;
        this.noPaciente = noPaciente;
        this.fechaConsulta = new Fecha(diaCon, mesCon, anioCon);
        this.diagnostico = diagnostico;
    }

    public void setNoConsulta(int noConsulta) {
        this.noConsulta = noConsulta;
    }

    // Método indicado en el diagrama UML (modifica el Id del médico)
    public void setNombre(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setNoPaciente(int noPaciente) {
        this.noPaciente = noPaciente;
    }

    public boolean setFechaConsulta(int dia, int mes, int anio) {
        Fecha fecha = new Fecha(dia, mes, anio);
        boolean correcta = fecha.fechaCorrecta();
        if (correcta) {
            this.fechaConsulta = fecha;
        }
        return correcta;
    }

    public void setFecha(Fecha fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getNoConsulta() {
        return noConsulta;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public int getNoPaciente() {
        return noPaciente;
    }

    public String getFechaConsulta() {
        return fechaConsulta.getFecha();
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getDatos() {
        return "No. Consulta: " + noConsulta
                + "\nId Empleado: " + idEmpleado
                + "\nNo. Paciente: " + noPaciente
                + "\nFecha de consulta: " + fechaConsulta.getFecha()
                + "\nDiagnóstico: " + diagnostico;
    }
}
