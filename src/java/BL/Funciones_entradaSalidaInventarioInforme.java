/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

//import Fuentes.Grillas;//
import BF.*;
import BL.Funciones_Generales;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_entradaSalidaInventarioInforme extends DA.consultas_entradaSalidaInventarioInforme{
    
    Date now = new Date(System.currentTimeMillis());
   private java.sql.ResultSetMetaData meta;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
   BL.Funciones_Generales general = new  Funciones_Generales();
   
   public ArrayList llenarTablaEntradaProducto(int idSucursal)
   {
       try {
           int idEmpresa = general.ideEmpresaXIdeSucursal(idSucursal);
           return general.resultSetToArrayList(consultaProductosEntrada(idEmpresa));
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
//       Grillas gr = new Grillas();
//       return gr.CargarGrd(consultaProductosEntrada(id_empresa), nombreColumnas, ancho);
   }
   public ArrayList llenarTablaSalidaProducto(int idSucursal)
   {
       try {
           int idEmpresa = general.ideEmpresaXIdeSucursal(idSucursal);
           return general.resultSetToArrayList(consultaProductosSalida(idEmpresa));
       } catch (Exception e) {
           return null;
       }
//       Grillas gr = new Grillas();
//       return gr.CargarGrd(consultaProductosSalida(id_empresa), nombreColumnas, ancho);
   }
   
//   public TableModel BuscarProducto(int id_sucursal,String[] nombreColumnas, int[] ancho,Object texto)
//   {
//       Grillas gr = new Grillas();
//       return gr.CargarGrd(buscarProducto(id_sucursal,texto), nombreColumnas, ancho);
//   } 
    
   
}
