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
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_MiSucursal extends Modelo.consultas_mi_sucursal{
    
    Date now = new Date(System.currentTimeMillis());
    private java.sql.ResultSetMetaData meta;
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    
    public TableModel llenarMisSucursales(int id_empresa, String[] nombreColumnas, int[] ancho)
    {
        Grillas gr = new Grillas();
        return gr.CargarGrd(consultaSucursales(id_empresa), nombreColumnas, ancho);
    }
    
    public Object[] llenarComboCiudad()
    {
        int columnas=0,i=0,filas=0,q=0;
        Object [] aux = null;
        ResultSet r= null;   
        r=llenarComboCidad_Mi_Sucursal();
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
    
    public boolean registarSucursal(Object nombre_sucursal,Object telefono,Object direccion, Object id_ciudad, Object cant_empleados,
             int id_empresa , int id_usuario)
    {
         Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("insertarProveedor", id_usuario, date.format(now),hora.format(now), "Se registra Sucursal: "+nombre_sucursal);
        Object fecha_creacion = date.format(now);
        id_ciudad = consultaIDComboCiudad(id_ciudad);
        return consultaRegistrarSucursal(nombre_sucursal, telefono, direccion, id_ciudad, cant_empleados, id_empresa, id_usuario, fecha_creacion);
    }
    
}
