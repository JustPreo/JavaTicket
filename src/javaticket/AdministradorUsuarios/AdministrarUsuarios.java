/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket.AdministradorUsuarios;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author user
 */
public class AdministrarUsuarios extends JFrame {
    
    private ManejoDeEventos eventos;
    private ManejoDeUsuarios manejo;
    private JButton Crear, Eliminar, Editar, Volver;
    
    public AdministrarUsuarios(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;
        
        setSize(500, 500);
        setTitle("Menu Principal");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.cyan);

        //Botones
        Crear = new JButton("Crear Usuario");
        Crear.setBounds(165, 50, 175, 50);
        add(Crear);
        
        Eliminar = new JButton("Eliminar Usuario");
        Eliminar.setBounds(165, 125, 175, 50);
        add(Eliminar);
        
        Editar = new JButton("Editar Usuario");
        Editar.setBounds(165, 200, 175, 50);
        add(Editar);
        
        Volver = new JButton("Volver");
        Volver.setBounds(165, 275, 175, 50);
        add(Volver);

        //Action Listeners
        Crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CrearUsuario(manejo, eventos).setVisible(true);
                dispose();
            }
        });
        
        Eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EliminarUsuario(manejo, eventos).setVisible(true);
                dispose();
            }
        });
        
        Editar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditarUsuario(manejo, eventos).setVisible(true);
                dispose();
            }
        });
        
        Volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                dispose();
            }
        });
        
    }
    
}
