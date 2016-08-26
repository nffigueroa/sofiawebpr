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
public class Funciones_forma_pago extends Modelo.consultas_Forma_Pago{
    
   Date now = new Date(System.currentTimeMillis());
   private java.sql.ResultSetMetaData meta;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
   
   public TableModel llenarFormaPago(String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(llenarTabla_Marca(), nombreColumnas, ancho);
   }
   
    public boolean insertarFormaPago(String marca, int id_usuario)
    {
        return  consultaRegistrarMarca(marca,id_usuario);
    }
    
}
