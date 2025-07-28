/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaticket;

import java.awt.Color;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class CrearEvento extends JFrame{
    ManejoDeUsuarios manejo;
    private JLabel codigoT,nombreT,descripcionT,fechaT,costoT;
    private JTextField codigo,nombre,descripcion,fecha,costo;
    public CrearEvento(ManejoDeUsuarios manejo)
    {
        this.manejo = manejo;
        setSize(500, 500);
        setTitle("Crear Evento");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.cyan);
        
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
        
        fechaT = new JLabel("Codigo");
        fechaT.setBounds(50, 70, 150, 35);
        add(fechaT);
        
        costo = new JTextField();
        costo.setBounds(160, 200, 150, 35);
        add(costo);
        
        
        
        
        
        
        
        
        
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        ManejoDeUsuarios manejo1 = new ManejoDeUsuarios();
        CrearEvento crear = new CrearEvento(manejo1);
    }
    
}