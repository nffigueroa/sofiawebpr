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
        boolean ban = false;
        try {
            
            conex = con.crearConexionNueva();
            ps = conex.prepareStatement(sql);
             if(ps.execute())
                 ban = true;
             else
                 ban=false;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_informeMasVendido.class.getName()).log(Level.SEVERE, null, ex);
        }
         return true;
    }
    
    public ResultSet consultaCantidadYCostoProducto(int id_sucursal)
    {
        try
        {
        CallableStatement cst = conex.prepareCall("CALL IVN_consultaCantidadYCostoProducto(?)");
        cst.setInt("id_sucursal", id_sucursal);
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
        CallableStatement cst = conex.prepareCall("CALL IVN_consultaCantidadYCostoProducto(?)");
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
            cst.setInt("id_sucursal",id_sucursal);
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
            cst.setInt("id_sucursal",id_sucursal);
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
            cst.setInt("id_sucursal", id_sucursal);
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
            cst.setInt("id_empresa", Integer.parseInt(empresa.toString()));
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
     
        sql=("INSERT INTO producto_inventario (id_producto,cantidad_producto_inventario,stock_producto_inventario,"
                + "id_sucursal,id_proveedor,barras_producto_inventario,precio_producto_inventario,precio_secundario_producto_inventario"
                + ",fecha_creacion,iva_producto_inventario,expiracion_producto_inventario,id_usuario_creacion,utilidad) "
                + "VALUES("+id_producto+","+cantidad+","+stock+","+id_sucursal+","+id_proveedor+","+barras+","+precio1+","+precio2+",'"+fecha_creacion+"','"+iva+"','"
                + ""+expiracion+"',"+id_usuario+","+utilidad+")");
        resultado = insertarResultados(sql);
        return resultado;
    }
    
    public Object consultaIdProducto(Object producto)
    {
        Object id = null;
        sql=("SELECT id_producto FROM producto_inventario WHERE id_producto_inventario="+producto+"");
        rh = consultaResusltados(sql);
             try {
            while(rh.next())
            id = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
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
        sql=("UPDATE producto_inventario SET id_producto='"+id_producto+"',cantidad_producto_inventario="+cantidad+","
                + "stock_producto_inventario="+stock+",id_sucursal= "+id_sucursal+",id_proveedor ="+id_proveedor+","
                + "barras_producto_inventario="+barras+",precio_producto_inventario = "+precio1+",precio_secundario_producto_inventario = '"+precio2.toString()+"'"
                + ",iva_producto_inventario = '"+iva+"',expiracion_producto_inventario='"+expiracion+"',id_usuario_creacion ='"+id_usuario+"'"
                + "WHERE id_producto_inventario='"+id_producto_inventario+"' ");
        resultado = insertarResultados(sql);
        return resultado;
    }
    public Object consultaIdSucursal(Object Sucursal)
    {
        Object id = null;
        try
        {
            CallableStatement cst = conex.prepareCall("CALL GEN_consultaIdSucursal(?)");
            cst.setInt("id_sucursal", Integer.parseInt(Sucursal.toString()));
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
        sql=("SELECT id_proveedor FROM proveedor WHERE empresa='"+Proveedor+"'");
        rh = consultaResusltados(sql);
             try {
            while(rh.next())
            id = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public boolean consultaEliminarProductoInventario(Object id)
    {
        sql=("DELETE FROM producto_inventario WHERE id_producto_inventario="+id+"");
        resultado = insertarResultados(sql);
        return resultado;
    }
    public ResultSet consultaMotivosEliminacion()
    {
        sql=("SELECT motivo_eliminacion FROM motivo_eliminacion_inventario");
        rh = consultaResusltados(sql);
        return rh;
    }
    
    public Object consultaIdMotivo(Object motivo)
    {
        Object id = null;
        sql =("SELECT id_mot_elimi_inv FROM motivo_eliminacion_inventario WHERE motivo_eliminacion='"+motivo+"'");
        rh = consultaResusltados(sql);
             try {
            while(rh.next())
            id = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public boolean consultaRegistrarProductoEliminado(Object id_producto_inventario, Object descripcion , Object id_usuario , Object id_motivo)
    {
        sql=("INSERT INTO producto_eliminado (id_producto_inventario,descripcion_producto_eliminado,id_usuario_creacion,id_mot_elimi_inv)"
                + " VALUES ("+id_producto_inventario+",'"+descripcion+"',"+id_usuario+","+id_motivo+")");
        resultado = insertarResultados(sql);
        return resultado;
    }
}
