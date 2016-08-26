/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Fuentes.Grillas;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_Historial extends Modelo.consultas_historial{
    
     Date now = new Date(System.currentTimeMillis());
    private java.sql.ResultSetMetaData meta;
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    
    
    public TableModel llenarHistorial(int id_sucursal,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(consultaLlenarHistorial(id_sucursal), nombreColumnas, ancho);
   }
    
    public TableModel buscarHistorial(int id_sucursal,Object op,String opcion,String[] nombreColumnas, int[] ancho)
    {
        Grillas gr = new Grillas();
       return gr.CargarGrd(consultaBuscarHistorial(opcion,id_sucursal,op), nombreColumnas, ancho);
    }
    
}
