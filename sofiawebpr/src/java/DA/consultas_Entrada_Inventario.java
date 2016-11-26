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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author Nestor1
 */
public class consultas_Entrada_Inventario extends Conexion{
    
    private ResultSet rh= null;
    private boolean resultado= false;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_Entrada_Inventario()
    {
        con = new Conexion();
        conex = con.crearConexionNueva();
    }
    
    public ResultSet consultaCantidadYCostoProducto(int id_sucursal)
    {
        try
        {
        CallableStatement cst = conex.prepareCall("CALL IVN_consultaCantidadYCostoProducto(?)");
        cst.setInt("id_sucursalLog", id_sucursal);
        cst.execute();
        return cst.getResultSet();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean consultaRegistrarCantidadProductoExistente(float cantidad,int id_producto)
    {
        try
        {
        CallableStatement cst = conex.prepareCall("CALL IVN_consultaRegistrarCantidadProductoExistente(?,?)");
        cst.setFloat("cantidad", cantidad);
        cst.setInt("id_producto", id_producto);
        return cst.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean consultaRegistrarDescuentoProducto(Object id_producto,Object id_motivo,Object cantidad,int id_usuario)
    {
        
       try
        {
            CallableStatement cst = conex.prepareCall("CALL CON_consultaRegistrarDescuentoProducto(?,?,?,?)");
            cst.setFloat("cantidad",Float.parseFloat(cantidad.toString()));
            cst.setInt("id_producto",Integer.parseInt( id_producto.toString()));
            cst.setInt("id_usuario", id_usuario);
            cst.setString("motivo", id_motivo.toString());
            return cst.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public ResultSet llenarTabla_inventario(int id_sucursal)
    {
        
       try
        {
            CallableStatement cst = conex.prepareCall("CALL IVN_llenarTabla_inventario(?)");
            cst.setInt("id_sucursalLog",id_sucursal);
            cst.execute();
            return cst.getResultSet();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        
    }
    
    public ResultSet consultaBuscarProductoInventario(String nombre,String opcionBuscar,int id_sucursal)
    {
        try
        {
            CallableStatement cst = conex.prepareCall("CALL IVN_consultaBuscarProductoInventario(?,?,?)");
            cst.setInt("id_sucursalLog",id_sucursal);
            cst.setString("nombre",nombre);
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
    
    public int consultaIdEmpresa(int id_sucursal)
    {
        int id_empresa = 0;
      
        try {
            CallableStatement cst = conex.prepareCall("CALL GEN_consultaIdEmpresa(?)");
            cst.setInt("id_sucursalLog", id_sucursal);
            cst.execute();
            rh = cst.getResultSet();
            while (rh.next())
            {
                id_empresa = rh.getInt("id_empresa");
            }
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Entrada_Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_empresa;
    }      
    
    public ResultSet consultaLlenarComboProveedor()
    {
        try {
            CallableStatement cst = conex.prepareCall("CALL GEN_consultaLlenarComboProveedor()");
            cst.execute();
            rh = cst.getResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Entrada_Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rh;
    }
    public ResultSet consultaLlenarComboSucursal(Object empresa)
    {
         try {
            CallableStatement cst = conex.prepareCall("CALL GEN_consultaLlenarComboSucursal(?)");
            cst.setInt("id_empresaLog", Integer.parseInt(empresa.toString()));
            cst.execute();
            rh = cst.getResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Entrada_Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rh;
    }
    
    public boolean consultaRegistrarProductoInventario(Object id_producto,Object cantidad,Object stock,Object id_sucursal,Object id_proveedor,
            Object barras, float precio1, float precio2, String iva, Object expiracion,Object id_usuario,float utilidad,Object fecha_creacion)
    {
        if(expiracion ==(""))
        {
            expiracion = "1900/01/01";
        }
     
     try
     {
         CallableStatement cst = conex.prepareCall("Call IVN_consultaRegistrarProductoInventario(?,?,?,?,?,?,?,?,?,?,?,?,?) ");
         cst.setInt("id_producto", Integer.parseInt(id_producto.toString()));
         cst.setObject("cantidad", cantidad);
         cst.setObject("stock", stock);
         cst.setInt("id_sucursalLog", Integer.parseInt((id_sucursal.toString())));
         cst.setInt("id_proveedor", Integer.parseInt(id_proveedor.toString()));
         cst.setObject("barras", barras);
         cst.setFloat("precio1", precio1);
         cst.setFloat("precio2", precio2);
         cst.setObject("iva", (iva));
         cst.setObject("expiracion", expiracion);
         cst.setInt("id_usuario", Integer.parseInt(id_usuario.toString()));
         cst.setFloat("utilidad", utilidad);
         cst.setObject("fecha_creacion", fecha_creacion);
         return cst.execute();
     }
     catch(SQLException ex)
     {
         ex.printStackTrace();
         return false;
     }
    }
    
    public Object consultaIdProducto(Object producto)
    {
        Object id = null;
        try {
                CallableStatement cst = conex .prepareCall("Call IVN_consultaIdProducto(?)");
                cst.setObject("producto", producto);
                cst.execute();
                while(rh.next())
                id = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return id;
    }
    
    public boolean consultaActualizarProductoInventario(Object id_producto,Object cantidad,Object stock,Object id_sucursal,Object id_proveedor,
            Object barras, Object precio1, Object precio2, String iva, String expiracion,Object id_usuario,Object id_producto_inventario)
    {
        if( precio2 == "")
        {
            precio2= null;
        }
       try
     {
         CallableStatement cst = conex.prepareCall("Call IVN_consultaActualizarProductoInventario(?,?,?,?,?,?,?,?,?,?,?,?,?) ");
         cst.setInt("id_producto", Integer.parseInt(id_producto.toString()));
         cst.setObject("cantidad", cantidad);
         cst.setObject("stock", stock);
         cst.setInt("id_scuursal", Integer.parseInt((id_sucursal.toString())));
         cst.setInt("id_proveedor", Integer.parseInt(id_proveedor.toString()));
         cst.setObject("barras", barras);
         cst.setFloat("precio1", Float.parseFloat(precio1.toString()));
         cst.setFloat("precio2", Float.parseFloat(precio1.toString()));
         cst.setObject("iva", (iva));
         cst.setObject("expiracion", expiracion);
         cst.setInt("id_usuario", Integer.parseInt(id_usuario.toString()));
         cst.setObject("id_producto_inventarioLog", id_producto_inventario);
         return cst.execute();
     }
     catch(SQLException ex)
     {
         ex.printStackTrace();
         return false;
     }
    }
    public Object consultaIdSucursal(Object Sucursal)
    {
        Object id = null;
        try
        {
            CallableStatement cst = conex.prepareCall("CALL GEN_consultaIdSucursal(?)");
            cst.setObject("Sucursal", (Sucursal));
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            id = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public Object consultaIdProveedor(Object Proveedor)
    {
         Object id = null;
       try
       {
           CallableStatement cst  = conex.prepareCall("Call GEN_consultaIdProveedor(?)");
           cst.setObject("proveedor", Proveedor);
           cst.execute();
           rh = cst.getResultSet();
            while(rh.next())
            id = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public boolean consultaEliminarProductoInventario(Object id)
    {
       try{
           CallableStatement cst  = conex.prepareCall("Calll IVN_consultaEliminarProductoInventario(?)");
           cst.setObject("id", id);
           return cst.execute();
       }
       catch(SQLException e)
       {
           e.printStackTrace();
           return false;
       }
    }
    public ResultSet consultaMotivosEliminacion()
    {
        try{
            CallableStatement cst = conex.prepareCall("Call GEN_consultaMotivosEliminacion()");
            cst.execute();
            return cst.getResultSet();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public Object consultaIdMotivo(Object motivo)
    {
        Object id = null;
       try{
           CallableStatement cst = conex.prepareCall("Call GEN_consultaIdMotivo(?)");
           cst.setObject("motivo", motivo);
           cst.execute();
           rh = cst.getResultSet();
            while(rh.next())
            id = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public boolean consultaRegistrarProductoEliminado(Object id_producto_inventario, Object descripcion , Object id_usuario , Object id_motivo)
    {
        try
        {
            CallableStatement cst = conex.prepareCall("Call GEN_consultaRegistrarProductoEliminado(?,?,?,?)");
            cst.setObject("id_producto_inventario", id_producto_inventario);
            cst.setObject("descripcion", descripcion);
            cst.setInt("id_usuario", Integer.parseInt(id_usuario.toString()));
            cst.setInt("id_motivo", Integer.parseInt(id_motivo.toString()));
            return cst.execute();            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
