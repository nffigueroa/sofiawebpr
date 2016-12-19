/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import Constructores.Constructo_Cantidad_Productos_Vendido;
import Constructores.Constructor_Cantidad_Categoria_Vendido;
import DA.Consultas_VentasCategorias;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_VentasCategoria extends DA.Consultas_informeMasVendido{
      private javax.swing.JTable grd;
     Date now = new Date(System.currentTimeMillis());
    private java.sql.ResultSetMetaData meta;
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructores.Constructor_Cantidad_Categoria_Vendido categoria_cantidad = new Constructor_Cantidad_Categoria_Vendido();
    
    
    public  Constructores.Constructor_Cantidad_Categoria_Vendido llenarMasVendido(int id_sucursal)
   {
       int aux =0;
       
       Funciones_Generales fun = new Funciones_Generales();
       categoria_cantidad = fun.listadoCategoriasProducto();
       DA.Consultas_VentasCategorias con = new Consultas_VentasCategorias();
       String[] categoria = categoria_cantidad.getCategoria();
       int [] id_categoria = categoria_cantidad.getId_categoria();
       float[] cantidad = new float[id_categoria.length];
       float[] iva = new float[id_categoria.length];
       float[] descuento = new float[id_categoria.length];
       float[] efectivo = new float[id_categoria.length];
       float[] credito = new float[id_categoria.length];
       float[] totalVenta = new float[id_categoria.length];
       float[] subTotal = new float[id_categoria.length];
       ResultSet rh = null;
       rh = (con.consultaProductoVendido(id_sucursal));
         try {
             while(rh.next())
             {
                 for (int i = 0; i < id_categoria.length; i++) {
                     
                    if(rh.getInt("PRO.id_categoria")==id_categoria[i])
                    {
                        cantidad[i]= cantidad[i]+ rh.getFloat("DET.cantidad_factura_detallado");
                        iva[i] = iva[i] + rh.getFloat("");
                    }
                 }
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(Funciones_frm_MasVendido.class.getName()).log(Level.SEVERE, null, ex);
         }
       categoria_cantidad.setCantidad(cantidad);
       
       return categoria_cantidad;
   }
    
    public float procentajeMasVendido(float cantidadXuno, float sumatoria)
    {
        float procentaje = (100/ sumatoria) * cantidadXuno;
        return procentaje;
    }
    
    public ArrayList modeloTablaCantidadCategoria(int id_sucursal)
    {
        return null;
//        llenarMasVendido(id_sucursal);
//        Object[] datos = categoria_cantidad.getCategoria();
//        String[] columnas = {"CODIGO", "CATEGORIA", "CANTIDAD"};
//        grd = new javax.swing.JTable();;
//        DefaultTableModel modelo = (DefaultTableModel) grd.getModel();
//        for (int i = 0; i < categoria_cantidad.getId_categoria().length; i++) {
//            modelo.addRow(datos);
//        }
//        
//        modelo.setColumnIdentifiers(columnas);
//        return modelo;
    }
    
   
    
}
