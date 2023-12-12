/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author lgfon
 */
public class Conexion {

    Connection ccn = null;
    Statement st = null;

    public Conexion() {

        try {  //Manejar errores
            String rutaFile;
            rutaFile = "./DB/EstudiantesDB.accdb";
            String url = "jdbc:ucanaccess://" + rutaFile;
            ccn = DriverManager.getConnection(url);
            st = ccn.createStatement();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "ERROR DE CONEXION" + e);
        }
    }

    /*
    Retornar la conexion
     */
    public Connection getConecction() {
        return ccn;
    }

    public void desconexion() {
        try {
            ccn.close();
            System.exit(0);
        } catch (Exception e) {

        }
    }
}
