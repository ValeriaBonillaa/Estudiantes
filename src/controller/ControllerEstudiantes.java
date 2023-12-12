/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Estudiantes;
import modelo.EstudiantesDAO;
import vista.frmCalificaciones;

/**
 *
 * @author lgfon
 */
public class ControllerEstudiantes implements ActionListener {

    frmCalificaciones vistaEstudiante = new frmCalificaciones();
    Estudiantes e = new Estudiantes();
    EstudiantesDAO dao = new EstudiantesDAO();

    public ControllerEstudiantes(frmCalificaciones frm) {
        this.vistaEstudiante = frm;
        this.vistaEstudiante.btnGuardar.addActionListener(this);
        this.vistaEstudiante.btnBuscar.addActionListener(this);
        this.vistaEstudiante.btnRefrescar.addActionListener(this);
        this.vistaEstudiante.btnModificar.addActionListener(this);
        this.vistaEstudiante.btnEliminar.addActionListener(this);
        this.vistaEstudiante.btnCancelar.addActionListener(this);
        this.vistaEstudiante.btnCalcular.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaEstudiante.btnGuardar) {

            if (vistaEstudiante.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaEstudiante, "Debe ingresar un nombre");
            } else if (vistaEstudiante.jsEdad.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaEstudiante, "Debe ingresar una edad");
            } else if (vistaEstudiante.jsNotaI.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaEstudiante, "Debe ingresar una primer nota");
            } else if (vistaEstudiante.jsNotaII.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaEstudiante, "Debe ingresar una segunda nota");
            } else if (vistaEstudiante.jsNotaIII.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaEstudiante, "Debe ingresar una tercera nota");
            } else {
                agregarEstudiantes();
            }
        }
        if (e.getSource() == vistaEstudiante.btnBuscar) {
            filtrarTablaPorNombre(vistaEstudiante.tblDatos, vistaEstudiante.txtBuscar.getText());
        }
        if (e.getSource() == vistaEstudiante.btnRefrescar) {
            filtrarTablaPorNombre(vistaEstudiante.tblDatos, "");
        }
        if (e.getSource() == vistaEstudiante.btnModificar) {

            if (vistaEstudiante.txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(vistaEstudiante, "Debe de seleccionar un registro en la tabla");
            } else if (vistaEstudiante.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaEstudiante, "Debe ingresar un nombre");
            } else if (vistaEstudiante.jsNotaI.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaEstudiante, "Debe ingresar una primer nota");
            } else if (vistaEstudiante.jsNotaII.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaEstudiante, "Debe ingresar una segunda nota");
            } else if (vistaEstudiante.jsNotaIII.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaEstudiante, "Debe ingresar una tercera nota");
            } else {
                actualizarEstudiantes();
                calcular();
            }
        }
        if (e.getSource() == vistaEstudiante.btnEliminar) {
            eliminarEstudiantes();
        }
        if (e.getSource() == vistaEstudiante.btnCancelar) {
            limpiarCampos();
        }
        if (e.getSource() == vistaEstudiante.btnCalcular) {
            calcular();
            System.out.println(calcular());
        }
    }

    public void agregarEstudiantes() {

        String nombre = vistaEstudiante.txtNombre.getText();
        String edad = vistaEstudiante.jsEdad.getValue().toString();
        String notaI = vistaEstudiante.jsNotaI.getValue().toString();
        String notaII = vistaEstudiante.jsNotaII.getValue().toString();
        String notaIII = vistaEstudiante.jsNotaIII.getValue().toString();
        String notaFinal = vistaEstudiante.txtNotaFinal.getText();
        e.setNombreCompleto(nombre);
        e.setEdad(Integer.parseInt(edad));
        e.setNotaI(Double.parseDouble(notaI));
        e.setNotaII(Double.parseDouble(notaII));
        e.setNotaIII(Double.parseDouble(notaIII));
        e.setNotaFinal(Double.parseDouble(notaFinal));
        e.setEstado(calcular());

        int r = dao.agregarEstudiantes(e);

        if (r == 1) {
            JOptionPane.showMessageDialog(vistaEstudiante, "ESTUDIANTE AGREGADO CORRECTAMENTE");
            filtrarTablaPorNombre(vistaEstudiante.tblDatos, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaEstudiante, "ESTUDIANTE NO AGREGADO CORRECTAMENTE");
        }
    }

    public void actualizarEstudiantes() {

        int idEstudiante = Integer.parseInt(vistaEstudiante.txtId.getText());
        String nombre = vistaEstudiante.txtNombre.getText();
        String edad = vistaEstudiante.jsEdad.getValue().toString();
        String notaI = vistaEstudiante.jsNotaI.getValue().toString();
        String notaII = vistaEstudiante.jsNotaII.getValue().toString();
        String notaIII = vistaEstudiante.jsNotaIII.getValue().toString();
        String notaFinal = vistaEstudiante.txtNotaFinal.getText();
        e.setIdEstudiante(idEstudiante);
        e.setNombreCompleto(nombre);
        e.setEdad(Integer.parseInt(edad));
        e.setNotaI(Double.parseDouble(notaI));
        e.setNotaII(Double.parseDouble(notaII));
        e.setNotaIII(Double.parseDouble(notaIII));
        e.setNotaFinal(Double.parseDouble(notaFinal));
        e.setEstado(calcular());

        int r = dao.actualizarEstudiantes(e);

        if (r == 1) {
            JOptionPane.showMessageDialog(vistaEstudiante, "ESTUDIANTE ACTUALIZADO CON EXITO");
            filtrarTablaPorNombre(vistaEstudiante.tblDatos, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaEstudiante, "ESTUDIANTE NO ACTUALIZADO");
        }
    }

    public void eliminarEstudiantes() {
        int fila = vistaEstudiante.tblDatos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaEstudiante, "DEBE SELECCIONAR UN REGISTRO EN LA TABLA");
        } else {
            int id = Integer.parseInt(vistaEstudiante.tblDatos.getValueAt(fila, 0).toString());
            int r = dao.eliminarEstudiantes(id);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaEstudiante, "EL REGISTRO SE ELIMINO CON EXITO");
                limpiarCampos();
                filtrarTablaPorNombre(vistaEstudiante.tblDatos, "");

            } else {
                JOptionPane.showMessageDialog(vistaEstudiante, "EL ESTUDIANTE NO SE ELIMINO");
            }
        }
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);
    }

    public void limpiarCampos() {
        vistaEstudiante.txtId.setText("");
        vistaEstudiante.txtNombre.setText("");
        vistaEstudiante.txtNotaFinal.setText("");
        vistaEstudiante.txtBuscar.setText("");
        vistaEstudiante.jsEdad.setValue(0);
        vistaEstudiante.jsNotaI.setValue(0);
        vistaEstudiante.jsNotaII.setValue(0);
        vistaEstudiante.jsNotaIII.setValue(0);
    }

    public void inicio() {
        filtrarTablaPorNombre(vistaEstudiante.tblDatos, "");
        limpiarCampos();
    }

    public String calcular() {

        double primeraNota = Double.parseDouble(vistaEstudiante.jsNotaI.getValue().toString());
        double segundaNota = Double.parseDouble(vistaEstudiante.jsNotaII.getValue().toString());
        double terceraNota = Double.parseDouble(vistaEstudiante.jsNotaIII.getValue().toString());
        double resultado = primeraNota + segundaNota + terceraNota;
        vistaEstudiante.txtNotaFinal.setText(String.valueOf(resultado));
        if (resultado >= 70) {
            String estado = "APROBADO";
            return estado;
        } else {
            String estado = "DESAPROBADO";
            return estado;
        }
    }
}
