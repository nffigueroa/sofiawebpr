/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Fuentes.Grillas;
import Constructores.Constructor_Clientes;
import Modelo.Consultas_Generales;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */

//ESTA CLASE CONTIENE LAS FUNCIONES PARA EL FORMULARIO DE FACTURA Y EL FORMULARIO DE GESTIONAR FACTURA

public class Funciones_frm_factura extends Modelo.consultas_factura{
    
     Date now = new Date(System.currentTimeMillis());
   private java.sql.ResultSetMetaData meta;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
   DecimalFormat formateador2 = new DecimalFormat("###,###.##");
   
   
   public boolean funcionGenerarHistorial(java.util.Date fecha,int cuotas, int id_sucursal)
   {
       int id_credito= cosultaIdCreditoUltima(id_sucursal);
       Object[] fechas = new Object[cuotas] ;
       Calendar cal = Calendar.getInstance();
       cal.setTime(fecha);
       for (int i = 0; i < cuotas; i++) {
           cal.add(Calendar.HOUR, 720);
           fechas[i]= date.format(cal.getTime());
       }
       consultaGenerarHistorialCredito(id_credito, fechas);
       return true;
   }
   
   public boolean funcionRegistrarUtilidad(float utilidad,int id_sucursal)
   {
       int ultimaFact=cosultaIdFacturaUltima(id_sucursal);
       return consultaRegistrarUtilidad(ultimaFact, utilidad);
   }
   
   public boolean descontarCantidadProducto(Object id_producto_inventario,float cantidad)
   {
       return consultaDescontarCantidadProducto(id_producto_inventario, cantidad);
   }
   public float existenciaProductoInventario(Object id_producto_inventario)
   {
       return consultaExistencia(id_producto_inventario);
   }
   public float stockProductoInventario(Object id_producto_inventario)
   {
       return consultaStock(id_producto_inventario);
   }
    
   public Object[] llenarComboPagarCon()
    {
        int columnas=0,i=0,filas=0,q=0;
        Object [] aux = null;
        ResultSet r= null;   
        r=consultallenarComboPagarCon();
       try {
           meta= r.getMetaData();
           columnas= meta.getColumnCount();
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
       }
        try {
            while(r.next())
            {
                filas=filas+1;
            }
            aux= new Object[filas];
            r.beforeFirst();
            while(r.next())
            { 
                if(q==0)
                {
                    r.first();
                    for ( i = 1; i <= columnas; i++) 
                    {
                        aux[q]=r.getObject(i);
                    }
                    q=q+1;
                }
                else
                {
                    for ( i = 1; i <= columnas; i++) 
                    {
                        aux[q]=r.getObject(i);
                    }
                    q=q+1;
                }
            }
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
           ex.printStackTrace();
       }
        return aux;
    }
   
   public boolean funccionesRegistrarFactura(int id_usuario,int id_sucursal, Object folio,Object subtotal, Object descuento, Object iva, Object total, Object cliente, Object formaPago
    ,Object recibe,Object cambio,Object numero_factura,Object tarjeta)
   {
       Consultas_Generales con_generales = new Consultas_Generales();
       con_generales.registrarHistorial("funcionesRegistrarFactura", id_usuario, date.format(now),hora.format(now), "Se registra factura #"+numero_factura+"");
       Object id_cliente = null,id_forma_pago=null,fecha;
       fecha = date.format(now);
       Object hora_factura = hora.format(now);
       id_cliente = cosultaIdCliente(cliente);
       id_forma_pago = cosultaIdFormaPago(formaPago);
       
       return registrarFactura(hora_factura,id_sucursal,folio, subtotal, descuento, iva, total, id_cliente, id_forma_pago, recibe, cambio, id_usuario, fecha,numero_factura,tarjeta);
   }
    public boolean funccionesRegistrarCredito(int id_usuario,int id_sucursal, Object valor_pagar,Object subtotal, Object descuento, Object iva, Object total, Object cliente
    ,Object cuotas,Object porcentaje, Object estado,Object numero_factura)
   {
       Consultas_Generales con_generales = new Consultas_Generales();
       numero_factura = cosultaIdCreditoUltima(id_sucursal);
       if(numero_factura.equals(0))
       {
           numero_factura=1;
       }
       con_generales.registrarHistorial("funccionesRegistrarCredito", id_usuario, date.format(now),hora.format(now), "Se registra credito #"+numero_factura+"");
       Object id_cliente = null,fecha;
       fecha = date.format(now);
       Object hora_factura = hora.format(now);
       id_cliente = cosultaIdCliente(cliente);
       return registrarCredito(hora_factura, id_sucursal, valor_pagar, subtotal, descuento, iva, total, id_cliente, cuotas, id_usuario, fecha, porcentaje, 1,id_cliente);
   }
   
   
   public boolean funccionesRegistrarCotizacion(int id_usuario,int id_sucursal, Object folio,Object subtotal, Object descuento, Object iva, Object total, Object cliente
    , Object descripcion,Object fecha_validez)
   {
       Object id_cliente = null,fecha;
       id_cliente = cosultaIdCliente(cliente);
       Consultas_Generales con_generales = new Consultas_Generales();
       con_generales.registrarHistorial("funcionesRegistrarCotizacion", id_usuario, date.format(now),hora.format(now), "Se registra cotizacion a cliente #"+id_cliente+"");
      
       fecha = date.format(now);
       Object hora_factura = hora.format(now);
       
       return registrarCotizacion(hora_factura,id_sucursal, subtotal, descuento, iva, total, id_cliente,descripcion, id_usuario, fecha,fecha_validez);
   }
   
