/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DA;

import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nestor1
 */
public class consulta_sesion extends DA.Conexion{
    private ResultSet rh = null;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    
    public  consulta_sesion()
    {
        con = new Conexion();
        con.crearConexionNueva();
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
    public ResultSet consultaLlenarTablaSesion(int id_empresa)
    {
       try {
         CallableStatement cst = conex.prepareCall("CALL US_consultaLlenarTablaSesion(?)");
         cst.setInt("id_empresaLog", id_empresa);
         cst.execute();
         rh =  cst.getResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_Generales.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return rh;
    }
    
    public ResultSet consultaLlenarTablaSesionBuscar(int id_empresa,String opcion,String nombre)
    { //..., Usuario, Sucursal, Fecha Inicio, Hora Inicio, Fecha Fin, Hora Fin
       try {
         CallableStatement cst = conex.prepareCall("CALL US_consultaLlenarTablaSesionBuscar(?,?,?)");
         cst.setInt("id_empresaLog", id_empresa);
         cst.setString("opcion", opcion);
         cst.setString("nombre", nombre);
         cst.execute();
         rh =  cst.getResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_Generales.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return rh;
    }
    
    
    
    
}
