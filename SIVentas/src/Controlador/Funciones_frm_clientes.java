/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Fuentes.Grillas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import Modelo.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_clientes extends Modelo.consultas_Cliente{
    
   Date now = new Date(System.currentTimeMillis());
   private java.sql.ResultSetMetaData meta;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    
   public TableModel llenarCliente(int id_sucursal,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(llenarTabla_Cliente(id_sucursal), nombreColumnas, ancho);
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
   
   public boolean insertarCliente(int id_usuario,int id_sucursal,String nombre, String apellido, String telefono, String direccion, String email, Object ciudad,String iden)
    {
        Object id_ciudad=null;
        id_ciudad=consultaIDComboCiudad(ciudad);
        registrarCliente(id_sucursal,nombre, apellido, telefono, direccion, email, id_ciudad, id_usuario,iden);
        return false;
    }
   
   public void eliminarCliente(int id_cliente)
   {
       boolean finalizar =false;
       int aux_d = 0;
       aux_d=JOptionPane.showConfirmDialog(null, "¿En realidad desea ELIMINAR este registro?");
       if(aux_d==0)
       {
           finalizar = eliminar_Cliente(id_cliente);
            if(finalizar ==true)
                JOptionPane.showMessageDialog(null, "Registro eliminado...");
            else
                JOptionPane.showMessageDialog(null, "No se pudo eliminar, intente nuevamente");
       }
       else
       {
           
       }
   }
   
    public boolean actualizarCliente(Object id_cliente,String nombre, String apellido, String email, String direccion, String telefono,Object ciudad)
    {
        Object id_ciudad_1;
        int usuario;
        //Constructor_Usuario con_usuario = new Constructor_Usuario();
        usuario=1;
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("actualizarCliente", usuario, date.format(now),hora.format(now), "Se modifica Usuariio "+nombre+"");
        id_ciudad_1=consultaIDComboCiudad(ciudad);
        consultaActualizarCliente(usuario,id_cliente, nombre,apellido, email, telefono, direccion,id_ciudad_1);
        
        return true;
    }
    
    public TableModel buscarCliente(int id_sucursal,String nombre,String opcion,String []nombreColumnas,int []ancho)
    {
       Grillas gr = new Grillas();
       
       return gr.CargarGrd(consultaBuscarCliente(id_sucursal,nombre,opcion), nombreColumnas, ancho);
    }
}