   public boolean funccionesRegistrarFacturaDetaalle(Object id_producto_inventario,Object item, Object cantidad_factura_detallado,
            Object descuento_factura_detalle, Object valor_unidad_producto, Object subtotal_factura_detalle,int id_sucursal,boolean ban)
   {
      
       Object id_credito;
       if(ban)
            id_credito = cosultaIdCreditoUltima(id_sucursal);
       else
            id_credito= cosultaIdFacturaUltima(id_sucursal);
       return registrarFacturaDetalle(id_producto_inventario, item, cantidad_factura_detallado, descuento_factura_detalle, valor_unidad_producto, subtotal_factura_detalle,id_credito,ban);
   }
   
   public boolean funcionesRegistrarCotizacionDetalle(Object id_producto_inventario,Object item, Object cantidad_factura_detallado,
            Object descuento_factura_detalle, Object valor_unidad_producto, Object subtotal_factura_detalle,int id_sucursal)
   {
       Object id_cotizacion;
       id_cotizacion = cosultaIdCotizacionUltima(id_sucursal);
       
       return registrarCotizacionDetalle(id_producto_inventario, item, cantidad_factura_detallado, descuento_factura_detalle, valor_unidad_producto, subtotal_factura_detalle,id_cotizacion);
   }
   
   public TableModel llenarTablaFacturas(boolean ban,int id_sucursal,String fecha,String fechaHasta,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       try
       {
       return gr.CargarGrd(consultaLlenarTablaFactura(id_sucursal,fecha,fechaHasta,ban), nombreColumnas, ancho);
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
           return null;
       }
       
   }
   public TableModel llenarTablaFacturasXEmpresa(int id_empresa,String fecha,String fechaHasta,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       try
       {
       return gr.CargarGrd(consultaLlenarTablaFacturaXEmpresa(id_empresa,fecha,fechaHasta), nombreColumnas, ancho);
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
           return null;
       }
       
   }
   
   public String primerFecha()
   {
       String fecha= null;
       fecha = consultaPrimerFecha();
       return fecha;
   }
   
   public TableModel llenarArticulos(Object id_factura,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
      
       return gr.CargarGrd(consultaLlenarArticulos(id_factura), nombreColumnas, ancho);
      
     
   }
   
   public Object funcionTotalFacturas(int id_empresa,Object fecha1, Object fecha2)
   {
       Object total=0;
       total=consultaTotalFacturacion(id_empresa,fecha1,fecha2);
       total= formateador2.format(total);
       return total;
   }
   
   public Object funcionTotalFacturasXEmpresas(int id_empresa,Object fecha1, Object fecha2)
   {
       Object total=0;
       total=consultaTotalFacturacion(id_empresa,fecha1,fecha2);
       total= formateador2.format(total);
       return total;
   }
   
   
   public Object funcionIvaFacturas(int id_empresa,Object fecha1, Object fecha2)
   {
       Object iva=0;
       iva=consultaTotalIvaFacturacion(id_empresa,fecha1,fecha2);
       iva= formateador2.format(iva);
       return iva;
   }
   public Object funcionDescuentoFacturas(int id_empresa,Object fecha1, Object fecha2)
   {
       Object descuento=0;
       descuento=consultaTotalIDescuentoFacturacion(id_empresa,fecha1,fecha2);
       descuento = formateador2.format(descuento);
       return descuento;
   }
   public Object funcionGananciaFacturas(int id_empresa,Object fecha1, Object fecha2)
   {
       Object ganancia=0;
       ganancia=consultaTotalIGananciaFacturacion(id_empresa,fecha1,fecha2);
       ganancia = formateador2.format(ganancia);
       return ganancia;
   }
   
   public Object[] llenarComboMotivoAnulacion()
    {
        int columnas=0,i=0,filas=0,q=0;
        Object [] aux = null;
        ResultSet r= null;   
        r=consultaMotivoAnulacion();
       try {
           meta= r.getMetaData();
           columnas= meta.getColumnCount();
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
       }
        try {
            while(r.next())
            {
                filas=filas+1;
            }
            aux= new Object[filas];
            r.beforeFirst();
            while(r.next())
            { 
                if(q==0)
                {
                    r.first();
                    for ( i = 1; i <= columnas; i++) 
                    {
                        aux[q]=r.getObject(i);
                    }
                    q=q+1;
                }
                else
                {
                    for ( i = 1; i <= columnas; i++) 
                    {
                        aux[q]=r.getObject(i);
                    }
                    q=q+1;
                }
            }
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
           ex.printStackTrace();
       }
        return aux;
    }
   
   public  Boolean funcionAnularFactura(Object id_factura,Object motivo, int id_usuario_sesion)
   {
       Consultas_Generales con_generales = new Consultas_Generales();
       con_generales.registrarHistorial("funcionAnularFactura", id_usuario_sesion, date.format(now),hora.format(now), "Se anula factura"+id_factura+"");
       int usuario=1, id_motivo=0,id_anulado=0;
       id_motivo = cosultaIdMotivo(motivo);
       consultaRegistrarFacturaAnulada(id_factura, id_motivo, usuario,date.format(now),hora.format(now));
       id_anulado= cosultaIdFacturaAnulada();
       return consultaLlenaarIdAnulacionFactura(id_anulado,id_factura);
//       if(consultaAnularFactura(id_factura))
//       return true;
//       else
//           return false;
   }
   
   public TableModel buscarFactura(int id_empresa,Object fecha1, Object fecha2 ,String nombre,String opcion,String []nombreColumnas,int []ancho)
    {
       Grillas gr = new Grillas();
       
       return gr.CargarGrd(consultaBuscarPorFacaturar(fecha1,fecha2,opcion,nombre,id_empresa), nombreColumnas, ancho);
    }
   
   public int ultimaFactura(int id_sucursal)
   {
       return cosultaNumeroFactura(id_sucursal);
   }
   
    
}
