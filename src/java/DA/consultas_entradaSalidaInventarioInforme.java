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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nestor1
 */
public class consultas_entradaSalidaInventarioInforme extends DA.Conexion {

    private ResultSet rh = null;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;

    public consultas_entradaSalidaInventarioInforme() {
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

    public ResultSet consultaProductosEntrada(int id_empresa) {

        try {
            CallableStatement cst = conex.prepareCall("Call IVN_consultaProductosEntrada(?)");
            cst.setInt("id_empresaLog", id_empresa);
            cst.execute();
            return cst.getResultSet();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet consultaProductosSalida(int id_empresa) {
         try {
            CallableStatement cst = conex.prepareCall("Call IVN_consultaProductosSalida(?)");
            cst.setInt("id_empresaLog", id_empresa);
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet buscarProducto(int id_empresa, Object texto) {

       try {
            CallableStatement cst = conex.prepareCall("Call IVN_consultaProductosSalida(?,?)");
            cst.setInt("idEmpresa", id_empresa);
            cst.setObject("textoLog", texto);
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
