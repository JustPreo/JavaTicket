package javaticket.AdministradorEventos;

import javaticket.Categorias.categorias;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javaticket.Categorias.Religioso;
import static javaticket.Manejo.ManejoDeUsuarios.userLogged;
import javaticket.Usuarios.Administrador;
import javaticket.Usuarios.Contenidos;
import javaticket.Usuarios.Limitado;
import javaticket.Usuarios.UsuarioTemplate;

public class EliminarEvento extends JFrame {

    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;

    private JLabel codigoL;
    private JTextField codigoTF;
    private JButton eliminarBtn, Volver;

    public EliminarEvento(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;
        
        Font paraLetras = new Font("Roboto", Font.BOLD, 16);
        Font paraTitulos = new Font("Roboto", Font.BOLD, 25);

        setSize(500, 500);
        setTitle("Eliminar Evento");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#394B66"));//Azul)?

        codigoL = new JLabel("Ingrese código del evento:");
        codigoL.setBounds(50, 100, 200, 30);
        codigoL.setFont(paraLetras);
        codigoL.setForeground(Color.decode("#EAE9D3"));
        add(codigoL);

        codigoTF = new JTextField();
        codigoTF.setBounds(250, 100, 150, 30);
        codigoTF.setFont(paraLetras);
        add(codigoTF);

        eliminarBtn = new JButton("Eliminar Evento");
        eliminarBtn.setBounds(170, 200, 150, 50);
        eliminarBtn.setFont(paraLetras);
        eliminarBtn.setBackground(Color.decode("#EAE9D3"));
        eliminarBtn.setForeground(Color.black);
        add(eliminarBtn);

        Volver = new JButton("Volver");
        Volver.setBounds(170, 300, 150, 50);
        Volver.setFont(paraLetras);
        Volver.setBackground(Color.decode("#EAE9D3"));
        Volver.setForeground(Color.black);
        add(Volver);

        Volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministrarEventos menu = new AdministrarEventos(manejo, eventos);
                menu.setVisible(true);
                dispose();
            }
        });

        eliminarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEvento();
                AdministrarEventos menu = new AdministrarEventos(manejo, eventos);
                menu.setVisible(true);
                dispose();
            }
        });
    }

    private void eliminarEvento() {
    try {
        int codigo = Integer.parseInt(codigoTF.getText());
        categorias evento = eventos.buscarEvento(codigo);

        if (evento == null) {
            JOptionPane.showMessageDialog(this, "No se encontro evento con ese codigo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (evento.isRealizado()) {
            JOptionPane.showMessageDialog(this, "El evento ya se realizo, no puede ser cancelado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean esCreador = false;
        UsuarioTemplate usuarioActual = manejo.getUser();
        if (usuarioActual instanceof Administrador) {
                esCreador = ((Administrador) usuarioActual).creador(evento.getCodigo());
                
            } else if (usuarioActual instanceof Contenidos) {
                esCreador = ((Contenidos) usuarioActual).creador(evento.getCodigo());
            } 
        
        if (!esCreador)
        {
            JOptionPane.showMessageDialog(null, "No puedes eliminar eventos que no hayas creado");
        return;
        }

        // comparar fecha
        Date fechaEvento = new SimpleDateFormat("yyyy-MM-dd").parse(evento.getFecha());
        Calendar calHoy = Calendar.getInstance();
        calHoy.set(Calendar.HOUR_OF_DAY, 0);
        calHoy.set(Calendar.MINUTE, 0);
        calHoy.set(Calendar.SECOND, 0);
        calHoy.set(Calendar.MILLISECOND, 0);

        Calendar calEvento = Calendar.getInstance();
        calEvento.setTime(fechaEvento);
        calEvento.set(Calendar.HOUR_OF_DAY, 0);
        calEvento.set(Calendar.MINUTE, 0);
        calEvento.set(Calendar.SECOND, 0);
        calEvento.set(Calendar.MILLISECOND, 0);

        // diferencia en dias
        long diffMillis = calEvento.getTimeInMillis() - calHoy.getTimeInMillis();
        long diffDias = diffMillis / (24 * 60 * 60 * 1000);

        double multa = 0;
        if (diffDias <= 1 && !(evento instanceof Religioso)) {
            multa = evento.getCosto() * 0.5; // 50% del costo
        }

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
    } catch (ParseException pe) {
        JOptionPane.showMessageDialog(this, "Error al leer la fecha del evento.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

}
