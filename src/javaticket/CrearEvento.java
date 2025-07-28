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
