/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Fuentes.Grillas;
import Constructores.Constructor_Proveedor;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_informeStock extends Modelo.consultas_informeStock{
    
    Date now = new Date(System.currentTimeMillis());
   private java.sql.ResultSetMetaData meta;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
   DecimalFormat formateador2 = new DecimalFormat("###,###.##");
   Constructores.Constructor_Proveedor proveedor = new Constructor_Proveedor();
  
   
   public TableModel llenarCoincidenciasProveedor(Object id_inventario,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       int id_producto=0,id_categoria=0;
       id_producto = consultaIdProducto(id_inventario);
       id_categoria=consultaIdCategoria(id_producto);
       return gr.CargarGrd(consultaPosiblesProveedores(id_categoria), nombreColumnas, ancho);
   }
   
   public Constructores.Constructor_Proveedor datosProveedor(Object id_proveedor)
   {
       ResultSet rh = consultaDatosProveedor(id_proveedor);
        try {
            while(rh.next())
            {
                proveedor.setMail_Proveedor(rh.getString("PRO.mail_proveedor"));
                proveedor.setDireccion_proveedor(rh.getString("PRO.direccion_proveedor"));
                proveedor.setContacto_empresa(rh.getString("PRO.contaco_empresa"));
                proveedor.setTelefono(rh.getString("PRO.telefono_proveedor"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_frm_informeStock.class.getName()).log(Level.SEVERE, null, ex);
        }
       return proveedor;
   }
   
   public TableModel llenarTablaInventarioEmpresa(int id_empresa,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(llenarTabla_inventario(id_empresa), nombreColumnas, ancho);
   }
   
   public TableModel buscarProductoInventario(int id_sucursal,String nombre,String opcion,String []nombreColumnas,int []ancho)
    {
       Grillas gr = new Grillas();
       
       return gr.CargarGrd(consultaBuscarProductoInventario(nombre,opcion,id_sucursal), nombreColumnas, ancho);
    }
       
}
