package javaticket.Categorias;

import javaticket.Categorias.categorias;

public class Religioso extends categorias {

    /*
    RELIGIOSO - LA CANTIDAD DE LAS PERSONAS CONVERTIDAS ESA NOCHE
     */

    private final int capacidadMaxima = 30000;
    private final double seguroFijo = 2000.0;
    private int convertidos;

    public Religioso(int codigo, String titulo, String descripcion, String fecha, double costo,int capacidad) {
        super(codigo, titulo, descripcion, fecha, costo,capacidad);
        convertidos = 0;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public double getSeguroFijo() {
        return seguroFijo;
    }

    public double getCostoTotal() {
        return getCosto() + seguroFijo;
    }

    public int getConvertidos() {
        return convertidos;
    }

    public void setConvertidos(int convertidos) {
        this.convertidos = convertidos;
    }
    
    public double calcularMulta() {
        return 0.0;
    }
}
