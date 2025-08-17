/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket;

import javaticket.Manejo.ManejoDeUsuarios;
import javaticket.Manejo.ManejoDeEventos;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author user
 */
public class Login extends JFrame {

    private JButton submit;
    private JLabel UsuarioI, ContraI, Titulo,Titulo2;
    private JTextField Usuario;
    private JPasswordField Contra;
    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;
    public Login(ManejoDeUsuarios manejo,ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;
        Font paraLetras = new Font("Roboto", Font.BOLD, 16);
        Font paraTitulos = new Font("Roboto", Font.BOLD, 25);

        setSize(500, 500);
        setTitle("Login");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#394B66"));

        submit = new JButton("Submit");
        submit.setBounds(200, 300, 100, 50);
        submit.setFont(paraLetras);
        submit.setBackground(Color.decode("#0D5109"));
        submit.setForeground(Color.white);
        add(submit);
        
        //Boton Usuario
        Usuario = new JTextField();
        Usuario.setBounds(175, 175, 150, 25);
        Usuario.setFont(paraLetras);
        add(Usuario);
        
        UsuarioI = new JLabel("Usuario");
        UsuarioI.setBounds(225, 150, 150, 25);
        UsuarioI.setFont(paraLetras);
        add(UsuarioI);
        
        //Boton Contra
        Contra = new JPasswordField();
        Contra.setBounds(175, 225, 150, 25);
        Contra.setFont(paraLetras);
        add(Contra);
        
        ContraI = new JLabel("Contrasena");
        ContraI.setBounds(215, 200, 150, 25);
        ContraI.setFont(paraLetras);
        add(ContraI);
        
        //Titulo
        Titulo = new JLabel("TICKET BARN");
        Titulo.setBounds(175, 50, 200, 25);
        Titulo.setFont(paraTitulos);
        Titulo.setForeground(Color.white);
        add(Titulo);
        
        Titulo2 = new JLabel("TICKET BARN");
        Titulo2.setBounds(175, 48, 210, 35);
        Titulo2.setFont(paraTitulos);
        add(Titulo2);
        
        //add(Titulo2);
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
        
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(125, 130, 250, 250); // posicion y tamaño del fondo
        loginPanel.setBackground(Color.decode("#EAE9D3")); // color del fondo del login
        loginPanel.setLayout(null);
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(loginPanel);
        
        

    }
    
    public boolean revisar()
    {
    return !(String.valueOf(Contra.getPassword()).equals("")) && !Usuario.getText().equals("");
    }
    
    

    
}
