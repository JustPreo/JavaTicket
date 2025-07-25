/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket;

import javax.swing.*;

/**
 *
 * @author user
 */
public class Login extends JFrame {
    private JButton submit;
    private JLabel UsuarioI,ContraI;
    private JTextField Usuario , Contra;
    public Login() {
        setSize(500, 500);
        setTitle("Login");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        submit = new JButton("Submit");
        
        
        
        
        
        setVisible(true);

    }
    
    public static void main(String[] args) {
        Login login = new Login();
        
    }
}
