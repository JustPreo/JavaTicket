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
    private String equipo1;
    private String equipo2;
    private String tipo;
    public Deportivo(int codigo, String titulo, String descripcion, String fecha, double costo,String equipo1 , String equipo2 , String tipo) 
    {
    super(codigo,titulo,descripcion,fecha,costo);
    this.equipo1 = equipo1;
    this.equipo2 = equipo2;
    this.tipo = tipo;
    }
    
    public String getEquipo1()
    {
    return this.equipo1;
    }
    
    public String getEquipo2()
    {
    return this.equipo2;
    }
    
    public String getTipoDeporte()
    {
    return this.tipo;
    }
    
    
    
}
