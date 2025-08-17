/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket.AdministradorUsuarios;

/**
 *
 * @author user
 */
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;

public class EliminarUsuario extends JFrame {

    private JLabel usernameL;
    private JTextField usernameTF;
    private JButton eliminarBtn, volverBtn;
    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;

    public EliminarUsuario(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;

        Font paraLetras = new Font("Roboto", Font.BOLD, 16);

        setSize(500, 500);
        setTitle("Eliminar Usuario");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#394B66"));

        // Label
        usernameL = new JLabel("Username del usuario");
        usernameL.setBounds(50, 100, 250, 30);
        usernameL.setFont(paraLetras);
        usernameL.setForeground(Color.decode("#EAE9D3"));
        add(usernameL);

        // TextField
        usernameTF = new JTextField();
        usernameTF.setBounds(250, 100, 150, 30);
        usernameTF.setFont(paraLetras);
        add(usernameTF);

        // Botones
        eliminarBtn = new JButton("Eliminar Usuario");
        eliminarBtn.setBounds(170, 200, 150, 50);
        eliminarBtn.setFont(paraLetras);
        eliminarBtn.setBackground(Color.decode("#EAE9D3"));
        eliminarBtn.setForeground(Color.black);
        add(eliminarBtn);

        volverBtn = new JButton("Volver");
        volverBtn.setBounds(170, 300, 150, 50);
        volverBtn.setFont(paraLetras);
        volverBtn.setBackground(Color.decode("#EAE9D3"));
        volverBtn.setForeground(Color.black);
        add(volverBtn);

        volverBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdministrarUsuarios(manejo, eventos).setVisible(true);
                dispose();
            }
        });

        eliminarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
    }

    private void limpiarCampos() {
        usernameTF.setText("");
    }

    public void eliminarUsuario() {
        try {
            String username = usernameTF.getText().trim();

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el username del usuario a eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(null,
                    "Esta seguro que desea eliminar al usuario \"" + username + "\"?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                if (manejo.eliminarUsuario(username)) {
                    JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                } else {
                    System.out.println("Los errores se dicen en ManejoDeUsuarios");
                }
            }

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al eliminar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
