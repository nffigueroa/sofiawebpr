/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Fuentes.Grillas;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_controlSesion extends Modelo.consulta_sesion{
    
    
    public TableModel llenarTablaSesion(int id_empresa,String[]nombre,int[] ancho)
    {
        Grillas gr = new Grillas();
        return gr.CargarGrd(consultaLlenarTablaSesion(id_empresa), nombre, ancho);
    }
    
    public TableModel llenarTablaSesionBuscar(int id_empresa,String[]nombre,int[] ancho,String opcion,String texto)
    {
        Grillas gr = new Grillas();
        return gr.CargarGrd(consultaLlenarTablaSesionBuscar(id_empresa,opcion,texto), nombre, ancho);
    }
    
}
