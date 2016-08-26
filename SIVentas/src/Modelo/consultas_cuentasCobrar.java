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
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nestor1
 */
public class consultas_cuentasCobrar extends Modelo.Conexion {

    private ResultSet rh = null;
    DecimalFormat formateador2 = new DecimalFormat("######");
     Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_cuentasCobrar()
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
         return true;
    }
    
    public ResultSet consultaLlenarCuentasCobrar(int id_empresa) {
        sql=("SELECT DISTINCT  CRE.id_credito,CRE.valor_pagar_mensual,CRE.cantidad_credito,CRE.subtotal,CRE.iva,CRE.descuento,CRE.porcentaje_interes"
                + ",CRE.cuotas,CRE.cuotas_pagas,CRE.cuotas_pendientes,EST.estado,USU.usuario,CRE.fecha,CRE.hora,CLI.id_cliente,CRE.id_usuario,CRE.id_sucursal,"
                + "USU.id_usuario,EST.id_estado,CRE.id_estado"
                + " FROM credito AS CRE, estado_credito AS EST,usuario AS USU,cliente AS CLI,sucursal AS SUC "
                + " where CLI.id_cliente=CRE.id_cliente AND USU.id_usuario = CRE.id_usuarIO AND CRE.id_estado=EST.id_estado AND CRE.id_sucursal =SUC.id_sucursal AND SUC.id_empresa=" + id_empresa + "");
        return consultaResusltados(sql);
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
        sql=("SELECT DISTINCT  HIST.id_historial_credito,HIST.id_credito,HIST.numero_cuota,HIST.recibe,HIST.cambio,HIST.fecha_pagar,"
                + "HIST.fecha,HIST.hora,HIST.id_usuario"
                + " FROM historial_credito AS HIST WHERE id_credito="+id_credito+"  ORDER BY HIST.numero_cuota");
        return consultaResusltados(sql);
    }
    public boolean consultaDescontaraCuota(int nuevaCuotaPendiente,int nuevaCuotaPaga,int id_credito)
    {
        
        
        if(nuevaCuotaPendiente == 0)
        
         sql=("UPDATE credito SET id_estado=2,cuotas_pendientes="+nuevaCuotaPendiente+" , cuotas_pagas="+nuevaCuotaPaga+" WHERE id_credito ="+id_credito+"");
        
        else
            sql=("UPDATE credito SET cuotas_pendientes="+nuevaCuotaPendiente+" , cuotas_pagas="+nuevaCuotaPaga+" WHERE id_credito ="+id_credito+"");
        return insertarResultados(sql);
    }
    public boolean consultaRegistraarCuotaPaga(int id_cuota, int id_credito,int id_usuario,Object hora , Object fecha, Object recicbe
            , Object cambio)
            
    {
        
        sql=("UPDATE historial_credito SET  recibe="+recicbe+",cambio="+cambio+",fecha='"+fecha+"',hora='"+hora+"',"
                + "id_usuario="+id_usuario+" WHERE id_credito="+id_credito+" AND numero_cuota="+id_cuota+"");
        return insertarResultados(sql);
    }
    
    public int consultaSiguienteCuota(int id_credito)
    {
       
        int numero_cuota =0;
        sql=("SELECT numero_cuota FROM historial_credito WHERE id_credito="+id_credito+" AND recibe=0 ORDER BY numero_cuota");
        rh = consultaResusltados(sql);
        try {
            if (rh.first()) {                
                numero_cuota = rh.getInt("numero_cuota");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero_cuota;
    }
    
    public String consultaEstadoCredito(int id_credito)
    {
        
        String estado = null;
        sql=("SELECT CRE.id_estado,EST.id_estado,EST.estado FROM credito AS CRE,"
                + " estado_credito AS EST WHERE CRE.id_credito="+id_credito+" AND EST.id_estado =CRE.id_estado");
        rh = consultaResusltados(sql);
        try {
            if (rh.first()) {
                estado= rh.getString("EST.estado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(consultas_cuentasCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }
    
    public int consultaCuotasPendientes(int id_credito)
    {
        
        int num_cuotas = 0;
        sql=("SELECT cuotas_pendientes FROM credito WHERE id_credito="+id_credito+" ");
        rh = consultaResusltados(sql);
        try {
            if (rh.first()) {
                num_cuotas= rh.getInt("cuotas_pendientes");
            }
        } catch (SQLException ex) {
            Logger.getLogger(consultas_cuentasCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num_cuotas;
    }
    
    public int consultaValorTotalCredito(int id_credito)
    {
        int total =0;
        sql=("SELECT cantidad_credito FROM credito WHERE id_credito="+id_credito+"");
        rh = consultaResusltados(sql);
        try {
            if (rh.first()) {
                total = rh.getInt("cantidad_credito");
            }
        } catch (SQLException ex) {
            Logger.getLogger(consultas_cuentasCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public int consultaValorMensualCredito(int id_credito)
    {
        int valor =0;
        sql=("SELECT valor_pagar_mensual FROM credito WHERE id_credito="+id_credito+"");
        rh = consultaResusltados(sql);
        try {
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
        int cuota_paga =0;
        sql=("SELECT cuotas_pagas FROM credito WHERE id_credito="+id_credito+"");
        rh = consultaResusltados(sql);
        try {
            if (rh.first()) {
                cuota_paga = rh.getInt("cuotas_pagas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(consultas_cuentasCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuota_paga;
        
    }
}
