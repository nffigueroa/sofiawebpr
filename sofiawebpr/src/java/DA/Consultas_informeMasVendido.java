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
public class Consultas_informeMasVendido extends Conexion {

    private ResultSet rh = null;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;

    public Consultas_informeMasVendido() {
        con = new Conexion();
        conex = con.crearConexionNueva();
    }
    public int consultaIdEmpresa(int id_sucursal) {
            int id_empresa = 0;
            try
            {
            CallableStatement cst = conex.prepareCall("CALL GEN_consultaIdEmpresa(?)");
            cst.setInt("id_sucursalLog", id_sucursal);
            cst.execute();
            rh =cst.getResultSet();
            while (rh.next()) {
                id_empresa = rh.getInt("id_empresa");
            }
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Entrada_Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_empresa;
    }

    public ResultSet consultaLlenarComboSucursal(Object empresa)
    {
        try
            {
            CallableStatement cst = conex.prepareCall("CALL GEN_consultaLlenarComboSucursal(?)");
            cst.setInt("id_empresaLog", Integer.parseInt((empresa.toString())));
            cst.execute();
            rh =cst.getResultSet();
            } catch (SQLException ex) 
            {
                Logger.getLogger(consultas_Entrada_Inventario.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        return rh;
     }
    

    public ResultSet consultaProductoVendido(int id_sucursal) {
        
        try
            {
            CallableStatement cst = conex.prepareCall("CALL IVN_consultaProductoVendidoXSucursal(?)");
            cst.setInt("id_sucursalLog", ((id_sucursal)));
            cst.execute();
            rh =cst.getResultSet();
            } catch (SQLException ex) 
            {
                Logger.getLogger(consultas_Entrada_Inventario.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        return rh;
    }

    public ResultSet consultaProductoVendidoXEmpresa(int id_empresa) {
        
//        String sql= ("SELECT DET.id_factura_detallle,DET.id_producto_inventario,DET.cantidad_factura_detallado,"
//                + "DET.id_factura,INVEN.id_producto_inventario,INVEN.id_producto,PRO.id_produccto,PRO.nombre_producto,"
//                + "FACT.id_factura,FACT.id_sucursal,SUC.id_sucursal,SUC.id_empresa FROM sucursal AS SUC,factura AS FACT,"
//                + "factura_detalle AS DET, producto_inventario AS INVEN, "
//                + "producto AS PRO WHERE FACT.id_factura_anulada=0 AND DET.id_producto_inventario = INVEN.id_producto_inventario "
//                + "AND DET.id_factura=FACT.id_factura "
//                + "AND SUC.id_empresa=" + id_empresa + " AND FACT.id_factura_anulada=0 AND FACT.id_sucursal=SUC.id_sucursal AND"
//                + " INVEN.id_producto = PRO.id_produccto");
//        rh = consultaResusltados(sql);
//        return rh;
         try{
            CallableStatement cst = conex.prepareCall("CALL IVN_consultaProductoVendido(?)");
            cst.setInt("id_empresaLog", id_empresa);
            cst.execute();
            return  cst.getResultSet();
       }
              catch(SQLException e)
       {
           e.printStackTrace();
           return null;
       }
    }

    public ResultSet consultaListaProducto(int id_sucursal) {
        
       try{
            CallableStatement cst = conex.prepareCall("CALL IVN_consultaListaProducto(?)");
            cst.setInt("id_sucursalLog", id_sucursal);
            cst.execute();
            return  cst.getResultSet();
       }
              catch(SQLException e)
       {
           e.printStackTrace();
           return null;
       }
    }

    public ResultSet consultaListaProductoXEmpressa(int id_empresa) {
        
        try{
            CallableStatement cst = conex.prepareCall("CALL IVN_consultaListaProductoXEmpressa(?)");
            cst.setInt("id_empresaLog", id_empresa);
            cst.execute();
            return  cst.getResultSet();
       }
              catch(SQLException e)
       {
           e.printStackTrace();
           return null;
       }
    }

    public Object consultaIdSucursal(Object Sucursal) {
        int id_sucursal = 0;
        try {
         CallableStatement cst = conex.prepareCall("CALL GEN_consultaIdSucursal(?)");
         cst.setInt("Sucursal", id_sucursal);
         cst.execute();
         rh =  cst.getResultSet();
            while (rh.next()) {
                id_sucursal = rh.getInt("id_sucursal");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_sucursal;
    }
}
