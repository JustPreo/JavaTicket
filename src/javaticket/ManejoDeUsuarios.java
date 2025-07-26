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

public class ManejoDeUsuarios {
    
    public static ArrayList<UsuarioTemplate> usuarios;
    public static UsuarioTemplate userLogged = null;
    public ManejoDeUsuarios()
    {
    usuarios = new ArrayList<UsuarioTemplate>();
    usuarios.add(new UsuarioTemplate("admin","admin","supersecreto",69,0));  //0,1,2 (0 = Administrador , 1 = Contenidos , 2 = Limitado)
    }
    
    public boolean crearUsuario(String nombre , String username , String password , int edad , int tipoU)
    {
        if (verificarContra(password) && verificarUsername(username))
        {
        usuarios.add(new UsuarioTemplate(nombre,username,password,edad,tipoU));
        return true;
        }
    
    return false;
    }
    
    public boolean verificarContra(String contra) {
        
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
    
    public boolean verificarUsername(String username)
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
}

