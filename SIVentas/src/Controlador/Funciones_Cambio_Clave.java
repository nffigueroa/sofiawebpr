/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javax.swing.JOptionPane;
import Controlador.Funciones_Login;
import Fuentes.EncriptadorPassword;
/**
 *
 * @author Nestor1
 */
public class Funciones_Cambio_Clave extends Modelo.consultas_cambio_clave{
    EncriptadorPassword encrip = new EncriptadorPassword("20150112");
    
    public void funcionCambiar_Clave(int id_usuario)
    {
        Funciones_Login fun = new Funciones_Login();
        Object password= fun.consultasPasswordCambioClave(id_usuario);
         Object ingresarClave = JOptionPane.showInputDialog("Ingresar CLAVE ACTUAL");
        if(ingresarClave.toString().isEmpty())
        {
           
        }
        else
        {
            ingresarClave = encrip.encrypt(ingresarClave.toString());
         if(ingresarClave.equals(password.toString()))
         {
             Object nuevaClave = JOptionPane.showInputDialog("INGRESE NUEVA CLAVE!");
             encrip.encrypt(nuevaClave.toString());
             if(consultaRegistrarCambioClave(id_usuario, nuevaClave))
             {
                 JOptionPane.showMessageDialog(null, "CLAVE ACTUALIZADA!");
             }
         }
            else
            {
             JOptionPane.showMessageDialog(null, "CONTRASEÑA NO VALIDA!",null,0);
            }
        }
    }
    
}
