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
                JOptionPane.showMessageDialog(this, "No se encontró evento con ese código.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean bool = false;
            if (userLogged instanceof Administrador) {
                bool = ((Administrador) userLogged).creador(codigo);

            } else if (userLogged instanceof Contenidos) {
                bool = ((Contenidos) userLogged).creador(codigo);
            }

            System.out.println(bool);

            if (!bool) {
                JOptionPane.showMessageDialog(this, "Evento no creado por usuario", "Eror", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (evento.isCancelado()) {
                JOptionPane.showMessageDialog(this, "El evento ya está cancelado.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (evento.isRealizado()) {
                JOptionPane.showMessageDialog(this, "El evento ya se realizó, no puede ser cancelado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar días para evento
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaEvento = sdf.parse(evento.getFecha());
            Date hoy = new Date();

            long diffMs = fechaEvento.getTime() - hoy.getTime();
            long diffDias = diffMs / (1000 * 60 * 60 * 24);

            double indemnizacion = 0.0;

            if (diffDias == 1) { // Un día antes
                if (!evento.getTitulo().equalsIgnoreCase("Religioso")) {
                    indemnizacion = evento.getCosto() * 0.5;
                }
            }

            eventos.marcarCancelado(evento);

            String mensaje = "Evento cancelado correctamente.";
            if (indemnizacion > 0) {
                mensaje += String.format("\nSe cobrará indemnización del 50%%: Lps %.2f", indemnizacion);
            } else {
                mensaje += "\nNo hay indemnización que cobrar.";
            }

            JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Código inválido, ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(this, "Error al procesar la fecha del evento.", "Error", JOptionPane.ERROR_MESSAGE);
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
