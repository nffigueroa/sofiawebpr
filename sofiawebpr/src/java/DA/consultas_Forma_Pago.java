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
import java.sql.*;

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
        conex = con.crearConexionNueva();
    } 
    
    public ResultSet llenarTabla_Marca() {

        try{
            CallableStatement cst = conex.prepareCall("Call GEN_llenarTabla_Marca()");
            cst.execute();
            return cst.getResultSet();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public boolean consultaRegistrarMarca(String marca, int id_usuario) {
     try{
            CallableStatement cst = conex.prepareCall("Call GEN_consultaRegistrarMarca(?,?)");
            cst.setInt("id_usuario", id_usuario);
            cst.setString("marca", marca);
            return cst.execute();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
