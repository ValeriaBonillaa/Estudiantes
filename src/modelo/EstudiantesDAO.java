/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lgfon
 */
public class EstudiantesDAO {

    PreparedStatement ps;
    Connection con;
    ResultSet rs;
    Conexion conectar = new Conexion();

    public int agregarEstudiantes(Estudiantes user) {

        int r = 0;
        String sql = "INSERT INTO Notas_Estudiantes (NombreCompleto, Edad, NotaI, NotaII, NotaIII, NotaFinal, Estado) VALUES (?,?,?,?,?,?,?)";//value1..value2

        try {
            con = conectar.getConecction();// 1: Abrir conexion con la BD
            ps = con.prepareStatement(sql);// 2: Prepara el query de sql
            ps.setString(1, user.getNombreCompleto());//3: Completar el query
            ps.setInt(2, user.getEdad());
            ps.setDouble(3, user.getNotaI());
            ps.setDouble(4, user.getNotaII());
            ps.setDouble(5, user.getNotaIII());
            ps.setDouble(6, user.getNotaFinal());
            ps.setString(7, user.getEstado());
            r = ps.executeUpdate();//4. Ejecutar query 0=Fallo 1= success modificacion en la base de datos
        } catch (Exception e) {
        }
        return r;
    }

    public int actualizarEstudiantes(Estudiantes user) {

        int r = 0;
        String sql = "UPDATE Notas_Estudiantes SET NombreCompleto=?, Edad=?, NotaI=?, NotaII=?, NotaIII=?, NotaFinal=?, Estado=? WHERE Id=?";
        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombreCompleto());
            ps.setInt(2, user.getEdad());
            ps.setDouble(3, user.getNotaI());
            ps.setDouble(4, user.getNotaII());
            ps.setDouble(5, user.getNotaIII());
            ps.setDouble(6, user.getNotaFinal());
            ps.setString(7, user.getEstado());
            ps.setInt(8, user.getIdEstudiante());
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public int eliminarEstudiantes(int id) {

        int r = 0;
        String sql = "DELETE FROM Notas_Estudiantes WHERE Id=" + id;
        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        String[] titulos = {"ID", "Nombre", "Edad", "NotaI", "NotaII", "NotaIII", "NotaFinal", "Estado"};
        String[] registros = new String[8];
        String sql = "SELECT * FROM Notas_Estudiantes WHERE NombreCompleto LIKE '%" + filtro + "%'"; //"Her"
        DefaultTableModel modelo;
        modelo = new DefaultTableModel(null, titulos);

        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                registros[0] = rs.getString("Id");
                registros[1] = rs.getString("NombreCompleto");
                registros[2] = rs.getString("Edad");
                registros[3] = rs.getString("NotaI");
                registros[4] = rs.getString("NotaII");
                registros[5] = rs.getString("NotaIII");
                registros[6] = rs.getString("NotaFinal");
                registros[7] = rs.getString("Estado");
                modelo.addRow(registros);
            }
            table.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error al buscar los datos " + e.getMessage());
        }
    }
}
