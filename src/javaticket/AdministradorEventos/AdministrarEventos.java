/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket.AdministradorEventos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;
import javaticket.MenuPrincipal;
import javaticket.Usuarios.Limitado;
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
        Font paraLetras = new Font("Roboto", Font.BOLD, 16);
        Font paraTitulos = new Font("Roboto", Font.BOLD, 25);

        setSize(500, 500);
        setTitle("Menu Principal");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#394B66"));//Azul)?

        Crear = new JButton("Crear Eventos");
        Crear.setBounds(165, 50, 175, 50);
        Crear.setFont(paraLetras);
        Crear.setBackground(Color.decode("#EAE9D3"));
        Crear.setForeground(Color.black);
        add(Crear);

        Eliminar = new JButton("Eliminar Eventos");
        Eliminar.setBounds(165, 125, 175, 50);
        Eliminar.setFont(paraLetras);
        Eliminar.setBackground(Color.decode("#EAE9D3"));
        Eliminar.setForeground(Color.black);
        add(Eliminar);

        Editar = new JButton("Editar eventos");
        Editar.setBounds(165, 200, 175, 50);
        Editar.setFont(paraLetras);
        Editar.setBackground(Color.decode("#EAE9D3"));
        Editar.setForeground(Color.black);
        add(Editar);

        Ver = new JButton("Ver eventos");
        Ver.setBounds(165, 275, 175, 50);
        Ver.setFont(paraLetras);
        Ver.setBackground(Color.decode("#EAE9D3"));
        Ver.setForeground(Color.black);
        add(Ver);

        Volver = new JButton("Volver");
        Volver.setBounds(165, 350, 175, 50);
        Volver.setFont(paraLetras);
        Volver.setBackground(Color.decode("#EAE9D3"));
        Volver.setForeground(Color.black);
        add(Volver);

        //Action Listeners
        Crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (manejo.getUser() instanceof Limitado) {
                    JOptionPane.showMessageDialog(null, "No puedes crear eventos!");
                } else {
                    CrearEvento crear = new CrearEvento(manejo, eventos);
                    crear.setVisible(true);
                    dispose();

                }

            }
        });

        Eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (manejo.getUser() instanceof Limitado) {
                    JOptionPane.showMessageDialog(null, "No puedes Eliminar eventos!");
                } else {
                    EliminarEvento eliminar = new EliminarEvento(manejo, eventos);
                    eliminar.setVisible(true);
                    dispose();

                }

            }

        });

        Editar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (manejo.getUser() instanceof Limitado) {
                    JOptionPane.showMessageDialog(null, "No puedes editar eventos!");
                } else {
                    EditarEvento editar = new EditarEvento(manejo, eventos);
                    editar.setVisible(true);
                    dispose();
                }
            }
        });

        Ver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VerEvento ver = new VerEvento(manejo, eventos);
                ver.setVisible(true);
                dispose();
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
