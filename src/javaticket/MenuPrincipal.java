/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public MenuPrincipal(ManejoDeUsuarios manejo) {
        this.manejo = manejo;
        
        setSize(500, 500);
        setTitle("Login");
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
                System.exit(0); // cerrar programa
            }
        });
        
        Usuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // cerrar programa
            }
        });
        
        Reportes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // cerrar programa
            }
        });
        setVisible(true);

    }

}
