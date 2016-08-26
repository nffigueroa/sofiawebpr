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
public class consultas_cambio_clave extends Modelo.Conexion{
    
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    private ResultSet rh= null;
    
    public consultas_cambio_clave()
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
    
    public boolean consultaRegistrarCambioClave(int id_usuario,Object nuevaPassword)
    {
        sql=("UPDATE usuario SET password="+nuevaPassword+" WHERE id_usuario="+id_usuario+"");
        return insertarResultados(sql);
    }
    
}
