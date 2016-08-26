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
public class consultas_mi_sucursal extends Modelo.Conexion{

    private ResultSet rh = null;
     Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_mi_sucursal()
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
    public ResultSet consultaSucursales(int id_empresa)
    {
        sql=("SELECT DISTINCT SUC.id_sucursal,SUC.nombre_sucursal,EMPRE.gerente,SUC.telefono_sucursal,SUC.direccion_sucursal,CIU.ciudad,SUC.cantidad_empleados"
                + ",SUC.fecha_creacion,USU.usuario,SUC.id_usuario,SUC.id_empresa,EMPRE.id_empresa,USU.id_usuario,SUC.id_ciudad,CIU.id_ciudad"
                + " FROM sucursal AS SUC, mi_empresa AS EMPRE,usuario AS USU, ciudad AS CIU WHERE SUC.id_empresa= EMPRE.id_empresa AND "
                + "SUC.id_empresa="+id_empresa+" AND SUC.id_usuario"
                + "=USU.id_usuario AND SUC.id_ciudad=CIU.id_ciudad ORDER BY SUC.nombre_sucursal");
        return consultaResusltados(sql);
    }
    
     public ResultSet llenarComboCidad_Mi_Sucursal()
    {
        sql=("SELECT ciudad FROM CIUDAD");
        return consultaResusltados(sql);
    }
     public Object consultaIDComboCiudad(Object ciudad)
    {
        Object id_categoria = null;
        sql=("SELECT id_ciudad FROM CIUDAD WHERE ciudad='"+ciudad+"'");
        rh = consultaResusltados(sql);
        try {
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
         sql=("INSERT INTO sucursal (nombre_sucursal,telefono_sucursal,direccion_sucursal,id_ciudad,cantidad_empleados,id_empresa"
                 + ",id_usuario,fecha_creacion) VALUES ('"+nombre_sucursal+"','"+telefono+"','"+direccion+"',"+id_ciudad+","+cant_empleados+","+id_empresa+","
                 + ""+id_usuario+",'"+fecha_creacion+"')");
         return insertarResultados(sql);
     }
}
