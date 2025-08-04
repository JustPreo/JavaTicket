package javaticket.Categorias;

import javaticket.Categorias.categorias;

public class Religioso extends categorias {

    private final int capacidadMaxima = 30000;
    private final double seguroFijo = 2000.0;

    public Religioso(int codigo, String titulo, String descripcion, String fecha, double costo) {
        super(codigo, titulo, descripcion, fecha, costo);
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
}
