/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javax.swing.JOptionPane;
import Constructores.Constructor_Usuario;
import Fuentes.EncriptadorPassword;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;

/**
 *
 * @author Nestor1
 */
public class Funciones_Login extends Modelo.consultas_login{
    
   public Constructor_Usuario usuario_control = new Constructor_Usuario();
   Date now = new Date(System.currentTimeMillis());
   private ResultSet rs= null;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    EncriptadorPassword encrip = new EncriptadorPassword("20150112");
    public boolean verificarUsuario(Object usuario, Object password)
    {
        Object passwordBD = consultasPassword(usuario);
        password = encrip.encrypt(password.toString());
        if(usuario.equals("") || password.equals(""))
        {
            return false;
        }
        else
        {
            try{
            if(password.toString().equalsIgnoreCase(passwordBD.toString()))
            {
                return true;
            }
            else
            {
                return false;
            }
            }
            catch(Exception ex)
            {
               return false; 
            }
                    
        }
    }
    
}
