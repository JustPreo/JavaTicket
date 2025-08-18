/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaticket;

import javaticket.Manejo.ManejoDeUsuarios;
import javaticket.Manejo.ManejoDeEventos;

/**
 *sadmadmna
 * @author user
 */
public class JavaTicket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ManejoDeUsuarios manejo = new ManejoDeUsuarios();
        ManejoDeEventos eventos = new ManejoDeEventos();
        Login login = new Login(manejo,eventos);
        login.setVisible(true);
        //SREK
        
        
        //Logica para abrir la ventana de inicio
    }
    
}
