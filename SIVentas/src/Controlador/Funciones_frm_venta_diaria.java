/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Constructores.Constructo_Cantidad_Productos_Vendido;
import Constructores.Constructor_Cantidad_Categoria_Vendido;
import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Proveedor;
import Constructores.Constructor_Usuario;
import Constructores.Constructor_usuario_permiso;
import Constructores.Constructor_venta_Diaria;
import Constructores.Contructor_Cliente_Seleccionado;
import Constructores.Contructor_CortesCaja;
import Fuentes.Grillas;
import Modelo.Consultas_Generales;
import Modelo.consultas_cortesCaja;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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
    
    
    
    public TableModel llenarTablaVentasDiarias(int id_empresa,String fecha,String fechaHasta,String[] nombreColumnas, int[] ancho)
   {
        Funciones_Generales fun = new Funciones_Generales();
        venta_diaria=fun.llenarVentaDiariaConstructor(id_empresa, fecha, fechaHasta);
        Object[] datos = venta_diaria.getFecha();
        String[] columnas = {"FECHA", "TOTAL VENTA"};
        grd = new javax.swing.JTable();;
        DefaultTableModel modelo = (DefaultTableModel) grd.getModel();
        for (int i = 0; i < venta_diaria.getFecha().length; i++) {
            modelo.addRow(datos);
        }
       modelo.setColumnIdentifiers(columnas);
        return modelo;
   }
    public TableModel llenarTablaVentasDiariasXSucursal(String id_sucursal,String fecha,String fechaHasta,String[] nombreColumnas, int[] ancho)
   {
       Modelo.Consultas_Generales con = new Consultas_Generales();
        Funciones_Generales fun = new Funciones_Generales();
        venta_diaria=fun.llenarVentaDiariaConstructorXSucursal(id_sucursal, fecha, fechaHasta);
        Object[] datos = venta_diaria.getFecha();
        String[] columnas = {"FECHA", "TOTAL VENTA"};
        grd = new javax.swing.JTable();;
        DefaultTableModel modelo = (DefaultTableModel) grd.getModel();
        for (int i = 0; i < venta_diaria.getFecha().length; i++) {
            modelo.addRow(datos);
        }
       modelo.setColumnIdentifiers(columnas);
        return modelo;
   }
    
    public float procentajeMasVendido(float cantidadXuno, float sumatoria)
    {
        float procentaje = (100/ sumatoria) * cantidadXuno;
        return procentaje;
    }
    
}
