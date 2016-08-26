/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Constructores.Constructo_Cantidad_Productos_Vendido;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_MasVendido extends Modelo.Consultas_informeMasVendido{
      private javax.swing.JTable grd;
     Date now = new Date(System.currentTimeMillis());
    private java.sql.ResultSetMetaData meta;
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructores.Constructo_Cantidad_Productos_Vendido producto_vendido = new Constructo_Cantidad_Productos_Vendido();
    
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
    public  Constructores.Constructo_Cantidad_Productos_Vendido llenarMasVendido(int id_sucur)
   {
       int aux =0;
       Funciones_Generales fun = new Funciones_Generales();
       producto_vendido = fun.listadoProductoInventario(id_sucur);
       String[] producto = producto_vendido.getProducto() ;
       
       int [] id_producto = producto_vendido.getId_producto_inventario();
       float[] cantidad = new float[id_producto.length];
       ResultSet rh = null;
       rh = consultaProductoVendido(id_sucur);
         try {
             while(rh.next())
             {
                 for (int i = 0; i < id_producto.length; i++) {
                     
                    if(rh.getInt("INVEN.id_producto_inventario")==id_producto[i])
                        cantidad[i]= cantidad[i]+ rh.getFloat("DET.cantidad_factura_detallado");
                 }
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(Funciones_frm_MasVendido.class.getName()).log(Level.SEVERE, null, ex);
         }
       producto_vendido.setCantidad(cantidad);
       return producto_vendido;
   }
    
     public  Constructores.Constructo_Cantidad_Productos_Vendido llenarMasVendidoXEmpresa(int id_empresa)
   {
       int aux =0;
       Funciones_Generales fun = new Funciones_Generales();
       producto_vendido = fun.listadoProductoInventarioXEmpresa(id_empresa);
       String[] producto = producto_vendido.getProducto() ;
       
       int [] id_producto = producto_vendido.getId_producto_inventario();
       float[] cantidad = new float[id_producto.length];
       ResultSet rh = null;
       rh = consultaProductoVendidoXEmpresa(id_empresa);
         try {
             while(rh.next())
             {
                 for (int i = 0; i < id_producto.length; i++) {
                     
                    if(rh.getInt("INVEN.id_producto_inventario")==id_producto[i])
                        cantidad[i]= cantidad[i]+ rh.getFloat("DET.cantidad_factura_detallado");
                 }
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(Funciones_frm_MasVendido.class.getName()).log(Level.SEVERE, null, ex);
         }
       producto_vendido.setCantidad(cantidad);
       return producto_vendido;
   }
    
    public float procentajeMasVendido(float cantidadXuno, float sumatoria)
    {
        float procentaje = (100/ sumatoria) * cantidadXuno;
        return procentaje;
    }
    
    public TableModel modeloTablaCantidadProductos(Object id_sucursal)
    {
        producto_vendido = null;
        int id_sucu=Integer.parseInt(consultaIdSucursal(id_sucursal).toString());
        llenarMasVendido(id_sucu);
        Object[] datos = producto_vendido.getProducto();
        String[] columnas = {"CODIGO", "PROUCTO", "CANTIDAD"};
        grd = new javax.swing.JTable();;
        DefaultTableModel modelo = (DefaultTableModel) grd.getModel();
        for (int i = 0; i < producto_vendido.getId_producto_inventario().length; i++) {
            modelo.addRow(datos);
        }
        
        modelo.setColumnIdentifiers(columnas);
        return modelo;
    }
    public TableModel modeloTablaCantidadProductosXEmpresa(int id_empresa)
    {
        llenarMasVendidoXEmpresa(id_empresa);
        Object[] datos = producto_vendido.getProducto();
        String[] columnas = {"CODIGO", "PROUCTO", "CANTIDAD"};
        grd = new javax.swing.JTable();;
        DefaultTableModel modelo = (DefaultTableModel) grd.getModel();
        for (int i = 0; i < producto_vendido.getId_producto_inventario().length; i++) {
            modelo.addRow(datos);
        }
        
        modelo.setColumnIdentifiers(columnas);
        return modelo;
    }
    
   
    
}
