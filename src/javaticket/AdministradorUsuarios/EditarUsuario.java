/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket.AdministradorUsuarios;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;
import javaticket.Usuarios.UsuarioTemplate;

public class EditarUsuario extends JFrame {

    public enum TipoUsuario {
        Seleccionar,
        ADMINISTRATIVO,
        CONTENIDO,
        LIMITADO
    }

    private JLabel buscarL, nombreL, usernameL, passwordL, edadL, tipoL;
    private JTextField buscarTF, nombreTF, usernameTF, edadTF;
    private JPasswordField passwordPF;
    private JComboBox<TipoUsuario> tipoCB;
    private JButton buscarBtn, guardarBtn, volverBtn;
    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;
    private UsuarioTemplate usuarioActual = null;

    public EditarUsuario(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;

        setSize(500, 550);
        setTitle("Editar Usuario");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        
        //Los TextField con sus Label
        
        buscarL = new JLabel("Buscar por Username:");
        buscarL.setBounds(30, 20, 150, 25);
        add(buscarL);

        buscarTF = new JTextField();
        buscarTF.setBounds(180, 20, 150, 25);
        add(buscarTF);

        buscarBtn = new JButton("Buscar");
        buscarBtn.setBounds(340, 20, 100, 25);
        add(buscarBtn);

        nombreL = new JLabel("Nombre completo:");
        nombreL.setBounds(30, 70, 150, 25);
        add(nombreL);

        nombreTF = new JTextField();
        nombreTF.setBounds(180, 70, 200, 25);
        add(nombreTF);

        usernameL = new JLabel("Username:");
        usernameL.setBounds(30, 110, 150, 25);
        add(usernameL);

        usernameTF = new JTextField();
        usernameTF.setBounds(180, 110, 200, 25);
        usernameTF.setEditable(false); 
        add(usernameTF);

        passwordL = new JLabel("Password:");
        passwordL.setBounds(30, 150, 150, 25);
        add(passwordL);

        passwordPF = new JPasswordField();
        passwordPF.setBounds(180, 150, 200, 25);
        add(passwordPF);

        edadL = new JLabel("Edad:");
        edadL.setBounds(30, 190, 150, 25);
        add(edadL);

        edadTF = new JTextField();
        edadTF.setBounds(180, 190, 200, 25);
        add(edadTF);

        tipoL = new JLabel("Tipo de usuario:");
        tipoL.setBounds(30, 230, 150, 25);
        add(tipoL);

        tipoCB = new JComboBox<>(TipoUsuario.values());
        tipoCB.setBounds(180, 230, 200, 25);
        tipoCB.setEnabled(false);
        add(tipoCB);

        guardarBtn = new JButton("Guardar Cambios");
        guardarBtn.setBounds(130, 300, 150, 40);
        add(guardarBtn);

        volverBtn = new JButton("Volver");
        volverBtn.setBounds(300, 300, 100, 40);
        add(volverBtn);

        // ActionListeners con clases anónimas
        buscarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });

        guardarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        volverBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdministrarUsuarios(manejo, eventos).setVisible(true);
                dispose();
            }
        });
    }

    private void buscarUsuario() {
        String username = buscarTF.getText().trim();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un username para buscar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        UsuarioTemplate u = manejo.buscarU(username);
        if (u == null) {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuarioActual = u;

        nombreTF.setText(u.getNombre());
        usernameTF.setText(u.getUserame());
        passwordPF.setText(u.getPassword());
        edadTF.setText(String.valueOf(u.getEdad()));

        // Asignar el tipo al combo según la clase del usuario
        switch (u.getClass().getSimpleName()) {
            case "Administrador":
                tipoCB.setSelectedItem(TipoUsuario.ADMINISTRATIVO);
                break;
            case "Contenidos":
                tipoCB.setSelectedItem(TipoUsuario.CONTENIDO);
                break;
            case "Limitado":
                tipoCB.setSelectedItem(TipoUsuario.LIMITADO);
                break;
            default:
                tipoCB.setSelectedItem(TipoUsuario.Seleccionar);
        }
    }

    private void guardarCambios() {
        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(this, "Busque un usuario para editar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            String nuevoNombre = nombreTF.getText();
            String nuevaPassword = new String(passwordPF.getPassword());
            int nuevaEdad = Integer.parseInt(edadTF.getText());

            manejo.editarUsuario(nuevoNombre, nuevaPassword, nuevaEdad, usuarioActual.getUserame());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
