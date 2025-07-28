/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ManejoDeEventos {
    public static ArrayList<categorias> eventos = new ArrayList<categorias>();
    
    public ManejoDeEventos()
    {
    
    }
   
    
    public boolean revisarFechas(String fecha)
    {
        for (categorias evento:eventos)
        {
        if(evento.getFecha().equals(fecha))
        {
            System.out.println("Misma Fecha");
        return false;
        }   
        }
        
        
    return true;
    }
    
    public boolean crearEvento(categorias Evento)
    {
        if (revisarFechas(Evento.getFecha())){
        eventos.add(Evento);
        return true;
        }
        return false;
    
    
    }
    
    
    
}
