/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket.AdministradorEventos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;
import javaticket.MenuPrincipal;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author user
 */
public class AdministrarEventos extends JFrame {

    private JButton Crear, Eliminar, Editar, Ver, Volver;
    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;

    public AdministrarEventos(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;

        setSize(500, 500);
        setTitle("Menu Principal");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.cyan);

        Crear = new JButton("Crear Eventos");
        Crear.setBounds(165, 50, 175, 50);
        add(Crear);

        Eliminar = new JButton("Eliminar Eventos");
        Eliminar.setBounds(165, 125, 175, 50);
        add(Eliminar);

        Editar = new JButton("Editar eventos");
        Editar.setBounds(165, 200, 175, 50);
        add(Editar);

        Ver = new JButton("Ver eventos");
        Ver.setBounds(165, 275, 175, 50);
        add(Ver);

        Volver = new JButton("Volver");
        Volver.setBounds(165, 350, 175, 50);
        add(Volver);

        //Action Listeners
        Crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CrearEvento crear = new CrearEvento(manejo,eventos);
                dispose();
            }
        });

        Eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EliminarEvento eliminar = new EliminarEvento(manejo,eventos);
                dispose();
            }
        });

        Editar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditarEvento editar = new EditarEvento(manejo,eventos);
                dispose();
            }
        });

        Ver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // cerrar programa
            }
        });

        Volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal menu = new MenuPrincipal(manejo,eventos);
                dispose();
            }
        });
        setVisible(true);

    }
    
    
}
