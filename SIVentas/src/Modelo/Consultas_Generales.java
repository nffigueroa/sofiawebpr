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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.olap4j.PreparedOlapStatement;
import java.sql.*;
import java.sql.CallableStatement;
import java.sql.Date;

/**
 *
 * @author Nestor1
 */
public class Consultas_Generales extends Conexion {

    private ResultSet rh = null;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    public Consultas_Generales()
    {
        con = new Conexion();
        conex = con.crearConexionNueva();
    }
      
    public String consultaPrimerFechaXEmpresa(int id_empresa) {
        String fecha = null;
//        sql=("SELECT FACT.fecha_creacion FROM factura AS FACT,sucursal AS SUC,mi_empresa AS EMPRE"
//                + " WHERE SUC.id_sucursal=FACT.id_sucursal AND SUC.id_empresa=" + id_empresa + "");
//        rh = consultaResusltados(sql);
        try {
            CallableStatement cst = conex.prepareCall("CALL consultaPrimerFechaXEmpresa(?)");
            cst.setInt("empresa", id_empresa);
            cst.execute();
            rh =  cst.getResultSet();
            if (rh.first()) {
                fecha = rh.getString("FACT.fecha_creacion");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }

    public int consultaUtilidadPorProducto(int id_producto_inven) {
        int aux = 0;
        
        try {
            
           CallableStatement cst = conex.prepareCall("CALL consultaUtilidadPorProducto(?)");
            cst.setInt("id_producto_inven", id_producto_inven);
            cst.execute();
            rh =  cst.getResultSet();
            while (rh.next()) {
                aux = rh.getInt("utilidad");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;
    }

    public float consultaCuantoDineroinventario(int id_sucursal) {
        
        float valor_unidad = 0, cantidad_unidad = 0, total = 0;
        try {
        CallableStatement cst = conex.prepareCall("CALL IVN_consultaCuantoDineroinventario()");
        cst.setInt("id_sucursal", id_sucursal);
        cst.execute();
        rh =  cst.getResultSet();
            while (rh.next()) {
                valor_unidad = rh.getFloat("INVEN.cantidad_producto_inventario");
                cantidad_unidad = rh.getFloat("INVEN.precio_producto_inventario");
                total = (valor_unidad * cantidad_unidad) + total;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public float consultaCuantoDineroPorBusqueda(int id_sucursal) {
        
        float valor_unidad = 0, cantidad_unidad = 0, total = 0;
         try {
            CallableStatement cst = conex.prepareCall("CALL CON_consultaCuantoDineroPorBusqueda(?)");
            cst.setInt("id_sucursal", id_sucursal);
            cst.execute();
            rh =  cst.getResultSet();
            while (rh.next()) {
                valor_unidad = rh.getFloat("INVEN.cantidad_producto_inventario");
                cantidad_unidad = rh.getFloat("INVEN.precio_producto_inventario");
                total = (valor_unidad * cantidad_unidad) + total;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public int consultaIdSucursal(String sucursal) {
        int id_sucursal = 0;
        try {
         CallableStatement cst = conex.prepareCall("CALL GEN_consultaIdSucursal(?)");
         cst.setInt("Sucursal", id_sucursal);
         cst.execute();
         rh =  cst.getResultSet();
            while (rh.next()) {
                id_sucursal = rh.getInt("id_sucursal");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_sucursal;
    }

    public int consultaUltimoIdInventario(int id_sucursal) {
        int id_inventario = 0;
        try {
         CallableStatement cst = conex.prepareCall("CALL GEN_consultaUltimoIdInventario(?)");
         cst.setInt("id_sucursal", id_sucursal);
         cst.execute();
         rh = cst.getResultSet();
            while (rh.next()) {
                if (rh.last()) {
                    id_inventario = rh.getInt("id_producto_inventario");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_inventario;
    }

    public boolean consultaRegistrarHistorialInventarioIngreso(Object id_inventario, Object cantidad, Object hora, Object fecha, int id_usuario) {
        boolean ban = false;
        try
        {
            CallableStatement cst = conex.prepareCall("CALL GEN_consultaRegistrarHistorialInventarioIngreso(?,?,?,?,?)");
            cst.setObject("id_inventario", id_inventario);
            cst.setObject("cantidad", cantidad);
            cst.setObject("hora", hora);
            cst.setObject("fecha", fecha);
            cst.setInt("id_usuario", id_usuario);
            ban=cst.execute();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        return ban;
    }

    public int consultaIdSesion(int id_usuario) {
        int id_sesion = 0;
        try {
            CallableStatement cst =  conex.prepareCall("CALL US_consultaIdSesion(?)");
            cst.setInt("id_usuario", id_usuario);
            cst.execute();
            rh = cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (rh.next()) {
                if (rh.last()) {
                    id_sesion = rh.getInt("id_control_sesion");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_sesion;
    }

    public boolean consultaRegistrarCerrarSession(int id_sesion_usuario, Object fecha_fin, Object hora_fin) {
        try
        {
            CallableStatement cst =  conex.prepareCall("CALL US_consultaRegistrarCerrarSession(?,?,?)");
            cst.setInt("id_sesion_usuario", id_sesion_usuario);
            cst.setObject("fecha_fin", fecha_fin);
            cst.setObject("hora_fin", hora_fin);
           return  cst.execute();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean consultaRegistrarSesion(int id_usuario, Object fecha_inicio, Object hora_inicio) {
        
        try
        {
            CallableStatement cst =  conex.prepareCall("CALL US_consultaRegistrarSesion(?,?,?)");
            cst.setInt("id_usuario", id_usuario);
            cst.setString("fecha_fin", fecha_inicio.toString());
            cst.setString("hora_fin", hora_inicio.toString());
            return  cst.execute();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public ResultSet consultaDatosMiEmpresa(int id_sucursal) {
       try{
        //llamarConexion()
        CallableStatement cst =  conex.prepareCall("CALL GEN_consultaDatosMiEmpresa(?)");
        cst.setInt("id_sucursal", id_sucursal);
        cst.execute();
        rh = cst.getResultSet();
       }
       catch(SQLException ex)
       {
           ex.printStackTrace();
           return null;
       }
            return rh;
    }

    public boolean registrarInicioEfectivo(int id_sucursal, Object valor) {
        try{
        CallableStatement cst =  conex.prepareCall("CALL CON_registrarInicioEfectivo(?,?)");
        cst.setInt("id_sucursal", id_sucursal);
        return cst.execute();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public float consultaEfectivoInicial(Object id_sucursal) {
        float efectivo = 0;
        try {
            int idSucursial = Integer.parseInt(id_sucursal.toString());
            CallableStatement cst =  conex.prepareCall("CALL CON_consultaEfectivoInicial(?)");
            cst.setInt("id_sucursal", idSucursial);
            cst.execute();
            rh = cst.getResultSet();
//        rh = ejecutarSQLSelect("SELECT efectivo_inicio,id_sucursal,total_ventas FROM corte_caja WHERE total_ventas=0 AND id_sucursal="+id_sucursal+"");

            if (rh.last()) {
                efectivo = rh.getFloat("efectivo_inicio");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }

        return efectivo;
    }

    public int consultaIdCorteCajaEfectivoInicial(Object id_sucursal) {
        int id_corte = 0;
       try {
            int idSucursal = Integer.parseInt(id_sucursal.toString());
            CallableStatement cst =  conex.prepareCall("CALL CON_consultaIdCorteCajaEfectivoInicial(?)");
            cst.setInt("id_sucursal", idSucursal);
            cst.execute();
            rh = cst.getResultSet();
            if (rh.last()) {
                id_corte = rh.getInt("id_corte_caja");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_corte;
    }

    public boolean registrarSesionUsuario(int id_usuario, String fecha, String hora) {
        try{
            CallableStatement cst =  conex.prepareCall("CALL US_registrarSesionUsuario (?,?,?)");
            cst.setInt("id_sucursal", id_usuario);
            cst.setDate("fecha",Date.valueOf(fecha));
            cst.setString("hora", hora);
            return cst.execute();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean registrarHistorial(String proceso, Object id_usuario, Object fecha, Object hora, String descripcion) {
        try
        {
            CallableStatement cst =  conex.prepareCall("CALL US_registrarHistorial (?,?,?,?)");
            cst.setInt("id_usuario", Integer.parseInt(id_usuario.toString()));
            cst.setString("fecha",(fecha.toString()));
            cst.setString("hora", hora.toString());
            cst.setString("descripcion", descripcion.toString());
            return cst.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
              return false;
        }
    }

    public ResultSet llenarCliente(Object ide) {
        try
        {
            CallableStatement cst =  conex.prepareCall("CALL GEN_llenarCliente (?)");
            cst.setInt("ide", Integer.parseInt(ide.toString()));
            cst.execute();
            return cst.getResultSet();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet llenarClientePorId(Object ide) {
        try
        {
            CallableStatement cst =  conex.prepareCall("CALL GEN_llenarClientePorId (?)");
            cst.setInt("ide", Integer.parseInt(ide.toString()));
            cst.execute();
            return cst.getResultSet();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
