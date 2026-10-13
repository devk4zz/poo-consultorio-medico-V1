/*
 * Desarrolladores:
 * Aban Miguel Angel
 * Cruz Ortega Alexis
 * López Licona Victor Hugo
 */

package Pck_Consultorio;

import java.io.Serializable;

public class Fecha implements Serializable {

    private int dia;
    private int mes;
    private int anio;

    public Fecha() {
        this.dia = 1;
        this.mes = 1;
        this.anio = 2024;
    }

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public void setFecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean bisiesto() {
        if (anio % 400 == 0) {
            return true;
        } else if (anio % 100 == 0) {
            return false;
        } else if (anio % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean fechaCorrecta() {
        if (anio < 1 || mes < 1 || mes > 12 || dia < 1) {
            return false;
        }

        int diasMes;

        if (mes == 2) {
            if (bisiesto()) {
                diasMes = 29;
            } else {
                diasMes = 28;
            }
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            diasMes = 30;
        } else {
            diasMes = 31;
        }

        return dia <= diasMes;
    }

    public String getFecha() {
        return dia + "/" + mes + "/" + anio;
    }

    public String getDatos() {
        return getFecha();
    }
}
