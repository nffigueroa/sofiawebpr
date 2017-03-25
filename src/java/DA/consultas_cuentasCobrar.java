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
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nestor1
 */
public class consultas_cuentasCobrar extends DA.Conexion {

    private ResultSet rh = null;
    DecimalFormat formateador2 = new DecimalFormat("######");
     Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_cuentasCobrar()
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
         return true;
    }
    
    public ResultSet consultaLlenarCuentasCobrar(int id_empresa) throws SQLException {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaCuentasCobrar(?)");    
            cst.setInt("idEmpresa", id_empresa);
            rh = cst.executeQuery();
            return rh;
        } catch (Exception e) {
            return null;
        }
        
//        sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
//                + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
//                + "USU.id_usuario,EST.id_estado,CRE.id_estado"
//                + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,cliente AS CLI,sucursal AS SUC "
//                + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + "");
//        return consultaResusltados(sql);
    }

    public ResultSet consultaBuscarCreddito(String opcionBuscar, int id_empresa, Object datoBuscar) {
        
        switch (opcionBuscar) {
            case "IDENTIFICACION":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.id_credito "
                        + "LIKE '" + datoBuscar + "%' ORDER BY CRE.id_credito");
                rh = consultaResusltados(sql);
                break;

            case "VALOR/MENSUAL":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.valor_pagar_mensual "
                        + "LIKE '" + datoBuscar + "%' ORDER BY CRE.valor_pagar_mensual");
                rh = consultaResusltados(sql);
                break;

            case "TOTAL":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.cantidad_credito "
                        + "LIKE '" + datoBuscar + "%' ORDER BY CRE.cantidad_credito");
                rh = consultaResusltados(sql);
                break;
            case "SUBTOTAL":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.subtotal "
                        + "LIKE '" + datoBuscar + "%' ORDER BY CRE.subtotal");
                rh = consultaResusltados(sql);
                break;

            case "IVA":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.iva "
                        + "LIKE '" + datoBuscar + "%' ORDER BY CRE.iva");
                rh = consultaResusltados(sql);
                break;

            case "DESCUENTO":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " CLI.id_cliente=CRE.id_cliente AND where USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.descuento "
                        + "LIKE '" + datoBuscar + "%' ORDER BY CRE.descuento");
                rh = consultaResusltados(sql);
                break;

            case "% INTERES":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.porcentaje_interes "
                        + "LIKE '" + datoBuscar + "%' ORDER BY CRE.porcentaje_interes");
                rh = consultaResusltados(sql);
                break;

            case "CUOTAS":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.cuotas "
                        + "LIKE '" + datoBuscar + "%' ORDER BY CRE.cuotas");
                rh = consultaResusltados(sql);
                break;

            case "CUOTAS_PAGAS":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.cuotas_pagas "
                        + "LIKE '" + datoBuscar + "%' ORDER BY CRE.cuotas_pagas");
                rh = consultaResusltados(sql);
                break;

            case "CUOTAS_PENDIENTES":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.cuotas_pendientes "
                        + "LIKE '" + datoBuscar + "%' ORDER BY CRE.cuotas_pendientes");
                rh = consultaResusltados(sql);
                break;

            case "ESTADO":
               sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                       + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND EST.estado "
                        + "LIKE '" + datoBuscar + "%' ORDER BY EST.estado");
               rh = consultaResusltados(sql);
                break;
            case "USUARIO":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND USU.usuario "
                        + "LIKE '" + datoBuscar + "%' ORDER BY USU.usuario");
                rh = consultaResusltados(sql);
                break;

            case "FECHA":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.fecha"
                        + "LIKE '" + datoBuscar + "%' ORDER BY CRE.fecha");
                rh = consultaResusltados(sql);
                break;

            case "HORA":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuario AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.hora "
                        + "LIKE '" + datoBuscar + "%' ORDER BY CRE.hora");
                rh = consultaResusltados(sql);
                break;
            case "CLIENTE":
                sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                        + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                        + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                        + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,sucursal AS SUC,cliente AS CLI "
                        + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuario AND CRE.id_estado=EST.id_estado AND "
                        + "CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + " AND CRE.id_cliente"
                        + "LIKE '" + datoBuscar + "%' ORDER CRE.id_cliente");
                rh = consultaResusltados(sql);
                break;
        }
        return rh;
    }
    
    public ResultSet consultaLlenarHistorial(int id_credito)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaHistorialCredito(?)");
            cst.setInt("idCredito", id_credito);
            return cst.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }
    public boolean consultaDescontaraCuota(int nuevaCuotaPendiente,int nuevaCuotaPaga,int id_credito)
    {
        try {
            CallableStatement cst= conex.prepareCall("Call CON_consultaDescontaraCuota(?,?,?)");
            cst.setInt("nuevaCuotaPendiente", nuevaCuotaPendiente);
            cst.setInt("nuevaCuotaPaga", nuevaCuotaPaga);
            cst.setInt("idCredito", id_credito);
            cst.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
   
        
//        if(nuevaCuotaPendiente == 0)
//        
//         sql=("UPDATE credito SET id_estado=2,cuotas_pendientes="+nuevaCuotaPendiente+" , cuotas_pagas="+nuevaCuotaPaga+" WHERE id_credito ="+id_credito+"");
//        
//        else
//            sql=("UPDATE credito SET cuotas_pendientes="+nuevaCuotaPendiente+" , cuotas_pagas="+nuevaCuotaPaga+" WHERE id_credito ="+id_credito+"");
//        return insertarResultados(sql);
    }
    public boolean consultaRegistraarCuotaPaga(int id_cuota, int id_credito,int id_usuario,Object hora , Object fecha, Object recicbe
            , Object cambio)
            
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaRegistraarCuotaPaga(?,?,?,?,?,?,?)");
            cst.setInt("idUsuario", id_usuario);
            cst.setInt("idCredito", id_credito);
            cst.setInt("idCuota", id_cuota);
            cst.setObject("fechaLog", fecha);
            cst.setObject("horaLog", hora);
            cst.setObject("recibeLog", recicbe);
            cst.setObject("cambioLog", cambio);
            cst.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
        
        //sql=("UPDATE historial_credito SET  recibe="+recicbe+",cambio="+cambio+",fecha='"+fecha+"',hora='"+hora+"',"
        //        + "id_usuario="+id_usuario+" WHERE id_credito="+id_credito+" AND numero_cuota="+id_cuota+"");
        //return insertarResultados(sql);
    }
    
    public int consultaSiguienteCuota(int id_credito)
    {
       try {
           CallableStatement cst = conex.prepareCall("Call CON_consultaSiguienteCuota(?)");
           cst.setInt("idCredito", id_credito);
           rh = cst.executeQuery();
        int numero_cuota =0;
        //sql=("SELECT numero_cuota FROM historial_credito WHERE id_credito="+id_credito+" AND recibe=0 ORDER BY numero_cuota");
        //rh = consultaResusltados(sql);
        
            if (rh.first()) {                
                numero_cuota = rh.getInt("numero_cuota");
            }
            return numero_cuota;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        
    }
    
    public String consultaEstadoCredito(int id_credito)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaEstadoCredito(?)");
            cst.setInt("idCredito", id_credito);
            rh = cst.executeQuery();
            String estado = null;
        //sql=("SELECT CRE.id_estado,EST.id_estado,EST.estado FROM credito AS CRE,"
          //      + " estado_credito AS EST WHERE CRE.id_credito="+id_credito+" AND EST.id_estado =CRE.id_estado");
            rh = consultaResusltados(sql);
        
            if (rh.first()) {
                estado= rh.getString("EST.estado");
            }
            return estado;
        } catch (SQLException ex) {
            Logger.getLogger(consultas_cuentasCobrar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public int consultaCuotasPendientes(int id_credito)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaCuotasPendientes(?) ");
            cst.setInt("idCredito", id_credito);
            rh = cst.executeQuery();
        int num_cuotas = 0;
        //sql=("SELECT cuotas_pendientes FROM credito WHERE id_credito="+id_credito+" ");
        //rh = consultaResusltados(sql);
        
            if (rh.first()) {
                num_cuotas= rh.getInt("cuotas_pendientes");
            }
            return num_cuotas;
        } catch (SQLException ex) {
            Logger.getLogger(consultas_cuentasCobrar.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
    
    public int consultaValorTotalCredito(int id_credito)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaValorTotalCredito(?)");
            cst.setInt("idCredito", id_credito);
            rh = cst.executeQuery();
        int total =0;
        //sql=("SELECT cantidad_credito FROM credito WHERE id_credito="+id_credito+"");
        //rh = consultaResusltados(sql);
        
            if (rh.first()) {
                total = rh.getInt("cantidad_credito");
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(consultas_cuentasCobrar.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
    
    public int consultaValorMensualCredito(int id_credito)
    {
        int valor =0;
        try {
        CallableStatement cst = conex.prepareCall("Call CON_consultaValorMensualCredito(?)");
        cst.setInt("idCredito", id_credito);
        rh = cst.executeQuery();
        //sql=("SELECT valor_pagar_mensual FROM credito WHERE id_credito="+id_credito+"");
        //rh = consultaResusltados(sql);
        
            if (rh.first()) {
                valor = rh.getInt("valor_pagar_mensual");
            }
        } catch (SQLException ex) {
            Logger.getLogger(consultas_cuentasCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }
    
    public int consultaCuotasPagas(int id_credito)
    { 
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaCuotasPagas(?)");
            cst.setInt("idCredito", id_credito);
            rh = cst.executeQuery();
        int cuota_paga =0;
        //sql=("SELECT cuotas_pagas FROM credito WHERE id_credito="+id_credito+"");
        //rh = consultaResusltados(sql);
        
            if (rh.first()) {
                cuota_paga = rh.getInt("cuotas_pagas");
            }
            return cuota_paga;
        } catch (SQLException ex) {
            Logger.getLogger(consultas_cuentasCobrar.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
        
    }
}
