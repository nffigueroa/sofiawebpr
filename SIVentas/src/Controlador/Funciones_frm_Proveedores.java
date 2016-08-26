/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Fuentes.Grillas;
import Modelo.Consultas_Generales;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_Proveedores extends Modelo.consultas_proveedores{
    Date now = new Date(System.currentTimeMillis());
    private java.sql.ResultSetMetaData meta;
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    
     public boolean modificarProveedor(int id_proveedor,Object[]Permisos,String empresa,String contacto,String tel, String di , String mail, Object id_ciudad ,
             Object id_sucursal,Object usuario, Object nit)
    {
     
        
        Object id_ciu=consultaIDComboCiudad(id_ciudad);
        
        
        consultaModificarProveedor(id_proveedor,empresa, contacto, tel, di, mail, id_ciu,nit);
        consultaEliminarCategoria(id_proveedor);
        insertarCategoriaProeedor(Permisos, nit);
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("modificarProveedor", usuario, date.format(now),hora.format(now), "Se modifica proveedor "+id_proveedor+"");
        
        return false;
    }
    
     
    
    public TableModel llenarProveedor(int id_sucursal,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(llenarTabla_Proveedor(id_sucursal), nombreColumnas, ancho);
   }
    
   public TableModel BuscarProveedor(int id_sucursal,String[] nombreColumnas, int[] ancho,Object texto,String combo)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(buscarProveedor(id_sucursal,combo,texto), nombreColumnas, ancho);
   } 
    
    public Object[] categoriasProveedor(Object id_proveedor)
    {
         Object[] permisos = null;
        int aux=0,filas=0;
        ResultSet rs = null;
        rs=consultaCategoriasProveedor(id_proveedor);
        try {
            while(rs.next())
                filas++;
            rs.beforeFirst();
           permisos= new Object[filas];
            while(rs.next())
            {
                permisos[aux] = rs.getString("CA.categoria");
                aux++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_frm_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return permisos;
    }
    
    public DefaultTableModel llenarTablaCategoria()
    {
       
        DefaultTableModel modelo = new  DefaultTableModel();
        
        int filas=0,col=0;
        int k;
        
        java.sql.ResultSetMetaData metaDatos;
        JTable tabla= new JTable();
        modelo = (DefaultTableModel) tabla.getModel();
        ResultSet rs = null;
        rs=llenarTabla_Categoria();
        try {
             metaDatos= rs.getMetaData();
             col = metaDatos.getColumnCount();
             String [] nombre = new String[col+1];
            while(rs.next())
            {
                filas=filas+1;
            }
            rs.beforeFirst();
            modelo.addColumn("id_categoria");
            modelo.addColumn("Categoria");
            modelo.addColumn("Seleccionar");
            while(rs.next())
                    {  
                         Object[] objetos= new Object[col+1];
                        for ( k = 0; k <= col; k++) 
                        {
                            if(k==2)
                            {
                                objetos[k] = Boolean.FALSE;
                            }else
                            {
                                objetos[k] = rs.getObject(k + 1);
                            }
                        }
                        //objetos[k+1]=null;
                        modelo.addRow(objetos);
                        
                    }
            tabla.updateUI();
            rs.close();
        }catch (SQLException ex) {
            Logger.getLogger(Funciones_frm_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }
    
    public boolean insertarProveedor(int id_sucursal,int id_usuario,Object[]categorias,String empresa, String contacto, String telefono, String direccion, String email, Object ciudad,String nit)
    {
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("insertarProveedor", id_usuario, date.format(now),hora.format(now), "Se registra Proveedor: "+empresa+" con NIT: "+nit);
        Object id_ciudad= null,fecha= date.format(now);
        id_ciudad=consultaIDComboCiudad(ciudad);
        consultaRegistrarProveedor(id_sucursal,fecha.toString(),empresa, contacto, telefono, direccion, email, id_ciudad, nit,id_usuario);
        insertarCategoriaProeedor(categorias, nit);
        
        return false;
    }
    
    public boolean insertarCategoriaProeedor(Object[]categorias,Object nit)
    {
        Object id_proveedor;
        try{
        id_proveedor= consultaSelectProveedor(nit);
        JOptionPane.showMessageDialog(null, "Se consigio el id");
        registrarCategoriaProveedor(categorias, id_proveedor);
        cerrarConexion();
        }
        catch(Exception ex)
                {
                    System.out.println(ex);
                }
        return false;
    }
    
    public Object[] llenarComboCiudad()
    {
        int columnas=0,i=0,filas=0,q=0;
        Object [] aux = null;
        ResultSet r= null;   
        r=llenarComboCidad_Proveedor();
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
            r.close();
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
           ex.printStackTrace();
       }
        return aux;
    }
    
     public void eliminarProveedor(int id_proveedor,int id_usuario_sesion)
   {
       Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("eliminarProveedor", id_usuario_sesion, date.format(now),hora.format(now), "Se elimina Proveedor: "+id_proveedor);
       boolean finalizar =false;
       int aux_d = 0;
       aux_d=JOptionPane.showConfirmDialog(null, "¿En realidad desea ELIMINAR este registro?");
       if(aux_d==0)
       {
           finalizar = eliminar_Proveedor(id_proveedor);
            if(finalizar ==true)
                JOptionPane.showMessageDialog(null, "Registro eliminado...");
            else
                JOptionPane.showMessageDialog(null, "No se pudo eliminar, intente nuevamente");
       }
       else
       {
           
       }
   }
    
}
