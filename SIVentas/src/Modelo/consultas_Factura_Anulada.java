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
public class consultas_Factura_Anulada extends Conexion{
    
    private ResultSet rh = null;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_Factura_Anulada()
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
    
    public ResultSet consultaLlenarFacturaAnuladas(int id_sucursal)
    {
        sql=("SELECT DISTINCT FACT.id_factura,FACT.numero_factura,"
                + "ANU.fecha_anulacion,ANU.hora_anulacion,MOT.motivo_anulacion,USU.usuario"
                + " FROM factura AS FACT, factura_anulada AS ANU,factura_motivo_anulacion"
                + " AS MOT,usuario AS USU WHERE FACT.id_factura=ANU.id_factura AND ANU.id_usuario_creacion = USU.id_usuario AND"
                + " MOT.id_motivo_factura_anulada = ANU.id_motivo_factura_anulada AND FACT.id_sucursal = "+id_sucursal+" ");
        return consultaResusltados(sql);
    }
    
    public ResultSet consultaBuscarFacturaAnulada(String opcion,int id_sucursal,Object op)
    {
        switch (opcion)
        {
            case"Identificacion":
                sql= ("SELECT DISTINCT FACT.id_factura,FACT.numero_factura,"
                + "ANU.fecha_anulacion,ANU.hora_anulacion,MOT.motivo_anulacion,USU.usuario"
                + " FROM factura AS FACT, factura_anulada AS ANU,factura_motivo_anulacion"
                + " AS MOT,usuario AS USU WHERE FACT.id_factura=ANU.id_factura AND ANU.id_usuario_creacion = USU.id_usuario AND"
                + " MOT.id_motivo_factura_anulada = ANU.id_motivo_factura_anulada AND FACT.id_sucursal = "+id_sucursal+" AND FACT.id_factura "
                        + "LIKE '"+op+"%' ORDER BY FACT.id_factura ");
                rh = consultaResusltados(sql);
                break;
            case"Numero Factura":
                sql=("SELECT DISTINCT FACT.id_factura,FACT.numero_factura,"
                + "ANU.fecha_anulacion,ANU.hora_anulacion,MOT.motivo_anulacion,USU.usuario"
                + " FROM factura AS FACT, factura_anulada AS ANU,factura_motivo_anulacion"
                + " AS MOT,usuario AS USU WHERE FACT.id_factura=ANU.id_factura AND ANU.id_usuario_creacion = USU.id_usuario AND"
                + " MOT.id_motivo_factura_anulada = ANU.id_motivo_factura_anulada AND FACT.id_sucursal = "+id_sucursal+" AND FACT.numero_factura "
                        + "LIKE '"+op+"%' ORDER BY FACT.numero_factura ");
                rh = consultaResusltados(sql);
                break;
            case"Fecha":
                sql=("SELECT DISTINCT FACT.id_factura,FACT.numero_factura,"
                + "ANU.fecha_anulacion,ANU.hora_anulacion,MOT.motivo_anulacion,USU.usuario"
                + " FROM factura AS FACT, factura_anulada AS ANU,factura_motivo_anulacion"
                + " AS MOT,usuario AS USU WHERE FACT.id_factura=ANU.id_factura AND ANU.id_usuario_creacion = USU.id_usuario AND"
                + " MOT.id_motivo_factura_anulada = ANU.id_motivo_factura_anulada AND FACT.id_sucursal = "+id_sucursal+" AND ANU.fecha_anulacion "
                        + "LIKE '"+op+"%' ORDER BY ANU.fecha_anulacion ");
                rh = consultaResusltados(sql);
                break;
            
            case"Hora":
                sql=("SELECT DISTINCT FACT.id_factura,FACT.numero_factura,"
                + "ANU.fecha_anulacion,ANU.hora_anulacion,MOT.motivo_anulacion,USU.usuario"
                + " FROM factura AS FACT, factura_anulada AS ANU,factura_motivo_anulacion"
                + " AS MOT,usuario AS USU WHERE FACT.id_factura=ANU.id_factura AND ANU.id_usuario_creacion = USU.id_usuario AND"
                + " MOT.id_motivo_factura_anulada = ANU.id_motivo_factura_anulada AND FACT.id_sucursal = "+id_sucursal+" AND ANU.hora_anulacion "
                        + "LIKE '"+op+"%' ORDER BY ANU.hora_anulacion ");
                rh = consultaResusltados(sql);
                break;
            case"Motivo":
                sql=("SELECT DISTINCT FACT.id_factura,FACT.numero_factura,"
                + "ANU.fecha_anulacion,ANU.hora_anulacion,MOT.motivo_anulacion,USU.usuario"
                + " FROM factura AS FACT, factura_anulada AS ANU,factura_motivo_anulacion"
                + " AS MOT,usuario AS USU WHERE FACT.id_factura=ANU.id_factura AND ANU.id_usuario_creacion = USU.id_usuario AND"
                + " MOT.id_motivo_factura_anulada = ANU.id_motivo_factura_anulada AND FACT.id_sucursal = "+id_sucursal+" AND MOT.motivo_anulacion "
                        + "LIKE '"+op+"%' ORDER BY MOT.motivo_anulacion ");
                rh = consultaResusltados(sql);
                break;
            case"Usuario":
                sql=("SELECT DISTINCT FACT.id_factura,FACT.numero_factura,"
                + "ANU.fecha_anulacion,ANU.hora_anulacion,MOT.motivo_anulacion,USU.usuario"
                + " FROM factura AS FACT, factura_anulada AS ANU,factura_motivo_anulacion"
                + " AS MOT,usuario AS USU WHERE FACT.id_factura=ANU.id_factura AND ANU.id_usuario_creacion = USU.id_usuario AND"
                + " MOT.id_motivo_factura_anulada = ANU.id_motivo_factura_anulada AND FACT.id_sucursal = "+id_sucursal+" AND USU.usuario "
                        + "LIKE '"+op+"%' ORDER BY USU.usuario ");
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
