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
public class Funciones_entradaSalidaInventarioInforme extends Modelo.consultas_entradaSalidaInventarioInforme{
    
    Date now = new Date(System.currentTimeMillis());
   private java.sql.ResultSetMetaData meta;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
   
   public TableModel llenarTablaEntradaProducto(int id_empresa,String[] nombreColumnas,int[] ancho)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(consultaProductosEntrada(id_empresa), nombreColumnas, ancho);
   }
   public TableModel llenarTablaSalidaProducto(int id_empresa,String[] nombreColumnas,int[] ancho)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(consultaProductosSalida(id_empresa), nombreColumnas, ancho);
   }
   
   public TableModel BuscarProducto(int id_sucursal,String[] nombreColumnas, int[] ancho,Object texto)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(buscarProducto(id_sucursal,texto), nombreColumnas, ancho);
   } 
    
   
}
