/*
 * Desarrolladores:
 * Aban Miguel Angel
 * Cruz Ortega Alexis
 * López Licona Victor Hugo
 */
package Pck_Consultorio;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ConsultorioMedico {

    // ---------- VARIABLES ----------
    private int opcion;
    private String menu;

    // ---------- OBJETOS ----------
    private ArrayList<Medico> listaMedicos = new ArrayList<>();
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private ArrayList<Consulta> listaConsultas = new ArrayList<>();

    // main siempre debe ser static en Java, aqui solo se crea el objeto y se arranca
    public static void main(String[] args) {
        ConsultorioMedico programa = new ConsultorioMedico();
        programa.iniciar();
    }

    // =====================================================
    //                       MENU
    // =====================================================
    public void iniciar() {
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
            } else if (menu.isBlank()) {
                JOptionPane.showMessageDialog(null, "La opción no puede quedar en blanco");
            } else if (!soloNumeros(menu) || menu.length() > 2) {
                JOptionPane.showMessageDialog(null, "Inserte un número entre 1 y 17");
            } else {
                opcion = Integer.parseInt(menu);
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
        } while (opcion != 17);

        mostrarCopyright();
    }

    // =====================================================
    //                       ALTAS
    // =====================================================
    public void altaFamiliar() {
        String id;
        String nombre;
        String direccion;
        String telefono;
        String turno;
        int consultorio;
        Fecha nacimiento;
        Fecha contratacion;
        General medico;

        id = pedirIdMedicoNuevo("Alta de médico familiar");
        if (id == null) {
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
        nacimiento = fechaNacMedico();
        if (nacimiento == null) {
            return;
        }
        contratacion = fechaContratacion(nacimiento);
        if (contratacion == null) {
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

        medico = new General(id, nombre, direccion, telefono, nacimiento, contratacion, turno, consultorio);
        listaMedicos.add(medico);
        guardarMedicos();
        JOptionPane.showMessageDialog(null, "Médico familiar registrado exitosamente");
    }

    public void altaEspecialista() {
        String id;
        String nombre;
        String direccion;
        String telefono;
        String especialidad;
        String area;
        String enfermera;
        Fecha nacimiento;
        Fecha contratacion;
        Especialista medico;

        id = pedirIdMedicoNuevo("Alta de médico especialista");
        if (id == null) {
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
        nacimiento = fechaNacMedico();
        if (nacimiento == null) {
            return;
        }
        contratacion = fechaContratacion(nacimiento);
        if (contratacion == null) {
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
        enfermera = pedirLetras("Nombre de la enfermera(o):", "Enfermera");
        if (enfermera == null) {
            return;
        }

        medico = new Especialista(id, nombre, direccion, telefono, nacimiento, contratacion,
                especialidad, area, enfermera);
        listaMedicos.add(medico);
        guardarMedicos();
        JOptionPane.showMessageDialog(null, "Médico especialista registrado exitosamente");
    }

    public void altaPaciente() {
        int numero = 0;
        boolean listo = false;
        String nombre;
        String direccion;
        String telefono;
        String correo;
        Fecha nacimiento;
        Paciente paciente;

        do {
            numero = pedirNumero("Número de paciente:", "Alta de paciente", 1, 999999999);
            if (numero == -1) {
                return;
            }
            if (buscarPaciente(numero) != -1) {
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
        nacimiento = fechaNacPaciente();
        if (nacimiento == null) {
            return;
        }
        correo = pedirCorreo();
        if (correo == null) {
            return;
        }

        paciente = new Paciente(numero, nombre, direccion, telefono, nacimiento, correo);
        listaPacientes.add(paciente);
        guardarPacientes();
        JOptionPane.showMessageDialog(null, "Paciente registrado exitosamente");
    }

    public void altaConsulta() {
        int numeroConsulta = 0;
        int numeroPaciente = 0;
        boolean listo = false;
        String id = "";
        String diagnostico;
        Fecha fecha;
        Consulta consulta;

        do {
            numeroConsulta = pedirNumero("Número de consulta:", "Alta de consulta", 1, 999999999);
            if (numeroConsulta == -1) {
                return;
            }
            if (buscarConsulta(numeroConsulta) != -1) {
                JOptionPane.showMessageDialog(null, "El número de consulta ya está registrado");
            } else {
                listo = true;
            }
        } while (!listo);

        listo = false;
        do {
            id = pedirId("ID de empleado del médico asignado:", "Médico de consulta");
            if (id == null) {
                return;
            }
            if (buscarMedico(id) == -1) {
                JOptionPane.showMessageDialog(null, "El médico " + id + " no está registrado");
            } else {
                listo = true;
            }
        } while (!listo);

        listo = false;
        do {
            numeroPaciente = pedirNumero("Número de paciente:", "Paciente de consulta", 1, 999999999);
            if (numeroPaciente == -1) {
                return;
            }
            if (buscarPaciente(numeroPaciente) == -1) {
                JOptionPane.showMessageDialog(null, "El paciente " + numeroPaciente + " no está registrado");
            } else {
                listo = true;
            }
        } while (!listo);

        fecha = fechaConsulta();
        if (fecha == null) {
            return;
        }
        diagnostico = pedirTexto("Diagnóstico:", "Diagnóstico");
        if (diagnostico == null) {
            return;
        }

        consulta = new Consulta(numeroConsulta, id, numeroPaciente, fecha, diagnostico);
        listaConsultas.add(consulta);
        guardarConsultas();
        JOptionPane.showMessageDialog(null, "Consulta registrada exitosamente");
    }

    // =====================================================
    //                      LISTADOS
    // =====================================================
    public void listarFamiliares() {
        int i;
        boolean hay = false;
        String texto = "MÉDICOS FAMILIARES\n\n";
        Medico m;
        General g;

        for (i = 0; i < listaMedicos.size(); i++) {
            m = listaMedicos.get(i);
            if (m instanceof General) {
                g = (General) m;
                texto = texto + g.getIdEmpleado() + " | " + g.getNombre() + " | Turno: " + g.getTurno()
                        + " | Consultorio: " + g.getConsultorio()
                        + " | Contratación: " + g.getFechaContratacion() + "\n";
                hay = true;
            }
        }
        if (hay) {
            JOptionPane.showMessageDialog(null, texto, "Médicos familiares", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hay médicos familiares registrados");
        }
    }

    public void listarEspecialistas() {
        int i;
        boolean hay = false;
        String texto = "MÉDICOS ESPECIALISTAS\n\n";
        Medico m;
        Especialista e;

        for (i = 0; i < listaMedicos.size(); i++) {
            m = listaMedicos.get(i);
            if (m instanceof Especialista) {
                e = (Especialista) m;
                texto = texto + e.getIdEmpleado() + " | " + e.getNombre() + " | " + e.getEspecialidad()
                        + " | Área: " + e.getArea()
                        + " | Contratación: " + e.getFechaContratacion() + "\n";
                hay = true;
            }
        }
        if (hay) {
            JOptionPane.showMessageDialog(null, texto, "Médicos especialistas", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hay médicos especialistas registrados");
        }
    }

    public void listarPacientes() {
        int i;
        String texto = "LISTADO DE PACIENTES\n\n";
        Paciente p;

        if (listaPacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pacientes registrados");
            return;
        }
        for (i = 0; i < listaPacientes.size(); i++) {
            p = listaPacientes.get(i);
            texto = texto + p.getNoPaciente() + " | " + p.getNombre() + " | Nacimiento: "
                    + p.getFechaNacimiento() + " | " + p.getCorreo() + "\n";
        }
        JOptionPane.showMessageDialog(null, texto, "Pacientes", JOptionPane.INFORMATION_MESSAGE);
    }

    public void listarConsultas() {
        int i;
        String texto = "LISTADO DE CONSULTAS\n\n";
        Consulta c;

        if (listaConsultas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay consultas registradas");
            return;
        }
        for (i = 0; i < listaConsultas.size(); i++) {
            c = listaConsultas.get(i);
            texto = texto + c.getNoConsulta() + " | Médico: " + c.getIdEmpleado()
                    + " | Paciente: " + c.getNoPaciente()
                    + " | Fecha: " + c.getFechaConsulta() + "\n";
        }
        JOptionPane.showMessageDialog(null, texto, "Consultas", JOptionPane.INFORMATION_MESSAGE);
    }

    // =====================================================
    //                      DETALLES
    // =====================================================
    public void detalleMedico(boolean familiar) {
        String id;
        int pos;
        Medico m;

        id = pedirId("Ingrese el Id Empleado del médico:", "Detalle de médico");
        if (id == null) {
            return;
        }
        pos = buscarMedico(id);
        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "El médico con ID " + id + " no existe");
            return;
        }
        m = listaMedicos.get(pos);
        if (familiar && !(m instanceof General)) {
            JOptionPane.showMessageDialog(null, "El médico con ID " + id + " no es un médico familiar");
            return;
        }
        if (!familiar && !(m instanceof Especialista)) {
            JOptionPane.showMessageDialog(null, "El médico con ID " + id + " no es un médico especialista");
            return;
        }
        // getDatos() se comporta distinto segun el tipo de medico (polimorfismo)
        JOptionPane.showMessageDialog(null, m.getDatos(), "Detalle de médico", JOptionPane.INFORMATION_MESSAGE);
    }

    public void detallePaciente() {
        int numero;
        int pos;
        Paciente p;

        numero = pedirNumero("Ingrese el No. de paciente:", "Detalle de paciente", 1, 999999999);
        if (numero == -1) {
            return;
        }
        pos = buscarPaciente(numero);
        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "El paciente " + numero + " no existe");
            return;
        }
        p = listaPacientes.get(pos);
        JOptionPane.showMessageDialog(null, p.getDatos(), "Detalle de paciente", JOptionPane.INFORMATION_MESSAGE);
    }

    public void detalleConsulta() {
        int numero;
        int pos;
        int posMedico;
        int posPaciente;
        String texto;
        Consulta c;

        numero = pedirNumero("Ingrese el No. de consulta:", "Detalle de consulta", 1, 999999999);
        if (numero == -1) {
            return;
        }
        pos = buscarConsulta(numero);
        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "La consulta " + numero + " no existe");
            return;
        }
        c = listaConsultas.get(pos);
        texto = c.getDatos();

        posMedico = buscarMedico(c.getIdEmpleado());
        if (posMedico != -1) {
            texto = texto + "\n\n" + listaMedicos.get(posMedico).getDatos();
        } else {
            texto = texto + "\n\nMÉDICO: información no disponible";
        }

        posPaciente = buscarPaciente(c.getNoPaciente());
        if (posPaciente != -1) {
            texto = texto + "\n\n" + listaPacientes.get(posPaciente).getDatos();
        } else {
            texto = texto + "\n\nPACIENTE: información no disponible";
        }

        JOptionPane.showMessageDialog(null, texto, "Detalle de consulta", JOptionPane.INFORMATION_MESSAGE);
    }

    // =====================================================
    //                     ELIMINAR
    // =====================================================
    public void eliminarMedico(boolean familiar) {
        String id;
        int pos;
        int i;
        int eliminadas = 0;
        int confirmacion;
        Medico m;

        id = pedirId("Id Empleado del médico a eliminar:", "Eliminar médico");
        if (id == null) {
            return;
        }
        pos = buscarMedico(id);
        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "El médico con ID " + id + " no existe");
            return;
        }
        m = listaMedicos.get(pos);
        if (familiar && !(m instanceof General)) {
            JOptionPane.showMessageDialog(null, "El médico con ID " + id + " no es un médico familiar");
            return;
        }
        if (!familiar && !(m instanceof Especialista)) {
            JOptionPane.showMessageDialog(null, "El médico con ID " + id + " no es un médico especialista");
            return;
        }

        confirmacion = JOptionPane.showConfirmDialog(null,
                m.getDatos() + "\n\n¿Está seguro de que desea eliminar a este médico?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            listaMedicos.remove(pos);
            guardarMedicos();

            // Tambien se borran las consultas de ese medico
            for (i = listaConsultas.size() - 1; i >= 0; i--) {
                if (listaConsultas.get(i).getIdEmpleado().equalsIgnoreCase(id)) {
                    listaConsultas.remove(i);
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
        int numero;
        int pos;
        int i;
        int eliminadas = 0;
        int confirmacion;
        Paciente p;

        numero = pedirNumero("No. de paciente a eliminar:", "Eliminar paciente", 1, 999999999);
        if (numero == -1) {
            return;
        }
        pos = buscarPaciente(numero);
        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "El paciente " + numero + " no existe");
            return;
        }
        p = listaPacientes.get(pos);

        confirmacion = JOptionPane.showConfirmDialog(null,
                p.getDatos() + "\n\n¿Está seguro de que desea eliminar a este paciente?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            listaPacientes.remove(pos);
            guardarPacientes();

            for (i = listaConsultas.size() - 1; i >= 0; i--) {
                if (listaConsultas.get(i).getNoPaciente() == numero) {
                    listaConsultas.remove(i);
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
        int numero;
        int pos;
        int confirmacion;
        Consulta c;

        numero = pedirNumero("No. de consulta a eliminar:", "Eliminar consulta", 1, 999999999);
        if (numero == -1) {
            return;
        }
        pos = buscarConsulta(numero);
        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "La consulta " + numero + " no existe");
            return;
        }
        c = listaConsultas.get(pos);

        confirmacion = JOptionPane.showConfirmDialog(null,
                c.getDatos() + "\n\n¿Está seguro de que desea eliminar esta consulta?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            listaConsultas.remove(pos);
            guardarConsultas();
            JOptionPane.showMessageDialog(null, "Consulta eliminada exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Eliminación cancelada");
        }
    }

    // =====================================================
    //                      BUSQUEDAS
    // =====================================================
    public int buscarMedico(String id) {
        int i;
        int pos = -1;
        for (i = 0; i < listaMedicos.size(); i++) {
            if (listaMedicos.get(i).getIdEmpleado().equalsIgnoreCase(id)) {
                pos = i;
            }
        }
        return pos;
    }

    public int buscarPaciente(int numero) {
        int i;
        int pos = -1;
        for (i = 0; i < listaPacientes.size(); i++) {
            if (listaPacientes.get(i).getNoPaciente() == numero) {
                pos = i;
            }
        }
        return pos;
    }

    public int buscarConsulta(int numero) {
        int i;
        int pos = -1;
        for (i = 0; i < listaConsultas.size(); i++) {
            if (listaConsultas.get(i).getNoConsulta() == numero) {
                pos = i;
            }
        }
        return pos;
    }

    // =====================================================
    //            REVISAR TEXTOS (sin matches ni trim)
    // =====================================================
    public boolean soloNumeros(String t) {
        int i;
        boolean ok = true;
        if (t.isEmpty()) {
            ok = false;
        }
        for (i = 0; i < t.length(); i++) {
            if (!Character.isDigit(t.charAt(i))) {
                ok = false;
            }
        }
        return ok;
    }

    public boolean soloLetras(String t) {
        int i;
        char c;
        boolean ok = true;
        for (i = 0; i < t.length(); i++) {
            c = t.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                ok = false;
            }
        }
        return ok;
    }

    public boolean soloLetrasYNumeros(String t) {
        int i;
        boolean ok = true;
        for (i = 0; i < t.length(); i++) {
            if (!Character.isLetterOrDigit(t.charAt(i))) {
                ok = false;
            }
        }
        return ok;
    }

    // =====================================================
    //                  PEDIR DATOS
    //   (todos regresan null o -1 si el usuario cancela)
    // =====================================================
    public String pedirTexto(String mensaje, String titulo) {
        String t;
        boolean listo = false;

        do {
            t = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
            if (t == null) {
                listo = true;
            } else if (t.isBlank()) {
                JOptionPane.showMessageDialog(null, "El campo " + titulo + " no puede quedar en blanco");
            } else if (t.contains(";")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter ;");
            } else {
                listo = true;
            }
        } while (!listo);
        return t;
    }

    public String pedirLetras(String mensaje, String titulo) {
        String t;
        boolean listo = false;

        do {
            t = pedirTexto(mensaje, titulo);
            if (t == null) {
                listo = true;
            } else if (!soloLetras(t)) {
                JOptionPane.showMessageDialog(null, "El campo " + titulo + " solo puede contener letras y espacios");
            } else {
                listo = true;
            }
        } while (!listo);
        return t;
    }

    public String pedirId(String mensaje, String titulo) {
        String t;
        boolean listo = false;

        do {
            t = pedirTexto(mensaje, titulo);
            if (t == null) {
                listo = true;
            } else if (!soloLetrasYNumeros(t)) {
                JOptionPane.showMessageDialog(null, "El ID solo puede tener letras y números, sin espacios");
            } else {
                listo = true;
            }
        } while (!listo);
        return t;
    }

    public String pedirIdMedicoNuevo(String titulo) {
        String id;
        boolean listo = false;

        do {
            id = pedirId("ID de empleado:", titulo);
            if (id == null) {
                listo = true;
            } else if (buscarMedico(id) != -1) {
                JOptionPane.showMessageDialog(null, "El ID ya está registrado");
            } else {
                listo = true;
            }
        } while (!listo);
        return id;
    }

    public String pedirTelefono() {
        String t;
        boolean listo = false;

        do {
            t = pedirTexto("Teléfono (10 dígitos):", "Teléfono");
            if (t == null) {
                listo = true;
            } else if (t.length() != 10 || !soloNumeros(t)) {
                JOptionPane.showMessageDialog(null, "El teléfono debe tener exactamente 10 dígitos");
            } else {
                listo = true;
            }
        } while (!listo);
        return t;
    }

    public String pedirCorreo() {
        String t;
        boolean listo = false;

        do {
            t = pedirTexto("Correo electrónico:", "Correo");
            if (t == null) {
                listo = true;
            } else if (t.contains(" ")
                    || t.indexOf('@') < 1
                    || t.indexOf('@') != t.lastIndexOf('@')
                    || t.lastIndexOf('.') < t.indexOf('@') + 2
                    || t.lastIndexOf('.') == t.length() - 1) {
                JOptionPane.showMessageDialog(null, "El formato del correo no es válido (ejemplo: nombre@correo.com)");
            } else {
                listo = true;
            }
        } while (!listo);
        return t;
    }

    public String pedirTurno() {
        String t;
        String resultado = null;
        boolean listo = false;

        do {
            t = JOptionPane.showInputDialog(null, "Turno (Matutino/Vespertino/Nocturno):", "Turno",
                    JOptionPane.QUESTION_MESSAGE);
            if (t == null) {
                listo = true;
            } else if (t.equalsIgnoreCase("Matutino")) {
                resultado = "Matutino";
                listo = true;
            } else if (t.equalsIgnoreCase("Vespertino")) {
                resultado = "Vespertino";
                listo = true;
            } else if (t.equalsIgnoreCase("Nocturno")) {
                resultado = "Nocturno";
                listo = true;
            } else {
                JOptionPane.showMessageDialog(null, "El turno debe ser Matutino, Vespertino o Nocturno");
            }
        } while (!listo);
        return resultado;
    }

    public int pedirNumero(String mensaje, String titulo, int min, int max) {
        String t;
        int valor = -1;
        boolean listo = false;

        do {
            t = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
            if (t == null) {
                valor = -1;
                listo = true;
            } else if (t.isBlank()) {
                JOptionPane.showMessageDialog(null, "El campo no puede quedar en blanco");
            } else if (!soloNumeros(t) || t.length() > 9) {
                JOptionPane.showMessageDialog(null, "Escriba solo números enteros (máximo 9 dígitos)");
            } else {
                valor = Integer.parseInt(t);
                if (valor < min || valor > max) {
                    JOptionPane.showMessageDialog(null, "El valor debe estar entre " + min + " y " + max);
                } else {
                    listo = true;
                }
            }
        } while (!listo);
        return valor;
    }

    // =====================================================
    //                       FECHAS
    // =====================================================
    public int numeroHoy() {
        LocalDate hoy = LocalDate.now();
        return hoy.getYear() * 10000 + hoy.getMonthValue() * 100 + hoy.getDayOfMonth();
    }

    public Fecha pedirFecha(String titulo) {
        int d;
        int m;
        int a;
        boolean listo = false;
        Fecha f = null;

        do {
            d = pedirNumero(titulo + "\nDía (1-31):", "Día", 1, 31);
            if (d == -1) {
                listo = true;
            } else {
                m = pedirNumero(titulo + "\nMes (1-12):", "Mes", 1, 12);
                if (m == -1) {
                    listo = true;
                } else {
                    a = pedirNumero(titulo + "\nAño:", "Año", 1900, 2030);
                    if (a == -1) {
                        listo = true;
                    } else {
                        f = new Fecha(d, m, a);
                        if (f.fechaCorrecta()) {
                            listo = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "La fecha " + f + " no existe en el calendario");
                            f = null;
                        }
                    }
                }
            }
        } while (!listo);
        return f;
    }

    public Fecha fechaNacPaciente() {
        int hoy = numeroHoy();
        int anioActual = LocalDate.now().getYear();
        boolean listo = false;
        Fecha f;

        do {
            f = pedirFecha("Fecha de nacimiento del paciente");
            if (f == null) {
                listo = true;
            } else if (numeroFecha(f) > hoy) {
                JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede ser posterior a hoy");
            } else if (f.getAnio() < anioActual - 115) {
                JOptionPane.showMessageDialog(null, "La edad del paciente no puede ser mayor a 115 años");
            } else {
                listo = true;
            }
        } while (!listo);
        return f;
    }

    public Fecha fechaNacMedico() {
        int hoy = numeroHoy();
        int anioActual = LocalDate.now().getYear();
        boolean listo = false;
        Fecha f;

        do {
            f = pedirFecha("Fecha de nacimiento del médico");
            if (f == null) {
                listo = true;
            } else if (numeroFecha(f) > hoy) {
                JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede ser posterior a hoy");
            } else if (anioActual - f.getAnio() < 24 || anioActual - f.getAnio() > 90) {
                JOptionPane.showMessageDialog(null, "La edad del médico debe estar entre 24 y 90 años");
            } else {
                listo = true;
            }
        } while (!listo);
        return f;
    }

    public Fecha fechaContratacion(Fecha nacimiento) {
        int hoy = numeroHoy();
        boolean listo = false;
        Fecha f;

        do {
            f = pedirFecha("Fecha de contratación del médico");
            if (f == null) {
                listo = true;
            } else if (numeroFecha(f) > hoy) {
                JOptionPane.showMessageDialog(null, "La fecha de contratación no puede ser posterior a hoy");
            } else if (numeroFecha(f) < numeroFecha(nacimiento) + 200000) {
                JOptionPane.showMessageDialog(null, "El médico debe tener mínimo 20 años al ser contratado");
            } else {
                listo = true;
            }
        } while (!listo);
        return f;
    }

    public Fecha fechaConsulta() {
        int hoy = numeroHoy();
        boolean listo = false;
        Fecha f;

        do {
            f = pedirFecha("Fecha de la consulta");
            if (f == null) {
                listo = true;
            } else if (numeroFecha(f) > hoy) {
                JOptionPane.showMessageDialog(null, "La fecha de consulta no puede ser posterior a hoy");
            } else if (numeroFecha(f) < hoy - 50000) {
                JOptionPane.showMessageDialog(null, "La fecha de consulta no puede ser de hace más de 5 años");
            } else {
                listo = true;
            }
        } while (!listo);
        return f;
    }

    // =====================================================
    //             ARCHIVOS DE TEXTO (.txt)
    // =====================================================
    // Convierte una fecha a un numero AAAAMMDD para poder compararla
    public int numeroFecha(Fecha f) {
        return f.getAnio() * 10000 + f.getMes() * 100 + f.getDia();
    }

    // Arman la linea de texto que se guarda en cada archivo
    public String lineaMedico(Medico m) {
        String linea;
        General g;
        Especialista e;

        linea = m.getIdEmpleado() + ";" + m.getNombre() + ";" + m.getDireccion() + ";" + m.getTelefono() + ";"
                + m.getFechaNacimientoObj().getDia() + ";" + m.getFechaNacimientoObj().getMes() + ";"
                + m.getFechaNacimientoObj().getAnio() + ";"
                + m.getFechaContratacionObj().getDia() + ";" + m.getFechaContratacionObj().getMes() + ";"
                + m.getFechaContratacionObj().getAnio();

        if (m instanceof General) {
            g = (General) m;
            linea = "G;" + linea + ";" + g.getTurno() + ";" + g.getConsultorio();
        } else {
            e = (Especialista) m;
            linea = "E;" + linea + ";" + e.getEspecialidad() + ";" + e.getArea() + ";" + e.getEnfermera();
        }
        return linea;
    }

    public String lineaPaciente(Paciente p) {
        return p.getNoPaciente() + ";" + p.getNombre() + ";" + p.getDireccion() + ";" + p.getTelefono() + ";"
                + p.getFechaNacimientoObj().getDia() + ";" + p.getFechaNacimientoObj().getMes() + ";"
                + p.getFechaNacimientoObj().getAnio() + ";" + p.getCorreo();
    }

    public String lineaConsulta(Consulta c) {
        return c.getNoConsulta() + ";" + c.getIdEmpleado() + ";" + c.getNoPaciente() + ";"
                + c.getFechaConsultaObj().getDia() + ";" + c.getFechaConsultaObj().getMes() + ";"
                + c.getFechaConsultaObj().getAnio() + ";" + c.getDiagnostico();
    }

    public void guardarMedicos() {
        int i;
        ArrayList<String> lineas = new ArrayList<>();

        for (i = 0; i < listaMedicos.size(); i++) {
            lineas.add(lineaMedico(listaMedicos.get(i)));
        }
        try {
            Files.write(Paths.get("Medicos.txt"), lineas);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Medicos.txt");
        }
    }

    public void guardarPacientes() {
        int i;
        ArrayList<String> lineas = new ArrayList<>();

        for (i = 0; i < listaPacientes.size(); i++) {
            lineas.add(lineaPaciente(listaPacientes.get(i)));
        }
        try {
            Files.write(Paths.get("Pacientes.txt"), lineas);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Pacientes.txt");
        }
    }

    public void guardarConsultas() {
        int i;
        ArrayList<String> lineas = new ArrayList<>();

        for (i = 0; i < listaConsultas.size(); i++) {
            lineas.add(lineaConsulta(listaConsultas.get(i)));
        }
        try {
            Files.write(Paths.get("Consultas.txt"), lineas);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Consultas.txt");
        }
    }

    public void cargarMedicos() {
        int i;
        String[] d;
        List<String> lineas;
        Fecha nac;
        Fecha con;
        General g;
        Especialista e;

        if (Files.exists(Paths.get("Medicos.txt"))) {
            try {
                lineas = Files.readAllLines(Paths.get("Medicos.txt"));
                for (i = 0; i < lineas.size(); i++) {
                    d = lineas.get(i).split(";");
                    nac = new Fecha(Integer.parseInt(d[5]), Integer.parseInt(d[6]), Integer.parseInt(d[7]));
                    con = new Fecha(Integer.parseInt(d[8]), Integer.parseInt(d[9]), Integer.parseInt(d[10]));
                    if (d[0].equals("G")) {
                        g = new General(d[1], d[2], d[3], d[4], nac, con, d[11], Integer.parseInt(d[12]));
                        listaMedicos.add(g);
                    } else {
                        e = new Especialista(d[1], d[2], d[3], d[4], nac, con, d[11], d[12], d[13]);
                        listaMedicos.add(e);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al leer Medicos.txt");
            }
        }
    }

    public void cargarPacientes() {
        int i;
        String[] d;
        List<String> lineas;
        Fecha nac;
        Paciente p;

        if (Files.exists(Paths.get("Pacientes.txt"))) {
            try {
                lineas = Files.readAllLines(Paths.get("Pacientes.txt"));
                for (i = 0; i < lineas.size(); i++) {
                    d = lineas.get(i).split(";");
                    nac = new Fecha(Integer.parseInt(d[4]), Integer.parseInt(d[5]), Integer.parseInt(d[6]));
                    p = new Paciente(Integer.parseInt(d[0]), d[1], d[2], d[3], nac, d[7]);
                    listaPacientes.add(p);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al leer Pacientes.txt");
            }
        }
    }

    public void cargarConsultas() {
        int i;
        String[] d;
        List<String> lineas;
        Fecha fecha;
        Consulta c;

        if (Files.exists(Paths.get("Consultas.txt"))) {
            try {
                lineas = Files.readAllLines(Paths.get("Consultas.txt"));
                for (i = 0; i < lineas.size(); i++) {
                    d = lineas.get(i).split(";");
                    fecha = new Fecha(Integer.parseInt(d[3]), Integer.parseInt(d[4]), Integer.parseInt(d[5]));
                    c = new Consulta(Integer.parseInt(d[0]), d[1], Integer.parseInt(d[2]), fecha, d[6]);
                    listaConsultas.add(c);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al leer Consultas.txt");
            }
        }
    }

    // =====================================================
    public void mostrarCopyright() {
        JOptionPane.showMessageDialog(null,
                "Consultorio Médico v1.0\n\n"
                + "Desarrolladores:\n"
                + "• Aban Miguel Angel\n"
                + "• Cruz Ortega Alexis\n"
                + "• López Licona Victor Hugo\n\n"
                + "© 2026 Todos los derechos reservados.",
                "Copyright", JOptionPane.INFORMATION_MESSAGE);
    }
}
