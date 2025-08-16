/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket.AdministradorReportes;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;
import javaticket.MenuPrincipal;
import javax.swing.*;

/**
 *
 * @author user
 */
public class AdministradorReportes extends JFrame {

    private JButton Realizados, Futuros, Cancelados, Fecha, Perfil, Volver;
    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;

    public AdministradorReportes(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;
        setSize(500, 500);
        setTitle("Administrador Reportes");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.cyan);

        Realizados = new JButton("Eventos Realizados");
        Realizados.setBounds(165, 50, 175, 50);
        add(Realizados);

        Futuros = new JButton("Eventos Futuros");
        Futuros.setBounds(165, 125, 175, 50);
        add(Futuros);

        Cancelados = new JButton("Eventos Cancelados");
        Cancelados.setBounds(165, 200, 175, 50);
        add(Cancelados);

        Fecha = new JButton("Eventos por fecha");
        Fecha.setBounds(165, 275, 175, 50);
        add(Fecha);

        Perfil = new JButton("Perfil Usuario");
        Perfil.setBounds(165, 350, 175, 50);
        add(Perfil);

        Volver = new JButton("Volver");
        Volver.setBounds(165, 425, 175, 50);
        add(Volver);

        //Action Listeners
        Realizados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        Futuros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EventosFuturo(manejo, eventos).setVisible(true);
                dispose();

            }

        });

        Cancelados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        Fecha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        Perfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        Volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal menu = new MenuPrincipal(manejo, eventos);
                menu.setVisible(true);
                dispose();
            }
        });

    }
}
