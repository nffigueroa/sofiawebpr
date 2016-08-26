/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nestor1
 */
public class consultas_importe extends Conexion{
    
    private ResultSet rh = null;
     Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_importe()
    {
        con = new Conexion();
    }
    
    private ResultSet consultaResusltados(String sql) {
        try {
            conex = con.crearConexionNueva();
            ps = conex.prepareStatement(sql);
            rh = ps.executeQuery();
           
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_informeMasVendido.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rh;
    }
    private boolean insertarResultados(String sql) {
        boolean ban = false;
        try {
            
            conex = con.crearConexionNueva();
            ps = conex.prepareStatement(sql);
            ban = ps.execute();
           
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_informeMasVendido.class.getName()).log(Level.SEVERE, null, ex);
        }
         return ban;
    }
    public boolean consultaCorteCajaImporte(Object id_importe,int id_sucursal,Object id_corte_caja)
    {
        sql=("UPDATE importes SET id_corte_caja="+id_corte_caja+" WHERE id_importe="+id_importe+" AND id_sucursal="+id_sucursal+"");
        return insertarResultados(sql);
    }
    
    public ResultSet consultaLLenarComboTipoImporte()
    {
        sql=("SELECT tipo_importe FROM tipo_importe");
        return consultaResusltados(sql);
    }
    public ResultSet consultaLLenarComboMotivoImporte(Object idTipoImporte)
    {
        sql=("SELECT motivo_importe FROM motivo_importe WHERE id_tipo_importe="+idTipoImporte+"");
        return consultaResusltados(sql);
    }
     public Object consultaIDComboTipoImporte(Object tipoImporte)
    {
        Object id_tipo_importe = null;
        sql=("SELECT id_tipo_importe FROM tipo_importe WHERE tipo_importe='"+tipoImporte+"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            id_tipo_importe = rh.getInt("id_tipo_importe");
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_tipo_importe;
    }
      public Object consultaIDComboMotivoImporte(Object motivoImporte)
    {
        Object id_motivo_importe = null;
        sql=("SELECT id_motivo_importe FROM motivo_importe WHERE motivo_importe='"+motivoImporte+"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            id_motivo_importe = rh.getInt("id_motivo_importe");
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_motivo_importe;
    }
      public boolean consultaRegistrarImporte(int id_sucursal,Object idTipoImporte, Object motivoImporte, Object importe, Object descripcion)
      {
          
          boolean resultado= false;
          sql=("INSERT INTO importes (id_tipo_importe,id_motivo_importe,importe,descripcion_importe,id_sucursal) VALUES ("+idTipoImporte+","+motivoImporte+""
                  + ","+importe+",'"+descripcion+"',"+id_sucursal+")");
          return insertarResultados(sql);
      }
    
}
