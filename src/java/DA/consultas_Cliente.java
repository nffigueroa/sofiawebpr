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
public class consultas_Cliente extends Conexion{
    
    private ResultSet rh = null;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    public consultas_Cliente()
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
            ban = ps.execute();
           
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_informeMasVendido.class.getName()).log(Level.SEVERE, null, ex);
        }
         return ban;
    }
    public ResultSet llenarTabla_Cliente(int id_sucursal)
    {
        
        try {
            
           CallableStatement cst = conex.prepareCall("CALL CLI_llenarTabla_Cliente(?)");
            cst.setInt("id_SucursalLog", id_sucursal);
            cst.execute();
            rh =  cst.getResultSet();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rh;
 
        
    }
    public ResultSet llenarImpuestoXCliente(int id_cliente)
    {
        
        try {
            
           CallableStatement cst = conex.prepareCall("CALL CON_consultaImpuestoXCliente(?)");
            cst.setInt("id_clienteLog", id_cliente);
            cst.execute();
            rh =  cst.getResultSet();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rh;
 
        
    }
    
    public ResultSet llenarComboCidad_Cliente()
    {
         try {
            
           CallableStatement cst = conex.prepareCall("CALL CLI_llenarComboCidad_Cliente()");
            cst.execute();
            rh =  cst.getResultSet();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rh;
    }
    public Boolean actualizarImpuestos(int idCliente, String milesIca, int declaraIva, int declaraIca,
            int retefuente, int id_proveedor)
    {
         try {
            
           CallableStatement cst = conex.prepareCall("CALL CON_ModificarImpuesto(?,?,?,?,?,?)");
           cst.setInt("idCliente", idCliente);
           cst.setString("milesIca", milesIca);
           cst.setInt("Iva", declaraIva);
           cst.setInt("declaraIca", declaraIca);
           cst.setInt("reteFuente", retefuente);
           cst.setInt("idProveedor", id_proveedor);
           cst.execute();
           return true; 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Object consultaIDComboCiudad(Object ciudad)
    {
        Object id_categoria = null;
         try {
            
           CallableStatement cst = conex.prepareCall("CALL GEN_consultaIDComboCiudad(?)");
           cst.setString("ciudadLog", ciudad.toString());
            cst.execute();
            rh =  cst.getResultSet();
            while(rh.next())
            id_categoria = rh.getInt("id_ciudad");
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_categoria;
    }
    
    public boolean registrarCliente(int idSucursal,String nombre,String apellido,String telefono, 
            String direccion , String mail, int id_ciudad , Object id_usuario,String iden,
            String tipoCliente, String declaraIva, String declaraIca,String reteFuente,String milesIca,String auxDv)
    {
        try {
            
           CallableStatement cst = conex.prepareCall("CALL CLI_registrarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
           cst.setInt("idSucursal", idSucursal);
           cst.setString("nombre", nombre);
           cst.setString("apellido", apellido);
           cst.setString("telefono", telefono);
           cst.setString("direccion", direccion);
           cst.setString("mail", mail);
           cst.setInt("id_ciudad", id_ciudad);
           cst.setInt("id_usuario", Integer.parseInt(id_usuario.toString()));
           cst.setString("iden", iden);
           cst.setString("tipoCliente", tipoCliente);
           cst.setString("declaraIva", declaraIva);
           cst.setString("declaraIca", declaraIca);
           cst.setString("reteFuente", reteFuente);
           cst.setString("milesIca", milesIca);
           cst.setString("dv", auxDv);
           cst.execute();
           return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean eliminar_Cliente(int ide)
    {
        try
        {
            CallableStatement cst = conex.prepareCall("CALL CLI_eliminar_Cliente(?)");
            cst.setInt("ide", ide);
            cst.execute();
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
     public boolean consultaActualizarCliente(Object usuario,Object id_cliente,String nombre, String apellido, String email, String telefono, String direccion, Object ciudad)
    {
        
        try{
            CallableStatement cst = conex.prepareCall("CALL CLI_consultaActualizarCliente(?,?,?,?,?,?,?,?)");
            cst.setInt("idUsuarioMod", Integer.parseInt(usuario.toString()));
            cst.setInt("idCliente", Integer.parseInt(id_cliente.toString()));
            cst.setString("nombre", nombre);
            cst.setString("apellido", apellido);
            cst.setString("telefono", telefono);
            cst.setString("direccion", direccion);
            cst.setString("mail", email);
            cst.setInt("ciudad", Integer.parseInt(ciudad.toString()));
            cst.execute();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
     
      public ResultSet consultaBuscarCliente(int id_sucursal,String nombre,String opcionBuscar)
    {
       try
       {
           CallableStatement cst = conex.prepareCall("CALL CLI_consultaBuscarCliente(?,?,?)");
           cst.setInt("id_sucursalLog", id_sucursal);
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
