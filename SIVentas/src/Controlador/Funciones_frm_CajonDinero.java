/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Consultas_Generales;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_CajonDinero extends Modelo.consultas_importe{
    
   Date now = new Date(System.currentTimeMillis());
   private java.sql.ResultSetMetaData meta;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    
    public Object[] llenarComboTpoimporte()
    {
        int columnas=0,i=0,filas=0,q=0;
        Object [] aux = null;
        ResultSet r= null;   
        r=consultaLLenarComboTipoImporte();
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
    
    public Object[] llenarComboMotivoImporte(Object tipoImporte)
    {
        int columnas=0,i=0,filas=0,q=0;
        Object id_tipoImporte=null;
        Object [] aux = null;
        id_tipoImporte=consultaIDComboTipoImporte(tipoImporte);
        ResultSet r= null;   
        r=consultaLLenarComboMotivoImporte(id_tipoImporte);
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
    
    public boolean registarImporte(int id_succursal,Object tipoImporte,Object motivoImporte, Object importe, Object descripcio,int id_usuario)
    {
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("registarImporte", id_usuario, date.format(now),hora.format(now), "Se realiza importe Tipo: "+tipoImporte+" Por: "+motivoImporte);
        Object id_tipo_importe=null,id_Motivo_Importe=null;
        id_Motivo_Importe = consultaIDComboMotivoImporte(motivoImporte);
        id_tipo_importe = consultaIDComboTipoImporte(tipoImporte);
        if(consultaRegistrarImporte(id_succursal,id_tipo_importe, id_Motivo_Importe, importe, descripcio))
        return true;
        else
            return false;
    }
    
}
