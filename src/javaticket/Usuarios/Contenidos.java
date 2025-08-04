/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket.Usuarios;

import java.util.ArrayList;
import javaticket.Categorias.categorias;

/**
 *
 * @author user
 */
public class Contenidos extends UsuarioTemplate{
    
    ArrayList<categorias> eventosCreados;
    
    public Contenidos(String nombre, String username, String password, int edad)
    {
    super(nombre,username,password,edad);
    eventosCreados = new ArrayList<>();
    }
    
    
    public void agregarEvento(categorias evento)
    {
    eventosCreados.add(evento);
    }
    
    public boolean creador(int codigo) {
        for (categorias v : eventosCreados) {

            if (codigo == v.getCodigo()) {
                return true;
            }
        }
        return false;

    }
}
