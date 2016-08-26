/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Fuentes.Grillas;
import Constructores.Contructor_Cliente_Seleccionado;
import Constructores.Constructor_Usuario;
import Modelo.Consultas_Generales;
import Modelo.consultas_factura;
import Modelo.consultas_importe;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_CortesCaja extends Modelo.consultas_cortesCaja{
     Constructor_Usuario cliente_sesion= new Constructor_Usuario();
    Contructor_Cliente_Seleccionado clientes = new Contructor_Cliente_Seleccionado();
    private ResultSet rs = null;
    Date now = new Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    
    
    
    public boolean registrarIdCorteCajaFactura(Object id_factura,Object id_corteCaja, int id_sucursal)
    {
        consultas_factura fac =new consultas_factura();
        return fac.consultaRegistrarCorteCajaFactura(id_corteCaja,id_factura,id_sucursal);
    }
    public boolean registrarIdCorteCajaImporte(Object id_importe,Object id_corteCaja, int id_sucursal,int id_usuario)
    {
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("registraCorteCaja", id_usuario, date.format(now),hora.format(now), "Se registra importe en corte caja ID:"+id_importe+"");
        Modelo.consultas_importe fac =new consultas_importe();
        return fac.consultaCorteCajaImporte(id_importe,id_sucursal,id_corteCaja);
    }
    public TableModel llenarFacturas(int id_sucursal,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(consultaFacturacionSinCorte(id_sucursal), nombreColumnas, ancho);
   }
    public TableModel llenarImportes(int id_sucursal,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(consultaImportes(id_sucursal), nombreColumnas, ancho);
   }
    
    public boolean registraCorteCaja(Constructores.Contructor_CortesCaja corte,int idSucursal,Object faltante,Object efecReal,int ide,int id_usuario)
    {
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("registraCorteCaja", id_usuario, date.format(now),hora.format(now), "Se realiza corte de caja ID:"+ide+"");
        Object fecha1=null,fecha2=null;
        fecha1=consultaPrimerFecha(idSucursal);
        fecha2=consultaUltimaFecha(idSucursal);
        return consultaRegistrarCorteCaja(corte,idSucursal,faltante,fecha1,fecha2,efecReal,ide);
    }
    
    
}
