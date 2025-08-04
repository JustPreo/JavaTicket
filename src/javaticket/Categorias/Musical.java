package javaticket.Categorias;

import javaticket.Categorias.categorias;

public class Musical extends categorias {

    private String tipoMusica; // String
    private final int capacidadMaxima = 25000;
    private final double seguroGrama = 0.3;

    public Musical(int codigo, String titulo, String descripcion, String fecha, double costo,
                   String tipoMusica) {
        super(codigo, titulo, descripcion, fecha, costo);
        this.tipoMusica = tipoMusica;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public double getSeguroGrama() {
        return getCosto() * seguroGrama;
    }

    public String getTipoMusica() {
        return tipoMusica;
    }

    public double getCostoTotal() {
        return getCosto() + getSeguroGrama();
    }
}
