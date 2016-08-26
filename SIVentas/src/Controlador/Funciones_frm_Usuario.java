/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Fuentes.EncriptadorPassword;
import Fuentes.Grillas;
import Modelo.Consultas_Generales;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_Usuario extends Modelo.consultas_usuario{
     Date now = new Date(System.currentTimeMillis());
    private java.sql.ResultSetMetaData meta;
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    
    EncriptadorPassword encrip = new EncriptadorPassword("20150112");
    
    public TableModel llenarUsuario(int id_empresa,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(consultaLlenarTabla_Usuario(id_empresa), nombreColumnas, ancho);
   }
    
    public boolean modificarUsuario(int id_usuario,Object[]Permisos,String nombre,String apellido,String cc, String telefono , String dir, Object id_cargo ,
             Object descri,String fecha_cre,int id_usuario_cre,Object id_sucursal,Object usuario, Object psw)
    {
     
        Funciones_Entrada_Inventario fun = new Funciones_Entrada_Inventario();
        int numeroCargo=0;
        int id_sucur= Integer.parseInt(fun.consultaIdSucursal(id_sucursal).toString());
        numeroCargo= Integer.parseInt(consultaIDComboCargo(id_cargo).toString());
        psw = encrip.encrypt(psw.toString());
        
        consultaModificarUsuario(id_usuario,nombre, apellido, cc, telefono, dir, numeroCargo, descri,id_sucur,usuario,psw);
        consultaEliminarPermisos(id_usuario);
        insertarPermisoUsuario(Permisos, id_usuario);
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("modificarUsuario", id_usuario_cre, date.format(now),hora.format(now), "Se modifica usuario "+nombre+"");
        
        return false;
    }
    
    public Object[] permisosUsuario(Object id_usuario)
    {
        Object[] permisos = null;
        int aux=0,filas=0;
        ResultSet rs = null;
        rs=consultaPermisoUsuario(id_usuario);
        try {
            while(rs.next())
                filas++;
            rs.beforeFirst();
           permisos= new Object[filas];
            while(rs.next())
            {
                permisos[aux] = rs.getString("PER.permiso");
                aux++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_frm_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return permisos;
    }
    
    public DefaultTableModel llenarTablaPermiso()
    {
        DefaultTableModel modelo = new  DefaultTableModel();
        int filas=0,col=0;
        int k;
        
        java.sql.ResultSetMetaData metaDatos;
        JTable tabla= new JTable();
        modelo = (DefaultTableModel) tabla.getModel();
        ResultSet rs = null;
        rs=llenarTabla_Permiso();
        try {
             metaDatos= rs.getMetaData();
             col = metaDatos.getColumnCount();
             String [] nombre = new String[col+1];
            while(rs.next())
            {
                filas=filas+1;
            }
            rs.beforeFirst();
            modelo.addColumn("id_permiso");
            modelo.addColumn("Form");
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
            Logger.getLogger(Funciones_frm_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }
    
    public boolean insertarUsuario(Object[]Permisos,String nombre,String apellido,String cc, String telefono , String dir, Object id_cargo ,
             Object descri,String fecha_cre,Object id_usuario_cre,Object id_sucursal,Object usuario, Object psw)
    {
        Funciones_Entrada_Inventario fun = new Funciones_Entrada_Inventario();
        Object id_usuario;
//        cerrarConexion();
        id_cargo= consultaIDComboCargo(id_cargo);
        int id_sucur= Integer.parseInt(fun.consultaIdSucursal(id_sucursal).toString());
        psw = encrip.encrypt(psw.toString());
        consultaRegistrarUsuario(nombre, apellido, cc, telefono, dir, id_cargo, descri,fecha_cre,id_usuario_cre,id_sucur,usuario,psw);
        cerrarConexion();
        id_usuario = consultaSelectUsuario(cc);
        insertarPermisoUsuario(Permisos, id_usuario);
        cerrarConexion();
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("insertarUsuario", id_usuario, date.format(now),hora.format(now), "Se registra usuario "+nombre+"");
        return false;
    }
    
    public boolean insertarCategoriaProeedor(Object[]categorias,Object cc)
    {
        Object id_usuario;
        try{
        id_usuario= consultaSelectUsuario(cc);
        insertarPermisoUsuario(categorias, id_usuario);
        cerrarConexion();
        }
        catch(Exception ex)
                {
                    System.out.println(ex);
                }
        return false;
    }
    
    public Object[] funcionesLLenarComboCargo()
    {
        int columnas=0,i=0,filas=0,q=0;
        Object [] aux = null;
        ResultSet r= null;   
        r=llenarComboCargo();
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
    
     public void eliminarUsuario(int id_usuario,int usuario_sesion)
   {
       Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("eliminarUsuario", usuario_sesion, date.format(now),hora.format(now), "Se elimina usuario id_usuario "+id_usuario+"");
       boolean finalizar =false;
       int aux_d = 0;
       aux_d=JOptionPane.showConfirmDialog(null, "¿En realidad desea ELIMINAR este registro?");
       if(aux_d==0)
       {
           finalizar = eliminar_Usuario(id_usuario);
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
