package javaticket.Categorias;

import java.util.ArrayList;
import javaticket.Categorias.categorias;

public class Deportivo extends categorias {

    private String tipoDeporte;  // String
    private final int capacidadMaxima = 20000;
    private String equipo1;
    private String equipo2;
    private ArrayList<String> jugadoresEquipo1;
    private ArrayList<String> jugadoresEquipo2;

    /*
    DEPORTIVO - LISTADO DE JUGADORES POR CADA EQUIPO (2 ARRAYS ENTONCES)
        
     */
    public Deportivo(int codigo, String titulo, String descripcion, String fecha, double costo,
            String tipoDeporte, String equipo1, String equipo2) {
        super(codigo, titulo, descripcion, fecha, costo);
        this.tipoDeporte = tipoDeporte;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        jugadoresEquipo1 = new ArrayList<>();
        jugadoresEquipo2 = new ArrayList<>();

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

    public void setTipoDeporte(String tipoDeporte) {
        this.tipoDeporte = tipoDeporte;
    }

    public ArrayList<String> getJugadoresEquipo1() {
        return jugadoresEquipo1;
    }

    public void setJugadoresEquipo1(ArrayList<String> jugadoresEquipo1) {
        this.jugadoresEquipo1 = jugadoresEquipo1;
    }

    public ArrayList<String> getJugadoresEquipo2() {
        return jugadoresEquipo2;
    }

    public void setJugadoresEquipo2(ArrayList<String> jugadoresEquipo2) {
        this.jugadoresEquipo2 = jugadoresEquipo2;
    }
    public double calcularMulta() {
        // Ejemplo: si evento cancelado y falta 1 día, multa es 50% del costo
        if (isCancelado()) {
            // Aquí pondrías la lógica real con fechas
            // Por ejemplo, retorno un 50% fijo para la demostración
            return getCosto() * 0.5;
        }
        return 0.0;
    }

}
