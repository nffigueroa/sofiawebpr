/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import DA.Consultas_Generales;
import DA.consultas_Cliente;
//import Fuentes.Grillas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 *
 * @author Nestor1
 */
public class Funciones_frm_clientes extends DA.consultas_Cliente{
    
   Date now = new Date(System.currentTimeMillis());
   private java.sql.ResultSetMetaData meta;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
   ResultSet rs = null;
   Funciones_Generales general = new Funciones_Generales();
    
   public ArrayList llenarCliente(int id_sucursal)
   {
     try {
           rs = llenarTabla_Cliente(id_sucursal);
           return general.resultSetToArrayList(rs);
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
   }   
   public ArrayList llenarImpuesto(int idCliente)
   {
     try {
           rs = llenarImpuestoXCliente(idCliente);
           return general.resultSetToArrayList(rs);
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
   }   
   
   public boolean actualizarImpuestos(int idCliente, String milesIca, int declaraIva, 
           int declaraIca, int retefuente)
   {
       try {
           return actualizarImpuestos(idCliente, milesIca, declaraIva, declaraIca, retefuente, 0);
       } catch (Exception e) {
            return false;
       }
      
   }
   
   public Object[] llenarComboCiudad()
    {
        int columnas=0,i=0,filas=0,q=0;
        Object [] aux = null;
        ResultSet r= null;   
        r=llenarComboCidad_Cliente();
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
   
   public boolean insertarCliente(int id_usuario,int id_sucursal,String nombre, String apellido, String telefono, String direccion, String email, int ciudad,
           String iden,String tipoCliente, String declaraIva, String declaraIca,String reteFuente,String milesIca, String auxDv)
    {
        //Object id_ciudad=null;
        //id_ciudad=consultaIDComboCiudad(ciudad);
        return registrarCliente(id_sucursal,nombre, apellido, telefono, direccion, email, ciudad, id_usuario,iden, tipoCliente,  declaraIva,  declaraIca, reteFuente, milesIca, auxDv);
    }
   
   public void eliminarCliente(int id_cliente)
   {
       boolean finalizar =false;
       int aux_d = 0;
       finalizar = eliminar_Cliente(id_cliente);
   }
   
    public boolean actualizarCliente(int usuarioMod,Object id_cliente,String nombre, String apellido, String email, String direccion, String telefono,Object ciudad)
    {
        Object id_ciudad_1;
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("actualizarCliente", usuarioMod, date.format(now),hora.format(now), "Se modifica Usuariio "+nombre+"");
        id_ciudad_1=consultaIDComboCiudad(ciudad);
        consultaActualizarCliente(usuarioMod,id_cliente, nombre,apellido, email, telefono, direccion,id_ciudad_1);
        return true;
    }
    
    public TableModel buscarCliente(int id_sucursal,String nombre,String opcion,String []nombreColumnas,int []ancho)
    {
      return null;
    }
}
