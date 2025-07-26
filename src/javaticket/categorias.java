/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket;

/**
 *
 * @author user
 */
public class categorias {

    int codigo;
    String titulo;
    String descripcion;
    String fecha;
    double costo;

    public categorias(int codigo, String titulo, String descripcion, String fecha, double costo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.costo = costo;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public String getTitulo()
    {
    return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public double getCosto() {
        return costo;
    }

}
