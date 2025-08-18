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
import javaticket.Usuarios.Limitado;
import javaticket.Usuarios.UsuarioTemplate;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public final class ManejoDeUsuarios {

    public static ArrayList<UsuarioTemplate> usuarios;
    public static UsuarioTemplate userLogged = null;
    public static Calendar fechaActual = null;

    public ManejoDeUsuarios() {
        usuarios = new ArrayList<>();
        usuarios.add(new Administrador("admin", "admin", "supersecreto", 69));
    }

    public enum TipoUsuario {
        ADMINISTRATIVO,
        CONTENIDO,
        LIMITADO
    }

    public boolean crearUsuario(String nombre, String username, String password, int edad, String tipoUsuario) {
        if (verificarContra(password) && verificarUsername(username)) {
            UsuarioTemplate nuevoUsuario = null;

            switch (tipoUsuario) {
                case "ADMINISTRATIVO":
                    nuevoUsuario = new Administrador(nombre, username, password, edad);
                    break;
                case "CONTENIDO":
                    nuevoUsuario = new Contenidos(nombre, username, password, edad);
                    break;
                case "LIMITADO":
                    nuevoUsuario = new Limitado(nombre, username, password, edad);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Tipo de usuario inválido");//Por si acaso
                    return false;
            }

            if (nuevoUsuario != null) {
                usuarios.add(nuevoUsuario);
                JOptionPane.showMessageDialog(null, "Usuario Creado Exitosamente");
                return true;
            }

        }
        return false;
    }

    private boolean verificarContra(String contra) {
        System.out.println(contra.length());
        if (contra.length() >= 8
                && contra.matches(".*\\d.*")
                && contra.matches(".*[~`!@#$%^&*()\\-+={}\\[\\]|\\\\:;\"'<>./?].*")
                && contra.matches(".*[A-Z].*")) {
            System.out.println("Contra valida");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Contrasena requiere al menos:" + "\n 8 Caracteres , Simbolos , 1 Mayuscula , 1 Minuscula");
        return false;
    }

    private boolean verificarUsername(String username) {
        for (UsuarioTemplate usuario : usuarios) {
            if (usuario.getUserame().equals(username)) {
                JOptionPane.showMessageDialog(null, "Ya existe con usuario con ese username", "Error", JOptionPane.ERROR_MESSAGE);
                return false; // No válido, username ya existe
            }
        }
        return true;
    }

    public boolean logearse(String username, String password) {
        UsuarioTemplate u = buscarU(username);

        if (u != null) {
            if (u.mismaPassword(password)) {
                userLogged = u;
                return true;
            }
        }
        return false;
    }

    public UsuarioTemplate buscarU(String username) {
        for (UsuarioTemplate U : usuarios) {
            if (U.getUserame().equalsIgnoreCase(username)) {
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

    public boolean eliminarUsuario(String username) {
        UsuarioTemplate u = buscarU(username);
        if (u != null && !(u.getUserame().equalsIgnoreCase(getUser().getUserame()))) { //No se si poner lo de administrador , supongo que si se pueden borrar?
            usuarios.remove(u);
            //Osea , esto remueve directamente el objeto sin necesidad de tener el index
            return true;
        }

        if (u != null & u.getUserame().equalsIgnoreCase(getUser().getUserame())) {
            //JOptionPane.showMessageDialog(null, "No se puede borrar el usuario logeado", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro un usuario con ese username", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;

    }
    
    public boolean editarUsuario(String nombre,String password,int edad,String username)
    {
        //Saco el username para buscar el usuario
        UsuarioTemplate u = buscarU(username);
        if (verificarContra(password)&&(edad>0))
        {
            u.setEdad(edad);
            u.setPass(password);
            u.setNombre(nombre);
            JOptionPane.showMessageDialog(null, "Usuario editado correctamente");
            return true;
        }
        return false;
    
    }
    
    public UsuarioTemplate getUser()
    {
    return userLogged;
    }
    
   
}
