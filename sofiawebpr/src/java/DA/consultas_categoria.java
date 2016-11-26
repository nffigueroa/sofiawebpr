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
public class consultas_categoria extends Conexion{
     private ResultSet rh = null;
      Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_categoria()
    {
        con = new Conexion();
        conex = con.crearConexionNueva();
    }
   
      public ResultSet llenarTabla_Categoria()
        {
            try {
                CallableStatement cst = conex.prepareCall("Call GEN_consultaListaCategoria()");
                cst.execute();
                return cst.getResultSet();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
      
      public boolean consultaRegistrarCategoria(String categoria,int id_usuario)
        {
        try {
                CallableStatement cst = conex.prepareCall("Call GEN_consultaRegistrarCategoria(?,?)");
                cst.setString("categoria", categoria);
                cst.setInt("id_usuario", id_usuario);
                return cst.execute();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
}
