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
public class consultas_medicion extends Conexion{
     private ResultSet rh = null;
      Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_medicion()
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
        public ResultSet llenarTabla_Medicion(int id_sucursal)
        {
            try {
                CallableStatement cst = conex.prepareCall("Call GEN_llenarTabla_Medicion(?)");
                cst.setInt("id_sucursalLog", id_sucursal);
                cst.execute();
                return cst.getResultSet();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
      
      public boolean consultaRegistrarMedicion(int id_sucursal,String medicion,int id_usuario)
        {
        try {
                CallableStatement cst = conex.prepareCall("Call GEN_consultaRegistrarMedicion(?,?,?)");
                cst.setInt("id_sucursal", id_sucursal);
                cst.setString("medicion", medicion);
                cst.setInt("id_usuario", id_usuario);
                return cst.execute();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
}
