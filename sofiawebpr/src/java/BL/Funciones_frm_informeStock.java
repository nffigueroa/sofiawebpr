/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

//import Fuentes.Grillas;
import Constructores.Constructor_Proveedor;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_informeStock extends DA.consultas_informeStock{
    
    Date now = new Date(System.currentTimeMillis());
   private java.sql.ResultSetMetaData meta;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
   DecimalFormat formateador2 = new DecimalFormat("###,###.##");
   Constructores.Constructor_Proveedor proveedor = new Constructor_Proveedor();
   BL.Funciones_Generales general = new Funciones_Generales();
   ResultSet rs = null;
  
   
   public ArrayList llenarCoincidenciasProveedor(Object id_inventario)
   {
       return null;
//     //  Grillas gr = new Grillas();
//       int id_producto=0,id_categoria=0;
//       id_producto = consultaIdProducto(id_inventario);
//       id_categoria=consultaIdCategoria(id_producto);
//       return gr.CargarGrd(consultaPosiblesProveedores(id_categoria), nombreColumnas, ancho);
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
   
   public ArrayList llenarTablaInventarioEmpresa(int idSucursal)
   {
       try {
            int idEmpresa = general.ideEmpresaXIdeSucursal(idSucursal);    
            return general.resultSetToArrayList(llenarTabla_inventario(idEmpresa));
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
//       Grillas gr = new Grillas();
//       return gr.CargarGrd(llenarTabla_inventario(id_empresa), nombreColumnas, ancho);
   }
   
   public ArrayList buscarProductoInventario(int id_sucursal,String nombre,String opcion)
    {
        return null;
//       Grillas gr = new Grillas();
//       
//       return gr.CargarGrd(consultaBuscarProductoInventario(nombre,opcion,id_sucursal), nombreColumnas, ancho);
    }
       
}
