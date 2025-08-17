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

    public boolean revisarFechas(String fecha) {
        for (categorias evento : eventos) {
            if (evento.getFecha().equals(fecha) && !evento.isCancelado()) {
                JOptionPane.showMessageDialog(null, "ERROR:Otro evento tiene esta fecha");
                return false;
            }

        }

        return true;
    }

    public boolean revisarCodigo(int codigo) {
        for (categorias evento : eventos) {
            if (evento.getCodigo() == codigo) {
                JOptionPane.showMessageDialog(null, "ERROR:Otro evento tiene este codigo");
                return false;
            }

        }
        return true;
    }

    public boolean crearEvento(categorias Evento) {

        if (revisarFechas(Evento.getFecha()) && revisarCodigo(Evento.getCodigo()) && Evento.getCosto() > 0) {
            eventos.add(Evento);
            return true;
        }
        return false;
    }

    public categorias buscarEvento(int codigo) {
        for (categorias evento : eventos) {
            if (codigo == evento.getCodigo()) {
                return evento;

            }
        }
        return null;
    }

    public boolean estaRealizado(categorias evento) {
        return evento.isRealizado();
    }

    public void marcarCancelado(categorias evento) {
        evento.setCancelado(true);
    }

}
