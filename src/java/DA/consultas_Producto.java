/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author Nestor1
 */
public class consultas_Producto extends Conexion{
    
    private ResultSet rh= null;
     Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_Producto()
    {
        con = new Conexion();
        conex = con.crearConexionNueva();
    }
    
//    private ResultSet consultaResusltados(String sql) {
//        try {
//            conex = con.crearConexionNueva();
//            ps = conex.prepareStatement(sql);
//            rh = ps.executeQuery();
//           
//        } catch (SQLException ex) {
//            Logger.getLogger(Consultas_informeMasVendido.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         return rh;
//    }
//    private boolean insertarResultados(String sql) {
//        boolean ban = false;
//        try {
//            
//            conex = con.crearConexionNueva();
//            ps = conex.prepareStatement(sql);
//            ban = ps.execute();
//           
//        } catch (SQLException ex) {
//            Logger.getLogger(Consultas_informeMasVendido.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         return true;
//    }
    public ResultSet llenarTabla_Producto(int sucursal)
    {
        
       try{
           CallableStatement cst = conex.prepareCall("Call IVN_llenarTabla_Producto(?)");
           cst.setInt("id_sucursalLog", sucursal);
           cst.execute();
           return cst.getResultSet();
       }
       catch(SQLException e)
       {
           e.printStackTrace();
           return null;
       }
        
    }
    public boolean eliminar_Producto(int ide)
    {
        try{
           CallableStatement cst = conex.prepareCall("Call IVN_eliminar_Producto(?)");
           cst.setInt("ide", ide);
          return  cst.execute();
       }
       catch(SQLException e)
       {
           e.printStackTrace();
           return false;
       }
    }
    public ResultSet consultaLLenarComboCategoria(int idSucursal)
    {
        try{
           CallableStatement cst = conex.prepareCall("Call GEN_consultaRelacionSucursalCategoria(?)");
           cst.setInt("idSucursal", idSucursal);
           cst.execute();
           return cst.getResultSet();
       }
       catch(SQLException e)
       {
           e.printStackTrace();
           return null;
       }
    }
    
    public ResultSet consultaLLenarComboMarca(int idSucursal)
    {
        try{
           CallableStatement cst = conex.prepareCall("Call GEN_consultaRelacionSucursalMarca(?)");
           cst.setInt("idSucursal", idSucursal);
           cst.execute();
           return cst.getResultSet();
       }
       catch(SQLException e)
       {
           e.printStackTrace();
           return null;
       }
    }
    
    public ResultSet consultaLLenarComboMedicion()
    {
       try{
           CallableStatement cst = conex.prepareCall("Call GEN_consultaLLenarComboMedicion()");
           cst.execute();
           return cst.getResultSet();
       }
       catch(SQLException e)
       {
           e.printStackTrace();
           return null;
       }
    }
    public ResultSet consultaLLenarComboMarca()
    {
        try{
           CallableStatement cst = conex.prepareCall("Call GEN_consultaLLenarComboMarca()");
           cst.execute();
           return cst.getResultSet();
       }
       catch(SQLException e)
       {
           e.printStackTrace();
           return null;
       }
    }
    public ResultSet consultaLLenarComboPresentacion()
    {
        try{
           CallableStatement cst = conex.prepareCall("Call GEN_consultaLLenarComboPresentacion()");
           cst.execute();
           return cst.getResultSet();
       }
       catch(SQLException e)
       {
           e.printStackTrace();
           return null;
       }
    }
    
    //CONSULTAR LOS IDS DE LOS COMBOBOX DEL FORMULARIO DE PRODUCTO 
    public Object consultaIDComboCategoria(Object categoria)
    {
        Object id_categoria = null;
        try {
        CallableStatement cst = conex.prepareCall("Call GEN_consultaIDComboCategoria(?)");
        cst.setObject("categoriaLog", categoria);
        cst.execute();
        rh = cst.getResultSet();
            while(rh.next())
            id_categoria = rh.getInt("id_categoria");
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_categoria;
    }
    public Object consultaIDComboPresentacion(Object presentacion)
    {
        Object id_presentacion = null;
        try {
        CallableStatement cst = conex.prepareCall("Call GEN_consultaIDComboPresentacion(?)");
        cst.setObject("presentacionLog", presentacion);
        cst.execute();
        rh = cst.getResultSet();
            while(rh.next())
            id_presentacion = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_presentacion;
    }
    public Object consultaIDComboMedicion(Object medicion)
    {
        Object id_medicion = null;
       try {
        CallableStatement cst = conex.prepareCall("Call GEN_consultaIDComboMedicion(?)");
        cst.setObject("medicionLog", medicion);
        cst.execute();
        rh = cst.getResultSet();
            while(rh.next())
            id_medicion = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id_medicion;
    }
    public Object consultaIDComboMarca(Object marca)
    {
        Object id_marca = null;
        try {
        CallableStatement cst = conex.prepareCall("Call GEN_consultaIDComboMarca(?)");
        cst.setObject("marcaLog", marca);
        cst.execute();
        rh = cst.getResultSet();
            while(rh.next())
            id_marca = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_marca;
    }
    
    public boolean registrarProducto(String nombre,String fecha_creacion,Object usuarioCreacion, Object categoria , Object marca,
            Object medicion , Object presentacion,Object id_sucursal)
    {
        try{
            CallableStatement cst = conex.prepareCall("Call IVN_registrarProducto(?,?,?,?,?,?,?,?)");
            cst.setString("nombre", nombre);
            cst.setString("fecha_creacion", fecha_creacion);
            cst.setInt("usuarioCreacion", Integer.parseInt(usuarioCreacion.toString()));
            cst.setInt("categoria", Integer.parseInt(categoria.toString()));
            cst.setInt("marca", Integer.parseInt(marca.toString()));
            cst.setInt("medicion", Integer.parseInt(medicion.toString()));
            cst.setInt("presentacion", Integer.parseInt(presentacion.toString()));
            cst.setInt("id_sucursalLog", Integer.parseInt(id_sucursal.toString()));
            cst.execute();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean consultaActualizarProducto(Object id_producto,String nombre, Object categoria , Object marca, Object medicion , Object presentacion)
    {
        
        try{
            CallableStatement cst = conex.prepareCall("Call IVN_consultaActualizarProducto(?,?,?,?,?,?)");
            cst.setInt("id_productoLog", Integer.parseInt(id_producto.toString()));
            cst.setString("nombre", nombre);
            cst.setInt("categoria", Integer.parseInt(categoria.toString()));
            cst.setInt("marca", Integer.parseInt(marca.toString()));
            cst.setInt("medicion", Integer.parseInt(medicion.toString()));
            cst.setInt("presentacion", Integer.parseInt(presentacion.toString()));
            cst.execute();
            return true;
        
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }

    }
    
    public ResultSet consultaBuscarProducto(int sucursal,String nombre,String opcionBuscar)
    {
        try{
            CallableStatement cst = conex.prepareCall("Call IVN_consultaBuscarProducto(?,?,?)");
            cst.setInt("sucursal", sucursal);
            cst.setString("nombre", nombre);
            cst.setString("opcionBuscar", opcionBuscar);
            cst.execute();
            return cst.getResultSet();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
     
    }
    
    
}
