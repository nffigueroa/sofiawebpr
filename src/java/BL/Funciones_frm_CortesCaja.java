/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

//import Fuentes.Grillas;
import Constructores.Contructor_Cliente_Seleccionado;
import Constructores.Constructor_Usuario;
import DA.Consultas_Generales;
import DA.consultas_factura;
import DA.consultas_importe;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import org.json.JSONObject;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_CortesCaja extends DA.consultas_cortesCaja{
     Constructor_Usuario cliente_sesion= new Constructor_Usuario();
    Contructor_Cliente_Seleccionado clientes = new Contructor_Cliente_Seleccionado();
    public ResultSet rs = null;
    Date now = new Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Funciones_Generales general = new Funciones_Generales();
    
    
    public boolean registrarIdCorteCajaFactura(Object id_factura,Object id_corteCaja, int id_sucursal)
    {
        consultas_factura fac =new consultas_factura();
        return fac.consultaRegistrarCorteCajaFactura(id_corteCaja,id_factura,id_sucursal);
    }
    public boolean registrarIdCorteCajaImporte(Object id_importe,Object id_corteCaja, int id_sucursal,int id_usuario)
    {
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("registraCorteCaja", id_usuario, date.format(now),hora.format(now), "Se registra importe en corte caja ID:"+id_importe+"");
        DA.consultas_importe fac =new consultas_importe();
        return fac.consultaCorteCajaImporte(id_importe,id_sucursal,id_corteCaja);
    }
    public ArrayList llenarFacturas(int id_sucursal)
   {
       try {
           rh = consultaFacturacionSinCorte(id_sucursal);
           return general.resultSetToArrayList(rh);
       } catch (Exception e) {
           return  null;
       }
       
//       Grillas gr = new Grillas();
//       return gr.CargarGrd(consultaFacturacionSinCorte(id_sucursal), nombreColumnas, ancho);
   }
    public ArrayList llenarImportes(int id_sucursal)
   {
       try {
           rh = consultaImportes(id_sucursal);
           return general.resultSetToArrayList(rh);
       } catch (Exception e) {
           return null;
       }
//       Grillas gr = new Grillas();
//       return gr.CargarGrd(consultaImportes(id_sucursal), nombreColumnas, ancho);
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
    
     public void registrarCorte(float efectivo_real,
                                int idSucursal, 
                                int idUsuario,
                                Constructores.Contructor_CortesCaja corte_caja,
                                JSONObject factura,
                                JSONObject importe)
      {
          float efectivo_faltante=0;
          ArrayList listaFactura = new ArrayList();
          ArrayList listaImporte = new ArrayList();
          //efectivo_real=Float.parseFloat(JOptionPane.showInputDialog(null,"EFECTIVO REAL? $"));
          efectivo_faltante= efectivo_real - corte_caja.getTotal();
         // JOptionPane.showMessageDialog(null, "EFECTIVO FALTANTE : "+efectivo_faltante);
          Funciones_frm_CortesCaja fun = new Funciones_frm_CortesCaja();
          Funciones_frm_CortesCaja corte= new  Funciones_frm_CortesCaja();
          int id_corteCaja = general.IdCorteInicial(idSucursal);
          if(fun.registraCorteCaja(corte_caja, idSucursal, efectivo_faltante,efectivo_real,id_corteCaja,idUsuario)){
              
              int cantidad=  factura.getJSONArray("id_factura").length();
              int cantidadImporte=  importe.getJSONArray("id_importe").length();
              for (int i = 0; i < cantidad; i++) {
                listaFactura.add(factura.getJSONArray("id_factura").get(i));
              }
              for (int i = 0; i < cantidadImporte; i++) {
                listaImporte.add(importe.getJSONArray("id_importe").get(i));
              }
              for (int i = 0; i < cantidad; i++) {
              corte.registrarIdCorteCajaFactura(listaFactura.get(i),id_corteCaja ,idSucursal);    
              }
              for (int i = 0; i < cantidadImporte; i++) {
              fun.registrarIdCorteCajaImporte(listaImporte.get(i),id_corteCaja , idSucursal,idUsuario);    
              }
      }
      }
    
    
}
