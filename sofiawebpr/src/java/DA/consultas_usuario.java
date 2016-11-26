/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DA;

import Constructores.Constructor_usuario_permiso;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Nestor1
 */
public class consultas_usuario extends Conexion {

    private ResultSet rh = null;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    public consultas_usuario()
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

    public boolean consultaModificarUsuario(int id_usuario, Object nombre, Object apellido, Object cc, Object tel,
            Object dir, int id_cargo, Object descr, int id_sucursal, Object user, Object pass) {
        try {
            CallableStatement cst = conex.prepareCall("Call US_consultaModificarUsuario(?,?,?,?,?,?,?,?,?,?,?)");
            cst.setInt("id_usuario", id_usuario);
            cst.setObject("nombre", nombre);
            cst.setObject("apellido", apellido);
            cst.setObject("apellido", apellido);
            cst.setObject("cc", cc);
            cst.setObject("tel", tel);
            cst.setObject("dir", dir);
            cst.setInt("id_cargo", id_cargo);
            cst.setObject("descr", descr);
            cst.setInt("id_sucursal", id_sucursal);
            cst.setObject("user", user);
            cst.setObject("pass", pass);
            return cst.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean consultaEliminarPermisos(Object id_usuario) {
         try {
            CallableStatement cst = conex.prepareCall("Call US_consultaEliminarPermisos(?)");
            cst.setInt("id_usuario", Integer.parseInt(id_usuario.toString()));
            return cst.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet consultaPermisoUsuario(Object id_usuario) {
        try {
            CallableStatement cst= conex.prepareCall("Call US_consultaPermisoUsuario(?)");
            cst.setInt("id_usuarioLog", Integer.parseInt(id_usuario.toString()));
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object consultaSelectUsuario(Object cc) {

        Object id_usuario = null;
       try{
           CallableStatement cst = conex.prepareCall("Call US_consultaSelectUsuario(?)");
           cst.setObject("cc", cc);
           cst.execute();
           rh = cst.getResultSet();
            while (rh.next()) {
                id_usuario = rh.getInt("id_usuario");
            }

        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id_usuario;
    }

    public ResultSet consultaLlenarTabla_Usuario(int id_empresa) {

        try {
            CallableStatement cst = conex.prepareCall("Call US_consultaLlenarTabla_Usuario(?)");
            cst.setInt("id_empresaLog", id_empresa);
            cst.execute();
            return cst.getResultSet();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            return null;
        }

    }

    public boolean consultaRegistrarUsuario(String nombre, String apellido, String cc, String telefono, String dir, Object id_cargo,
            Object descri, String fecha_cre, Object id_usuario_cre, Object id_sucursal, Object usuario, Object psw) {
        boolean resultado = false;
        sql=("INSERT INTO usuario (nombre_usuario,apellido_usuario,cc_usuario,telefono_usuario,"
                + "direccion_usuario,id_cargo,descripcion,fecha_creacion,id_usuario_creacion,id_sucursal,usuario,password) "
                + "VALUES ('" + nombre + "','" + apellido + "','" + cc + "','"
                + telefono + "','" + dir + "'," + id_cargo + ",'" + descri + "','" + fecha_cre + "','" + id_usuario_cre + "','" + id_sucursal + "','" + usuario + "','" + psw + "')");
        return insertarResultados(sql);
    }
    
    public boolean consultaEliminarPermisosUsuario ( int id_usuario)
     {
         try{
            CallableStatement cst = conex.prepareCall("CALL US_BorrarPermisosUsuario(?)");
            cst.setInt("idUsuario", id_usuario);
            cst.execute();
            return true;
         }
         catch(Exception e)
         {
             e.printStackTrace();
             return false;
         }
     }

    public boolean insertarPermisoUsuario(JSONObject permisos, Object id_usuario) {
          boolean resultado = false;
       
         
        int cantidades = permisos.getJSONArray("ids").length();
     //    for (int i = 0; i < categorias.size(); i++) {
         for (int i = 0; i < cantidades; i++) {
             
            int id = permisos.getJSONArray("ids").getInt(i);
             try {
                 CallableStatement cst = conex.prepareCall("Call US_registrarPermisos(?,?)");
                 cst.setInt("permiso",id);
                 cst.setInt("idUsuario", Integer.parseInt(id_usuario.toString()));
                 cst.execute();
                 resultado = true  ;
             } catch (Exception e) {
                 e.printStackTrace();
                 return false;
             }
         }
         return resultado;
    }

    public ResultSet llenarTabla_Permiso() {

       sql=("SELECT DISTINCT * FROM permiso");
        return consultaResusltados(sql);

    }

    public ResultSet llenarComboCargo() {
        sql=("SELECT cargo FROM cargo");
        return consultaResusltados(sql);
    }

    public Object consultaIDComboCargo(Object cargo) {
        Object id_cargo = null;
        sql=("SELECT id_cargo FROM cargo WHERE cargo='" + cargo + "'");
        rh = consultaResusltados(sql);
        try {
            while (rh.next()) {
                id_cargo = rh.getInt("id_cargo");
            }

        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id_cargo;
    }

    public boolean eliminar_Usuario(int ide) {
        
        try {
            CallableStatement cst = conex.prepareCall("CALL US_eliminarUsuario(?)");
            cst.setInt("idUsuario", ide);
            cst.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
        
//        if (insertarResultados("DELETE FROM usuario WHERE id_usuario=" + ide + "")) {
//            insertarResultados("DELETE FROM permiso_usuario WHERE id_usuario=" + ide + "");
//            return true;
//        } else {
//            return false;
//        }
    }
    
    public ResultSet usuario_permiso(int id_usuario) {
        sql=("SELECT id_permiso,id_usuario FROM permiso_usuario WHERE id_usuario=" + id_usuario + "");
        return consultaResusltados(sql);
    }
    
}
