package DA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    public Connection conexion = null;

    
    public Connection getConexion() {
        return conexion;

    }
//    public boolean crearConexion() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//           conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swincomc_20140512_siventas", "root","");
//            conexion = DriverManager.getConnection("jdbc:mysql://72.29.85.225/swincomc_20140512_siventas", "swincomc_swinfab","CQel8P1WG]ZZ");
//
//        } catch (SQLException | ClassNotFoundException ex) {
//            return false;
//        } catch (InstantiationException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return true;
//    }
    
    public Connection crearConexionNuevaDominio() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swincomc_20140512_siventas", "root","");
            con = DriverManager.getConnection("jdbc:mysql://72.29.85.225/swincomc_20140512_siventas", "swincomc_swinfab","CQel8P1WG]ZZ");

        } catch (SQLException | ClassNotFoundException ex) {
            return null;
        } catch (InstantiationException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }
    
    
    public Connection crearConexionNueva() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swincomc_20140512_siventas", "root","");
//            con = DriverManager.getConnection("jdbc:mysql://72.29.85.225/swincomc_20140512_siventas", "swincomc_swinfab","CQel8P1WG]ZZ");

        } catch (SQLException | ClassNotFoundException ex) {
            return null;
        } catch (InstantiationException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    public boolean cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean ejecutarSQL(String sql) {
        try {
            Statement sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public ResultSet ejecutarSQLSelect(String sql) {
        ResultSet resultado;
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return resultado;
    }
}
