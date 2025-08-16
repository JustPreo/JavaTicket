/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket;

import javaticket.Manejo.ManejoDeUsuarios;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.AdministradorEventos.AdministrarEventos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaticket.AdministradorEventos.EditarEvento;
import javaticket.AdministradorReportes.AdministradorReportes;
import javaticket.AdministradorReportes.EventosFuturo;
import javaticket.AdministradorUsuarios.AdministrarUsuarios;
import javaticket.Usuarios.Limitado;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/*
 -Administracion de Eventos
 -Administracion de Usuarios
 -Reportes
 */
public class MenuPrincipal extends JFrame{
    private JButton Eventos,Usuarios,Reportes;
    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;

    public MenuPrincipal(ManejoDeUsuarios manejo,ManejoDeEventos eventos ) {
        this.manejo = manejo;
        this.eventos = eventos;
        
        setSize(500, 500);
        setTitle("Menu Principal");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.cyan);
        
        Eventos = new JButton("Administrar Eventos");
        Eventos.setBounds(165, 50, 175, 50);
        add(Eventos);
        
        Usuarios = new JButton("Administrar Usuarios");
        Usuarios.setBounds(165, 150, 175, 50);
        add(Usuarios);
        
        Reportes = new JButton("Reportes");
        Reportes.setBounds(165, 250, 175, 50);
        add(Reportes);
        
        
        //Action Listeners
        
        Eventos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdministrarEventos(manejo, eventos).setVisible(true);
                dispose();
            }
        });
        
        Usuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                               if(manejo.getUser() instanceof Limitado)
                {
                    JOptionPane.showMessageDialog(null, "No puedes editar usuarios!");
                }
                else
                {
                new AdministrarUsuarios(manejo,eventos).setVisible(true);
               dispose();
                dispose();
                }
                
                
            }
        });
        
        Reportes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdministradorReportes(manejo,eventos).setVisible(true);
                dispose();
            }
        });
        setVisible(true);

    }

}
