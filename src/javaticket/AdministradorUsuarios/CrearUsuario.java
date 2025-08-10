package javaticket.AdministradorUsuarios;

/**
 *
 * @author user
 */
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaticket.Login;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;

public class CrearUsuario extends JFrame {

    // Enum para tipos de usuario
    public enum TipoUsuario {
        Seleccionar,
        ADMINISTRATIVO,
        CONTENIDO,
        LIMITADO
    }

    private JLabel nombreL, usernameL, passwordL, edadL, tipoL;
    private JTextField nombreTF, usernameTF, edadTF;
    private JPasswordField passwordPF;
    private JComboBox<TipoUsuario> tipoCB;
    private JButton crearBtn, volverBtn;
    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;

    public CrearUsuario(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;
        setSize(500, 500);
        setTitle("Crear Usuario");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        //Labels
        nombreL = new JLabel("Nombre completo");
        nombreL.setBounds(30, 30, 150, 25);
        add(nombreL);

        nombreTF = new JTextField();
        nombreTF.setBounds(30, 55, 150, 30);
        add(nombreTF);

        usernameL = new JLabel("Username (único)");
        usernameL.setBounds(30, 90, 150, 25);
        add(usernameL);

        usernameTF = new JTextField();
        usernameTF.setBounds(30, 115, 150, 30);
        add(usernameTF);

        passwordL = new JLabel("Password");
        passwordL.setBounds(30, 150, 150, 25);
        add(passwordL);

        passwordPF = new JPasswordField();
        passwordPF.setBounds(30, 175, 150, 30);
        add(passwordPF);

        edadL = new JLabel("Edad");
        edadL.setBounds(250, 30, 150, 25);
        add(edadL);

        edadTF = new JTextField();
        edadTF.setBounds(250, 55, 100, 30);
        add(edadTF);

        tipoL = new JLabel("Tipo de usuario");
        tipoL.setBounds(250, 90, 150, 25);
        add(tipoL);

        tipoCB = new JComboBox<>(TipoUsuario.values());
        tipoCB.setBounds(250, 115, 130, 30);
        add(tipoCB);

        crearBtn = new JButton("Crear Usuario");
        crearBtn.setBounds(160, 300, 150, 40);
        add(crearBtn);

        volverBtn = new JButton("Volver");
        volverBtn.setBounds(160, 360, 150, 40);
        add(volverBtn);

        volverBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });

        crearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CrearU();

            }
        });

        setVisible(true);
    }

    private void limpiarCampos() {
        /*
        private JTextField nombreTF, usernameTF, edadTF;
        private JPasswordField passwordPF;
         */

        nombreTF.setText("");
        usernameTF.setText("");
        edadTF.setText("");
        passwordPF.setText("");
        tipoCB.setSelectedItem("Seleccionar");
    }

    public void CrearU() {
        try {
            String nombre = nombreTF.getText();
            String username = usernameTF.getText();
            String password = new String(passwordPF.getPassword());
            int edad = Integer.parseInt(edadTF.getText());
            TipoUsuario tipo = (TipoUsuario) tipoCB.getSelectedItem();
            String tipoStr = tipo.toString();

            switch (tipo)//Probando con enums
            {
                case ADMINISTRATIVO:
                    if (manejo.crearUsuario(nombre, username, password, edad, tipoStr)) {
                        limpiarCampos();
                    }
                    break;
                case CONTENIDO:
                    if (manejo.crearUsuario(nombre, username, password, edad, tipoStr)) {
                        limpiarCampos();
                    }
                    break;
                case LIMITADO:
                    if (manejo.crearUsuario(nombre, username, password, edad, tipoStr)) {
                        limpiarCampos();
                    }
                    break;
                case Seleccionar:
                    JOptionPane.showMessageDialog(null, "Ocupa elegir uno de los tipos de usuarios", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Ingresa caracteres validos", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args) {
        ManejoDeUsuarios manejo = new ManejoDeUsuarios();
        ManejoDeEventos eventos = new ManejoDeEventos();
        CrearUsuario crear = new CrearUsuario(manejo, eventos);

    }
}
