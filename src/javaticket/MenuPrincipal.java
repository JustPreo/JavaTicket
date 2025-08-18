/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket;

import javaticket.Manejo.ManejoDeUsuarios;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.AdministradorEventos.AdministrarEventos;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaticket.AdministradorReportes.AdministradorReportes;
import javaticket.AdministradorUsuarios.AdministrarUsuarios;
import javaticket.Usuarios.Contenidos;
import javaticket.Usuarios.Limitado;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/*
 -Administracion de Eventos
 -Administracion de Usuarios
 -Reportes
 */
public class MenuPrincipal extends JFrame {

    private JButton Eventos, Usuarios, Reportes, Salir;
    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;

    public MenuPrincipal(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
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

        Eventos = new JButton("Administrar Eventos");
        Eventos.setBounds(155, 50, 200, 50);
        Eventos.setFont(paraLetras);
        Eventos.setBackground(Color.decode("#EAE9D3"));
        Eventos.setForeground(Color.black);
        add(Eventos);

        Usuarios = new JButton("Administrar Usuarios");
        Usuarios.setBounds(155, 150, 200, 50);
        Usuarios.setFont(paraLetras);
        Usuarios.setBackground(Color.decode("#EAE9D3"));
        Usuarios.setForeground(Color.black);
        add(Usuarios);

        Reportes = new JButton("Reportes");
        Reportes.setBounds(155, 250, 200, 50);
        Reportes.setFont(paraLetras);
        Reportes.setBackground(Color.decode("#EAE9D3"));//Blanco hueso algo asi
        Reportes.setForeground(Color.black);
        add(Reportes);

        Salir = new JButton("Cerrar sesion");
        Salir.setBounds(155, 350, 200, 50);
        Salir.setFont(paraLetras);
        Salir.setBackground(Color.decode("#EAE9D3"));//Blanco hueso algo asi
        Salir.setForeground(Color.black);
        add(Salir);

        //Action Listeners
        Eventos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdministrarEventos(manejo, eventos).setVisible(true);
                dispose();
            }
        });

        Usuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (manejo.getUser() instanceof Limitado || manejo.getUser() instanceof Contenidos) {
                    JOptionPane.showMessageDialog(null, "No puedes editar usuarios!");
                } else {
                    new AdministrarUsuarios(manejo, eventos).setVisible(true);
                    dispose();
                    
                }
            }
        });

        Reportes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdministradorReportes(manejo, eventos).setVisible(true);
                dispose();
            }
        });
        
        Salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login(manejo,eventos).setVisible(true);
                ManejoDeUsuarios.userLogged = null;
                dispose();
            }
        });
        setVisible(true);

    }

}
