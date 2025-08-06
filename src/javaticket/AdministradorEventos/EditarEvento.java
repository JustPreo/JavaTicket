/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket.AdministradorEventos;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javaticket.Categorias.Deportivo;
import javaticket.Categorias.Musical;
import javaticket.Categorias.Religioso;
import javaticket.Categorias.categorias;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;
import static javaticket.Manejo.ManejoDeUsuarios.userLogged;
import javaticket.Usuarios.Administrador;
import javaticket.Usuarios.Contenidos;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author user
 */
public class EditarEvento extends JFrame {

    private ManejoDeUsuarios manejo;
    private ManejoDeEventos eventos;
    private JLabel codigoL, nombreT, descripcionT, fechaT, costoT;
    private JTextField codigoTF, nombre, descripcion, costo;
    private JDateChooser fechaS;
    private JButton buscarBtn, Volver, guardarCambios;
    private JComboBox<String> c1, c2;

    public EditarEvento(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;

        setSize(500, 500);
        setTitle("Eliminar Evento");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.cyan);

        codigoL = new JLabel("Ingrese codigo del evento:");
        codigoL.setBounds(0, 20, 200, 30);
        add(codigoL);

        codigoTF = new JTextField();
        codigoTF.setBounds(160, 20, 150, 30);
        add(codigoTF);

        buscarBtn = new JButton("Buscar Evento");
        buscarBtn.setBounds(50, 50, 130, 30);
        add(buscarBtn);

        Volver = new JButton("Volver");
        Volver.setBounds(170, 360, 150, 50);
        add(Volver);

        guardarCambios = new JButton("Guardar Cambios");
        guardarCambios.setBounds(170, 300, 150, 50);
        add(guardarCambios);

        guardarCambios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });

        Volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministrarEventos menu = new AdministrarEventos(manejo, eventos);
                dispose();
            }
        });

        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEvento();
            }
        });

        //Cosos extra
        nombreT = new JLabel("Nombre del evento");
        nombreT.setBounds(30, 90, 150, 25);
        add(nombreT);

        nombre = new JTextField();
        nombre.setBounds(30, 115, 150, 30);
        add(nombre);

        descripcionT = new JLabel("Descripción");
        descripcionT.setBounds(30, 150, 150, 25);
        add(descripcionT);

        descripcion = new JTextField();
        descripcion.setBounds(30, 175, 150, 30);
        add(descripcion);

        fechaT = new JLabel("Fecha");
        fechaT.setBounds(250, 30, 150, 25);
        add(fechaT);

        fechaS = new JDateChooser();
        fechaS.setDateFormatString("yyyy-MM-dd");
        fechaS.setMinSelectableDate(new java.util.Date());
        fechaS.setBounds(250, 55, 150, 30);
        add(fechaS);

        costoT = new JLabel("Costo");
        costoT.setBounds(250, 90, 150, 25);
        add(costoT);

        costo = new JTextField();
        costo.setBounds(250, 115, 150, 30);
        add(costo);

        setVisible(true);
    }

    private void buscarEvento() {
        try {
            int codigo = Integer.parseInt(codigoTF.getText());
            categorias evento = eventos.buscarEvento(codigo);
            if (evento == null) {
                JOptionPane.showMessageDialog(this, "No se encontro evento con ese código.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Poner los cositos donde deberian ir los cositos
            nombre.setText(evento.getTitulo());
            descripcion.setText(evento.getDescripcion());
            //Formatear fechas
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            fechaS.setDate((Date) format.parse(evento.getFecha()));

            if (evento instanceof Deportivo) {
                System.out.println("A");
                eliminarCombo();
                String[] deportivo = {"Futbol", "Tenis", "Rugby", "Baseball"};
                c2 = new JComboBox<>(deportivo);
                c2.setBounds(250, 175, 150, 30);
                add(c2);
                repaint();
                c2.setSelectedIndex(buscarIndex("Deportivo", ((Deportivo) evento).getTipoDeporte()));

            } else if (evento instanceof Musical) {
                System.out.println("B");
                eliminarCombo();
                String[] musical = {"Pop", "Rock", "Rap", "Clasica", "Reggaeton", "Otro"};
                c2 = new JComboBox<>(musical);
                c2.setBounds(250, 175, 150, 30);
                add(c2);
                repaint();
                c2.setSelectedIndex(buscarIndex("Musical", ((Musical) evento).getTipoMusica()));

            } else if (evento instanceof Religioso) {
                System.out.println("C");
                eliminarCombo();//Porque no tiene otras opciones
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ingresa valores validos!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    private void eliminarCombo() {
        if (c2 != null) {
            remove(c2);
            c2 = null;
            repaint();
        }
    }

    private int buscarIndex(String tipo, String subtipo) {
        if ("Deportivo".equals(tipo)) {
            String[] deportivo = {"Futbol", "Tenis", "Rugby", "Baseball"};
            for (int i = 0; i < deportivo.length; i++) {
                if (deportivo[i].equals(subtipo)) {
                    return i;
                }
            }

        } else if ("Musical".equals(tipo)) {
            String[] musical = {"Pop", "Rock", "Rap", "Clasica", "Reggaeton", "Otro"};
            for (int i = 0; i < musical.length; i++) {
                if (musical[i].equals(subtipo)) {
                    return i;
                }
            }

        }
        return 0;

    }

    private void editar() {
        //Logica para editar
    }

    public static void main(String[] args) {
        ManejoDeUsuarios manejo = new ManejoDeUsuarios();
        ManejoDeEventos eventos = new ManejoDeEventos();

        EditarEvento a = new EditarEvento(manejo, eventos);
    }

}
