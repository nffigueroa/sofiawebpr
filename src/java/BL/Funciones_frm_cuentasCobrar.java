/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

//import Fuentes.Grillas;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_cuentasCobrar extends DA.consultas_cuentasCobrar{
    
   Date now = new Date(System.currentTimeMillis());
   private java.sql.ResultSetMetaData meta;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
   DecimalFormat formateador2 = new DecimalFormat("###,###.##");
   BL.Funciones_Generales general = new Funciones_Generales();
   ResultSet rs = null;
   
   
   public ArrayList funcionLlenarCredito(int idSucursal)
   {
       try {
           BL.Funciones_Generales fun = new Funciones_Generales();
           int id_empresa = fun.ideEmpresaXIdeSucursal(idSucursal);
           rs = consultaLlenarCuentasCobrar(id_empresa);
           return general.resultSetToArrayList(rs);
       } catch (Exception e) {
           return null;
       }
//       Grillas gr = new Grillas();
//       return gr.CargarGrd(consultaLlenarCuentasCobrar(id_empresa), nombreColumnas, ancho);
   }
   
   public ArrayList funcionBuscarCredito(int id_empresa,String opcionBuscar,Object datoBuscar)
   {
       try {
           rs = consultaBuscarCreddito(opcionBuscar, id_empresa, datoBuscar);
           return general.resultSetToArrayList(rs);
       } catch (Exception e) {
           return null;
       }
//       Grillas gr = new Grillas();
//       return gr.CargarGrd(consultaBuscarCreddito(opcionBuscar,id_empresa,datoBuscar), nombreColumnas, ancho);
   }
   
   public ArrayList funcionLlenarHistorialCredito(int id_credito)
   {
       try {
           rs  = consultaLlenarHistorial(id_credito);
           return general.resultSetToArrayList(rs);
       } catch (Exception e) {
           return null;
       }
//       Grillas gr = new Grillas();
//       return gr.CargarGrd(consultaLlenarHistorial(id_credito), nombreColumnas, ancho);
   }
   
   public int funcionSiguienteCuota(int id_credito)
   {
       return consultaSiguienteCuota(id_credito);
   }
   
   public String funcionDatosCredito(int id_credito)
   {
      
       return  consultaEstadoCredito(id_credito);
   }
   
   public int funcionNumeroCuotas(int id_credito)
   {
      
       return  consultaCuotasPendientes(id_credito);
   }
   public int funcionValorTotalCredito(int id_credito)
   {
       return consultaValorTotalCredito(id_credito);
   }
   public int funcionValorMensualCredito(int id_credito)
   {
       return consultaValorMensualCredito(id_credito);
   }
   
   public boolean funcionRegistrarAbonoCredito(int id_credito,int id_cuota,Object recibe , Object cambio , Object fecha , 
           Object hora , int id_usuario,int cantidad_cuotas)
   {
       cantidad_cuotas= cantidad_cuotas -1;
       int cuotas_pagas = consultaCuotasPagas(id_credito)+1;
       consultaDescontaraCuota(cantidad_cuotas, cuotas_pagas, id_credito);
       return consultaRegistraarCuotaPaga(id_cuota, id_credito, id_usuario, hora, fecha, recibe, cambio);
   }
   
    
}
