/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import Constructores.Constructo_Cantidad_Productos_Vendido;
import Constructores.Constructor_Cantidad_Categoria_Vendido;
import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Proveedor;
import Constructores.Constructor_Usuario;
import Constructores.Constructor_usuario_permiso;
import Constructores.Constructor_venta_Diaria;
import Constructores.Contructor_Cliente_Seleccionado;
import Constructores.Contructor_CortesCaja;
import DA.Consultas_Generales;
import DA.consultas_cortesCaja;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_venta_diaria {
    
     Constructor_Usuario cliente_sesion= new Constructor_Usuario();
    Contructor_Cliente_Seleccionado clientes = new Contructor_Cliente_Seleccionado();
    private ResultSet rs = null;
    Date now = new Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructor_usuario_permiso permisos = new Constructor_usuario_permiso();
    Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
    public Connection conn=null;
    private javax.swing.JTable grd;
    Constructores.Constructor_venta_Diaria venta_diaria = new Constructor_venta_Diaria();
    Funciones_Generales general = new Funciones_Generales();
    
    
    
    public ArrayList llenarTablaVentasDiarias(int id_empresa,String fecha,String fechaHasta)
   {
       try
       {
           Funciones_frm_factura fun = new Funciones_frm_factura();
           rs = fun.consultaLlenarTablaFactura(id_empresa, fecha, fechaHasta,false);
           return general.resultSetToArrayList(rs);
       }
       catch(Exception e)
       {
           return null;
       }
   }
    public ArrayList llenarTablaVentasDiariasXSucursal(String id_sucursal,String fecha,String fechaHasta)
   {
       try {
           Funciones_frm_factura fun = new Funciones_frm_factura();
           rs = fun.consultaLlenarTablaFactura(Integer.parseInt(id_sucursal.toString()), fecha, fechaHasta,true);
           return general.resultSetToArrayList(rs);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
    
    public float procentajeMasVendido(float cantidadXuno, float sumatoria)
    {
        float procentaje = (100/ sumatoria) * cantidadXuno;
        return procentaje;
    }
    
}
