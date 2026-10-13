/*
 * Desarrolladores:
 * Aban Miguel Angel
 * Cruz Ortega Alexis
 * López Licona Victor Hugo
 */
package Pck_Consultorio;

public class Consulta {

    private int noConsulta;
    private String idEmpleado;
    private int noPaciente;
    private Fecha fechaConsulta;
    private String diagnostico;

    public Consulta() {
        this.noConsulta = 0;
        this.idEmpleado = "";
        this.noPaciente = 0;
        this.fechaConsulta = new Fecha();
        this.diagnostico = "";
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

    public int getNoConsulta() {
        return noConsulta;
    }

    public void setNoConsulta(int noConsulta) {
        this.noConsulta = noConsulta;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setNombre(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getNoPaciente() {
        return noPaciente;
    }

    public void setNoPaciente(int noPaciente) {
        this.noPaciente = noPaciente;
    }

    public boolean setFechaConsulta(int dia, int mes, int anio) {
        Fecha f = new Fecha(dia, mes, anio);
        if (f.fechaCorrecta()) {
            this.fechaConsulta = f;
            return true;
        }
        return false;
    }

    public void setFecha(Fecha fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public void setFechaConsulta(Fecha fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getFechaConsulta() {
        return fechaConsulta.getFecha();
    }

    public Fecha getFechaConsultaObj() {
        return fechaConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getDatos() {
        return "Número de consulta: " + noConsulta
                + "\nId Empleado: " + idEmpleado
                + "\nNúmero de paciente: " + noPaciente
                + "\nFecha de consulta: " + fechaConsulta.getFecha()
                + "\nDiagnóstico: " + diagnostico;
    }
}
