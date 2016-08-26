/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
    
    private ResultSet rh = null;
     Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_cortesCaja()
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
        sql=("SELECT fecha_creacion,id_sucursal,id_corte_caja FROM factura WHERE id_corte_caja=0 AND id_sucursal="+idSucursal+"");
        rh = consultaResusltados(sql);
        try {
            
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
        sql=("SELECT fecha_creacion,id_sucursal,id_corte_caja FROM factura WHERE id_corte_caja=0 AND id_sucursal="+idSucursal+"");
        rh = consultaResusltados(sql);
        try {
            
                if(rh.last())
                fecha = rh.getDate("fecha_creacion");
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }
    public boolean consultaRegistrarCorteCaja(Constructores.Contructor_CortesCaja corte,Object id_sucursal,Object faltante,Object feIni,Object fecFin,Object efecREal,int ide)
    {
        sql=("UPDATE corte_caja SET total_ventas="+corte.getTotal()+",total_tarjta_credito="+corte.getTotal_tarjeta_credito()+""
                + ",total_tarjta_debito="+corte.getTotal_tarjeta_debito()+",total_transaccion="+corte.getTotal_transaccion()+","
                + "total_importe_egreso="+corte.getTotal_importe_egreso()+",total_importe_ingreso="+corte.getTotal_importe_ingreso()+","
                + "total_ventas_efectivo="+corte.getTotal_efectivo()+",cheque="+corte.getTotal_cheque()+",credito="+corte.getTotal_nota_credito()+","
                + "total_efectivo_real="+efecREal+",efectivo_faltante="+faltante+",fecha_inicio_corte='"+feIni+"',"
                + "fecha_fin_corte='"+fecFin+"',id_sucursal="+id_sucursal+" WHERE id_corte_caja="+ide+"");
        return insertarResultados(sql);
    }
    
    public ResultSet consultaFacturacionSinCorte(int id_sucursal)
    {
        sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.id_corte_caja,FACT.id_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI WHERE FACT.id_corte_caja=0 AND FACT.id_factura_anulada =0 AND FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND FACT.id_sucursal = "+id_sucursal+" ");
        return consultaResusltados(sql);
    }
    
    public ResultSet consultaImportes(int id_sucursal)
    {
        sql=("SELECT IMP.id_importe,FORMAT(IMP.importe,2),IMP.descripcion_importe"
                + ",TIP.tipo_importe,MOT.motivo_importe,IMP.id_tipo_importe,IMP.id_motivo_importe,MOT.id_motivo_importe,TIP.id_tipo_importe,IMP.id_sucursal,IMP.id_corte_caja "
                + "FROM importes AS IMP,tipo_importe AS TIP, motivo_importe"
                + " AS MOT WHERE IMP.id_sucursal="+id_sucursal+" AND IMP.id_motivo_importe=MOT.id_motivo_importe AND IMP.id_tipo_importe=TIP.id_tipo_importe AND IMP.id_corte_caja=0");
        return consultaResusltados(sql);
    }
    
    public ResultSet consultaTotalFacturacion(int id_sucursal)
    {
        sql=("SELECT DISTINCT FACT.total,FACT.id_forma_pago,FACT.id_sucursal,FACT.id_corte_caja,FORM.id_forma_pago,FORM.forma_pago"
                + "  FROM factura AS FACT, forma_pago AS FORM WHERE FACT.id_corte_caja=0 AND FACT.id_factura_anulada=0 AND FACT.id_sucursal="+id_sucursal+" AND FACT.id_forma_pago = FORM.id_forma_pago");
        return consultaResusltados(sql);
    }
    public ResultSet consultaTotalImporte(int id_sucursal)
    {
        sql=("SELECT DISTINCT id_tipo_importe,id_motivo_importe,importe,id_sucursal,id_corte_caja FROM importes WHERE id_corte_caja=0 AND "
                + "id_sucursal="+id_sucursal+"");
        return consultaResusltados(sql);
    }
   
    
    
}
