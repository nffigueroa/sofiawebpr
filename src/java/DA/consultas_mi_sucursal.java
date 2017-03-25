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
public class consultas_mi_sucursal extends DA.Conexion{

    private ResultSet rh = null;
     Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_mi_sucursal()
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
    public ResultSet consultaSucursales(int id_empresa)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_consultaSucursales(?)");
            cst.setInt("id_empresaLog", id_empresa);
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public ResultSet llenarComboCidad_Mi_Sucursal()
    {
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_llenarComboCidad_Mi_Sucursal()");
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public Object consultaIDComboCiudad(Object ciudad)
    {
        Object id_categoria = null;
        try{
            CallableStatement cst = conex.prepareCall("Call GEN_consultaIDComboCiudad(?)");
            cst.setObject("ciudad", ciudad);
            rh = cst.getResultSet();
            while(rh.next())
            id_categoria = rh.getInt("id_ciudad");
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_categoria;
    }
     
     public boolean consultaRegistrarSucursal(Object nombre_sucursal,Object telefono,Object direccion, Object id_ciudad, Object cant_empleados,
             int id_empresa , int id_usuario, Object  fecha_creacion )
     {
         try {
             CallableStatement cst = conex.prepareCall("Call GEN_consultaRegistrarSucursal(?,?,?,?,?,?,?,?)");
             cst.setObject("nombre_sucursal", nombre_sucursal);
             cst.setObject("telefono", telefono);
             cst.setObject("direccion", direccion);
             cst.setInt("id_ciudad", Integer.parseInt(id_ciudad.toString()));
             cst.setInt("cant_empleados", Integer.parseInt(cant_empleados.toString()));
             cst.setInt("id_empresa", id_empresa);
             cst.setInt("id_usuario", id_usuario);
             cst.setObject("fecha_creacion", fecha_creacion);
             return cst.execute();
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }
     }
}
