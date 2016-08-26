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
import mondrian.olap.Parameter;

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
         String sql =("SELECT password FROM usuario WHERE usuario='"+usuario+"'");
         rh = consultaResusltados(sql);
        try {
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
         String sql =("SELECT password FROM usuario WHERE id_usuario='"+usuario+"'");
         rh = consultaResusltados(sql);
        try {
            while(rh.next())
            psw = rh.getObject("password");
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return psw;
    }
    public ResultSet consultasDatosLogeoUsuario(Object usuario)
    {
        String sql = ("SELECT nombre_usuario,apellido_usuario,cc_usuario,telefono_usuario,usuario,password,id_usuario,id_sucursal,id_cargo FROM usuario WHERE usuario='"+usuario+"'");
        rh = consultaResusltados(sql);
        return rh;
    }
    
    public ResultSet consultasDatosLogeoPorID(Object usuario)
    {
        String sql = ("SELECT nombre_usuario,apellido_usuario,cc_usuario,telefono_usuario,usuario,password,id_usuario,id_sucursal,id_cargo FROM usuario WHERE id_usuario='"+usuario+"'");
        rh = consultaResusltados(sql);
        return rh;
    }
    
}
