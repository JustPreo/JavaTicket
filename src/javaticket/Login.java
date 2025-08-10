/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket;

import javaticket.Manejo.ManejoDeUsuarios;
import javaticket.Manejo.ManejoDeEventos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author user
 */
public class Login extends JFrame {

    private JButton submit;
    private JLabel UsuarioI, ContraI, Titulo;
    private JTextField Usuario;
    private JPasswordField Contra;
    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;
    public Login(ManejoDeUsuarios manejo,ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;
        setSize(500, 500);
        setTitle("Login");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.cyan);

        submit = new JButton("Submit");
        submit.setBounds(200, 300, 100, 50);
        add(submit);
        //Boton Usuario
        Usuario = new JTextField();
        Usuario.setBounds(175, 175, 150, 25);
        add(Usuario);
        UsuarioI = new JLabel("Usuario");
        UsuarioI.setBounds(225, 150, 150, 25);
        add(UsuarioI);
        //Boton Contra
        Contra = new JPasswordField();
        Contra.setBounds(175, 225, 150, 25);
        add(Contra);
        ContraI = new JLabel("Contrasena");
        ContraI.setBounds(215, 200, 150, 25);
        add(ContraI);
        //Titulo
        Titulo = new JLabel("Titulo");
        Titulo.setBounds(230, 50, 150, 25);
        add(Titulo);
        //Action Listeners
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (revisar()) {
                    System.out.println("Entro");
                    
                    if (manejo.logearse(Usuario.getText(), Contra.getText()))
                    {
                    JOptionPane.showMessageDialog(null, "Logueado correctamente");
                    MenuPrincipal menu = new MenuPrincipal(manejo,eventos);
                    dispose();
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "Acceso denegado");
                    } 
                } else if (!revisar()) {
                    JOptionPane.showMessageDialog(null, "ERROR:Ocupas llenar todos los datos necesarios");
                }
                
            }
        });

    }
    
    public boolean revisar()
    {
    return !(String.valueOf(Contra.getPassword()).equals("")) && !Usuario.getText().equals("");
    }

    
}
