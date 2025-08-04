package javaticket.Categorias;

import javaticket.Categorias.categorias;

public class Deportivo extends categorias {

    private String tipoDeporte;  // String
    private final int capacidadMaxima = 20000;
    private String equipo1;
    private String equipo2;

    public Deportivo(int codigo, String titulo, String descripcion, String fecha, double costo,
                     String tipoDeporte, String equipo1, String equipo2) {
        super(codigo, titulo, descripcion, fecha, costo);
        this.tipoDeporte = tipoDeporte;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public String getTipoDeporte() {
        return tipoDeporte;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }
}
