/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Fuentes.Grillas;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import Modelo.Consultas_Generales;
import java.text.DecimalFormat;
/**
 *
 * @author Nestor1
 */
public class Funciones_Entrada_Inventario extends Modelo.consultas_Entrada_Inventario{
   
   private java.sql.ResultSetMetaData meta;
   Date now = new Date(System.currentTimeMillis());
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
   public ResultSet rh = null;
   DecimalFormat formateador = new DecimalFormat("###,###");
   public boolean descontarCantidad(Object id_producto_inventario,float cantidad,Object motivo, int id_usuario)
   {
       motivo=funcionConsultarIdMotivo(motivo);
       Funciones_frm_factura fun = new Funciones_frm_factura();
       float cantidad_bd;
       cantidad_bd = fun.existenciaProductoInventario(id_producto_inventario);
       cantidad_bd = cantidad_bd-cantidad;
        fun.descontarCantidadProducto(id_producto_inventario, cantidad_bd);
       return consultaRegistrarDescuentoProducto(id_producto_inventario, motivo,cantidad,id_usuario);
   }
   
   public boolean agregarCantidadProductoExistente(int id_producto_inventario,float cantidad,int id_usuario,float cantidad2)
   {
       if(consultaRegistrarCantidadProductoExistente(cantidad, id_producto_inventario))
       {
Funciones_Generales fun = new Funciones_Generales();
           fun.registrarHistorialEntradaInventairio(id_producto_inventario, cantidad2, id_usuario);
           fun.registrarHistorial("agregarCantidadProductoExistente", id_usuario, date.format(now),hora.format(now), "Se registro producto en inventario ID:"+id_producto_inventario+"");           
       }
       else
       {
           
       }
       return true;
   }
   public Object totalInversionInventario(int id_sucursal)
    {
        float cantidad=0,valor=0,total=0 ;
        Object total_mostrar;
        try {
            Modelo.consultas_Entrada_Inventario mol = new Funciones_Entrada_Inventario();
            rh = mol.consultaCantidadYCostoProducto(id_sucursal);
            while(rh.next())
            {
                cantidad = rh.getFloat("PROD.cantidad_producto_inventario");
                valor = rh.getFloat("PROD.precio_secundario_producto_inventario");
                total = total + (cantidad*valor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        total_mostrar = String.valueOf(formateador.format(total));
        return total_mostrar;
    }
   public Object funcionCuantoDineroInventario(int id_sucursal)
   {
       rh = llenarTabla_inventario(id_sucursal);
        float total=0,cantidad=0,valor_cantidad=0;
        Object total_mostrar=0;
       try {
           while (rh.next())
           {
               cantidad = rh.getFloat("PRO.cantidad_producto_inventario");
               valor_cantidad = rh.getFloat("PRO.precio_producto_inventario");
               total = total + (cantidad*valor_cantidad);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       total_mostrar = String.valueOf(formateador.format(total));
       return total_mostrar;
   }
   
   public Object funcionCuantoGanaciaInventario(int id_sucursal)
   {
       rh = llenarTabla_inventario(id_sucursal);
        float total=0,cantidad=0,valor_cantidad=0,precio_compra = 0;
        Object total_mostrar=0;
       try {
           while (rh.next())
           {
               cantidad = rh.getFloat("PRO.cantidad_producto_inventario");
               valor_cantidad = rh.getFloat("PRO.precio_producto_inventario");
               precio_compra = rh.getFloat("PRO.precio_secundario_producto_inventario");
               total = total + (cantidad*valor_cantidad);
               total = total - (precio_compra*cantidad);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       total_mostrar = String.valueOf(formateador.format(total));
       return total_mostrar;
   }
   public Object funcionCuantoGanaciaInventarioPorBusqueda(String nombre,String opcion,int id_sucursal)
   {
       rh = consultaBuscarProductoInventario(nombre,opcion,id_sucursal);
        float total=0,cantidad=0,valor_cantidad=0,precio_compra = 0;
        Object total_mostrar=0;
       try {
           while (rh.next())
           {
               cantidad = rh.getFloat("PRO.cantidad_producto_inventario");
               valor_cantidad = rh.getFloat("PRO.precio_producto_inventario");
               precio_compra = rh.getFloat("PRO.precio_secundario_producto_inventario");
               total = total + (cantidad*valor_cantidad);
               total = total - (precio_compra*cantidad);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       total_mostrar = String.valueOf(formateador.format(total));
       return total_mostrar;
   }
   
   public Object funcionCuantoDineroInventarioPorBusqueda(String nombre,String opcion,int id_sucursal)
   {
       rh = consultaBuscarProductoInventario(nombre,opcion,id_sucursal);
        float total=0,cantidad=0,valor_cantidad=0;
        Object total_mostrar=0;
       try {
           while (rh.next())
           {
               cantidad = rh.getFloat("PRO.cantidad_producto_inventario");
               valor_cantidad = rh.getFloat("PRO.precio_producto_inventario");
               total = total + (cantidad*valor_cantidad);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
        total_mostrar = String.valueOf(formateador.format(total));
       return total_mostrar;
   }
   
    public TableModel llenarTablaInventarioFunciones(int id_sucursal,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       rh = llenarTabla_inventario(id_sucursal);
       return gr.CargarGrd(rh, nombreColumnas, ancho);
   }
    
   public Object[] llenarComboProveedor()
    {
        int columnas=0,i=0,filas=0,q=0;
        Object [] aux = null;
        ResultSet r= null;   
        r=consultaLlenarComboProveedor();
       try {
           meta= r.getMetaData();
           columnas= meta.getColumnCount();
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
       }
        try {
            while(r.next())
            {
                filas=filas+1;
            }
            aux= new Object[filas];
            r.beforeFirst();
            while(r.next())
            { 
                if(q==0)
                {
                    r.first();
                    for ( i = 1; i <= columnas; i++) 
                    {
                        aux[q]=r.getObject(i);
                    }
                    q=q+1;
                }
                else
                {
                    for ( i = 1; i <= columnas; i++) 
                    {
                        aux[q]=r.getObject(i);
                    }
                    q=q+1;
                }
            }
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
           ex.printStackTrace();
       }
        return aux;
    } 
   
    public Object[] llenarComboSucursal(int id_sucursal)
    {
        int columnas=0,i=0,filas=0,q=0,id_empresa=0;
        Object [] aux = null;
        ResultSet r= null;   
        id_empresa= consultaIdEmpresa(id_sucursal);
        r=consultaLlenarComboSucursal(id_empresa);
       try {
           meta= r.getMetaData();
           columnas= meta.getColumnCount();
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
       }
        try {
            while(r.next())
            {
                filas=filas+1;
            }
            aux= new Object[filas];
            r.beforeFirst();
            while(r.next())
            { 
                if(q==0)
                {
                    r.first();
                    for ( i = 1; i <= columnas; i++) 
                    {
                        aux[q]=r.getObject(i);
                    }
                    q=q+1;
                }
                else
                {
                    for ( i = 1; i <= columnas; i++) 
                    {
                        aux[q]=r.getObject(i);
                    }
                    q=q+1;
                }
            }
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
           ex.printStackTrace();
       }
        return aux;
    } 
   
  public boolean registrarProductoInventario(int id_sucursal_sesion,Object id_producto,Object cantidad,Object stock,Object sucursal,Object proveedor,
            Object barras, float precio1, float precio2, String iva, String expiracion, int id_usuario_sesion)
  {
      Funciones_Generales con_generales = new Funciones_Generales();
      con_generales.registrarHistorial("registrarProductoInventario", id_usuario_sesion, date.format(now),hora.format(now), "Se registro producto en inventario ID:"+id_producto+"");
      float utilidad =0;
      utilidad = Math.round(precio1-precio2);
      Object id_sucursal,id_proveedor,id_usuario;
      id_usuario= id_usuario_sesion;//con_usuario.getId_usuario();
      id_sucursal=consultaIdSucursal(sucursal);
      id_proveedor=consultaIdProveedor(proveedor);
      consultaRegistrarProductoInventario(id_producto, cantidad, stock, id_sucursal, id_proveedor, barras, precio1, precio2, iva, expiracion,id_usuario,utilidad,date.format(now));
      int id_inventario;
      id_inventario = con_generales.consultaUltimoIdInventario(Integer.parseInt(id_sucursal.toString()));
      con_generales.registrarHistorialEntradaInventairio(id_inventario, cantidad, id_usuario_sesion);
      return true;
  }
  
  public boolean actualizarProductoInventario(Object cantidad,Object stock,Object sucursal,Object proveedor,
            Object barras, Object precio1, Object precio2, String iva, String expiracion,Object id_producto_inventario,int id_usuario_sesion)
  {
      Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("actualizarProductoInventario", id_usuario_sesion, date.format(now),hora.format(now), "Se modifica producto en inventario ID: "+id_producto_inventario+"");
      Object id_sucursal,id_proveedor,id_usuario,id_inventario_producto,id_medicion;
      id_usuario= id_usuario_sesion;//con_usuario.getId_usuario();
      id_sucursal=consultaIdSucursal(sucursal);
      id_proveedor=consultaIdProveedor(proveedor);
      id_inventario_producto = consultaIdProducto(id_producto_inventario);
      consultaActualizarProductoInventario(id_inventario_producto, cantidad, stock, id_sucursal, id_proveedor, barras, precio1, precio2, iva, expiracion,id_usuario,id_producto_inventario);
      return true;
  }
  
  public TableModel buscarProductoInventario(int id_sucursal,String nombre,String opcion,String []nombreColumnas,int []ancho)
    {
       Grillas gr = new Grillas();
       
       return gr.CargarGrd(consultaBuscarProductoInventario(nombre,opcion,id_sucursal), nombreColumnas, ancho);
    }
  public boolean eliminarProductoInventario(Object id_producto_inventario, Object fecha, Object hora, Object idMotivo)
  {
      
      Object id_usuario=null;
      id_usuario= 1;//con_usuario.getId_usuario();
      
      if(consultaEliminarProductoInventario(id_producto_inventario)==true)
      {
          String descripcion = "Se elimino producto del inventario con identificacion: "+id_producto_inventario+"";
          Consultas_Generales con_gen = new Consultas_Generales();
          con_gen.registrarHistorial("eliminarProductoInventario", id_usuario, fecha, hora, descripcion);
          consultaRegistrarProductoEliminado(id_producto_inventario, descripcion, id_usuario, idMotivo);
      }
      return true;
  }
  public Object[] motivoEliminacionCombo()
  {
        int columnas=0,i=0,filas=0,q=0;
        Object [] aux = null;
        ResultSet r= null;   
        r=consultaMotivosEliminacion();
       try {
           meta= r.getMetaData();
           columnas= meta.getColumnCount();
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
       }
        try {
            while(r.next())
            {
                filas=filas+1;
            }
            aux= new Object[filas];
            r.beforeFirst();
            while(r.next())
            { 
                if(q==0)
                {
                    r.first();
                    for ( i = 1; i <= columnas; i++) 
                    {
                        aux[q]=r.getObject(i);
                    }
                    q=q+1;
                }
                else
                {
                    for ( i = 1; i <= columnas; i++) 
                    {
                        aux[q]=r.getObject(i);
                    }
                    q=q+1;
                }
            }
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
           ex.printStackTrace();
       }
        return aux;
    
  }
  
  public Object funcionConsultarIdMotivo(Object motivo)
  {
      Object id_motivo = consultaIdMotivo(motivo);
      return id_motivo;
  }
    
    
}
