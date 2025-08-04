/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket.Manejo;
import java.util.ArrayList;
import java.util.Calendar;
import javaticket.Categorias.categorias;
import javaticket.Usuarios.Administrador;
import javaticket.Usuarios.Contenidos;
import javaticket.Usuarios.UsuarioTemplate;

/**
 *
 * @author user
 */

public class ManejoDeUsuarios {
    
    //Funciones que tiene crearUsuario()|verificarContra()|verificarUsername()|logearse()|buscarU()|
    
    public static ArrayList<UsuarioTemplate> usuarios;
    public static UsuarioTemplate userLogged = null;
    public static Calendar fechaActual = null;
    public ManejoDeUsuarios()
    {
    usuarios = new ArrayList<UsuarioTemplate>();
    usuarios.add(new Administrador("admin","admin","supersecreto",69));
    }
    
    public boolean crearUsuario(String nombre , String username , String password , int edad)
    {
        if (verificarContra(password) && verificarUsername(username))
        {
        usuarios.add(new UsuarioTemplate(nombre,username,password,edad));
        return true;
        }
    
    return false;
    }
    
    private boolean verificarContra(String contra) {
        
        System.out.println(contra.length());
        if (contra.length() >= 8
                && contra.matches(".*\\d.*")
                && contra.matches(".*[~`!@#$%^&*()\\-+={}\\[\\]|\\\\:;\"'<>./?].*")
                && contra.matches(".*[A-Z].*"))
        {
            System.out.println("Contra valida");
            return true;
        }
        return false;
    }
    
    private boolean verificarUsername(String username)
    {
    for (UsuarioTemplate usuario:usuarios)
    {
    if (usuario.getUserame().equals(username)){
    return false;//No valido
    }
    }
    return true;
    }
    
    public boolean logearse(String username,String password)
    {
    UsuarioTemplate u = buscarU(username);
        
        if (u != null)
    {
        if (u.mismaPassword(password))
        {
            userLogged = u;
        return true;
        }
    }
    return false;
    }
    
    public UsuarioTemplate buscarU (String username)
    {
    for (UsuarioTemplate U:usuarios)
    {
        if (U.getUserame().equals(username))
        {
        return U;
        }
    }
    
    return null;
    }
    
    public void agregarArray(categorias evento) {
    if (userLogged instanceof Administrador) {
        ((Administrador) userLogged).agregarEvento(evento);
        System.out.println("A");
    } else if (userLogged instanceof Contenidos) {
        ((Contenidos) userLogged).agregarEvento(evento);
        System.out.println("A");
    }
}
}

