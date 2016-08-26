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
    
    public boolean consultaModificarProveedor(int id_proveedor, Object empresa, Object contacto, Object tel, Object dir, Object mail, Object id_ciudad, Object nit) {
       sql=("UPDATE proveedor SET empresa='" + empresa + "', contaco_empresa='" + contacto + "', telefono_proveedor='" + 
               tel + "',direccion_proveedor='" + dir + "'"+ ",mail_proveedor='" + mail + "',"
                + "id_ciudad=" + id_ciudad + ",nit_proveedor='" + nit + "' WHERE id_proveedor=" + id_proveedor + "");
       return insertarResultados(sql);
    }
    
    public Boolean consultaEliminarCategoria(Object id_usuario) {
        sql=("DELETE FROM permiso_usuario WHERE id_usuario=" + id_usuario + "");
        return insertarResultados(sql);
    }
    public ResultSet consultaCategoriasProveedor(Object id_proveedor)
    {
        sql=("SELECT CAT.id_categoria,CAT.id_proveedor,CA.id_categoria,CA.categoria FROM categoria_proveedor AS CAT, categoria AS CA"
               + " WHERE CAT.id_categoria=CA.id_categoria AND CAT.id_proveedor="+id_proveedor+"");
        return consultaResusltados(sql);
    }       
     
    public Object consultaSelectProveedor(Object nit)
    {
        
        Object id_proveedor = null;
        sql=("SELECT id_proveedor FROM proveedor WHERE nit_proveedor='"+nit+"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            id_proveedor = rh.getInt("id_proveedor");
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_proveedor;
    }
     public ResultSet llenarTabla_Proveedor(int id_sucursal)
    {
        
        sql=("SELECT DISTINCT PRO.id_proveedor,PRO.empresa,PRO.contaco_empresa,PRO.telefono_proveedor,"
                + "PRO.direccion_proveedor,PRO.mail_proveedor,PRO.nit_proveedor,CIU.ciudad,"
                + "CIU.id_ciudad,PRO.id_ciudad,PRO.id_sucursal FROM proveedor AS PRO, ciudad AS CIU WHERE CIU.id_ciudad=PRO.id_ciudad AND "
                + "PRO.id_sucursal="+id_sucursal+" ORDER BY empresa");
        return consultaResusltados(sql);
        
    }
     public boolean consultaRegistrarProveedor(int id_sucursal,String fecha_crea,String empresa,String contacto,String telefono, String direccion , String mail, Object id_ciudad , Object nit,int usuario)
    {
        boolean resultado = false;
        sql=("INSERT INTO proveedor (empresa,contaco_empresa,telefono_proveedor,direccion_proveedor,"
                + "mail_proveedor,id_ciudad,nit_proveedor,id_usuario_creacion,fecha_creacion,id_sucursal) VALUES ('"+empresa+"','"+contacto+"','"+telefono+"','"
                + direccion+"','"+mail+"',"+id_ciudad+",'"+nit+"',"+usuario+",'"+fecha_crea+"',"+id_sucursal+")");
        return insertarResultados(sql);
    }
     
     public boolean registrarCategoriaProveedor(Object[]categorias, Object id_proveedor)
     {
         
         boolean resultado = false;
         for (int i = 0; i < categorias.length; i++) {
             sql=("INSERT INTO categoria_proveedor (id_categoria,id_proveedor) VALUES ("+categorias[i]+","+id_proveedor+")");  
             resultado= insertarResultados(sql);
         }
         return resultado;
     }
     
    public ResultSet llenarTabla_Categoria()
    {
        
        sql=("SELECT DISTINCT id_categoria,categoria FROM categoria");
        return consultaResusltados(sql);
        
    }
      public ResultSet llenarComboCidad_Proveedor()
    {
        sql=("SELECT ciudad FROM CIUDAD");
        
        return consultaResusltados(sql);
    }
     public Object consultaIDComboCiudad(Object ciudad)
    {
        Object id_categoria = null;
        sql=("SELECT id_ciudad FROM CIUDAD WHERE ciudad='"+ciudad+"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            id_categoria = rh.getInt("id_ciudad");
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_categoria;
    }
    
     public boolean eliminar_Proveedor(int ide)
    {
        
        if(insertarResultados("DELETE FROM proveedor WHERE id_proveedor="+ide+""))
        {
            insertarResultados("DELETE FROM categoria_proveedor WHERE id_proveedor="+ide+"");
            return true;}
        else{
            return false;}   
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
