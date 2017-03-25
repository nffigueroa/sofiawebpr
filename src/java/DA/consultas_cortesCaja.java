/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DA;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nestor1
 */
public class consultas_cortesCaja extends Conexion{
    
    public ResultSet rh = null;
     Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_cortesCaja()
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
        boolean ban = true;
        try {
            
            conex = con.crearConexionNueva();
            ps = conex.prepareStatement(sql);
            ban = ps.execute();
           
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_informeMasVendido.class.getName()).log(Level.SEVERE, null, ex);
        }
         return true;
    }
    
    public Date consultaPrimerFecha(int idSucursal)
    {
        Date fecha=null;
       try{
           CallableStatement cst = conex.prepareCall("Call CON_consultaPrimerFecha(?)");
           cst.setInt("idSucursal", idSucursal);
           cst.execute();
           rh = cst.getResultSet();
                if(rh.first())
                fecha = rh.getDate("fecha_creacion");
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }
    public Date consultaUltimaFecha(int idSucursal)
    {
        Date fecha=null;
       try{
           CallableStatement cst = conex.prepareCall("Call CON_consultaUltimaFecha(?)");
           cst.setInt("idSucursal", idSucursal);
           cst.execute();
           rh = cst.getResultSet();
                if(rh.first())
                fecha = rh.getDate("fecha_creacion");
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }
    public boolean consultaRegistrarCorteCaja(Constructores.Contructor_CortesCaja corte,
                                            Object id_sucursal,
                                            Object faltante,
                                            Object feIni,
                                            Object fecFin,
                                            Object efecREal,
                                            int ide) // IdCorte Caja
    {

        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaRegistrarCorteCaja(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            cst.setObject("total", corte.getTotal());
            cst.setObject("totalTarjetaCredito", corte.getTotal_tarjeta_credito());
            cst.setObject("totalTarjetaDebito", corte.getTotal_tarjeta_debito());
            cst.setObject("totalTransaccion", corte.getTotal_transaccion());
            cst.setObject("totalImporteEgreso", corte.getTotal_importe_egreso());
            cst.setObject("totalImporteIngreso", corte.getTotal_importe_ingreso());
            cst.setObject("totalEfectivo", corte.getTotal_efectivo());
            cst.setObject("totalCheque", corte.getTotal_cheque());
            cst.setObject("notaCredito", corte.getTotal_nota_credito());
            cst.setObject("efectivoReal", efecREal);
            cst.setObject("dineroFaltante", faltante);
            cst.setObject("fechaInicio", feIni);
            cst.setObject("fechaFinal", fecFin);
            cst.setObject("idSucursal", id_sucursal);
            cst.setInt("idCorteCaja", ide);
            cst.execute();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public ResultSet consultaFacturacionSinCorte(int id_sucursal)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaFacturacionSinCorte(?)");
            cst.setInt("id_sucursalLog", id_sucursal);
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ResultSet consultaImportes(int id_sucursal)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaImportes(?)");
            cst.setInt("id_sucursalLog", id_sucursal);
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ResultSet consultaTotalFacturacion(int id_sucursal)
    {
         try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaTotalFacturacion(?)");
            cst.setInt("id_sucursalLog", id_sucursal);
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet consultaTotalImporte(int id_sucursal)
    {
         try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaTotalImporte(?)");
            cst.setInt("id_sucursalLog", id_sucursal);
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   
    
    
}
