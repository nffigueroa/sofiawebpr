/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DA;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nestor1
 */
public class consultas_proveedores extends Conexion{
    
     private ResultSet rh = null;
      Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_proveedores()
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
         return true;
    }
    
    public boolean consultaModificarProveedor(int id_proveedor, Object empresa, Object contacto, Object tel, Object dir, 
            Object mail, Object id_ciudad, Object nit) {
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_consultaModificarProveedor(?,?,?,?,?,?,?,?)");
            cst.setInt("id_proveedorLog", id_proveedor);
            cst.setObject("empresaLog", empresa);
            cst.setObject("contacto", contacto);
            cst.setObject("tel", tel);
            cst.setObject("dir", dir);
            cst.setObject("mail", mail);
            cst.setInt("id_ciudadLog", Integer.parseInt(id_ciudad.toString()));
            cst.setObject("nit", nit);
            return cst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Boolean consultaEliminarCategoria(Object id_usuario) {
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_consultaEliminarCategoria(?)");
            cst.setInt("id_usuarioLog", Integer.parseInt(id_usuario.toString()));
            return cst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ResultSet consultaCategoriasProveedor(Object id_proveedor)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_consultaCategoriasProveedor(?) ");
            cst.setInt("id_proveedorLog", Integer.parseInt(id_proveedor.toString()));
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    
    public ResultSet consultaCategoriasProveedorSeleccionados(Object id_proveedor)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_CategoriasProveedorConsultar(?) ");
            cst.setInt("id_proveedorPag", Integer.parseInt(id_proveedor.toString()));
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }  
     
    public Object consultaSelectProveedor(Object nit)
    {
        
        Object id_proveedor = null;
        try{
            CallableStatement cst = conex.prepareCall("Call GEN_consultaSelectProveedor(?)");
            cst. setObject("nit", nit);
            cst. execute();
            rh = cst.getResultSet();
            while(rh.next())
            id_proveedor = rh.getInt("id_proveedor");
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_proveedor;
    }
     public ResultSet llenarTabla_Proveedor(int id_sucursal)
    {
        
       try {
            CallableStatement cst = conex.prepareCall("Call GEN_llenarTabla_Proveedor(?) ");
            cst.setInt("id_sucursalLog", id_sucursal);
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
     public boolean consultaEliminarCategoriasProveedor ( int id_proveedor)
     {
         try{
            CallableStatement cst = conex.prepareCall("CALL GEN_eliminar_Categoria_Proveedor(?)");
            cst.setInt("ide", id_proveedor);
            cst.execute();
            return true;
         }
         catch(Exception e)
         {
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean consultaRegistrarProveedor(int id_sucursal,String fecha_crea,String empresa,String contacto,String telefono, 
             String direccion , String mail, Object id_ciudad , Object nit,int usuario)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_consultaRegistrarProveedor(?,?,?,?,?,?,?,?,?,?)");
            cst.setInt("id_sucursalLog", id_sucursal);
            cst.setString("fecha_crea", fecha_crea);
            cst.setString("empresaLog", empresa);
            cst.setString("contacto", contacto);
            cst.setString("telefono", telefono);
            cst.setString("direccion", direccion);
            cst.setString("mail", mail);
            cst.setInt("id_ciudadLog", Integer.parseInt(id_ciudad.toString()));
            cst. setObject("nit", nit);
            cst.setInt("usuario", usuario);
            return cst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     
     public boolean registrarCategoriaProveedor(JSONObject categorias, Object id_proveedor)
     {
         
         boolean resultado = false;
       
         
        int cantidades = categorias.getJSONArray("ids").length();
     //    for (int i = 0; i < categorias.size(); i++) {
         for (int i = 0; i < cantidades; i++) {
             
            int id = categorias.getJSONArray("ids").getInt(i);
             try {
                 CallableStatement cst = conex.prepareCall("Call GEN_registrarCategoriaProveedor(?,?)");
                 cst.setInt("categorias",id);
                 cst.setInt("id_proveedorLog", Integer.parseInt(id_proveedor.toString()));
                 resultado = cst.execute();
             } catch (Exception e) {
                 e.printStackTrace();
                 return false;
             }
         }
         return resultado;
     }
     
    public ResultSet llenarTabla_Categoria()
    {
        
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_consultaLLenarComboCategoria()");
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
      public ResultSet llenarComboCidad_Proveedor()
    {
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_llenarComboCidad_Mi_Sucursal()");
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public Object consultaIDComboCiudad(Object ciudad)
    {
        Object id_categoria = null;
       try{
           CallableStatement cst = conex.prepareCall("Call GEN_consultaIDComboCiudad(?)");
           cst.setObject("ciudad", ciudad);
           cst.execute();
           rh = cst.getResultSet();
            while(rh.next())
            id_categoria = rh.getInt("id_ciudad");
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_categoria;
    }
    
     public boolean eliminar_Proveedor(int ide)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_eliminar_proveedor(?)");
            cst. setInt("ide", ide);
            return cst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
 
    }
     
     public ResultSet buscarProveedor(int id_sucursal,String combo , Object texto)
     {
      
      switch(combo)
      {
          case "Identificacion":
              sql=("SELECT DISTINCT PRO.id_proveedor,PRO.empresa,PRO.contaco_empresa,PRO.telefono_proveedor,"
                + "PRO.direccion_proveedor,PRO.mail_proveedor,PRO.nit_proveedor,CIU.ciudad,"
                + "CIU.id_ciudad,PRO.id_ciudad,PRO.id_sucursal FROM proveedor AS PRO, ciudad AS CIU WHERE PRO.id_sucursal="+id_sucursal+" AND "
                      + "PRO.id_proveedor LIKE '"+texto+"%' ORDER BY PRO.id_proveedor");
              rh = consultaResusltados(sql);
              break;
           case "Empresa":
              sql=("SELECT DISTINCT PRO.id_proveedor,PRO.empresa,PRO.contaco_empresa,PRO.telefono_proveedor,"
                + "PRO.direccion_proveedor,PRO.mail_proveedor,PRO.nit_proveedor,CIU.ciudad,"
                + "CIU.id_ciudad,PRO.id_ciudad,PRO.id_sucursal FROM proveedor AS PRO, ciudad AS CIU WHERE PRO.id_sucursal="+id_sucursal+" "
                      + " AND PRO.empresa LIKE '"+texto+"%' ORDER BY PRO.empresa");
              rh = consultaResusltados(sql);
              break;
             case "Contacto":
              sql=("SELECT DISTINCT PRO.id_proveedor,PRO.empresa,PRO.contaco_empresa,PRO.telefono_proveedor,"
                + "PRO.direccion_proveedor,PRO.mail_proveedor,PRO.nit_proveedor,CIU.ciudad,"
                + "CIU.id_ciudad,PRO.id_ciudad,PRO.id_sucursal FROM proveedor AS PRO, ciudad AS CIU WHERE PRO.id_sucursal="+id_sucursal+" "
                      + " AND PRO.contaco_empresa LIKE '"+texto+"%' ORDER BY PRO.contaco_empresa");
              rh = consultaResusltados(sql);
              break;
             case "Telefono":
              sql=("SELECT DISTINCT PRO.id_proveedor,PRO.empresa,PRO.contaco_empresa,PRO.telefono_proveedor,"
                + "PRO.direccion_proveedor,PRO.mail_proveedor,PRO.nit_proveedor,CIU.ciudad,"
                + "CIU.id_ciudad,PRO.id_ciudad,PRO.id_sucursal FROM proveedor AS PRO, ciudad AS CIU WHERE PRO.id_sucursal="+id_sucursal+" "
                      + "AND PRO.telefono_proveedor LIKE '"+texto+"%' ORDER BY  PRO.telefono_proveedor");
              rh = consultaResusltados(sql);
              break;
             case "Direccion":
              sql=("SELECT DISTINCT PRO.id_proveedor,PRO.empresa,PRO.contaco_empresa,PRO.telefono_proveedor,"
                + "PRO.direccion_proveedor,PRO.mail_proveedor,PRO.nit_proveedor,CIU.ciudad,"
                + "CIU.id_ciudad,PRO.id_ciudad,PRO.id_sucursal FROM proveedor AS PRO, ciudad AS CIU WHERE PRO.id_sucursal="+id_sucursal+" "
                      + " AND PRO.direccion_proveedor LIKE '"+texto+"%' ORDER BY PRO.direccion_direccion");
              rh = consultaResusltados(sql);
              break;
             case "Mail":
              sql=("SELECT DISTINCT PRO.id_proveedor,PRO.empresa,PRO.contaco_empresa,PRO.telefono_proveedor,"
                + "PRO.direccion_proveedor,PRO.mail_proveedor,PRO.nit_proveedor,CIU.ciudad,"
                + "CIU.id_ciudad,PRO.id_ciudad,PRO.id_sucursal FROM proveedor AS PRO, ciudad AS CIU WHERE PRO.id_sucursal="+id_sucursal+" "
                      + " AND PRO.mail LIKE '"+texto+"%' ORDER BY PRO.mail");
              rh = consultaResusltados(sql);
              break;
             case "Nit":
              sql=("SELECT DISTINCT PRO.id_proveedor,PRO.empresa,PRO.contaco_empresa,PRO.telefono_proveedor,"
                + "PRO.direccion_proveedor,PRO.mail_proveedor,PRO.nit_proveedor,CIU.ciudad,"
                + "CIU.id_ciudad,PRO.id_ciudad,PRO.id_sucursal FROM proveedor AS PRO, ciudad AS CIU WHERE PRO.id_sucursal="+id_sucursal+" "
                      + " AND PRO.nit_proveedor LIKE '"+texto+"%' ORDER BY PRO.nit_proveedor");
              rh = consultaResusltados(sql);
              break;
             case "Ciudad":
              sql=("SELECT DISTINCT PRO.id_proveedor,PRO.empresa,PRO.contaco_empresa,PRO.telefono_proveedor,"
                + "PRO.direccion_proveedor,PRO.mail_proveedor,PRO.nit_proveedor,CIU.ciudad,"
                + "CIU.id_ciudad,PRO.id_ciudad,PRO.id_sucursal FROM proveedor AS PRO, ciudad AS CIU WHERE PRO.id_sucursal="+id_sucursal+" "
                      + " AND CIU.ciudad LIKE '"+texto+"%' ORDER BY CIU.ciudad");
              rh = consultaResusltados(sql);
              break;
                       
                                          

              
              
      }
        
        
        return rh;
        
    
     }
     
}
