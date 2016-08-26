/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Constructores.Constructor_usuario_permiso;
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
public class consultas_usuario extends Conexion {

    private ResultSet rh = null;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    public consultas_usuario()
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
         return ban;
    }

    public boolean consultaModificarUsuario(int id_usuario, Object nombre, Object apellido, Object cc, Object tel, Object dir, int id_cargo, Object descr, int id_sucursal, Object user, Object pass) {
       sql=("UPDATE usuario SET nombre_usuario='" + nombre + "', apellido_usuario='" + apellido + "', cc_usuario='" + cc + "',telefono_usuario='" + tel + "'"
                + ",direccion_usuario='" + dir + "',"
                + "id_cargo=" + id_cargo + ",descripcion='" + descr + "',id_sucursal=" + id_sucursal + ",usuario='" + user + "', password='" + pass + "' WHERE id_usuario=" + id_usuario + "");
       return insertarResultados(sql);
    }

    public Boolean consultaEliminarPermisos(Object id_usuario) {
        sql=("DELETE FROM permiso_usuario WHERE id_usuario=" + id_usuario + "");
        return insertarResultados(sql);
    }

    public ResultSet consultaPermisoUsuario(Object id_usuario) {
        sql=("SELECT PER_USU.id_usuario,PER_USU.id_permiso,PER.permiso FROM permiso_usuario"
                + " AS PER_USU,permiso AS PER"
                + " WHERE PER_USU.id_permiso=PER.id_permiso AND PER_USU.id_usuario=" + id_usuario + "");
        return consultaResusltados(sql);
    }

    public Object consultaSelectUsuario(Object cc) {

        Object id_usuario = null;
        sql=("SELECT id_usuario FROM usuario WHERE cc_usuario='" + cc + "'");
        rh = consultaResusltados(sql);
        try {
            while (rh.next()) {
                id_usuario = rh.getInt("id_usuario");
            }

        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id_usuario;
    }

    public ResultSet consultaLlenarTabla_Usuario(int id_empresa) {

       sql=("SELECT DISTINCT USU.id_usuario,USU.nombre_usuario,USU.apellido_usuario,USU.cc_usuario,"
                + "USU.telefono_usuario,USU.direccion_usuario,USU.descripcion,"
                + "USU.fecha_creacion,USU.usuario,SUC.nombre_sucursal,CAR.id_cargo,CAR.cargo,USU.id_cargo,USU.id_sucursal "
                + "FROM usuario AS USU, sucursal AS SUC ,cargo AS CAR WHERE USU.id_cargo=CAR.id_cargo AND USU.id_sucursal=SUC.id_sucursal"
                + " AND SUC.id_empresa =" + id_empresa + " ORDER BY USU.nombre_usuario");
        return consultaResusltados(sql);

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

    public boolean insertarPermisoUsuario(Object[] permisos, Object id_usuario) {
        
        boolean resultado = false;
        for (int i = 0; i < permisos.length; i++) {
            resultado = insertarResultados("INSERT INTO permiso_usuario (id_usuario,id_permiso) VALUES (" + id_usuario + "," + permisos[i] + ")");
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
        
        if (insertarResultados("DELETE FROM usuario WHERE id_usuario=" + ide + "")) {
            insertarResultados("DELETE FROM permiso_usuario WHERE id_usuario=" + ide + "");
            return true;
        } else {
            return false;
        }
    }
    
    public ResultSet usuario_permiso(int id_usuario) {
        sql=("SELECT id_permiso,id_usuario FROM permiso_usuario WHERE id_usuario=" + id_usuario + "");
        return consultaResusltados(sql);
    }
    
}
