/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DA;

import java.sql.CallableStatement;
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
        conex = con.crearConexionNueva();
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
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaCorteCajaImporte(?,?,?)");
            cst.setInt("id_importeLog", Integer.parseInt(id_importe.toString()));
            cst.setInt("id_sucursalLog", id_sucursal);
            cst.setInt("id_corte_cajaLog",Integer.parseInt(id_corte_caja.toString()));
            return cst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ResultSet consultaLLenarComboTipoImporte()
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaLLenarComboTipoImporte()");
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet consultaLLenarComboMotivoImporte(Object idTipoImporte)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaLLenarComboMotivoImporte(?)");
            cst.setInt("idTipoImporte", Integer.parseInt(idTipoImporte.toString()));
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public Object consultaIDComboTipoImporte(Object tipoImporte)
    {
        Object id_tipo_importe = null;
        try{
           CallableStatement cst = conex.prepareCall("Call CON_consultaIDComboTipoImporte(?)");
           cst.setInt("tipoImporte", Integer.parseInt(tipoImporte.toString()));
           cst.execute();
           rh = cst.getResultSet();
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
        try{
           CallableStatement cst = conex.prepareCall("Call CON_consultaIDComboTipoImporte(?)");
           cst.setObject("motivoImporte", (motivoImporte));
           cst.execute();
           rh = cst.getResultSet();
            while(rh.next())
            id_motivo_importe = rh.getInt("id_motivo_importe");
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_motivo_importe;
    }
      public boolean consultaRegistrarImporte(int id_sucursal,Object idTipoImporte, Object motivoImporte, Object importe, Object descripcion)
      {
          try {
              CallableStatement cst = conex.prepareCall("Call CON_consultaRegistrarImporte(?,?,?,?,?)");
              cst.setInt("id_sucursal", id_sucursal);
              cst.setInt("idTipoImporte", Integer.parseInt(idTipoImporte.toString()));
              cst.setObject("motivoImporte", motivoImporte);
              cst.setObject("importe", importe);
              cst.setObject("descripcion", descripcion);
              return cst.execute();
          } catch (Exception e) {
              e.printStackTrace();
              return false;
          }
   
      }
    
}
