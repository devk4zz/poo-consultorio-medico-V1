/*
 * Desarrolladores:
 * Aban Miguel Angel
 * Cruz Ortega Alexis
 * López Licona Victor Hugo
 */
package Pck_Consultorio;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsultorioMedico {

    private final String ARCHIVO_MEDICOS = "Medicos.dat";
    private final String ARCHIVO_PACIENTES = "Pacientes.dat";
    private final String ARCHIVO_CONSULTAS = "Consultas.dat";

    private int opcion;
    private String menu;

    private ArrayList<Medico> listaMedicos = new ArrayList<>();
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private ArrayList<Consulta> listaConsultas = new ArrayList<>();

    public static void main(String[] args) {
        ConsultorioMedico programa = new ConsultorioMedico();
        programa.iniciar();
    }

    // Menú
    public void iniciar() {
        boolean numeroValido;

        cargarMedicos();
        cargarPacientes();
        cargarConsultas();

        opcion = 0;
        do {
            menu = JOptionPane.showInputDialog(null,
                    "------ CONSULTORIO MÉDICO ------\n"
                    + "1) Alta de un médico familiar\n"
                    + "2) Alta de un médico especialista\n"
                    + "3) Alta de un paciente\n"
                    + "4) Alta de una consulta\n"
                    + "5) Listar médicos familiares\n"
                    + "6) Listar médicos especialistas\n"
                    + "7) Listar pacientes\n"
                    + "8) Listar consultas\n"
                    + "9) Ver detalle de un médico familiar\n"
                    + "10) Ver detalle de un médico especialista\n"
                    + "11) Ver detalle de un paciente\n"
                    + "12) Ver detalle de una consulta\n"
                    + "13) Eliminar un médico familiar\n"
                    + "14) Eliminar un médico especialista\n"
                    + "15) Eliminar un paciente\n"
                    + "16) Eliminar una consulta\n"
                    + "17) Salir\n\n"
                    + "Elija una opción:", "Consultorio Médico", JOptionPane.QUESTION_MESSAGE);

            if (menu == null) {
                opcion = 17;
            } else if (menu.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "La opción no puede quedar en blanco");
            } else {
                numeroValido = true;
                try {
                    opcion = Integer.parseInt(menu.trim());
                } catch (NumberFormatException ex) {
                    opcion = 0;
                    numeroValido = false;
                    JOptionPane.showMessageDialog(null, "Inserte un número entero entre 1 y 17");
                }
                if (numeroValido) {
                    ejecutarOpcion();
                }
            }
        } while (opcion != 17);

        mostrarCopyright();
    }

    public void ejecutarOpcion() {
        switch (opcion) {
            case 1:
                altaFamiliar();
                break;
            case 2:
                altaEspecialista();
                break;
            case 3:
                altaPaciente();
                break;
            case 4:
                altaConsulta();
                break;
            case 5:
                listarFamiliares();
                break;
            case 6:
                listarEspecialistas();
                break;
            case 7:
                listarPacientes();
                break;
            case 8:
                listarConsultas();
                break;
            case 9:
                detalleMedico(true);
                break;
            case 10:
                detalleMedico(false);
                break;
            case 11:
                detallePaciente();
                break;
            case 12:
                detalleConsulta();
                break;
            case 13:
                eliminarMedico(true);
                break;
            case 14:
                eliminarMedico(false);
                break;
            case 15:
                eliminarPaciente();
                break;
            case 16:
                eliminarConsulta();
                break;
            case 17:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Inserte un número entre 1 y 17");
        }
    }

    // Altas
    public void altaFamiliar() {
        String idEmpleado;
        String nombre;
        String direccion;
        String telefono;
        String turno;
        int consultorio;
        Fecha fechaNacimiento;
        Fecha fechaContratacion;
        General familiar;

        idEmpleado = pedirIdMedicoNuevo("Alta de médico familiar");
        if (idEmpleado == null) {
            return;
        }
        nombre = pedirLetras("Nombre completo del médico:", "Nombre");
        if (nombre == null) {
            return;
        }
        direccion = pedirTexto("Dirección:", "Dirección");
        if (direccion == null) {
            return;
        }
        telefono = pedirTelefono();
        if (telefono == null) {
            return;
        }
        fechaNacimiento = fechaNacMedico();
        if (fechaNacimiento == null) {
            return;
        }
        fechaContratacion = fechaContratacion(fechaNacimiento);
        if (fechaContratacion == null) {
            return;
        }
        turno = pedirTurno();
        if (turno == null) {
            return;
        }
        consultorio = pedirNumero("Número de consultorio:", "Consultorio", 1, 999999999);
        if (consultorio == -1) {
            return;
        }

        familiar = new General(idEmpleado, nombre, direccion, telefono,
                fechaNacimiento, fechaContratacion, turno, consultorio);
        listaMedicos.add(familiar);
        guardarMedicos();
        JOptionPane.showMessageDialog(null, "Médico familiar registrado exitosamente");
    }

    public void altaEspecialista() {
        String idEmpleado;
        String nombre;
        String direccion;
        String telefono;
        String especialidad;
        String area;
        String enfermera;
        Fecha fechaNacimiento;
        Fecha fechaContratacion;
        Especialista especialista;

        idEmpleado = pedirIdMedicoNuevo("Alta de médico especialista");
        if (idEmpleado == null) {
            return;
        }
        nombre = pedirLetras("Nombre completo del médico:", "Nombre");
        if (nombre == null) {
            return;
        }
        direccion = pedirTexto("Dirección:", "Dirección");
        if (direccion == null) {
            return;
        }
        telefono = pedirTelefono();
        if (telefono == null) {
            return;
        }
        fechaNacimiento = fechaNacMedico();
        if (fechaNacimiento == null) {
            return;
        }
        fechaContratacion = fechaContratacion(fechaNacimiento);
        if (fechaContratacion == null) {
            return;
        }
        especialidad = pedirLetras("Especialidad:", "Especialidad");
        if (especialidad == null) {
            return;
        }
        area = pedirLetras("Área:", "Área");
        if (area == null) {
            return;
        }
        enfermera = pedirLetras("Nombre de la enfermera(o):", "Enfermera(o)");
        if (enfermera == null) {
            return;
        }

        especialista = new Especialista(idEmpleado, nombre, direccion, telefono,
                fechaNacimiento, fechaContratacion, especialidad, area, enfermera);
        listaMedicos.add(especialista);
        guardarMedicos();
        JOptionPane.showMessageDialog(null, "Médico especialista registrado exitosamente");
    }

    public void altaPaciente() {
        int noPaciente;
        boolean listo = false;
        String nombre;
        String direccion;
        String telefono;
        String correo;
        Fecha fechaNacimiento;
        Paciente paciente;

        do {
            noPaciente = pedirNumero("Número de paciente:", "Alta de paciente", 1, 999999999);
            if (noPaciente == -1) {
                return;
            }
            if (buscarPaciente(noPaciente) != -1) {
                JOptionPane.showMessageDialog(null, "El número de paciente ya está registrado");
            } else {
                listo = true;
            }
        } while (!listo);

        nombre = pedirLetras("Nombre completo del paciente:", "Nombre");
        if (nombre == null) {
            return;
        }
        direccion = pedirTexto("Dirección:", "Dirección");
        if (direccion == null) {
            return;
        }
        telefono = pedirTelefono();
        if (telefono == null) {
            return;
        }
        fechaNacimiento = fechaNacPaciente();
        if (fechaNacimiento == null) {
            return;
        }
        correo = pedirCorreo();
        if (correo == null) {
            return;
        }

        paciente = new Paciente(noPaciente, nombre, direccion, telefono, fechaNacimiento, correo);
        listaPacientes.add(paciente);
        guardarPacientes();
        JOptionPane.showMessageDialog(null, "Paciente registrado exitosamente");
    }

    public void altaConsulta() {
        int noConsulta;
        int noPaciente;
        boolean listo = false;
        String idEmpleado;
        String diagnostico;
        Fecha fechaConsulta;
        Consulta consulta;

        do {
            noConsulta = pedirNumero("Número de consulta:", "Alta de consulta", 1, 999999999);
            if (noConsulta == -1) {
                return;
            }
            if (buscarConsulta(noConsulta) != -1) {
                JOptionPane.showMessageDialog(null, "El número de consulta ya está registrado");
            } else {
                listo = true;
            }
        } while (!listo);

        listo = false;
        do {
            idEmpleado = pedirId("Id Empleado del médico asignado:", "Médico de la consulta");
            if (idEmpleado == null) {
                return;
            }
            if (buscarMedico(idEmpleado) == -1) {
                JOptionPane.showMessageDialog(null, "El médico " + idEmpleado + " no está registrado");
            } else {
                listo = true;
            }
        } while (!listo);

        listo = false;
        do {
            noPaciente = pedirNumero("Número de paciente:", "Paciente de la consulta", 1, 999999999);
            if (noPaciente == -1) {
                return;
            }
            if (buscarPaciente(noPaciente) == -1) {
                JOptionPane.showMessageDialog(null, "El paciente " + noPaciente + " no está registrado");
            } else {
                listo = true;
            }
        } while (!listo);

        fechaConsulta = fechaConsulta();
        if (fechaConsulta == null) {
            return;
        }
        diagnostico = pedirTexto("Diagnóstico:", "Diagnóstico");
        if (diagnostico == null) {
            return;
        }

        consulta = new Consulta(noConsulta, idEmpleado, noPaciente, fechaConsulta, diagnostico);
        listaConsultas.add(consulta);
        guardarConsultas();
        JOptionPane.showMessageDialog(null, "Consulta registrada exitosamente");
    }

    // Listados
    public void listarFamiliares() {
        int indice;
        boolean hayRegistros = false;
        String texto;
        Medico medico;
        General familiar;

        texto = "MÉDICOS FAMILIARES\n"
                + rellenar("Id Empleado", 13) + rellenar("Nombre", 35) + rellenar("Turno", 12)
                + rellenar("Consultorio", 13) + "Fecha de Contratación\n"
                + linea(98) + "\n";

        for (indice = 0; indice < listaMedicos.size(); indice++) {
            medico = listaMedicos.get(indice);
            if (medico instanceof General) {
                familiar = (General) medico;
                texto = texto + rellenar(familiar.getIdEmpleado(), 13)
                        + rellenar(familiar.getNombre(), 35)
                        + rellenar(familiar.getTurno(), 12)
                        + rellenar("" + familiar.getConsultorio(), 13)
                        + familiar.getFechaContratacion() + "\n";
                hayRegistros = true;
            }
        }
        if (hayRegistros) {
            mostrarTexto(texto, "Médicos familiares", true);
        } else {
            JOptionPane.showMessageDialog(null, "No hay médicos familiares registrados");
        }
    }

    public void listarEspecialistas() {
        int indice;
        boolean hayRegistros = false;
        String texto;
        Medico medico;
        Especialista especialista;

        texto = "MÉDICOS ESPECIALISTAS\n"
                + rellenar("Id Empleado", 13) + rellenar("Nombre", 35) + rellenar("Especialidad", 20)
                + rellenar("Área", 16) + "Fecha de Contratación\n"
                + linea(106) + "\n";

        for (indice = 0; indice < listaMedicos.size(); indice++) {
            medico = listaMedicos.get(indice);
            if (medico instanceof Especialista) {
                especialista = (Especialista) medico;
                texto = texto + rellenar(especialista.getIdEmpleado(), 13)
                        + rellenar(especialista.getNombre(), 35)
                        + rellenar(especialista.getEspecialidad(), 20)
                        + rellenar(especialista.getArea(), 16)
                        + especialista.getFechaContratacion() + "\n";
                hayRegistros = true;
            }
        }
        if (hayRegistros) {
            mostrarTexto(texto, "Médicos especialistas", true);
        } else {
            JOptionPane.showMessageDialog(null, "No hay médicos especialistas registrados");
        }
    }

    public void listarPacientes() {
        int indice;
        String texto;
        Paciente paciente;

        if (listaPacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pacientes registrados");
            return;
        }

        texto = "LISTADO DE PACIENTES\n"
                + rellenar("No.Paciente", 13) + rellenar("Nombre", 35)
                + rellenar("Fecha de Nacimiento", 21) + "Correo\n"
                + linea(100) + "\n";

        for (indice = 0; indice < listaPacientes.size(); indice++) {
            paciente = listaPacientes.get(indice);
            texto = texto + rellenar("" + paciente.getNoPaciente(), 13)
                    + rellenar(paciente.getNombre(), 35)
                    + rellenar(paciente.getFechaNacimiento(), 21)
                    + paciente.getCorreo() + "\n";
        }
        mostrarTexto(texto, "Pacientes", true);
    }

    public void listarConsultas() {
        int indice;
        String texto;
        Consulta consulta;

        if (listaConsultas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay consultas registradas");
            return;
        }

        texto = "LISTADO DE CONSULTAS\n"
                + rellenar("No.Consulta", 14) + rellenar("Id Empleado", 14)
                + rellenar("No.Paciente", 14) + "Fecha de Consulta\n"
                + linea(70) + "\n";

        for (indice = 0; indice < listaConsultas.size(); indice++) {
            consulta = listaConsultas.get(indice);
            texto = texto + rellenar("" + consulta.getNoConsulta(), 14)
                    + rellenar(consulta.getIdEmpleado(), 14)
                    + rellenar("" + consulta.getNoPaciente(), 14)
                    + consulta.getFechaConsulta() + "\n";
        }
        mostrarTexto(texto, "Consultas", true);
    }

    // Detalles
    public void detalleMedico(boolean familiar) {
        String idEmpleado;
        int posicion;
        Medico medico;
        String encabezado;

        idEmpleado = pedirId("Ingrese el Id Empleado del médico a consultar:", "Detalle de médico");
        if (idEmpleado == null) {
            return;
        }
        posicion = buscarMedico(idEmpleado);
        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "El médico con Id Empleado " + idEmpleado + " no existe");
            return;
        }
        medico = listaMedicos.get(posicion);
        if (familiar && !(medico instanceof General)) {
            JOptionPane.showMessageDialog(null, "El médico " + idEmpleado + " no es un médico familiar");
            return;
        }
        if (!familiar && !(medico instanceof Especialista)) {
            JOptionPane.showMessageDialog(null, "El médico " + idEmpleado + " no es un médico especialista");
            return;
        }

        if (familiar) {
            encabezado = "DETALLE DE MÉDICO FAMILIAR";
        } else {
            encabezado = "DETALLE DE MÉDICO ESPECIALISTA";
        }
        mostrarTexto(encabezado + "\n" + medico.getDatos(), "Detalle de médico", false);
    }

    public void detallePaciente() {
        int noPaciente;
        int posicion;
        Paciente paciente;

        noPaciente = pedirNumero("Ingrese el No. de paciente a consultar:", "Detalle de paciente", 1, 999999999);
        if (noPaciente == -1) {
            return;
        }
        posicion = buscarPaciente(noPaciente);
        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "El paciente " + noPaciente + " no existe");
            return;
        }
        paciente = listaPacientes.get(posicion);
        mostrarTexto("DETALLE DE PACIENTE\n" + paciente.getDatos(), "Detalle de paciente", false);
    }

    public void detalleConsulta() {
        int noConsulta;
        int posicionConsulta;
        int posicionMedico;
        int posicionPaciente;
        String texto;
        Consulta consulta;
        Medico medico;

        noConsulta = pedirNumero("Ingrese el No. de consulta que desea consultar:", "Detalle de consulta", 1, 999999999);
        if (noConsulta == -1) {
            return;
        }
        posicionConsulta = buscarConsulta(noConsulta);
        if (posicionConsulta == -1) {
            JOptionPane.showMessageDialog(null, "La consulta " + noConsulta + " no existe");
            return;
        }
        consulta = listaConsultas.get(posicionConsulta);

        texto = "DETALLE DE UNA CONSULTA\nNo. Consulta: " + consulta.getNoConsulta();

        posicionMedico = buscarMedico(consulta.getIdEmpleado());
        if (posicionMedico != -1) {
            medico = listaMedicos.get(posicionMedico);
            if (medico instanceof General) {
                texto = texto + "\n\nMÉDICO FAMILIAR\n" + medico.getDatos();
            } else {
                texto = texto + "\n\nMÉDICO ESPECIALISTA\n" + medico.getDatos();
            }
        } else {
            texto = texto + "\n\nMÉDICO\nInformación no disponible";
        }

        posicionPaciente = buscarPaciente(consulta.getNoPaciente());
        if (posicionPaciente != -1) {
            texto = texto + "\n\nPACIENTE\n" + listaPacientes.get(posicionPaciente).getDatos();
        } else {
            texto = texto + "\n\nPACIENTE\nInformación no disponible";
        }

        texto = texto + "\n\nFecha de consulta: " + consulta.getFechaConsulta()
                + "\nDiagnóstico: " + consulta.getDiagnostico();

        mostrarTexto(texto, "Detalle de consulta", false);
    }

    // Eliminar
    public void eliminarMedico(boolean familiar) {
        String idEmpleado;
        int posicion;
        int indice;
        int eliminadas = 0;
        int confirmacion;
        Medico medico;

        idEmpleado = pedirId("Id Empleado del médico a eliminar:", "Eliminar médico");
        if (idEmpleado == null) {
            return;
        }
        posicion = buscarMedico(idEmpleado);
        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "El médico con Id Empleado " + idEmpleado + " no existe");
            return;
        }
        medico = listaMedicos.get(posicion);
        if (familiar && !(medico instanceof General)) {
            JOptionPane.showMessageDialog(null, "El médico " + idEmpleado + " no es un médico familiar");
            return;
        }
        if (!familiar && !(medico instanceof Especialista)) {
            JOptionPane.showMessageDialog(null, "El médico " + idEmpleado + " no es un médico especialista");
            return;
        }

        confirmacion = JOptionPane.showConfirmDialog(null,
                medico.getDatos() + "\n\n¿Está seguro de que desea eliminar a este médico?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            listaMedicos.remove(posicion);
            guardarMedicos();

            for (indice = listaConsultas.size() - 1; indice >= 0; indice--) {
                if (listaConsultas.get(indice).getIdEmpleado().equalsIgnoreCase(idEmpleado)) {
                    listaConsultas.remove(indice);
                    eliminadas++;
                }
            }
            if (eliminadas > 0) {
                guardarConsultas();
                JOptionPane.showMessageDialog(null, "Se eliminaron " + eliminadas + " consulta(s) de este médico");
            }
            JOptionPane.showMessageDialog(null, "Médico eliminado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Eliminación cancelada");
        }
    }

    public void eliminarPaciente() {
        int noPaciente;
        int posicion;
        int indice;
        int eliminadas = 0;
        int confirmacion;
        Paciente paciente;

        noPaciente = pedirNumero("No. de paciente a eliminar:", "Eliminar paciente", 1, 999999999);
        if (noPaciente == -1) {
            return;
        }
        posicion = buscarPaciente(noPaciente);
        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "El paciente " + noPaciente + " no existe");
            return;
        }
        paciente = listaPacientes.get(posicion);

        confirmacion = JOptionPane.showConfirmDialog(null,
                paciente.getDatos() + "\n\n¿Está seguro de que desea eliminar a este paciente?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            listaPacientes.remove(posicion);
            guardarPacientes();

            for (indice = listaConsultas.size() - 1; indice >= 0; indice--) {
                if (listaConsultas.get(indice).getNoPaciente() == noPaciente) {
                    listaConsultas.remove(indice);
                    eliminadas++;
                }
            }
            if (eliminadas > 0) {
                guardarConsultas();
                JOptionPane.showMessageDialog(null, "Se eliminaron " + eliminadas + " consulta(s) de este paciente");
            }
            JOptionPane.showMessageDialog(null, "Paciente eliminado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Eliminación cancelada");
        }
    }

    public void eliminarConsulta() {
        int noConsulta;
        int posicion;
        int confirmacion;
        Consulta consulta;

        noConsulta = pedirNumero("No. de consulta a eliminar:", "Eliminar consulta", 1, 999999999);
        if (noConsulta == -1) {
            return;
        }
        posicion = buscarConsulta(noConsulta);
        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "La consulta " + noConsulta + " no existe");
            return;
        }
        consulta = listaConsultas.get(posicion);

        confirmacion = JOptionPane.showConfirmDialog(null,
                consulta.getDatos() + "\n\n¿Está seguro de que desea eliminar esta consulta?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            listaConsultas.remove(posicion);
            guardarConsultas();
            JOptionPane.showMessageDialog(null, "Consulta eliminada exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Eliminación cancelada");
        }
    }

    // Búsquedas
    public int buscarMedico(String idEmpleado) {
        int indice = 0;
        int posicion = -1;
        while (indice < listaMedicos.size() && posicion == -1) {
            if (listaMedicos.get(indice).getIdEmpleado().equalsIgnoreCase(idEmpleado)) {
                posicion = indice;
            }
            indice++;
        }
        return posicion;
    }

    public int buscarPaciente(int noPaciente) {
        int indice = 0;
        int posicion = -1;
        while (indice < listaPacientes.size() && posicion == -1) {
            if (listaPacientes.get(indice).getNoPaciente() == noPaciente) {
                posicion = indice;
            }
            indice++;
        }
        return posicion;
    }

    public int buscarConsulta(int noConsulta) {
        int indice = 0;
        int posicion = -1;
        while (indice < listaConsultas.size() && posicion == -1) {
            if (listaConsultas.get(indice).getNoConsulta() == noConsulta) {
                posicion = indice;
            }
            indice++;
        }
        return posicion;
    }

    // Validaciones
    public boolean soloNumeros(String texto) {
        int indice;
        boolean correcto = !texto.isEmpty();
        for (indice = 0; indice < texto.length(); indice++) {
            if (texto.charAt(indice) < '0' || texto.charAt(indice) > '9') {
                correcto = false;
            }
        }
        return correcto;
    }

    public boolean soloLetras(String texto) {
        int indice;
        char caracter;
        boolean correcto = true;
        for (indice = 0; indice < texto.length(); indice++) {
            caracter = texto.charAt(indice);
            if (!Character.isLetter(caracter) && caracter != ' ') {
                correcto = false;
            }
        }
        return correcto;
    }

    public boolean soloLetrasYNumeros(String texto) {
        int indice;
        boolean correcto = true;
        for (indice = 0; indice < texto.length(); indice++) {
            if (!Character.isLetterOrDigit(texto.charAt(indice))) {
                correcto = false;
            }
        }
        return correcto;
    }

    public boolean textoValido(String texto) {
        int indice;
        char caracter;
        boolean correcto = true;
        for (indice = 0; indice < texto.length(); indice++) {
            caracter = texto.charAt(indice);
            if (!Character.isLetterOrDigit(caracter) && " .,#-/:()".indexOf(caracter) == -1) {
                correcto = false;
            }
        }
        return correcto;
    }

    public boolean correoValido(String correo) {
        int indice;
        char caracter;
        int posArroba = correo.indexOf('@');
        int posPunto = correo.lastIndexOf('.');
        boolean correcto = true;

        for (indice = 0; indice < correo.length(); indice++) {
            caracter = correo.charAt(indice);
            if (!((caracter >= 'a' && caracter <= 'z') || (caracter >= 'A' && caracter <= 'Z')
                    || (caracter >= '0' && caracter <= '9')
                    || caracter == '@' || caracter == '.' || caracter == '_' || caracter == '-')) {
                correcto = false;
            }
        }
        if (posArroba < 1 || posArroba != correo.lastIndexOf('@')
                || posPunto < posArroba + 2 || posPunto == correo.length() - 1) {
            correcto = false;
        }
        return correcto;
    }

    // Captura de datos
    public String pedirNoVacio(String mensaje, String titulo) {
        String texto;
        boolean listo = false;

        do {
            texto = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
            if (texto == null) {
                listo = true;
            } else if (texto.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo " + titulo + " no puede quedar en blanco");
            } else {
                texto = texto.trim();
                listo = true;
            }
        } while (!listo);
        return texto;
    }

    public String pedirTexto(String mensaje, String titulo) {
        String texto;
        boolean listo = false;

        do {
            texto = pedirNoVacio(mensaje, titulo);
            if (texto == null) {
                listo = true;
            } else if (!textoValido(texto)) {
                JOptionPane.showMessageDialog(null, "El campo " + titulo
                        + " no permite caracteres especiales (& % $ ¡ ¿ | ° ; entre otros)");
            } else {
                listo = true;
            }
        } while (!listo);
        return texto;
    }

    public String pedirLetras(String mensaje, String titulo) {
        String texto;
        boolean listo = false;

        do {
            texto = pedirNoVacio(mensaje, titulo);
            if (texto == null) {
                listo = true;
            } else if (!soloLetras(texto)) {
                JOptionPane.showMessageDialog(null, "El campo " + titulo + " solo puede contener letras y espacios");
            } else {
                listo = true;
            }
        } while (!listo);
        return texto;
    }

    public String pedirId(String mensaje, String titulo) {
        String texto;
        boolean listo = false;

        do {
            texto = pedirNoVacio(mensaje, titulo);
            if (texto == null) {
                listo = true;
            } else if (!soloLetrasYNumeros(texto)) {
                JOptionPane.showMessageDialog(null, "El Id solo puede tener letras y números, sin espacios");
            } else {
                listo = true;
            }
        } while (!listo);
        return texto;
    }

    public String pedirIdMedicoNuevo(String titulo) {
        String idEmpleado;
        boolean listo = false;

        do {
            idEmpleado = pedirId("Id Empleado:", titulo);
            if (idEmpleado == null) {
                listo = true;
            } else if (buscarMedico(idEmpleado) != -1) {
                JOptionPane.showMessageDialog(null, "El Id Empleado ya está registrado");
            } else {
                listo = true;
            }
        } while (!listo);
        return idEmpleado;
    }

    public String pedirTelefono() {
        String telefono;
        boolean listo = false;

        do {
            telefono = pedirNoVacio("Teléfono (10 dígitos):", "Teléfono");
            if (telefono == null) {
                listo = true;
            } else if (telefono.length() != 10 || !soloNumeros(telefono)) {
                JOptionPane.showMessageDialog(null, "El teléfono debe tener exactamente 10 dígitos numéricos");
            } else {
                listo = true;
            }
        } while (!listo);
        return telefono;
    }

    public String pedirCorreo() {
        String correo;
        boolean listo = false;

        do {
            correo = pedirNoVacio("Correo electrónico:", "Correo");
            if (correo == null) {
                listo = true;
            } else if (!correoValido(correo)) {
                JOptionPane.showMessageDialog(null,
                        "El formato del correo no es válido (ejemplo: nombre@correo.com)");
            } else {
                listo = true;
            }
        } while (!listo);
        return correo;
    }

    public String pedirTurno() {
        String texto;
        String turno = null;
        boolean listo = false;

        do {
            texto = pedirNoVacio("Turno (Matutino/Vespertino/Nocturno):", "Turno");
            if (texto == null) {
                listo = true;
            } else if (texto.equalsIgnoreCase("Matutino")) {
                turno = "Matutino";
                listo = true;
            } else if (texto.equalsIgnoreCase("Vespertino")) {
                turno = "Vespertino";
                listo = true;
            } else if (texto.equalsIgnoreCase("Nocturno")) {
                turno = "Nocturno";
                listo = true;
            } else {
                JOptionPane.showMessageDialog(null, "El turno debe ser Matutino, Vespertino o Nocturno");
            }
        } while (!listo);
        return turno;
    }

    public int pedirNumero(String mensaje, String titulo, int minimo, int maximo) {
        String texto;
        int valor = -1;
        boolean listo = false;

        do {
            texto = pedirNoVacio(mensaje, titulo);
            if (texto == null) {
                valor = -1;
                listo = true;
            } else {
                try {
                    valor = Integer.parseInt(texto);
                    if (valor < minimo || valor > maximo) {
                        JOptionPane.showMessageDialog(null,
                                "El valor debe estar entre " + minimo + " y " + maximo);
                    } else {
                        listo = true;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Escriba solo números enteros, sin letras ni símbolos");
                }
            }
        } while (!listo);
        return valor;
    }

    // Fechas
    public int numeroHoy() {
        LocalDate hoy = LocalDate.now();
        return hoy.getYear() * 10000 + hoy.getMonthValue() * 100 + hoy.getDayOfMonth();
    }

    public int numeroFecha(Fecha fecha) {
        return fecha.getAnio() * 10000 + fecha.getMes() * 100 + fecha.getDia();
    }

    public int calcularEdad(Fecha nacimiento) {
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - nacimiento.getAnio();
        if (hoy.getMonthValue() < nacimiento.getMes()
                || (hoy.getMonthValue() == nacimiento.getMes() && hoy.getDayOfMonth() < nacimiento.getDia())) {
            edad--;
        }
        return edad;
    }

    public Fecha pedirFecha(String titulo) {
        int dia;
        int mes;
        int anio;
        boolean listo = false;
        Fecha fecha = null;

        do {
            dia = pedirNumero(titulo + "\nDía (1-31):", "Día", 1, 31);
            if (dia == -1) {
                listo = true;
            } else {
                mes = pedirNumero(titulo + "\nMes (1-12):", "Mes", 1, 12);
                if (mes == -1) {
                    listo = true;
                } else {
                    anio = pedirNumero(titulo + "\nAño:", "Año", 1900, 2100);
                    if (anio == -1) {
                        listo = true;
                    } else {
                        fecha = new Fecha(dia, mes, anio);
                        if (fecha.fechaCorrecta()) {
                            listo = true;
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "La fecha " + fecha.getFecha() + " no existe en el calendario");
                            fecha = null;
                        }
                    }
                }
            }
        } while (!listo);
        return fecha;
    }

    public Fecha fechaNacPaciente() {
        boolean listo = false;
        Fecha fecha;

        do {
            fecha = pedirFecha("Fecha de nacimiento del paciente");
            if (fecha == null) {
                listo = true;
            } else if (numeroFecha(fecha) > numeroHoy()) {
                JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede ser posterior a hoy");
            } else if (calcularEdad(fecha) > 115) {
                JOptionPane.showMessageDialog(null, "La edad del paciente no puede ser mayor a 115 años");
            } else {
                listo = true;
            }
        } while (!listo);
        return fecha;
    }

    public Fecha fechaNacMedico() {
        boolean listo = false;
        int edad;
        Fecha fecha;

        do {
            fecha = pedirFecha("Fecha de nacimiento del médico");
            if (fecha == null) {
                listo = true;
            } else if (numeroFecha(fecha) > numeroHoy()) {
                JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede ser posterior a hoy");
            } else {
                edad = calcularEdad(fecha);
                if (edad < 20 || edad >= 28) {
                    JOptionPane.showMessageDialog(null,
                            "La edad del médico debe ser menor a 28 años (mínimo 20 años)");
                } else {
                    listo = true;
                }
            }
        } while (!listo);
        return fecha;
    }

    public Fecha fechaContratacion(Fecha nacimiento) {
        boolean listo = false;
        Fecha fecha;

        do {
            fecha = pedirFecha("Fecha de contratación del médico");
            if (fecha == null) {
                listo = true;
            } else if (numeroFecha(fecha) > numeroHoy()) {
                JOptionPane.showMessageDialog(null, "La fecha de contratación no puede ser posterior a hoy");
            } else if (numeroFecha(fecha) < numeroFecha(nacimiento) + 200000) {
                JOptionPane.showMessageDialog(null, "El médico debe tener mínimo 20 años al ser contratado");
            } else {
                listo = true;
            }
        } while (!listo);
        return fecha;
    }

    public Fecha fechaConsulta() {
        boolean listo = false;
        Fecha fecha;

        do {
            fecha = pedirFecha("Fecha de la consulta");
            if (fecha == null) {
                listo = true;
            } else if (numeroFecha(fecha) > numeroHoy()) {
                JOptionPane.showMessageDialog(null, "La fecha de consulta no puede ser posterior a hoy");
            } else if (numeroFecha(fecha) < numeroHoy() - 50000) {
                JOptionPane.showMessageDialog(null, "La fecha de consulta no puede ser de hace más de 5 años");
            } else {
                listo = true;
            }
        } while (!listo);
        return fecha;
    }

    // Presentación
    public String rellenar(String texto, int ancho) {
        String resultado = texto;
        while (resultado.length() < ancho) {
            resultado = resultado + " ";
        }
        return resultado;
    }

    public String linea(int ancho) {
        int indice;
        String resultado = "";
        for (indice = 0; indice < ancho; indice++) {
            resultado = resultado + "-";
        }
        return resultado;
    }

    public int contarLineas(String texto) {
        int indice;
        int lineas = 1;
        for (indice = 0; indice < texto.length(); indice++) {
            if (texto.charAt(indice) == '\n') {
                lineas++;
            }
        }
        return lineas;
    }

    public void mostrarTexto(String texto, String titulo, boolean tabla) {
        JTextArea area = new JTextArea(texto);
        area.setEditable(false);
        area.setRows(Math.min(contarLineas(texto) + 1, 25));
        if (tabla) {
            area.setFont(new Font("Monospaced", Font.PLAIN, 12));
            area.setColumns(105);
        } else {
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setColumns(50);
        }
        JOptionPane.showMessageDialog(null, new JScrollPane(area), titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    // Archivos
    public void guardarMedicos() {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO_MEDICOS));
            salida.writeObject(listaMedicos);
            salida.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el archivo " + ARCHIVO_MEDICOS);
        }
    }

    public void guardarPacientes() {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PACIENTES));
            salida.writeObject(listaPacientes);
            salida.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el archivo " + ARCHIVO_PACIENTES);
        }
    }

    public void guardarConsultas() {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CONSULTAS));
            salida.writeObject(listaConsultas);
            salida.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el archivo " + ARCHIVO_CONSULTAS);
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarMedicos() {
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARCHIVO_MEDICOS));
            listaMedicos = (ArrayList<Medico>) entrada.readObject();
            entrada.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo leer el archivo " + ARCHIVO_MEDICOS);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "El archivo " + ARCHIVO_MEDICOS + " tiene un formato no válido");
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarPacientes() {
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARCHIVO_PACIENTES));
            listaPacientes = (ArrayList<Paciente>) entrada.readObject();
            entrada.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo leer el archivo " + ARCHIVO_PACIENTES);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "El archivo " + ARCHIVO_PACIENTES + " tiene un formato no válido");
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarConsultas() {
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARCHIVO_CONSULTAS));
            listaConsultas = (ArrayList<Consulta>) entrada.readObject();
            entrada.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo leer el archivo " + ARCHIVO_CONSULTAS);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "El archivo " + ARCHIVO_CONSULTAS + " tiene un formato no válido");
        }
    }

    // Copyright
    public void mostrarCopyright() {
        JOptionPane.showMessageDialog(null,
                "Consultorio Médico\n\n"
                + "Desarrolladores:\n"
                + "• Aban Miguel Angel\n"
                + "• Cruz Ortega Alexis\n"
                + "• López Licona Victor Hugo\n\n"
                + "© 2026 Todos los derechos reservados.",
                "Copyright", JOptionPane.INFORMATION_MESSAGE);
    }
}