package javaticket.AdministradorReportes;

import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;
import javaticket.Usuarios.UsuarioTemplate;
import javaticket.Categorias.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import javaticket.Usuarios.Administrador;
import javaticket.Usuarios.Contenidos;
import javaticket.Usuarios.Limitado;

public class verPerfil extends JFrame {

    private UsuarioTemplate usuarioActual;
    private ManejoDeUsuarios manejo;
    private ManejoDeEventos eventos;

    private JTextField nombreTF, usernameTF, edadTF;
    private JTextField tipoU;
    private JTable tablaEventos;
    private DefaultTableModel modeloTabla;
    private JButton volverBtn;

    public verPerfil(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;
        this.usuarioActual = manejo.getUser();

        setSize(700, 600);
        setTitle("Perfil del Usuario");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#394B66"));
        Font fuente = new Font("Roboto", Font.BOLD, 16);

        // Labels y campos de usuario
        JLabel nombreL = new JLabel("Nombre completo:");
        nombreL.setBounds(30, 20, 150, 25);
        nombreL.setForeground(Color.WHITE);
        nombreL.setFont(fuente);
        add(nombreL);

        nombreTF = new JTextField(usuarioActual.getNombre());
        nombreTF.setBounds(180, 20, 200, 25);
        nombreTF.setFont(fuente);
        nombreTF.setEditable(false);
        add(nombreTF);

        JLabel usernameL = new JLabel("Username:");
        usernameL.setBounds(30, 60, 150, 25);
        usernameL.setForeground(Color.WHITE);
        usernameL.setFont(fuente);
        add(usernameL);

        usernameTF = new JTextField(usuarioActual.getUserame());
        usernameTF.setBounds(180, 60, 200, 25);
        usernameTF.setFont(fuente);
        usernameTF.setEditable(false);
        add(usernameTF);

        JLabel passwordL = new JLabel("Tipo Usuario:");
        passwordL.setBounds(30, 100, 150, 25);
        passwordL.setForeground(Color.WHITE);
        passwordL.setFont(fuente);
        add(passwordL);

        tipoU = new JTextField("");
        tipoU.setBounds(180, 100, 200, 25);
        tipoU.setFont(fuente);
        tipoU.setEditable(false);
        

        if (usuarioActual instanceof Administrador) {
            tipoU.setText("Administrador");
        } else if (usuarioActual instanceof Contenidos) {
            tipoU.setText("Contenidos");
        } else if (usuarioActual instanceof Limitado) {
            tipoU.setText("Limitado");
        }
        
        add(tipoU);

        JLabel edadL = new JLabel("Edad:");
        edadL.setBounds(30, 140, 150, 25);
        edadL.setForeground(Color.WHITE);
        edadL.setFont(fuente);
        add(edadL);

        edadTF = new JTextField(String.valueOf(usuarioActual.getEdad()));
        edadTF.setBounds(180, 140, 50, 25);
        edadTF.setFont(fuente);
        edadTF.setEditable(false);
        add(edadTF);

        // Tabla de eventos creados
        String[] columnas = {"ID", "TIPO", "TITULO", "MONTO"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaEventos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaEventos);
        scroll.setBounds(30, 200, 620, 300);
        add(scroll);

        cargarEventosUsuario();

        // Botón volver
        volverBtn = new JButton("Volver");
        volverBtn.setBounds(280, 520, 120, 30);
        volverBtn.setFont(fuente);
        volverBtn.setBackground(Color.decode("#EAE9D3"));
        volverBtn.setForeground(Color.BLACK);
        add(volverBtn);

        volverBtn.addActionListener(e -> {
            new AdministradorReportes(manejo, eventos).setVisible(true);
            dispose();
        });
    }

    private void cargarEventosUsuario() {
        modeloTabla.setRowCount(0); // limpiar tabla

        for (categorias evento : eventos.eventos) {

            boolean esCreador = false;

            if (usuarioActual instanceof Administrador) {
                esCreador = ((Administrador) usuarioActual).creador(evento.getCodigo());
                tipoU.setText("Administrador");
            } else if (usuarioActual instanceof Contenidos) {
                esCreador = ((Contenidos) usuarioActual).creador(evento.getCodigo());
                tipoU.setText("Contenidos");
            } else if (usuarioActual instanceof Limitado) {
                tipoU.setText("Limitado");
            }
            if (esCreador) {
                String tipo = "";
                if (evento instanceof Deportivo) {
                    tipo = "DEPORTIVO";
                } else if (evento instanceof Religioso) {
                    tipo = "RELIGIOSO";
                } else if (evento instanceof Musical) {
                    tipo = "MUSICAL";
                }

                double monto = evento.getCosto();
                if (evento instanceof Religioso) {
                    monto += 2000;
                }
                if (evento instanceof Musical) {
                    monto += evento.getCosto() * 0.3;
                }

                modeloTabla.addRow(new Object[]{
                    evento.getCodigo(), tipo, evento.getTitulo(), String.format("%.2f", monto)
                });
            }
        }
    }
}
