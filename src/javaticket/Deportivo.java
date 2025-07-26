/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket;

/**
 *
 * @author user
 */
public class Deportivo extends categorias {
    //Funciones getCodigo()|getTitulo()|getFecha()|getCosto()
    String equipo1;
    String equipo2;
    String tipo;
    public Deportivo(int codigo, String titulo, String descripcion, String fecha, double costo,String equipo1 , String equipo2 , String tipo) 
    {
    super(codigo,titulo,descripcion,fecha,costo);
    this.equipo1 = equipo1;
    this.equipo2 = equipo2;
    this.tipo = tipo;
    }
    
}
