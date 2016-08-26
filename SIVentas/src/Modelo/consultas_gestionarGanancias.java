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
public class consultas_gestionarGanancias extends Conexion{
    
    private ResultSet rh = null;
     Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_gestionarGanancias()
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
    public ResultSet consultaLlenarTablaInventarioGanacia()
    {
        sql=("SELECT INVEN.id_producto_inventario,PRO.nombre_producto,MARC.marca,CAT.categoria,PRE.presentacion,MED.medicion,"
                + "INVEN.cantidad_producto_inventario,INVEN.precio_producto_inventario,INVEN.precio_secundario_producto_inventario,"
                + "INVEN.iva_producto_inventario,INVEN.utilidad FROM producto_inventario AS INVEN,marca AS MARC,medicion AS MED,presentacion AS PRE,"
                + "categoria AS CAT,producto AS PRO,sucursal AS SUC, mi_empresa AS EMPRE WHERE INVEN.id_producto=PRO.id_produccto AND "
                + "PRO.id_marca=MARC.id_marca AND PRO.id_categoria=CAT.id_categoria AND PRO.id_presentacion=PRE.id_presentacion AND PRO.id_medicion=MED.id_medicion");
        return consultaResusltados(sql);
    }
    
}
