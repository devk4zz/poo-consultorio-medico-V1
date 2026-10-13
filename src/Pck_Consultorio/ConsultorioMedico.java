/*
 * Desarrolladores:
 * Aban Miguel Angel
 * Cruz Ortega Alexis
 * López Licona Victor Hugo
 */

package Pck_Consultorio;

import java.io.EOFException;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConsultorioMedico {

    public static void main(String[] args) {
        ArrayList<Medico> listaMedicos = cargarMedicos();
        ArrayList<Paciente> listaPacientes = cargarPacientes();
        ArrayList<Consulta> listaConsultas = cargarConsultas();

        String menu;
        int op = 18;
        boolean opValida;

        do {
            opValida = true;
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
                op = 17;
                opValida = true;
            } else if (menu.isBlank()) {
                JOptionPane.showMessageDialog(null, "La opción no puede quedar en blanco", "Error", JOptionPane.WARNING_MESSAGE);
                opValida = false;
            } else {
                try {
                    op = Integer.parseInt(menu.trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Inserte un número entero!", "Error", JOptionPane.ERROR_MESSAGE);
                    opValida = false;
                    op = 18;
                }

                if (opValida && (op < 1 || op > 17)) {
                    JOptionPane.showMessageDialog(null, "Inserte un número entre 1 y 17!", "Error", JOptionPane.ERROR_MESSAGE);
                    opValida = false;
                }

                if (opValida) {
                    switch (op) {
                        case 1: {
                            // Alta de un médico familiar
                            boolean salir = false;
                            String idEmpleado = "";
                            do {
                                idEmpleado = JOptionPane.showInputDialog(null, "ID de empleado del médico familiar:", "Alta de médico familiar", JOptionPane.QUESTION_MESSAGE);
                                if (idEmpleado == null) {
                                    salir = true;
                                    break;
                                }
                                if (idEmpleado.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El ID de empleado no puede quedar en blanco");
                                } else if (!idEmpleado.trim().matches("^[a-zA-Z0-9]+$")) {
                                    JOptionPane.showMessageDialog(null, "El ID no debe contener espacios ni caracteres especiales");
                                } else if (buscarMedico(listaMedicos, idEmpleado.trim()) != -1) {
                                    JOptionPane.showMessageDialog(null, "El ID ya está registrado");
                                } else {
                                    break;
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            String nombre = pedirTextoAlfabetico("Nombre completo del médico:", "Nombre");
                            if (nombre == null) {
                                break;
                            }

                            String direccion = pedirTextoNoVacio("Dirección:", "Dirección");
                            if (direccion == null) {
                                break;
                            }

                            String telefono = pedirTelefono();
                            if (telefono == null) {
                                break;
                            }

                            Fecha fechaNacimiento = pedirFechaNacimientoMedico();
                            if (fechaNacimiento == null) {
                                break;
                            }

                            Fecha fechaContratacion = pedirFechaContratacionMedico(fechaNacimiento);
                            if (fechaContratacion == null) {
                                break;
                            }

                            String turno = pedirTurno();
                            if (turno == null) {
                                break;
                            }

                            Integer consultorio = pedirEnteroPositivo("Número de consultorio:", "Consultorio");
                            if (consultorio == null) {
                                break;
                            }

                            General medicoFamiliar = new General(idEmpleado.trim(), nombre, direccion, telefono,
                                    fechaNacimiento, fechaContratacion, turno, consultorio);
                            listaMedicos.add(medicoFamiliar);
                            guardarMedicos(listaMedicos);
                            JOptionPane.showMessageDialog(null, "Médico familiar registrado exitosamente");
                            break;
                        }

                        case 2: {
                            // Alta de un médico especialista
                            boolean salir = false;
                            String idEmpleado = "";
                            do {
                                idEmpleado = JOptionPane.showInputDialog(null, "ID de empleado del médico especialista:", "Alta de médico especialista", JOptionPane.QUESTION_MESSAGE);
                                if (idEmpleado == null) {
                                    salir = true;
                                    break;
                                }
                                if (idEmpleado.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El ID de empleado no puede quedar en blanco");
                                } else if (!idEmpleado.trim().matches("^[a-zA-Z0-9]+$")) {
                                    JOptionPane.showMessageDialog(null, "El ID no debe contener espacios ni caracteres especiales");
                                } else if (buscarMedico(listaMedicos, idEmpleado.trim()) != -1) {
                                    JOptionPane.showMessageDialog(null, "El ID ya está registrado");
                                } else {
                                    break;
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            String nombre = pedirTextoAlfabetico("Nombre completo del médico:", "Nombre");
                            if (nombre == null) {
                                break;
                            }

                            String direccion = pedirTextoNoVacio("Dirección:", "Dirección");
                            if (direccion == null) {
                                break;
                            }

                            String telefono = pedirTelefono();
                            if (telefono == null) {
                                break;
                            }

                            Fecha fechaNacimiento = pedirFechaNacimientoMedico();
                            if (fechaNacimiento == null) {
                                break;
                            }

                            Fecha fechaContratacion = pedirFechaContratacionMedico(fechaNacimiento);
                            if (fechaContratacion == null) {
                                break;
                            }

                            String especialidad = pedirTextoAlfabetico("Especialidad:", "Especialidad");
                            if (especialidad == null) {
                                break;
                            }

                            String area = pedirTextoAlfabetico("Área:", "Área");
                            if (area == null) {
                                break;
                            }

                            String enfermera = pedirTextoAlfabetico("Nombre de la enfermera(o):", "Enfermera");
                            if (enfermera == null) {
                                break;
                            }

                            Especialista especialista = new Especialista(idEmpleado.trim(), nombre, direccion, telefono,
                                    fechaNacimiento, fechaContratacion, especialidad, area, enfermera);
                            listaMedicos.add(especialista);
                            guardarMedicos(listaMedicos);
                            JOptionPane.showMessageDialog(null, "Médico especialista registrado exitosamente");
                            break;
                        }

                        case 3: {
                            // Alta de un paciente
                            boolean salir = false;
                            int noPaciente = 0;
                            do {
                                String noPacTexto = JOptionPane.showInputDialog(null, "Número de paciente:", "Alta de paciente", JOptionPane.QUESTION_MESSAGE);
                                if (noPacTexto == null) {
                                    salir = true;
                                    break;
                                }
                                if (noPacTexto.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El número de paciente no puede quedar en blanco");
                                } else if (!noPacTexto.trim().matches("[0-9]+")) {
                                    JOptionPane.showMessageDialog(null, "El número de paciente solo puede contener números");
                                } else {
                                    try {
                                        int num = Integer.parseInt(noPacTexto.trim());
                                        if (num <= 0) {
                                            JOptionPane.showMessageDialog(null, "El número de paciente debe ser mayor a cero");
                                        } else if (buscarPaciente(listaPacientes, num) != -1) {
                                            JOptionPane.showMessageDialog(null, "El número de paciente ya está registrado");
                                        } else {
                                            noPaciente = num;
                                            break;
                                        }
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "El número de paciente es demasiado grande");
                                    }
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            String nombre = pedirTextoAlfabetico("Nombre completo del paciente:", "Nombre");
                            if (nombre == null) {
                                break;
                            }

                            String direccion = pedirTextoNoVacio("Dirección:", "Dirección");
                            if (direccion == null) {
                                break;
                            }

                            String telefono = pedirTelefono();
                            if (telefono == null) {
                                break;
                            }

                            Fecha fechaNacimiento = pedirFechaNacimientoPaciente();
                            if (fechaNacimiento == null) {
                                break;
                            }

                            String correo = pedirCorreo();
                            if (correo == null) {
                                break;
                            }

                            Paciente paciente = new Paciente(noPaciente, nombre, direccion, telefono, fechaNacimiento, correo);
                            listaPacientes.add(paciente);
                            guardarPacientes(listaPacientes);
                            JOptionPane.showMessageDialog(null, "Paciente registrado exitosamente");
                            break;
                        }

                        case 4: {
                            // Alta de una consulta
                            boolean salir = false;
                            int noConsulta = 0;
                            do {
                                String noConTexto = JOptionPane.showInputDialog(null, "Número de consulta:", "Alta de consulta", JOptionPane.QUESTION_MESSAGE);
                                if (noConTexto == null) {
                                    salir = true;
                                    break;
                                }
                                if (noConTexto.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El número de consulta no puede quedar en blanco");
                                } else if (!noConTexto.trim().matches("[0-9]+")) {
                                    JOptionPane.showMessageDialog(null, "El número de consulta solo puede contener números");
                                } else {
                                    try {
                                        int num = Integer.parseInt(noConTexto.trim());
                                        if (num <= 0) {
                                            JOptionPane.showMessageDialog(null, "El número de consulta debe ser mayor a cero");
                                        } else if (buscarConsulta(listaConsultas, num) != -1) {
                                            JOptionPane.showMessageDialog(null, "El número de consulta ya está registrado");
                                        } else {
                                            noConsulta = num;
                                            break;
                                        }
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "El número de consulta es demasiado grande");
                                    }
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            String idEmpleado = "";
                            do {
                                idEmpleado = JOptionPane.showInputDialog(null, "ID de empleado del médico asignado:", "Médico de consulta", JOptionPane.QUESTION_MESSAGE);
                                if (idEmpleado == null) {
                                    salir = true;
                                    break;
                                }
                                if (idEmpleado.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El ID de empleado no puede quedar en blanco");
                                } else if (!idEmpleado.trim().matches("^[a-zA-Z0-9]+$")) {
                                    JOptionPane.showMessageDialog(null, "El ID no debe contener espacios ni caracteres especiales");
                                } else {
                                    int posMedico = buscarMedico(listaMedicos, idEmpleado.trim());
                                    if (posMedico == -1) {
                                        JOptionPane.showMessageDialog(null, "El médico con ID " + idEmpleado.trim() + " no está registrado en el catálogo");
                                    } else {
                                        idEmpleado = idEmpleado.trim();
                                        break;
                                    }
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            int noPaciente = 0;
                            do {
                                String noPacTexto = JOptionPane.showInputDialog(null, "Número de paciente:", "Paciente de consulta", JOptionPane.QUESTION_MESSAGE);
                                if (noPacTexto == null) {
                                    salir = true;
                                    break;
                                }
                                if (noPacTexto.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El número de paciente no puede quedar en blanco");
                                } else if (!noPacTexto.trim().matches("[0-9]+")) {
                                    JOptionPane.showMessageDialog(null, "El número de paciente solo puede contener números");
                                } else {
                                    try {
                                        int num = Integer.parseInt(noPacTexto.trim());
                                        int posPaciente = buscarPaciente(listaPacientes, num);
                                        if (posPaciente == -1) {
                                            JOptionPane.showMessageDialog(null, "El paciente con número " + num + " no está registrado en el catálogo");
                                        } else {
                                            noPaciente = num;
                                            break;
                                        }
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "El número de paciente es demasiado grande");
                                    }
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            Fecha fechaConsulta = pedirFechaConsulta();
                            if (fechaConsulta == null) {
                                break;
                            }

                            String diagnostico = pedirTextoNoVacio("Diagnóstico:", "Diagnóstico");
                            if (diagnostico == null) {
                                break;
                            }

                            Consulta consulta = new Consulta(noConsulta, idEmpleado, noPaciente, fechaConsulta, diagnostico);
                            listaConsultas.add(consulta);
                            guardarConsultas(listaConsultas);
                            JOptionPane.showMessageDialog(null, "Consulta registrada exitosamente");
                            break;
                        }

                        case 5: {
                            // Listar médicos familiares
                            DefaultTableModel modelo = new DefaultTableModel();
                            modelo.addColumn("Id Empleado");
                            modelo.addColumn("Nombre");
                            modelo.addColumn("Turno");
                            modelo.addColumn("Consultorio");
                            modelo.addColumn("Fecha de Contratación");

                            System.out.println("\nMÉDICOS FAMILIARES");
                            System.out.printf("%-14s %-28s %-14s %-14s %-20s\n",
                                    "Id Empleado", "Nombre", "Turno", "Consultorio", "Fecha de Contratación");
                            System.out.println("------------------------------------------------------------------------------------------------");

                            boolean hayRegistros = false;
                            for (int i = 0; i < listaMedicos.size(); i++) {
                                Medico m = listaMedicos.get(i);
                                if (m instanceof General) {
                                    General g = (General) m;
                                    modelo.addRow(new Object[]{g.getIdEmpleado(), g.getNombre(), g.getTurno(), g.getConsultorio(), g.getFechaContratacion()});
                                    System.out.printf("%-14s %-28s %-14s %-14d %-20s\n",
                                            g.getIdEmpleado(), g.getNombre(), g.getTurno(), g.getConsultorio(), g.getFechaContratacion());
                                    hayRegistros = true;
                                }
                            }

                            if (hayRegistros) {
                                JTable tabla = new JTable(modelo);
                                JScrollPane scroll = new JScrollPane(tabla);
                                tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                                scroll.setPreferredSize(new java.awt.Dimension(800, 300));
                                JOptionPane.showMessageDialog(null, scroll, "MÉDICOS FAMILIARES", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                System.out.println("No hay médicos familiares registrados.");
                                JOptionPane.showMessageDialog(null, "No hay médicos familiares registrados");
                            }
                            break;
                        }

                        case 6: {
                            // Listar médicos especialistas
                            DefaultTableModel modelo = new DefaultTableModel();
                            modelo.addColumn("Id Empleado");
                            modelo.addColumn("Nombre");
                            modelo.addColumn("Especialidad");
                            modelo.addColumn("Área");
                            modelo.addColumn("Fecha de Contratación");

                            System.out.println("\nMÉDICOS ESPECIALISTAS");
                            System.out.printf("%-14s %-28s %-20s %-16s %-20s\n",
                                    "Id Empleado", "Nombre", "Especialidad", "Área", "Fecha de Contratación");
                            System.out.println("-----------------------------------------------------------------------------------------------");

                            boolean hayRegistros = false;
                            for (int i = 0; i < listaMedicos.size(); i++) {
                                Medico m = listaMedicos.get(i);
                                if (m instanceof Especialista) {
                                    Especialista e = (Especialista) m;
                                    modelo.addRow(new Object[]{e.getIdEmpleado(), e.getNombre(), e.getEspecialidad(), e.getArea(), e.getFechaContratacion()});
                                    System.out.printf("%-14s %-28s %-20s %-16s %-20s\n",
                                            e.getIdEmpleado(), e.getNombre(), e.getEspecialidad(), e.getArea(), e.getFechaContratacion());
                                    hayRegistros = true;
                                }
                            }

                            if (hayRegistros) {
                                JTable tabla = new JTable(modelo);
                                JScrollPane scroll = new JScrollPane(tabla);
                                tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                                scroll.setPreferredSize(new java.awt.Dimension(800, 300));
                                JOptionPane.showMessageDialog(null, scroll, "MÉDICOS ESPECIALISTAS", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                System.out.println("No hay médicos especialistas registrados.");
                                JOptionPane.showMessageDialog(null, "No hay médicos especialistas registrados");
                            }
                            break;
                        }

                        case 7: {
                            // Listar pacientes
                            DefaultTableModel modelo = new DefaultTableModel();
                            modelo.addColumn("No.Paciente");
                            modelo.addColumn("Nombre");
                            modelo.addColumn("Fecha de Nacimiento");
                            modelo.addColumn("Correo");

                            System.out.println("\nLISTADO DE PACIENTES");
                            System.out.printf("%-14s %-28s %-22s %-28s\n",
                                    "No.Paciente", "Nombre", "Fecha de Nacimiento", "Correo");
                            System.out.println("--------------------------------------------------------------------------------------------");

                            if (listaPacientes.isEmpty()) {
                                System.out.println("No hay pacientes registrados.");
                                JOptionPane.showMessageDialog(null, "No hay pacientes registrados");
                                break;
                            }

                            for (int i = 0; i < listaPacientes.size(); i++) {
                                Paciente p = listaPacientes.get(i);
                                modelo.addRow(new Object[]{p.getNoPaciente(), p.getNombre(), p.getFechaNacimiento(), p.getCorreo()});
                                System.out.printf("%-14d %-28s %-22s %-28s\n",
                                    p.getNoPaciente(), p.getNombre(), p.getFechaNacimiento(), p.getCorreo());
                            }

                            JTable tabla = new JTable(modelo);
                            JScrollPane scroll = new JScrollPane(tabla);
                            tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                            scroll.setPreferredSize(new java.awt.Dimension(800, 300));
                            JOptionPane.showMessageDialog(null, scroll, "LISTADO DE PACIENTES", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }

                        case 8: {
                            // Listar consultas
                            DefaultTableModel modelo = new DefaultTableModel();
                            modelo.addColumn("No.Consulta");
                            modelo.addColumn("Id Empleado");
                            modelo.addColumn("No.Paciente");
                            modelo.addColumn("Fecha de Consulta");

                            System.out.println("\nLISTADO DE CONSULTAS");
                            System.out.printf("%-14s %-16s %-14s %-20s\n",
                                    "No.Consulta", "Id Empleado", "No.Paciente", "Fecha de Consulta");
                            System.out.println("-------------------------------------------------------------------------------------------");

                            if (listaConsultas.isEmpty()) {
                                System.out.println("No hay consultas registradas.");
                                JOptionPane.showMessageDialog(null, "No hay consultas registradas");
                                break;
                            }

                            for (int i = 0; i < listaConsultas.size(); i++) {
                                Consulta c = listaConsultas.get(i);
                                modelo.addRow(new Object[]{c.getNoConsulta(), c.getIdEmpleado(), c.getNoPaciente(), c.getFechaConsulta()});
                                System.out.printf("%-14d %-16s %-14d %-20s\n",
                                        c.getNoConsulta(), c.getIdEmpleado(), c.getNoPaciente(), c.getFechaConsulta());
                            }

                            JTable tabla = new JTable(modelo);
                            JScrollPane scroll = new JScrollPane(tabla);
                            tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                            scroll.setPreferredSize(new java.awt.Dimension(800, 300));
                            JOptionPane.showMessageDialog(null, scroll, "LISTADO DE CONSULTAS", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }

                        case 9: {
                            // Ver detalle de un médico familiar
                            String idConsultaTexto;
                            boolean salir = false;
                            do {
                                idConsultaTexto = JOptionPane.showInputDialog(null, "Ingrese el Id Empleado del médico a consultar:", "Consultar médico familiar", JOptionPane.QUESTION_MESSAGE);
                                if (idConsultaTexto == null) {
                                    salir = true;
                                    break;
                                }
                                if (idConsultaTexto.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El Id Empleado no puede quedar en blanco");
                                } else if (!idConsultaTexto.trim().matches("^[a-zA-Z0-9]+$")) {
                                    JOptionPane.showMessageDialog(null, "El ID no debe contener espacios ni caracteres especiales");
                                } else {
                                    break;
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            int pos = buscarMedico(listaMedicos, idConsultaTexto.trim());
                            if (pos == -1) {
                                JOptionPane.showMessageDialog(null, "El médico con ID " + idConsultaTexto.trim() + " no existe");
                                break;
                            }

                            Medico m = listaMedicos.get(pos);
                            if (!(m instanceof General)) {
                                JOptionPane.showMessageDialog(null, "El médico con ID " + idConsultaTexto.trim() + " no es un médico familiar");
                                break;
                            }

                            General g = (General) m;
                            String detalle = "DETALLE DE MÉDICO FAMILIAR\n"
                                    + "Id Empleado: " + g.getIdEmpleado() + "\n"
                                    + "Nombre: " + g.getNombre() + "\n"
                                    + "Dirección: " + g.getDireccion() + "\n"
                                    + "Teléfono: " + g.getTelefono() + "\n"
                                    + "Fecha de nacimiento: " + g.getFechaNacimiento() + "\n"
                                    + "Fecha de contratación: " + g.getFechaContratacion() + "\n"
                                    + "Turno: " + g.getTurno() + "\n"
                                    + "Consultorio: " + g.getConsultorio();

                            System.out.println("\n" + detalle);
                            JOptionPane.showMessageDialog(null, detalle, "Detalle de médico familiar", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }

                        case 10: {
                            // Ver detalle de un médico especialista
                            String idConsultaTexto;
                            boolean salir = false;
                            do {
                                idConsultaTexto = JOptionPane.showInputDialog(null, "Ingrese el Id Empleado del médico a consultar:", "Consultar médico especialista", JOptionPane.QUESTION_MESSAGE);
                                if (idConsultaTexto == null) {
                                    salir = true;
                                    break;
                                }
                                if (idConsultaTexto.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El Id Empleado no puede quedar en blanco");
                                } else if (!idConsultaTexto.trim().matches("^[a-zA-Z0-9]+$")) {
                                    JOptionPane.showMessageDialog(null, "El ID no debe contener espacios ni caracteres especiales");
                                } else {
                                    break;
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            int pos = buscarMedico(listaMedicos, idConsultaTexto.trim());
                            if (pos == -1) {
                                JOptionPane.showMessageDialog(null, "El médico con ID " + idConsultaTexto.trim() + " no existe");
                                break;
                            }

                            Medico m = listaMedicos.get(pos);
                            if (!(m instanceof Especialista)) {
                                JOptionPane.showMessageDialog(null, "El médico con ID " + idConsultaTexto.trim() + " no es un médico especialista");
                                break;
                            }

                            Especialista esp = (Especialista) m;
                            String detalle = "DETALLE DE MÉDICO ESPECIALISTA\n"
                                    + "Id Empleado: " + esp.getIdEmpleado() + "\n"
                                    + "Nombre: " + esp.getNombre() + "\n"
                                    + "Dirección: " + esp.getDireccion() + "\n"
                                    + "Teléfono: " + esp.getTelefono() + "\n"
                                    + "Fecha de nacimiento: " + esp.getFechaNacimiento() + "\n"
                                    + "Fecha de contratación: " + esp.getFechaContratacion() + "\n"
                                    + "Especialidad: " + esp.getEspecialidad() + "\n"
                                    + "Área: " + esp.getArea() + "\n"
                                    + "Enfermera(o): " + esp.getEnfermera();

                            System.out.println("\n" + detalle);
                            JOptionPane.showMessageDialog(null, detalle, "Detalle de médico especialista", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }

                        case 11: {
                            // Ver detalle de un paciente
                            String noPacTexto;
                            boolean salir = false;
                            do {
                                noPacTexto = JOptionPane.showInputDialog(null, "Ingrese el No.Paciente a consultar:", "Consultar paciente", JOptionPane.QUESTION_MESSAGE);
                                if (noPacTexto == null) {
                                    salir = true;
                                    break;
                                }
                                if (noPacTexto.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El No.Paciente no puede quedar en blanco");
                                } else if (!noPacTexto.trim().matches("[0-9]+")) {
                                    JOptionPane.showMessageDialog(null, "El No.Paciente solo puede contener números");
                                } else {
                                    break;
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            int noPaciente = Integer.parseInt(noPacTexto.trim());
                            int pos = buscarPaciente(listaPacientes, noPaciente);
                            if (pos == -1) {
                                JOptionPane.showMessageDialog(null, "El paciente con número " + noPaciente + " no existe");
                                break;
                            }

                            Paciente p = listaPacientes.get(pos);
                            String detalle = "DETALLE DE PACIENTE\n"
                                    + "No. Paciente: " + p.getNoPaciente() + "\n"
                                    + "Nombre: " + p.getNombre() + "\n"
                                    + "Dirección: " + p.getDireccion() + "\n"
                                    + "Teléfono: " + p.getTelefono() + "\n"
                                    + "Fecha de nacimiento: " + p.getFechaNacimiento() + "\n"
                                    + "Correo: " + p.getCorreo();

                            System.out.println("\n" + detalle);
                            JOptionPane.showMessageDialog(null, detalle, "Detalle de paciente", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }

                        case 12: {
                            // Ver detalle de una consulta
                            String noConTexto;
                            boolean salir = false;
                            do {
                                noConTexto = JOptionPane.showInputDialog(null, "Ingrese el No. de consulta que desea consultar:", "Consultar consulta", JOptionPane.QUESTION_MESSAGE);
                                if (noConTexto == null) {
                                    salir = true;
                                    break;
                                }
                                if (noConTexto.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El número de consulta no puede quedar en blanco");
                                } else if (!noConTexto.trim().matches("[0-9]+")) {
                                    JOptionPane.showMessageDialog(null, "El número de consulta solo puede contener números");
                                } else {
                                    break;
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            int noConsulta = Integer.parseInt(noConTexto.trim());
                            int posConsulta = buscarConsulta(listaConsultas, noConsulta);

                            if (posConsulta == -1) {
                                JOptionPane.showMessageDialog(null, "La consulta número " + noConsulta + " no existe");
                                break;
                            }

                            Consulta c = listaConsultas.get(posConsulta);
                            int posMedico = buscarMedico(listaMedicos, c.getIdEmpleado());
                            int posPaciente = buscarPaciente(listaPacientes, c.getNoPaciente());

                            StringBuilder sb = new StringBuilder();
                            sb.append("DETALLE DE UNA CONSULTA\n");
                            sb.append("No. Consulta: ").append(c.getNoConsulta()).append("\n");

                            if (posMedico != -1) {
                                Medico m = listaMedicos.get(posMedico);
                                if (m instanceof Especialista) {
                                    Especialista esp = (Especialista) m;
                                    sb.append("MÉDICO ESPECIALISTA\n");
                                    sb.append("Id Empleado: ").append(esp.getIdEmpleado()).append("\n");
                                    sb.append("Nombre: ").append(esp.getNombre()).append("\n");
                                    sb.append("Dirección: ").append(esp.getDireccion()).append("\n");
                                    sb.append("Teléfono: ").append(esp.getTelefono()).append("\n");
                                    sb.append("Fecha de nacimiento: ").append(esp.getFechaNacimiento()).append("\n");
                                    sb.append("Fecha de contratación: ").append(esp.getFechaContratacion()).append("\n");
                                    sb.append("Especialidad: ").append(esp.getEspecialidad()).append("\n");
                                    sb.append("Área: ").append(esp.getArea()).append("\n");
                                    sb.append("Enfermera(o): ").append(esp.getEnfermera()).append("\n");
                                } else if (m instanceof General) {
                                    General g = (General) m;
                                    sb.append("MÉDICO FAMILIAR\n");
                                    sb.append("Id Empleado: ").append(g.getIdEmpleado()).append("\n");
                                    sb.append("Nombre: ").append(g.getNombre()).append("\n");
                                    sb.append("Dirección: ").append(g.getDireccion()).append("\n");
                                    sb.append("Teléfono: ").append(g.getTelefono()).append("\n");
                                    sb.append("Fecha de nacimiento: ").append(g.getFechaNacimiento()).append("\n");
                                    sb.append("Fecha de contratación: ").append(g.getFechaContratacion()).append("\n");
                                    sb.append("Turno: ").append(g.getTurno()).append("\n");
                                    sb.append("Consultorio: ").append(g.getConsultorio()).append("\n");
                                }
                            } else {
                                sb.append("MÉDICO: Información no disponible (Id Empleado: ").append(c.getIdEmpleado()).append(")\n");
                            }

                            if (posPaciente != -1) {
                                Paciente p = listaPacientes.get(posPaciente);
                                sb.append("PACIENTE\n");
                                sb.append("No. Paciente: ").append(p.getNoPaciente()).append("\n");
                                sb.append("Nombre: ").append(p.getNombre()).append("\n");
                                sb.append("Dirección: ").append(p.getDireccion()).append("\n");
                                sb.append("Teléfono: ").append(p.getTelefono()).append("\n");
                                sb.append("Fecha de nacimiento: ").append(p.getFechaNacimiento()).append("\n");
                                sb.append("Correo: ").append(p.getCorreo()).append("\n");
                            } else {
                                sb.append("PACIENTE: Información no disponible (No. Paciente: ").append(c.getNoPaciente()).append(")\n");
                            }

                            sb.append("Fecha de consulta: ").append(c.getFechaConsulta()).append("\n");
                            sb.append("Diagnóstico: ").append(c.getDiagnostico());

                            System.out.println("\n" + sb.toString());
                            JOptionPane.showMessageDialog(null, sb.toString(), "Detalle de consulta", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }

                        case 13: {
                            // Eliminar un médico familiar
                            String idTexto;
                            boolean salir = false;
                            do {
                                idTexto = JOptionPane.showInputDialog(null, "Id Empleado del médico familiar a eliminar:", "Eliminar médico familiar", JOptionPane.QUESTION_MESSAGE);
                                if (idTexto == null) {
                                    salir = true;
                                    break;
                                }
                                if (idTexto.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El Id Empleado no puede quedar en blanco");
                                } else if (!idTexto.trim().matches("^[a-zA-Z0-9]+$")) {
                                    JOptionPane.showMessageDialog(null, "El ID no debe contener espacios ni caracteres especiales");
                                } else {
                                    break;
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            int pos = buscarMedico(listaMedicos, idTexto.trim());
                            if (pos == -1) {
                                JOptionPane.showMessageDialog(null, "El médico con ID " + idTexto.trim() + " no existe");
                                break;
                            }

                            Medico m = listaMedicos.get(pos);
                            if (!(m instanceof General)) {
                                JOptionPane.showMessageDialog(null, "El médico con ID " + idTexto.trim() + " no es médico familiar");
                                break;
                            }

                            int confirmacion = JOptionPane.showConfirmDialog(null,
                                    m.getDatos() + "\n\n¿Está seguro de que desea eliminar a este médico familiar?",
                                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                            if (confirmacion == JOptionPane.YES_OPTION) {
                                listaMedicos.remove(pos);
                                guardarMedicos(listaMedicos);

                                int eliminadas = 0;
                                for (int i = listaConsultas.size() - 1; i >= 0; i--) {
                                    if (listaConsultas.get(i).getIdEmpleado().equalsIgnoreCase(idTexto.trim())) {
                                        listaConsultas.remove(i);
                                        eliminadas++;
                                    }
                                }
                                if (eliminadas > 0) {
                                    guardarConsultas(listaConsultas);
                                    JOptionPane.showMessageDialog(null, "Se eliminaron en cascada " + eliminadas + " consulta(s) asociada(s) a este médico");
                                }
                                JOptionPane.showMessageDialog(null, "Médico familiar eliminado exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "Operación de eliminación cancelada");
                            }
                            break;
                        }

                        case 14: {
                            // Eliminar un médico especialista
                            String idTexto;
                            boolean salir = false;
                            do {
                                idTexto = JOptionPane.showInputDialog(null, "Id Empleado del médico especialista a eliminar:", "Eliminar médico especialista", JOptionPane.QUESTION_MESSAGE);
                                if (idTexto == null) {
                                    salir = true;
                                    break;
                                }
                                if (idTexto.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El Id Empleado no puede quedar en blanco");
                                } else if (!idTexto.trim().matches("^[a-zA-Z0-9]+$")) {
                                    JOptionPane.showMessageDialog(null, "El ID no debe contener espacios ni caracteres especiales");
                                } else {
                                    break;
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            int pos = buscarMedico(listaMedicos, idTexto.trim());
                            if (pos == -1) {
                                JOptionPane.showMessageDialog(null, "El médico con ID " + idTexto.trim() + " no existe");
                                break;
                            }

                            Medico m = listaMedicos.get(pos);
                            if (!(m instanceof Especialista)) {
                                JOptionPane.showMessageDialog(null, "El médico con ID " + idTexto.trim() + " no es médico especialista");
                                break;
                            }

                            int confirmacion = JOptionPane.showConfirmDialog(null,
                                    m.getDatos() + "\n\n¿Está seguro de que desea eliminar a este médico especialista?",
                                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                            if (confirmacion == JOptionPane.YES_OPTION) {
                                listaMedicos.remove(pos);
                                guardarMedicos(listaMedicos);

                                int eliminadas = 0;
                                for (int i = listaConsultas.size() - 1; i >= 0; i--) {
                                    if (listaConsultas.get(i).getIdEmpleado().equalsIgnoreCase(idTexto.trim())) {
                                        listaConsultas.remove(i);
                                        eliminadas++;
                                    }
                                }
                                if (eliminadas > 0) {
                                    guardarConsultas(listaConsultas);
                                    JOptionPane.showMessageDialog(null, "Se eliminaron en cascada " + eliminadas + " consulta(s) asociada(s) a este médico");
                                }
                                JOptionPane.showMessageDialog(null, "Médico especialista eliminado exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "Operación de eliminación cancelada");
                            }
                            break;
                        }

                        case 15: {
                            // Eliminar un paciente
                            String noPacTexto;
                            boolean salir = false;
                            do {
                                noPacTexto = JOptionPane.showInputDialog(null, "No.Paciente a eliminar:", "Eliminar paciente", JOptionPane.QUESTION_MESSAGE);
                                if (noPacTexto == null) {
                                    salir = true;
                                    break;
                                }
                                if (noPacTexto.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El No.Paciente no puede quedar en blanco");
                                } else if (!noPacTexto.trim().matches("[0-9]+")) {
                                    JOptionPane.showMessageDialog(null, "El No.Paciente solo puede contener números");
                                } else {
                                    break;
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            int noPaciente = Integer.parseInt(noPacTexto.trim());
                            int pos = buscarPaciente(listaPacientes, noPaciente);
                            if (pos == -1) {
                                JOptionPane.showMessageDialog(null, "El paciente con número " + noPaciente + " no existe");
                                break;
                            }

                            Paciente p = listaPacientes.get(pos);
                            int confirmacion = JOptionPane.showConfirmDialog(null,
                                    p.getDatos() + "\n\n¿Está seguro de que desea eliminar a este paciente?",
                                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                            if (confirmacion == JOptionPane.YES_OPTION) {
                                listaPacientes.remove(pos);
                                guardarPacientes(listaPacientes);

                                int eliminadas = 0;
                                for (int i = listaConsultas.size() - 1; i >= 0; i--) {
                                    if (listaConsultas.get(i).getNoPaciente() == noPaciente) {
                                        listaConsultas.remove(i);
                                        eliminadas++;
                                    }
                                }
                                if (eliminadas > 0) {
                                    guardarConsultas(listaConsultas);
                                    JOptionPane.showMessageDialog(null, "Se eliminaron en cascada " + eliminadas + " consulta(s) asociada(s) a este paciente");
                                }
                                JOptionPane.showMessageDialog(null, "Paciente eliminado exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "Operación de eliminación cancelada");
                            }
                            break;
                        }

                        case 16: {
                            // Eliminar una consulta
                            String noConTexto;
                            boolean salir = false;
                            do {
                                noConTexto = JOptionPane.showInputDialog(null, "No.Consulta a eliminar:", "Eliminar consulta", JOptionPane.QUESTION_MESSAGE);
                                if (noConTexto == null) {
                                    salir = true;
                                    break;
                                }
                                if (noConTexto.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El No.Consulta no puede quedar en blanco");
                                } else if (!noConTexto.trim().matches("[0-9]+")) {
                                    JOptionPane.showMessageDialog(null, "El No.Consulta solo puede contener números");
                                } else {
                                    break;
                                }
                            } while (true);
                            if (salir) {
                                break;
                            }

                            int noConsulta = Integer.parseInt(noConTexto.trim());
                            int pos = buscarConsulta(listaConsultas, noConsulta);
                            if (pos == -1) {
                                JOptionPane.showMessageDialog(null, "La consulta con número " + noConsulta + " no existe");
                                break;
                            }

                            Consulta c = listaConsultas.get(pos);
                            int confirmacion = JOptionPane.showConfirmDialog(null,
                                    c.getDatos() + "\n\n¿Está seguro de que desea eliminar esta consulta?",
                                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                            if (confirmacion == JOptionPane.YES_OPTION) {
                                listaConsultas.remove(pos);
                                guardarConsultas(listaConsultas);
                                JOptionPane.showMessageDialog(null, "Consulta eliminada exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "Operación de eliminación cancelada");
                            }
                            break;
                        }

                        case 17: {
                            guardarMedicos(listaMedicos);
                            guardarPacientes(listaPacientes);
                            guardarConsultas(listaConsultas);
                            mostrarCopyright();
                            break;
                        }

                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida");
                    }
                }
            }
        } while (op != 17);
    }

    // Funciones de búsqueda exigidas por la rúbrica y UML
    public static int buscarMedico(ArrayList<Medico> lista, String idEmpleado) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdEmpleado().equalsIgnoreCase(idEmpleado)) {
                return i;
            }
        }
        return -1;
    }

    public static int buscarPaciente(ArrayList<Paciente> lista, int noPaciente) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNoPaciente() == noPaciente) {
                return i;
            }
        }
        return -1;
    }

    public static int buscarConsulta(ArrayList<Consulta> lista, int noConsulta) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNoConsulta() == noConsulta) {
                return i;
            }
        }
        return -1;
    }

    // Métodos auxiliares comunes para entradas repetitivas
    public static String pedirTextoNoVacio(String mensaje, String campo) {
        String texto;
        do {
            texto = JOptionPane.showInputDialog(null, mensaje, campo, JOptionPane.QUESTION_MESSAGE);
            if (texto == null) {
                return null;
            }
            if (texto.isBlank()) {
                JOptionPane.showMessageDialog(null, "El campo " + campo + " no puede quedar en blanco");
            } else if (texto.matches(".*[&%\\$¡¿|°].*")) {
                JOptionPane.showMessageDialog(null, "No se permite el ingreso de caracteres especiales (&, %, $, ¡, ¿, |, °)");
            } else {
                return texto.trim();
            }
        } while (true);
    }

    public static String pedirTextoAlfabetico(String mensaje, String campo) {
        String texto;
        do {
            texto = JOptionPane.showInputDialog(null, mensaje, campo, JOptionPane.QUESTION_MESSAGE);
            if (texto == null) {
                return null;
            }
            if (texto.isBlank()) {
                JOptionPane.showMessageDialog(null, "El campo " + campo + " no puede quedar en blanco");
            } else if (!texto.trim().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                JOptionPane.showMessageDialog(null, "El campo " + campo + " solo puede contener letras y espacios");
            } else {
                return texto.trim();
            }
        } while (true);
    }

    public static String pedirTelefono() {
        String telefono;
        do {
            telefono = JOptionPane.showInputDialog(null, "Teléfono (10 dígitos):", "Teléfono", JOptionPane.QUESTION_MESSAGE);
            if (telefono == null) {
                return null;
            }
            if (telefono.isBlank()) {
                JOptionPane.showMessageDialog(null, "El teléfono no puede quedar en blanco");
            } else if (!telefono.trim().matches("[0-9]{10}")) {
                JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 10 dígitos numéricos");
            } else {
                return telefono.trim();
            }
        } while (true);
    }

    public static String pedirCorreo() {
        String correo;
        do {
            correo = JOptionPane.showInputDialog(null, "Correo electrónico:", "Correo", JOptionPane.QUESTION_MESSAGE);
            if (correo == null) {
                return null;
            }
            if (correo.isBlank()) {
                JOptionPane.showMessageDialog(null, "El correo no puede quedar en blanco");
            } else if (!correo.trim().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                JOptionPane.showMessageDialog(null, "El formato de correo electrónico no es válido");
            } else {
                return correo.trim();
            }
        } while (true);
    }

    public static String pedirTurno() {
        String turno;
        do {
            turno = JOptionPane.showInputDialog(null, "Turno (Matutino/Vespertino/Nocturno):", "Turno", JOptionPane.QUESTION_MESSAGE);
            if (turno == null) {
                return null;
            }
            if (turno.isBlank()) {
                JOptionPane.showMessageDialog(null, "El turno no puede quedar en blanco");
            } else if (!turno.trim().equalsIgnoreCase("Matutino")
                    && !turno.trim().equalsIgnoreCase("Vespertino")
                    && !turno.trim().equalsIgnoreCase("Nocturno")) {
                JOptionPane.showMessageDialog(null, "El turno debe ser Matutino, Vespertino o Nocturno");
            } else {
                String t = turno.trim();
                return t.substring(0, 1).toUpperCase() + t.substring(1).toLowerCase();
            }
        } while (true);
    }

    public static Integer pedirEnteroPositivo(String mensaje, String titulo) {
        String texto;
        do {
            texto = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
            if (texto == null) {
                return null;
            }
            if (texto.isBlank()) {
                JOptionPane.showMessageDialog(null, "El campo no puede quedar en blanco");
            } else if (!texto.trim().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "Solo puede contener números enteros");
            } else {
                try {
                    int valor = Integer.parseInt(texto.trim());
                    if (valor <= 0) {
                        JOptionPane.showMessageDialog(null, "El número debe ser mayor a cero");
                    } else {
                        return valor;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El número ingresado es demasiado grande");
                }
            }
        } while (true);
    }

    public static Integer pedirEnteroRango(String mensaje, String titulo, int min, int max) {
        String texto;
        do {
            texto = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
            if (texto == null) {
                return null;
            }
            if (texto.isBlank()) {
                JOptionPane.showMessageDialog(null, "El campo no puede quedar en blanco");
            } else if (!texto.trim().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "Solo puede contener números");
            } else {
                try {
                    int valor = Integer.parseInt(texto.trim());
                    if (valor < min || valor > max) {
                        JOptionPane.showMessageDialog(null, "El valor debe estar entre " + min + " y " + max);
                    } else {
                        return valor;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El valor debe estar entre " + min + " y " + max);
                }
            }
        } while (true);
    }

    public static Fecha pedirFechaCalendario(String titulo) {
        do {
            Integer dia = pedirEnteroRango(titulo + "\nDía (1-31):", "Día", 1, 31);
            if (dia == null) {
                return null;
            }
            Integer mes = pedirEnteroRango(titulo + "\nMes (1-12):", "Mes", 1, 12);
            if (mes == null) {
                return null;
            }
            Integer anio = pedirEnteroRango(titulo + "\nAño:", "Año", 1900, 2030);
            if (anio == null) {
                return null;
            }
            Fecha f = new Fecha(dia, mes, anio);
            if (!f.fechaCorrecta()) {
                JOptionPane.showMessageDialog(null, "La fecha ingresada (" + dia + "/" + mes + "/" + anio + ") no es válida en el calendario");
            } else {
                return f;
            }
        } while (true);
    }

    public static Fecha pedirFechaNacimientoPaciente() {
        LocalDate hoy = LocalDate.now();
        int anioActual = hoy.getYear();
        do {
            Fecha f = pedirFechaCalendario("Fecha de nacimiento del paciente");
            if (f == null) {
                return null;
            }
            LocalDate fechaIngresada = LocalDate.of(f.getAnio(), f.getMes(), f.getDia());
            if (fechaIngresada.isAfter(hoy)) {
                JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede ser posterior a la fecha actual");
            } else if (f.getAnio() < anioActual - 115) {
                JOptionPane.showMessageDialog(null, "La edad del paciente no puede ser mayor a 115 años");
            } else {
                return f;
            }
        } while (true);
    }

    public static Fecha pedirFechaNacimientoMedico() {
        LocalDate hoy = LocalDate.now();
        int anioActual = hoy.getYear();
        do {
            Fecha f = pedirFechaCalendario("Fecha de nacimiento del médico");
            if (f == null) {
                return null;
            }
            LocalDate fechaIngresada = LocalDate.of(f.getAnio(), f.getMes(), f.getDia());
            if (fechaIngresada.isAfter(hoy)) {
                JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede ser posterior a la fecha actual");
            } else if (anioActual - f.getAnio() < 24 || anioActual - f.getAnio() > 90) {
                JOptionPane.showMessageDialog(null, "La edad del médico debe ser válida para laborar (entre 24 y 90 años)");
            } else {
                return f;
            }
        } while (true);
    }

    public static Fecha pedirFechaContratacionMedico(Fecha fechaNacimiento) {
        LocalDate hoy = LocalDate.now();
        do {
            Fecha f = pedirFechaCalendario("Fecha de contratación del médico");
            if (f == null) {
                return null;
            }
            LocalDate fechaIngresada = LocalDate.of(f.getAnio(), f.getMes(), f.getDia());
            LocalDate fechaNac = LocalDate.of(fechaNacimiento.getAnio(), fechaNacimiento.getMes(), fechaNacimiento.getDia());
            if (fechaIngresada.isAfter(hoy)) {
                JOptionPane.showMessageDialog(null, "La fecha de contratación no puede ser posterior a la actual");
            } else if (fechaIngresada.isBefore(fechaNac.plusYears(20))) {
                JOptionPane.showMessageDialog(null, "La contratación debe ser posterior al nacimiento (mínimo 20 años de edad)");
            } else {
                return f;
            }
        } while (true);
    }

    public static Fecha pedirFechaConsulta() {
        LocalDate hoy = LocalDate.now();
        do {
            Fecha f = pedirFechaCalendario("Fecha de la consulta");
            if (f == null) {
                return null;
            }
            LocalDate fechaIngresada = LocalDate.of(f.getAnio(), f.getMes(), f.getDia());
            if (fechaIngresada.isAfter(hoy)) {
                JOptionPane.showMessageDialog(null, "La fecha de consulta no puede ser posterior a la fecha actual");
            } else if (fechaIngresada.isBefore(hoy.minusYears(5))) {
                JOptionPane.showMessageDialog(null, "La fecha de consulta no puede ser tan anterior (máximo 5 años)");
            } else {
                return f;
            }
        } while (true);
    }

    // Persistencia de objetos en archivos .dat
    public static ArrayList<Medico> cargarMedicos() {
        ArrayList<Medico> lista = new ArrayList<>();
        FileInputStream archivo = null;
        try {
            archivo = new FileInputStream("Medicos.dat");
            ObjectInputStream entrada = new ObjectInputStream(archivo);
            while (true) {
                Medico m = (Medico) entrada.readObject();
                lista.add(m);
            }
        } catch (EOFException ex) {
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error E/S al leer médicos");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró la clase Medico");
        } finally {
            try {
                if (archivo != null) {
                    archivo.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar archivo");
            }
        }
        return lista;
    }

    public static void guardarMedicos(ArrayList<Medico> lista) {
        FileOutputStream archivo = null;
        try {
            archivo = new FileOutputStream("Medicos.dat");
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            for (int i = 0; i < lista.size(); i++) {
                salida.writeObject(lista.get(i));
            }
            salida.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error E/S al guardar médicos");
        } finally {
            try {
                if (archivo != null) {
                    archivo.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar archivo");
            }
        }
    }

    public static ArrayList<Paciente> cargarPacientes() {
        ArrayList<Paciente> lista = new ArrayList<>();
        FileInputStream archivo = null;
        try {
            archivo = new FileInputStream("Pacientes.dat");
            ObjectInputStream entrada = new ObjectInputStream(archivo);
            while (true) {
                Paciente p = (Paciente) entrada.readObject();
                lista.add(p);
            }
        } catch (EOFException ex) {
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error E/S al leer pacientes");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró la clase Paciente");
        } finally {
            try {
                if (archivo != null) {
                    archivo.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar archivo");
            }
        }
        return lista;
    }

    public static void guardarPacientes(ArrayList<Paciente> lista) {
        FileOutputStream archivo = null;
        try {
            archivo = new FileOutputStream("Pacientes.dat");
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            for (int i = 0; i < lista.size(); i++) {
                salida.writeObject(lista.get(i));
            }
            salida.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error E/S al guardar pacientes");
        } finally {
            try {
                if (archivo != null) {
                    archivo.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar archivo");
            }
        }
    }

    public static ArrayList<Consulta> cargarConsultas() {
        ArrayList<Consulta> lista = new ArrayList<>();
        FileInputStream archivo = null;
        try {
            archivo = new FileInputStream("Consultas.dat");
            ObjectInputStream entrada = new ObjectInputStream(archivo);
            while (true) {
                Consulta c = (Consulta) entrada.readObject();
                lista.add(c);
            }
        } catch (EOFException ex) {
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error E/S al leer consultas");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró la clase Consulta");
        } finally {
            try {
                if (archivo != null) {
                    archivo.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar archivo");
            }
        }
        return lista;
    }

    public static void guardarConsultas(ArrayList<Consulta> lista) {
        FileOutputStream archivo = null;
        try {
            archivo = new FileOutputStream("Consultas.dat");
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            for (int i = 0; i < lista.size(); i++) {
                salida.writeObject(lista.get(i));
            }
            salida.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error E/S al guardar consultas");
        } finally {
            try {
                if (archivo != null) {
                    archivo.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar archivo");
            }
        }
    }

    public static void mostrarCopyright() {
        JOptionPane.showMessageDialog(null,
                "Consultorio Médico v1.0\n\n"
                + "Desarrolladores:\n"
                + "• Aban Miguel Angel\n"
                + "• Cruz Ortega Alexis\n"
                + "• López Licona Victor Hugo\n\n"
                + "© 2026 Todos los derechos reservados.",
                "Copyright",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
