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
public class consultas_historial extends Conexion{
    
    private ResultSet rh = null;
     Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_historial()
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
    public ResultSet consultaLlenarHistorial(int id_sucursal)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call US_consultaLlenarHistorial()");
            cst.setInt("id_sucursalLog", id_sucursal);
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public ResultSet consultaBuscarHistorial(String opcion,int id_sucursal,Object op)
    {
        switch (opcion)
        {
            case"Proceso":
                sql=("SELECT HISTO.id_historial,HISTO.proceso,USU.usuario,HISTO.fecha_historial,HISTO.hora,"
                + "HISTO.descripcion_sistema,HISTO.id_usuario,USU.id_usuario,USU.id_sucursal FROM usuario AS USU,historial AS HISTO "
                + "WHERE USU.id_sucursal="+id_sucursal+" AND HISTO.id_usuario=USU.id_usuario  AND HISTO.proceso LIKE '"+op+"%' ORDER BY HISTO.proceso");
                rh = consultaResusltados(sql);
                break;
            case"Usuario":
                sql=("SELECT HISTO.id_historial,HISTO.proceso,USU.usuario,HISTO.fecha_historial,HISTO.hora,"
                + "HISTO.descripcion_sistema,HISTO.id_usuario,USU.id_usuario,USU.id_sucursal FROM usuario AS USU,historial AS HISTO "
                + "WHERE USU.id_sucursal="+id_sucursal+" AND HISTO.id_usuario=USU.id_usuario  AND USU.usuario LIKE '"+op+"%' ORDER BY HISTO.proceso");
                rh = consultaResusltados(sql);
                break;
            case"Fecha":
                sql=("SELECT HISTO.id_historial,HISTO.proceso,USU.usuario,HISTO.fecha_historial,HISTO.hora,"
                + "HISTO.descripcion_sistema,HISTO.id_usuario,USU.id_usuario,USU.id_sucursal FROM usuario AS USU,historial AS HISTO "
                + "WHERE USU.id_sucursal="+id_sucursal+" AND HISTO.id_usuario=USU.id_usuario  AND HISTO.fecha_historial LIKE '"+op+"%' ORDER BY HISTO.proceso");
                rh = consultaResusltados(sql);
                break;
        }
        return rh;
    }
    
//    public ResultSet consultaLlenarHistorial(int id_sucursal)
//    {
//        crearConexion();
//        return ejecutarSQLSelect("SELECT HISTO.id_control_sesion,HISTO.id_usuario,HISTO.fecha_inicio,HISTO.hora_inicio,"
//                + "HISTO.fecha_fin,HISTO.hora_fin,USU.id_usuario,USU.id_suucursal "
//                + "FROM control_sesion AS HISTO, usuario AS USU WHERE USU.id_sucursal="+id_sucursal+" AND HISTO.id_usuario = USU.id_usuario ORDER BY HISTO.fecha_inicio");
//    }
    
}
