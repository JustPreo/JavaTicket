/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket.Manejo;

import javaticket.Categorias.categorias;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public final class ManejoDeEventos {

    public static ArrayList<categorias> eventos;

    public ManejoDeEventos() {
        eventos = new ArrayList<>();

    }

    public final boolean revisarFechas(String fecha) {
        return revisarFechasRec(fecha, 0);
    }

    private boolean revisarFechasRec(String fecha, int index) {
        if (index >= eventos.size()) {
            return true;
        }
        categorias evento = eventos.get(index);
        if (evento.getFecha().equals(fecha) && !evento.isCancelado()) {
            JOptionPane.showMessageDialog(null, "ERROR:Otro evento tiene esta fecha");
            return false;
        }
        return revisarFechasRec(fecha, index + 1);
    }

    public final boolean revisarCodigo(int codigo) {
        return revisarCodigoRec(codigo, 0);
    }

    private boolean revisarCodigoRec(int codigo, int index) {
        if (index >= eventos.size()) {
            return true;
        }
        categorias evento = eventos.get(index);
        if (evento.getCodigo() == codigo) {
            JOptionPane.showMessageDialog(null, "ERROR:Otro evento tiene este codigo");
            return false;
        }
        return revisarCodigoRec(codigo, index + 1);
    }

    public boolean crearEvento(categorias Evento) {

        if (revisarFechas(Evento.getFecha()) && revisarCodigo(Evento.getCodigo()) && Evento.getCosto() > 0) {
            eventos.add(Evento);
            return true;
        }
        if (Evento.getCosto() <= 0) {
            JOptionPane.showMessageDialog(null, "ERROR:No puedes tener costos negativos o iguales a 0");

        }
        return false;
    }

    public categorias buscarEvento(int codigo) {
        return buscarEventoRec(codigo, 0);
    }

    private categorias buscarEventoRec(int codigo, int index) {
        if (index >= eventos.size()) {
            return null;
        }
        categorias evento = eventos.get(index);
        if (evento.getCodigo() == codigo) {
            return evento;
        }
        return buscarEventoRec(codigo, index + 1);
    }

    public final boolean estaRealizado(categorias evento) {
        return evento.isRealizado();
    }

    public void marcarCancelado(categorias evento) {
        evento.setCancelado(true);
    }

}
