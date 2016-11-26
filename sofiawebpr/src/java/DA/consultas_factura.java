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
import java.text.DecimalFormat;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Nestor1
 */
public class consultas_factura extends Conexion{
    
    private ResultSet rh = null;
    DecimalFormat formateador2 = new DecimalFormat("######");
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_factura()
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
    
    public boolean consultaGenerarHistorialCredito(int id_credito,Object[] fechas_pagos)
    {
        
        for (int i = 0; i < fechas_pagos. length; i++) {
             try {
                 CallableStatement cst = conex.prepareCall("Call CON_consultaGenerarHistorialCredito(?,?,?)");
                 cst.setInt("id_creditoLog", id_credito);
                 cst.setInt("i", +1);
                 cst.setObject("fechaPagos", fechas_pagos[i]);
                 cst.execute();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
       return true;
    }
    
    public boolean consultaDescontarCantidadProducto(Object id_producto_inventario,float cantidad)
    {
        
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaDescontarCantidadProducto(?,?)");
            cst.setFloat("cantidad", cantidad);
            cst.setInt("id_producto_inventarioLog",Integer.parseInt( id_producto_inventario.toString()) );
            return cst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public float consultaExistencia(Object id_producto_inventario)
    {
        float cantidad =0;
        try{
            CallableStatement cst = conex.prepareCall("Call IVN_consultaExistencia(?)");
            cst.setInt("idProductoinventario", Integer.parseInt(id_producto_inventario.toString()));
            cst. execute();
            rh = cst.getResultSet();
            while(rh.next())
            cantidad = rh.getFloat("cantidad_producto_inventario");
        } catch (SQLException ex) {
            Logger.getLogger(consultas_factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }
    public float consultaStock(Object id_producto_inventario)
    {
        float stock =0;
        try{
            CallableStatement cst = conex.prepareCall("Call IVN_consultaStock(?)");
            cst.setInt("id_producto_inventarioLog", Integer.parseInt(id_producto_inventario.toString()));
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            stock = rh.getFloat("stock_producto_inventario");
        } catch (SQLException ex) {
            Logger.getLogger(consultas_factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }
    
    public ResultSet consultaDatosEmpresaParaFactura(int id_sucursal)
    {
       
       try {
            CallableStatement cst = conex.prepareCall("Call GEN_consultaDatosEmpresaParaFactura(?)");
            cst.setInt("idSucursal", id_sucursal);
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public int cosultaIdCotizacionUltima(int id_sucursal)
    {
        int id_cotizacion=0;
         try{
            CallableStatement cst = conex.prepareCall("Call CON_cosultaIdCotizacionUltima(?)");
            cst.setInt("id_producto_inventarioLog", id_sucursal);
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            {
                if(rh.last())
                id_cotizacion = rh.getInt("id_cotizaciones");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_cotizacion;
    }
     
     public int cosultaIdCreditoUltima(int id_sucursal)
    {
        int id_cotizacion=0;
       try{
            CallableStatement cst = conex.prepareCall("Call CON_cosultaIdCreditoUltima(?)");
            cst.setInt("idSucursal", id_sucursal);
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            {
                if(rh.last())
                id_cotizacion = rh.getInt("id_credito");
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_cotizacion;
    }
    
    
    public int cosultaIdFacturaUltima(int id_sucursal)
    {
        int id_factura=0;
        try{
            CallableStatement cst = conex.prepareCall("Call CON_cosultaIdFacturaUltima(?)");
            cst.setInt("idSucursal", id_sucursal);
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            {
                if(rh.last())
                id_factura = rh.getInt("id_factura");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_factura;
    }
    
    public int cosultaNumeroFactura(int id_sucursal)
    {
        int id_factura=0;
         try{
            CallableStatement cst = conex.prepareCall("Call CON_cosultaNumeroFactura(?)");
            cst.setInt("idSucursal", id_sucursal);
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            {
                if(rh.last())
                id_factura = rh.getInt("numero_factura");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_factura;
    }
    public boolean consultaRegistrarCorteCajaFactura(Object id_corte_caja,Object id_factura, int id_sucursal)
    {
        
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaRegistrarCorteCajaFactura(?,?,?) ");
            cst.setInt("idCorteCaja", Integer.parseInt(id_corte_caja.toString()));
            cst.setInt("idFactura", Integer.parseInt(id_factura.toString()));
            cst.setInt("idSucursal", id_sucursal);
            return cst.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ResultSet consultallenarComboPagarCon()
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultallenarComboPagarCon()");
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean consultaRegistrarUtilidad(int id_factura,float utilidad)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_consultaRegistrarUtilidad(?,?)");
            cst.setInt("idFactura", id_factura);
            cst.setFloat("utilidadLog", utilidad);
            return cst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean registrarFacturaDetalle(Object id_producto_inventario,Object item, Object cantidad_factura_detallado,
            Object descuento_factura_detalle, Object valor_unidad_producto, Object subtotal_factura_detalle, Object id_factura,boolean ban)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_registrarFacturaDetalle(?,?,?,?,?,?)");       
            if(ban==false)
            {
                cst.setInt("idPrInventario", Integer.parseInt("id_producto_inventario"));
                cst.setInt("itemLog", Integer.parseInt(item.toString()));
                cst.setFloat("cantidad", Float.parseFloat(cantidad_factura_detallado.toString()));
                cst.setFloat("valorUnidad", Float.parseFloat(valor_unidad_producto.toString()));
                cst.setFloat("subtotal", Float.parseFloat(subtotal_factura_detalle.toString()));
                cst.setFloat("factDet", Float.parseFloat(descuento_factura_detalle.toString()));
                cst.setInt("idFactura", Integer.parseInt(id_factura.toString()));
                cst.setBoolean("opcion", ban);
                return cst.execute();
            }
            else
            {
                cst.setInt("idPrInventario", Integer.parseInt("id_producto_inventario"));
                cst.setInt("itemLog", Integer.parseInt(item.toString()));
                cst.setFloat("cantidad", Float.parseFloat(cantidad_factura_detallado.toString()));
                cst.setFloat("valorUnidad", Float.parseFloat(valor_unidad_producto.toString()));
                cst.setFloat("subtotal", Float.parseFloat(subtotal_factura_detalle.toString()));
                cst.setFloat("factDet", Float.parseFloat(descuento_factura_detalle.toString()));
                cst.setInt("idFactura", Integer.parseInt(id_factura.toString()));
                cst.setBoolean("opcion", ban);
                return cst.execute();
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        
        }
    }
    
     public boolean registrarCotizacionDetalle(Object id_producto_inventario,Object item, Object cantidad_factura_detallado,
            Object descuento_factura_detalle, Object valor_unidad_producto, Object subtotal_factura_detalle, Object id_cotizacion)
    {
        
        try {
            CallableStatement cst = conex.prepareCall("Call CON_registrarCotizacionDetalle(?,?,?,?,?,?,?)");
            cst.setInt("idPrInventario", Integer.parseInt(id_producto_inventario.toString()));
            cst.setInt("itemLog", Integer.parseInt(item.toString()));
            cst.setFloat("cantidadFacDet", Float.parseFloat(cantidad_factura_detallado.toString()));
            cst.setFloat("descFactDet", Float.parseFloat(descuento_factura_detalle.toString()));
            cst.setFloat("valorUnidad", Float.parseFloat(valor_unidad_producto.toString()));
            cst.setFloat("subTotal", Float.parseFloat(subtotal_factura_detalle.toString()));
            cst.setInt("idCotizacion", Integer.parseInt(id_cotizacion.toString()));
            return cst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean registrarFactura(Object hora,int id_sucursal,Object folio,Object subtotal, Object descuento, Object iva, Object total, 
     Object id_cliente, Object id_forma_pago ,Object recibe,Object cambio, Object usuario, Object fecha,Object numero_fact,Object tarjeta)
    {
        if(folio =="")
        {
            folio=null;
        }
    
        if(tarjeta !="")
        {
            tarjeta=0;
        }
          
        try {
            CallableStatement cst = conex.prepareCall("Call CON_registrarFactura(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            cst.setInt("idCliente", Integer.parseInt(id_cliente.toString()));
            cst.setObject("horaLog", hora);
            cst. setInt("idSucursal", id_sucursal);
            cst.setInt("folioLog",Integer.parseInt(folio.toString()));
            cst.setFloat("subtotalLog", Float.parseFloat(subtotal.toString()));
            cst.setFloat("descuentoLog", Float.parseFloat(descuento.toString()));
            cst.setFloat("ivaLog", Float.parseFloat(iva.toString()));
            cst.setFloat("totalLog", Float.parseFloat(total.toString()));
            cst.setInt(("idFormaPago"), Integer.parseInt(id_forma_pago.toString()));
            cst.setFloat("recibeLog", Float.parseFloat(recibe.toString()));
            cst.setFloat("cambioLog", Float.parseFloat(cambio.toString()));
            cst.setInt("usuarioLog",Integer.parseInt(usuario.toString()));
            cst.setObject("fechaLog", fecha);
            cst.setInt("numFact",Integer.parseInt(numero_fact.toString()));
            cst.setObject("tarjetaLog", tarjeta);
            return cst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public boolean registrarCredito(Object hora,int id_sucursal,Object valor_pagar,Object subtotal, Object descuento, Object iva, Object total, 
             Object id_cliente
    ,Object cuotas, Object id_usuario, Object fecha,Object porcentaje_interes,Object id_estado,Object cliente)
    {
       
        try {
            CallableStatement cst = conex.prepareCall("Call registrarCredito(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            cst.setObject("horaLog", hora);
            cst.setInt("idSucursal", id_sucursal);
            cst.setFloat("valorPagar",Float.parseFloat(valor_pagar.toString()));
            cst.setFloat("subTotalLog", Float.parseFloat(subtotal.toString()));
            cst.setFloat("descuentoLog", Float.parseFloat(descuento.toString())); 
            cst.setFloat("ivaLog", Float.parseFloat(iva.toString()));
            cst.setFloat("totalLog", Float.parseFloat(total.toString()));
            cst.setInt("idCliente", Integer.parseInt(id_cliente.toString()));
            cst.setInt("cuotasLog", Integer.parseInt(cuotas.toString()));
            cst.setInt("idUsuario", Integer.parseInt(id_usuario.toString()));
            cst.setObject("fechaLog", fecha);
            cst.setInt("porcentajeInteres", Integer.parseInt(porcentaje_interes.toString()));
            cst.setInt("idEstado", Integer.parseInt(id_estado.toString()));
            return cst.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        
    }
    
    public boolean registrarCotizacion(Object hora,int id_sucursal,Object subtotal, Object descuento, Object iva, Object total, Object id_cliente
    , Object descripcion, Object usuario, Object fecha,Object fec_validez)
    {
           
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_registrarCotizacion(?,?,?,?,?,?,?,?,?,?,?)");
            cst.setObject("horaLog", hora);
            cst.setInt("idSucursal", id_sucursal);
            cst.setFloat("subTotalLog", Integer.parseInt(subtotal.toString()));
            cst.setFloat("descuentoLog", Float.parseFloat(descuento.toString())); 
            cst.setFloat("ivaLog", Float.parseFloat(iva.toString()));
            cst.setFloat("totalLog", Float.parseFloat(total.toString()));
            cst.setInt("idCliente", Integer.parseInt(id_cliente.toString()));
            cst.setObject("descripcionLog", descripcion);
            cst.setInt("usuarioLog", Integer.parseInt(usuario.toString()));
            cst.setObject("fechaLog", fecha);
            cst.setObject("fechaValidez", fec_validez);
            return cst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int cosultaIdCliente(Object cliente)
    {
        int id_cliente=0;
       try{
           CallableStatement cst= conex.prepareCall("Call GEN_cosultaIdCliente(?)");
           cst.setObject("cliente", cliente);
           cst.execute();
           rh = cst.getResultSet();
            while(rh.next())
            id_cliente = rh.getInt("id_cliente");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
        return id_cliente;
    }
    
     public int cosultaIdEstadoCredito(Object estado)
    {
        int id_estado=0;
       try{
           CallableStatement cst= conex.prepareCall("Call CRE_cosultaIdEstadoCredito(?)");
           cst.setObject("estadoLog", estado);
           cst.execute();
           rh = cst.getResultSet();
            while(rh.next())
            id_estado = rh.getInt("id_estado");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_estado;
    }
    
    public int cosultaIdFormaPago(Object formaPago)
    {
        int id_forma_pago=0;
       try{
           CallableStatement cst = conex.prepareCall("Call GEN_cosultaIdFormaPago(?)");
           cst.setObject("formaPago", formaPago);
           cst.execute();
           rh = cst.getResultSet();
            while(rh.next())
            id_forma_pago = rh.getInt("id_forma_pago");
            
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return id_forma_pago;
    }
    
     public int cosultaIdFactura()
    {
        int id_factura=0;
       try{
           CallableStatement cst = conex.prepareCall("Call CON_cosultaIdFactura()");
           cst.execute();
           rh = cst.getResultSet();
            while(rh.next())
            {
                if(rh.last())
                id_factura = rh.getInt("id_factura");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_factura;
    }
     
     public int cosultaIdFacturaAnulada()
    {
        int id_factura=0;
        try{
            CallableStatement cst = conex.prepareCall("Call CON_cosultaIdFacturaAnulada()");
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            {
                if(rh.last())
                id_factura = rh.getInt("id_factura_anulada");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_factura;
    }
     
     
     public Boolean consultaLlenaarIdAnulacionFactura(Object id_factura_anulada, Object id_factura)
     {
         try {
             CallableStatement cst = conex.prepareCall("Call CON_consultaLlenaarIdAnulacionFactura(?,?)");
             cst.setInt("idFactura", Integer.parseInt(id_factura.toString()));
             cst.setInt("id_factura_anuladaLog",Integer.parseInt(id_factura_anulada.toString()));
             return cst.execute();
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }
     }
     
     public ResultSet consultaLlenarTablaFactura(int id_sucursal, Object fecha,Object fecha_Hasta,boolean ban)
     {
         try{
             CallableStatement cst = conex.prepareCall("Call CON_consultaLlenarTablaFactura(?,?,?,?)");
             
         if(ban)
         {
            cst.setInt("idSucursal", id_sucursal);
            cst.setObject("fechaLog", fecha);
            cst.setObject("fechaHasta", fecha_Hasta);
            cst.setBoolean("ban", ban);
            cst.execute();
            return cst. getResultSet();
         }
            else
            {
                cst.setInt("idSucursal", id_sucursal);
               cst.setObject("fechaLog", fecha);
               cst.setObject("fechaHasta", fecha_Hasta);
               cst.setBoolean("ban", ban);
               cst.execute();
               return cst. getResultSet();
            }
         }
         catch(SQLException e)
         {
             e.printStackTrace();
             return null;
         }
     }
     public ResultSet consultaLlenarTablaFacturaXEmpresa(int id_empresa, Object fecha,Object fecha_Hasta)
     {
         //ESTA FUNCION LLENA LA TABLA DE VENTAS DIARIAS
         try {
             CallableStatement cst= conex.prepareCall("Call CON_consultaLlenarTablaFacturaXEmpresa(?,?,?)");
             cst.setInt("idEmpresa", id_empresa);
             cst.setObject("fechaLog", fecha);
             cst.setObject("fechaHastaLog", fecha_Hasta);
             cst.execute();
             return cst.getResultSet();
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
     }
     public ResultSet consultaLlenarVentaXMesXEmpresa(int id_empresa, Object fecha,Object fecha_Hasta)
     {
         //ESTA FUNCION LLENA LA TABLA DE VENTAS DIARIAS POR EMPRESA
         try {
             CallableStatement cst = conex.prepareCall("Call GEN_consultaLlenarVentaXMesXEmpresa(?,?,?)");
             cst.setInt("idEmpresa", id_empresa);
             cst.setObject("fechaLog", fecha);
             cst.setObject("fechaHastaLog", fecha_Hasta);
             cst. execute();
             return cst.getResultSet();
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
     }
     
      public ResultSet consultaLlenarVentaXMesXSucursal(int id_sucursal, Object fecha,Object fecha_Hasta)
     {
         //ESTA FUNCION LLENA LA TABLA DE VENTAS DIARIAS
        try {
             CallableStatement cst = conex.prepareCall("Call GEN_consultaLlenarVentaXMesXSucursal(?,?,?)");
             cst.setInt("idSucursal", id_sucursal);
             cst.setObject("fechaLog", fecha);
             cst.setObject("fechaHastaLog", fecha_Hasta);
             cst. execute();
             return cst.getResultSet();
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
     }
     
    public String consultaPrimerFecha()
    {
        String fecha=null;
        try{
            CallableStatement cst = conex.prepareCall("Call CON_consultaPrimerFechaFactura()");
            cst.execute();
            rh = cst.getResultSet();
                if(rh.first())
                fecha = rh.getString("fecha_creacion");
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }
    
    public ResultSet consultaLlenarArticulos(Object id_factura)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_consultaLlenarArticulos(?)");
            cst.setInt("idFactura", Integer.parseInt(id_factura.toString()));
            cst.execute();
            return  cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public float consultaTotalFacturacion(int id_empresa,Object fecha,Object fecha_Hasta)
    {
        float total = 0;
       try{
            CallableStatement cst = conex.prepareCall("Call CON_consultaTotalFacturacionXEmpresa(?,?,?)");
            cst.setInt("idEmpresa", id_empresa);
            cst.setObject("fechaLog", fecha);
            cst.setObject("fechaHasta", fecha_Hasta);
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            {
                total=  total +(rh.getFloat("FACT.total"));  
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
    }
    public float consultaTotalIvaFacturacion(int id_empresa,Object fecha,Object fecha_Hasta)
    {
        float iva=0;
     try{
         CallableStatement cst = conex.prepareCall("Call CON_consultaTotalIvaFacturacion(?,?,?)");
         cst.setInt("idEmpresa", id_empresa);
         cst.setObject("fechaLog", fecha);
         cst.setObject("fechaHasta", fecha_Hasta);
         cst.execute();
         rh = cst.getResultSet();
            while(rh.next())
            {
                iva = iva+ Float.parseFloat(rh.getObject("FACT.iva").toString());
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return iva;
    }
    public float consultaTotalIDescuentoFacturacion(int id_empresa,Object fecha,Object fecha_Hasta)
    {
        float descuento=0;
        try{
        CallableStatement cst = conex.prepareCall("Call CON_consultaTotalIDescuentoFacturacion(?,?,?)");
        cst.setInt("idEmpresa", id_empresa);
        cst.setObject("fechaLog", fecha);
        cst.setObject("fechaHasta", fecha_Hasta);
        cst.execute();
        rh = cst.getResultSet();
            while(rh.next())
            {
                descuento = descuento+ Float.parseFloat(rh.getObject("FACT.descuento").toString());
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return descuento;
    }
    
    public float consultaTotalIGananciaFacturacion(int id_empresa,Object fecha,Object fecha_Hasta)
    {
        float ganancia=0;
        try{
            CallableStatement cst = conex.prepareCall("Call CON_consultaTotalIGananciaFacturacion(?,?,?)");
            cst.setInt("idEmpresa", id_empresa);
            cst.setObject("fechaLog", fecha);
            cst.setObject("fechaHasta", fecha_Hasta);
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            {
                ganancia = ganancia+ (rh.getFloat("FACT.ganancia_factura"));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ganancia;
    }
    
    public boolean consultaRegistrarFacturaAnulada(Object id_facctura,Object id_motivo, Object id_usuario,Object fecha, Object hora)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaRegistrarFacturaAnulada(?,?,?,?,?)");
            cst.setInt("idFactura", Integer.parseInt(id_facctura.toString()));
            cst.setInt("idMotivo", Integer.parseInt(id_motivo.toString()));
            cst.setInt("idUsuario", Integer.parseInt(id_usuario.toString()));
            cst.setObject("fechaLog", fecha);
            cst.setObject("horaLog", hora);
            return cst. execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
   public boolean consultaAnularFactura(Object id_facctura)
   {
       try {
           CallableStatement cst = conex.prepareCall("Call CON_consultaAnularFactura(?)");
           cst.setInt("idFactura", Integer.parseInt(id_facctura.toString()));
           return cst.execute();
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
   }
   public ResultSet consultaMotivoAnulacion()
   {
       try {
           CallableStatement cst = conex.prepareCall("Call GEN_consultaMotivoAnulacion()");
           cst.execute();
           rh = cst.getResultSet();
           return rh;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
   
    public int cosultaIdMotivo(Object motivo)
    {
        int id_motivo=0;
       try{
           CallableStatement cst = conex.prepareCall("Call GEN_cosultaIdMotivo(?)");
           cst.setObject("motivo", motivo);
           cst.execute();
           rh = cst.getResultSet();
            while(rh.next())
            id_motivo = rh.getInt("id_motivo_factura_anulada");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_motivo;
    }
    
    public ResultSet consultaBuscarPorFacaturar( Object fecha,Object fecha_Hasta, String opcion,Object objetoBuscar, int id_empresa)
     {
         //Factura, Folio, Forma Pago, Nombre Cliente, Apellido Cliente, Cedula Cliente, Fecha
         switch (opcion)
         {
             case "Factura":
                 sql =("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,sucursal AS SUC,cliente AS CLI where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                        + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND FACT.id_factura LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                 rh = consultaResusltados(sql);
                 break;
                case "Forma Pago":
                      
                sql =("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI,sucursal AS SUC where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                        + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND FACT.forma_pago LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                rh = consultaResusltados(sql);
             break;
                case "Nombre Cliente":
                      sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI,sucursal AS SUC where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                        + " FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND CLI.nombre_cliente LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                      rh = consultaResusltados(sql);
             break;
                case "Apellido Cliente":
                    
                sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI,sucursal AS SUC where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" "
                        + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND CLI.apellido_cliente LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                rh = consultaResusltados(sql);
             break;
                case "Cedula Cliente":
                    
                    sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI,sucursal AS SUC where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                        + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND CLI.cedula_cliente LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                    rh = consultaResusltados(sql);
             break;
                case "Fecha":
                    sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI, sucursal AS SUC where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                        + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND FACT.fecha_creacion LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                    rh = consultaResusltados(sql);
             break;
                 
                    case "Sucursal":
                    sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI,sucursal AS SUC where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                        + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND SUC.nombre_sucursal LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                    rh = consultaResusltados(sql);
             break;
                                     
         }
         
         return rh;
     }
   
    
}

