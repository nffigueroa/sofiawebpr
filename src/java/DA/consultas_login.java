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
public class consultas_login  extends Conexion{
    
    private ResultSet rh = null;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_login()
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
            ex.printStackTrace();
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
   public Object consultasPassword(Object usuario)
    {
        
         Object psw = null;
       try{
           CallableStatement cst = conex.prepareCall("Call US_consultasPassword(?)");
           cst.setObject("usuarioLog", usuario);
           cst.execute();
           rh = cst.getResultSet();
            while(rh.next())
            psw = rh.getObject("password");
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return psw;
    }
    public Object consultasPasswordCambioClave(int usuario)
    {
        Object psw = null;
         try{
             CallableStatement cst = conex.prepareCall("Call US_consultasPasswordCambioClave(?)");
             cst.setInt("usuario",usuario);
             cst.execute();
             rh = cst.getResultSet();
            while(rh.next())
            psw = rh.getObject("password");
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return psw;
    }
    public ResultSet consultasDatosLogeoUsuario(Object usuario)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call US_consultasDatosLogeoUsuario(?)");
            cst.setObject("usuarioLog", usuario);
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ResultSet consultasDatosLogeoPorID(Object usuario)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call US_consultasDatosLogeoPorID(?)");
            cst.setInt("usuario",Integer.parseInt(usuario.toString()));
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
