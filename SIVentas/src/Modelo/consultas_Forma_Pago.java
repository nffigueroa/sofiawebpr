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
public class consultas_Forma_Pago extends Conexion {

    private ResultSet rh = null;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_Forma_Pago()
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
    
    
    
    public ResultSet llenarTabla_Marca() {

        sql=("SELECT id_marca,marca FROM marca ORDER BY marca");
        return consultaResusltados(sql);

    }

    public boolean consultaRegistrarMarca(String marca, int id_usuario) {
        sql=("INSERT INTO marca (marca,id_usuario) VALUES ('" + marca + "'," + id_usuario + ")");
        return insertarResultados(sql);
    }

}
