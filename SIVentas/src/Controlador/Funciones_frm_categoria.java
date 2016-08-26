/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Fuentes.Grillas;
import Modelo.Consultas_Generales;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_categoria extends Modelo.consultas_categoria{
     Date now = new Date(System.currentTimeMillis());
    private java.sql.ResultSetMetaData meta;
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    
    public TableModel llenarFormaPago(String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(llenarTabla_Categoria(), nombreColumnas, ancho);
   }
   
    public boolean InsertarCategoria(String categoria,int id_usuario)
    {
        categoria = categoria.toUpperCase();
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("InsertarCategoria", id_usuario, date.format(now),hora.format(now), "Se registra categoria "+categoria+"");
        consultaRegistrarCategoria(categoria,id_usuario);
        return false;
    }
    
}
    

