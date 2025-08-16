package javaticket.Categorias;

import java.util.ArrayList;
import javaticket.Categorias.categorias;

public class Musical extends categorias {

    /*
    MUSICAL - LOS NOMBRES DE TODAS LAS PERSONAS QUE CONFORMAR 
    EL EQUIPO QUE MONTA TODO.
     */
    private ArrayList<String> equipoMontaje;
    private String tipoMusica; // String
    private final int capacidadMaxima = 25000;
    private final double seguroGrama = 0.3; // Porcentaje

    public Musical(int codigo, String titulo, String descripcion, String fecha, double costo,int capacidad,
            String tipoMusica) {
        super(codigo, titulo, descripcion, fecha, costo,capacidad);
        this.tipoMusica = tipoMusica;
        equipoMontaje = new ArrayList<>();
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

    public ArrayList<String> getEquipoMontaje() {
        return equipoMontaje;
    }

    public void setEquipoMontaje(ArrayList<String> equipoMontaje) {
        this.equipoMontaje = equipoMontaje;
    }

    public void setTipoMusica(String tipoMusica) {
        this.tipoMusica = tipoMusica;
    }
    
    public double calcularMulta() {
        if (isCancelado()) {
            // Multa 30% del costo para eventos musicales cancelados
            return getCosto() * 0.3;
        }
        return 0.0;
    }

}
