/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author Nestor1
 */
public class Consultas_VentasCategorias extends Conexion{
    private ResultSet rh = null;
    Connection conex =null;
    PreparedStatement ps ;
    Conexion con ;
    public Consultas_VentasCategorias()
    {
        con = new Conexion();
        conex = con.crearConexionNueva();
    }
    public ResultSet consultaProductoVendido(int id_empresa)
    {
       try{
            CallableStatement cst = conex.prepareCall("CALL IVN_consultaProductoVendido(?)");
            cst.setInt("id_empresaLog", id_empresa);
            cst.execute();
            return  cst.getResultSet();
       }
              catch(SQLException e)
       {
           e.printStackTrace();
           return null;
       }
    }
    
    public ResultSet consultaListaCategoria()
    {
        try {
            CallableStatement cst = conex.prepareCall("CALL GEN_consultaListaCategoria()");
            cst.execute();
            return  cst.getResultSet();
//        return ejecutarSQLSelect("SELECT categoria,id_categoria FROM categoria");
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_VentasCategorias.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
}
