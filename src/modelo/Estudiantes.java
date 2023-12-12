/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author lgfon
 */
public class Estudiantes {

    private int idEstudiante;
    private String nombreCompleto;
    private int edad;
    private double notaI;
    private double notaII;
    private double notaIII;
    private double notaFinal;
    private String estado;

    public Estudiantes() {
    }

    public Estudiantes(int idEstudiante, String nombreCompleto, int edad, double notaI, double notaII, double notaIII, double notaFinal, String estado) {
        this.idEstudiante = idEstudiante;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.notaI = notaI;
        this.notaII = notaII;
        this.notaIII = notaIII;
        this.notaFinal = notaFinal;
        this.estado = estado;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getNotaI() {
        return notaI;
    }

    public void setNotaI(double notaI) {
        this.notaI = notaI;
    }

    public double getNotaII() {
        return notaII;
    }

    public void setNotaII(double notaII) {
        this.notaII = notaII;
    }

    public double getNotaIII() {
        return notaIII;
    }

    public void setNotaIII(double notaIII) {
        this.notaIII = notaIII;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double Calcular (){
        this.notaFinal = (notaI + notaII + notaIII)/100;
        return this.notaFinal;
    }
}
