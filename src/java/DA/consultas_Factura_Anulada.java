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
public class consultas_Factura_Anulada extends Conexion{
    
    private ResultSet rh = null;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_Factura_Anulada()
    {
        con = new Conexion();
        conex = con.crearConexionNueva();
    }
      
    public ResultSet consultaLlenarFacturaAnuladas(int id_sucursal)
    {
        try{
            CallableStatement cst = conex.prepareCall("Call CON_consultaLlenarFacturaAnuladas(?)");
            cst.setInt("idSucursal", id_sucursal);
            cst.execute();
            return cst.getResultSet();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public ResultSet consultaBuscarFacturaAnulada(String opcion,int id_sucursal,Object op)
    {
       try{
           CallableStatement cst = conex.prepareCall("Call CON_consultaBuscarFacturaAnulada(?,?,?)");
           cst.setInt("id_sucursalLog", id_sucursal);
           cst.setObject("op", op);
           cst.setString("opcion", opcion);
           cst.execute();
           return cst.getResultSet();           
        }
       catch(SQLException e)
       {
           e.printStackTrace();
           return null;
       }
    }
    
//    public ResultSet consultaLlenarHistorial(int id_sucursal)
//    {
//        crearConexion();
//        return ejecutarSQLSelect("SELECT HISTO.id_control_sesion,HISTO.id_usuario,HISTO.fecha_inicio,HISTO.hora_inicio,"
//                + "HISTO.fecha_fin,HISTO.hora_fin,USU.id_usuario,USU.id_suucursal "
//                + "FROM control_sesion AS HISTO, usuario AS USU WHERE USU.id_sucursal="+id_sucursal+" AND HISTO.id_usuario = USU.id_usuario ORDER BY HISTO.fecha_inicio");
//    }
    
}
