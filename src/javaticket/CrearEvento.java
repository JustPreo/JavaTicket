/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaticket;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.InputMismatchException;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CrearEvento extends JFrame {

    ManejoDeUsuarios manejo;
    private JLabel codigoT, nombreT, descripcionT, fechaT, costoT;
    private JTextField codigo, nombre, descripcion, fecha, costo;
    private JButton crear;
    private JComboBox<String> c2;
    ManejoDeEventos eventos;

    public CrearEvento(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.eventos = eventos;
        this.manejo = manejo;
        setSize(500, 500);
        setTitle("Crear Evento");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.cyan);

        //LOGICA COMBOBOX
        String[] categorias = {"", "Deportivo", "Musical", "Religioso"};

        JComboBox<String> c1 = new JComboBox<>(categorias);
        c1.setBounds(160, 300, 150, 35);
        add(c1);

        c1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String item = c1.getSelectedItem().toString();
                System.out.println(item);

                switch (item) {
                    case "Deportivo":
                        crearCOMBO(1);
                        break;
                    case "Musical":
                        crearCOMBO(2);
                        break;
                    case "Religioso":
                        crearCOMBO(3);
                        break;
                    default:
                        crearCOMBO(3);
                        eliminarCombo();
                        break;

                }
            }
        });

        crear = new JButton("Crear");
        crear.setBounds(180, 280, 100, 50);
        add(crear);

        codigo = new JTextField();
        codigo.setBounds(0, 100, 150, 35);
        add(codigo);

        codigoT = new JLabel("Codigo");
        codigoT.setBounds(50, 70, 150, 35);
        add(codigoT);

        nombre = new JTextField();
        nombre.setBounds(0, 200, 150, 35);
        add(nombre);

        nombreT = new JLabel("Nombre del evento");
        nombreT.setBounds(20, 170, 150, 35);
        add(nombreT);

        descripcion = new JTextField();
        descripcion.setBounds(0, 300, 150, 35);
        add(descripcion);

        descripcionT = new JLabel("Descripcion del evento");
        descripcionT.setBounds(10, 270, 150, 35);
        add(descripcionT);

        fecha = new JTextField();
        fecha.setBounds(160, 100, 150, 35);
        add(fecha);

        fechaT = new JLabel("Fecha");
        fechaT.setBounds(160, 70, 150, 35);
        add(fechaT);

        costo = new JTextField();
        costo.setBounds(160, 200, 150, 35);
        add(costo);

        costoT = new JLabel("Costo");
        costoT.setBounds(160, 170, 150, 35);
        add(costoT);

        crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearEvento();

            }
        });
        setVisible(true);
    }

    public void crearCOMBO(int numero) {
        eliminarCombo();
        String[] deportivo = {"Futbol", "Tenis", "Rugby", "Baseball"};
        String[] musical = {"Pop", "Rock", "Rap", "Clasica", "Reggeaton", "Otro"};

        switch (numero) {

            case 1:
                c2 = new JComboBox<>(deportivo);
                break;

            case 2:
                c2 = new JComboBox<>(musical);
                break;

            default:
                System.out.println("A");
                break;

        }

        if (c2 != null) {
            c2.setBounds(160, 400, 150, 35);
            add(c2);
            repaint();
        }

    }

    public void eliminarCombo() {
        if (c2 != null) {
            remove(c2);
            c2 = null;
            repaint();
        }

    }

    public static void main(String[] args) {
        ManejoDeUsuarios manejo1 = new ManejoDeUsuarios();
        ManejoDeEventos eventos1 = new ManejoDeEventos();

        CrearEvento crear = new CrearEvento(manejo1, eventos1);
    }

    public boolean crearEvento() {

        if (revisarEmpty()) {
            try {
                Deportivo evento = new Deportivo(parseInt(codigo.getText()), nombre.getText(), fecha.getText(), fecha.getText(), parseDouble(costo.getText()), "b", "a", "a");
                System.out.println("CREADOOO");

                if (eventos.crearEvento(evento)) {
                    System.out.println("A");
                } else {
                    System.out.println("ERROR");
                }
            } catch (Exception e) {
                System.out.println("ERORRRRRRRR");
                return false;
            }

        }
        System.out.println("EMPTY");
        return false;
    }

    public boolean revisarEmpty() {
        return !codigo.getText().equals("") && !nombre.getText().equals("") && !descripcion.getText().equals("") && !fecha.getText().equals("") && !costo.getText().equals("");
    }

}
