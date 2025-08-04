/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket.Usuarios;

/**
 *
 * @author user
 */
public class UsuarioTemplate {

    private String nombre;
    private String username;
    private String password;
    private int edad;
    //0,1,2 (0 = Administrador , 1 = Contenidos , 2 = Limitado)

    public UsuarioTemplate(String nombre, String username, String password, int edad) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.edad = edad;
        
    }

    public String getNombre() {
        return nombre;
    }

    public String getUserame() {
        return username;
    }

    public boolean mismaPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }
    
    public int getEdad()
    {
    return edad;
    }
    
    
}
