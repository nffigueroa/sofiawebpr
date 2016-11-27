/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import DA.Consultas_Generales;
import Constructores.Constructor_Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import java.sql.Date;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_producto extends DA.consultas_Producto{
   
   
   private java.sql.ResultSetMetaData meta;
   Date now = new Date(System.currentTimeMillis());
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
   Constructor_Usuario usuari = new Constructor_Usuario();
   private ResultSet rs= null;
   Funciones_Generales general = new Funciones_Generales();
   
   public ArrayList llenarProductos(int sucursal)
   {
       try {
           rs = llenarTabla_Producto(sucursal);
           return general.resultSetToArrayList(rs);
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
       
   }
   
  
   
   
   
   public void eliminarProducto(int id_prod)
   {
       boolean finalizar =false;
       int aux_d = 0;
       aux_d=1;
       if(aux_d==1)
       {
           finalizar = eliminar_Producto(id_prod);
            
       }
       else
       {
           
       }
   }
   
    public ArrayList llenarComboCategoria()
    {
        try {
            rs = consultaLLenarComboCategoria();
            return general.resultSetToArrayList(rs);
        } catch (Exception e) {
            return null;
        }
//        int columnas=0,i=0,filas=0,q=0;
//        Object [] aux = null;
//        ResultSet r= null;   
//        r=consultaLLenarComboCategoria();
//       try {
//           meta= r.getMetaData();
//           columnas= meta.getColumnCount();
//       } catch (SQLException ex) {
//           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
//       }
//        try {
//            while(r.next())
//            {
//                filas=filas+1;
//            }
//            aux= new Object[filas];
//            r.first();
//            while(r.next())
//            { 
//                if(q==0)
//                {
//                    r.first();
//                    for ( i = 1; i <= columnas; i++) 
//                    {
//                        aux[q]=r.getObject(i);
//                    }
//                    q=q+1;
//                }
//                else
//                {
//                    for ( i = 1; i <= columnas; i++) 
//                    {
//                        aux[q]=r.getObject(i);
//                    }
//                    q=q+1;
//                }
//            }
//       } catch (SQLException ex) {
//           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
//           ex.printStackTrace();
//       }
//        return aux;
    }
   
    public Object[] llenarComboMedicion()
    {
        int columnas=0,i=0,filas=0,q=0;
        Object [] aux = null;
        ResultSet r= null;   
        r=consultaLLenarComboMedicion();
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
            r.first();
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

    public Object[] llenarComboMarca()
    {
        int columnas=0,i=0,filas=0,q=0;
        Object [] aux = null;
        ResultSet r= null;   
        r=consultaLLenarComboMarca();
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
            r.first();
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

    public Object[] llenarComboPresentacion()
    {
        int columnas=0,i=0,filas=0,q=0;
        Object [] aux = null;
        ResultSet r= null;   
        r=consultaLLenarComboPresentacion();
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
            r.first();
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
    
    public boolean insertarProducto(String nombre,String fecha_creacion,int usuarioCreacion, Object categoria , Object marca, Object medicion , Object presentacion )
    {
        Funciones_Generales fun = new Funciones_Generales();
        usuari=fun.usuarioPorId(usuarioCreacion);
        fecha_creacion=date.format(now);
        Object id_categoria,id_marca,id_medicion,id_presentacion;
        id_categoria=consultaIDComboCategoria(categoria);
        id_marca=consultaIDComboMarca(marca);
        id_medicion=consultaIDComboMedicion(medicion);
        id_presentacion=consultaIDComboPresentacion(presentacion);
       return registrarProducto(nombre, fecha_creacion, usuari.getId_usuario(), id_categoria, id_marca, id_medicion, id_presentacion,usuari.getId_sucursal());
    }
    
    public boolean actualizarProducto(Object id_producto,String nombre, Object categoria , Object marca, Object medicion , Object presentacion )
    {
        Object id_categoria,id_marca,id_medicion,id_presentacion,usuario;
        Constructor_Usuario con_usuario = new Constructor_Usuario();
        usuario=1;
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("actualizarProducto", usuario, date.format(now),hora.format(now), "Se modifica producto "+nombre+"");
        id_categoria=consultaIDComboCategoria(categoria);
        id_marca=consultaIDComboMarca(marca);
        id_medicion=consultaIDComboMedicion(medicion);
        id_presentacion=consultaIDComboPresentacion(presentacion);
        consultaActualizarProducto(id_producto, nombre,id_categoria, id_marca, id_medicion, id_presentacion);
        
        return true;
    }
    
    public TableModel buscarProducto(int sucursal,String nombre,String opcion,String []nombreColumnas,int []ancho)
    {
//       Grillas gr = new Grillas();
//       
//       return gr.CargarGrd(consultaBuscarProducto(sucursal,nombre,opcion), nombreColumnas, ancho);
        
        return null;
    }
}
