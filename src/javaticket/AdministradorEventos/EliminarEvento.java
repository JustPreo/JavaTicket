package javaticket.AdministradorEventos;

import javaticket.Categorias.categorias;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static javaticket.Manejo.ManejoDeUsuarios.userLogged;
import javaticket.Usuarios.Administrador;
import javaticket.Usuarios.Contenidos;

public class EliminarEvento extends JFrame {

    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;

    private JLabel codigoL;
    private JTextField codigoTF;
    private JButton eliminarBtn, Volver;

    public EliminarEvento(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;

        setSize(500, 500);
        setTitle("Eliminar Evento");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.cyan);

        codigoL = new JLabel("Ingrese código del evento:");
        codigoL.setBounds(50, 100, 200, 30);
        add(codigoL);

        codigoTF = new JTextField();
        codigoTF.setBounds(250, 100, 150, 30);
        add(codigoTF);

        eliminarBtn = new JButton("Eliminar Evento");
        eliminarBtn.setBounds(170, 200, 150, 50);
        add(eliminarBtn);

        Volver = new JButton("Volver");
        Volver.setBounds(170, 300, 150, 50);
        add(Volver);

        Volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministrarEventos menu = new AdministrarEventos(manejo, eventos);
                dispose();
            }
        });

        eliminarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEvento();
            }
        });

        setVisible(true);
    }

    private void eliminarEvento() {
    try {
        int codigo = Integer.parseInt(codigoTF.getText());
        categorias evento = eventos.buscarEvento(codigo);

        if (evento == null) {
            JOptionPane.showMessageDialog(this, "No se encontro evento con ese codigo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean bool = false;
        if (userLogged instanceof Administrador) {
            bool = ((Administrador) userLogged).creador(codigo);
        } else if (userLogged instanceof Contenidos) {
            bool = ((Contenidos) userLogged).creador(codigo);
        }

        if (!bool) {
            JOptionPane.showMessageDialog(this, "Evento no creado por usuario", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (evento.isCancelado()) {
            JOptionPane.showMessageDialog(this, "El evento ya esta cancelado.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (evento.isRealizado()) {
            JOptionPane.showMessageDialog(this, "El evento ya se realizo, no puede ser cancelado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double multa = evento.calcularMulta();

        evento.setCancelado(true);
        evento.setMulta(multa);

        String mensaje = "Evento cancelado correctamente.";
        if (multa > 0) {
            mensaje += String.format("\nSe cobrara una multa de: Lps %.2f", multa);
        } else {
            mensaje += "\nNo hay multa que cobrar.";
        }

        JOptionPane.showMessageDialog(this, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);

    } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(this, "Codigo invalido, ingrese un numero.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    public static void main(String[] args) {
        ManejoDeUsuarios manejo = new ManejoDeUsuarios();
        ManejoDeEventos eventos = new ManejoDeEventos();
        EliminarEvento login = new EliminarEvento(manejo, eventos);
    }

}
