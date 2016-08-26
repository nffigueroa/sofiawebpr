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
public class consultas_medicion extends Conexion{
     private ResultSet rh = null;
      Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_medicion()
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
        public ResultSet llenarTabla_Medicion(int id_sucursal)
        {
        sql=("SELECT id_medicion,medicion FROM medicion WHERE id_sucursal="+id_sucursal+" ORDER BY medicion");
        
        return consultaResusltados(sql);
        }
      
      public boolean consultaRegistrarMedicion(int id_sucursal,String medicion,int id_usuario)
        {
        sql=("INSERT INTO medicion (medicion,id_usuario,id_sucursal) VALUES ('"+medicion+"',"+id_usuario+","+id_sucursal+")");
        return insertarResultados(sql);
        }
}
