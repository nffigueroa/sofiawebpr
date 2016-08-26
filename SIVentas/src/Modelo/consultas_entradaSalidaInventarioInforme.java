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
public class consultas_entradaSalidaInventarioInforme extends Modelo.Conexion {

    private ResultSet rh = null;
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;

    public consultas_entradaSalidaInventarioInforme() {
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

    public ResultSet consultaProductosEntrada(int id_empresa) {

        sql = ("SELECT DISTINCT\n"
                + "                INVEN.id_producto_inventario,\n"
                + "                PRO.nombre_producto,HISTO.cantidad,CAT.categoria,PRE.presentacion,"
                + "MED.medicion, MARC.marca,HISTO.fecha_historial,HISTO.hora_historial,USU.usuario,SUC.nombre_sucursal,HISTO.id_usuario,"
                + "USU.id_usuario,PRO.id_medicion,HISTO.id_producto_inventario,PRO.id_marca,PRO.id_presentacion,"
                + "INVEN.id_sucursal,INVEN.id_producto,\n"
                + "          PRO.id_produccto,SUC.id_sucursal,SUC.id_empresa,CAT.id_categoria,MED.id_medicion,PRO.id_categoria,MARC.id_marca,"
                + "PRE.id_presentacion \n"
                + "               FROM sucursal AS SUC,producto_inventario AS INVEN, categoria AS CAT, medicion AS MED , historial_entrada_inventario "
                + "AS HISTO,presentacion AS PRE , \n"
                + "                marca AS MARC,producto AS PRO,usuario AS USU WHERE SUC.id_empresa =" + id_empresa + " AND INVEN.id_sucursal=SUC.id_sucursal AND  "
                + "HISTO.id_producto_inventario = INVEN.id_producto_inventario AND  USU.id_usuario = HISTO.id_usuario AND INVEN.id_producto=PRO.id_produccto AND PRO.id_medicion = MED.id_medicion AND PRO.id_presentacion = PRE.id_presentacion AND PRO.id_marca = MARC.id_marca AND CAT.id_categoria= PRO.id_categoria \n"
                + " ORDER BY INVEN.fecha_creacion  ");
        return consultaResusltados(sql);
    }

    public ResultSet consultaProductosSalida(int id_empresa) {
        sql = ("SELECT DISTINCT INVEN.id_producto_inventario,PRO.nombre_producto,DET.cantidad_factura_detallado,CAT.categoria,PRE.presentacion,MED.medicion,MARC.marca,FACT.fecha_creacion,FACT.hora_creacion,USU.usuario,SUC.nombre_sucursal,\n"
                + "FACT.id_factura,FACT.id_sucursal,FACT.id_usuario_creacion,\n"
                + "DET.id_factura,DET.id_producto_inventario,\n"
                + "INVEN.id_producto,\n"
                + "USU.id_usuario,\n"
                + "PRO.id_produccto,PRO.id_medicion,PRO.id_marca,PRO.id_categoria,PRO.id_presentacion,SUC.id_empresa,\n"
                + "MED.id_medicion,\n"
                + "MARC.id_marca,\n"
                + "CAT.id_categoria,\n"
                + "PRE.id_presentacion\n"
                + "FROM sucursal AS SUC,\n"
                + "factura AS FACT,\n"
                + "factura_detalle AS DET,\n"
                + "producto_inventario AS INVEN,\n"
                + "usuario AS USU,\n"
                + "producto AS PRO,\n"
                + "medicion AS MED,\n"
                + "marca AS MARC,\n"
                + "categoria AS CAT,\n"
                + "presentacion AS PRE\n"
                + "WHERE SUC.id_empresa =" + id_empresa + " AND FACT.id_sucursal = SUC.id_sucursal \n"
                + "AND FACT.id_factura = DET.id_factura\n"
                + "AND INVEN.id_producto = PRO.id_produccto \n"
                + "AND DET.id_producto_inventario = INVEN.id_producto_inventario\n"
                + "AND FACT.id_usuario_creacion = USU.id_usuario_creacion \n"
                + "AND PRO.id_medicion=MED.id_medicion \n"
                + "AND PRO.id_marca=MARC.id_marca\n"
                + "AND PRO.id_categoria=CAT.id_categoria\n"
                + "AND PRO.id_presentacion = PRE.id_presentacion\n"
                + "AND FACT.id_usuario_creacion = USU.id_usuario");
        return consultaResusltados(sql);
    }

    public ResultSet buscarProducto(int id_empresa, Object texto) {

        sql = ("SELECT DISTINCT\n"
                + "                INVEN.id_producto_inventario,\n"
                + "                PRO.nombre_producto,HISTO.cantidad,CAT.categoria,PRE.presentacion,"
                + "MED.medicion, MARC.marca,HISTO.fecha_historial,HISTO.hora_historial,USU.usuario,SUC.nombre_sucursal,HISTO.id_usuario,"
                + "USU.id_usuario,PRO.id_medicion,HISTO.id_producto_inventario,PRO.id_marca,PRO.id_presentacion,"
                + "INVEN.id_sucursal,INVEN.id_producto,\n"
                + "          PRO.id_produccto,SUC.id_sucursal,SUC.id_empresa,CAT.id_categoria,MED.id_medicion,PRO.id_categoria,MARC.id_marca,"
                + "PRE.id_presentacion \n"
                + "               FROM sucursal AS SUC,producto_inventario AS INVEN, categoria AS CAT, medicion AS MED , historial_entrada_inventario "
                + "AS HISTO,presentacion AS PRE , \n"
                + "                marca AS MARC,producto AS PRO,usuario AS USU WHERE SUC.id_empresa =" + id_empresa + " AND INVEN.id_sucursal=SUC.id_sucursal AND  "
                + "HISTO.id_producto_inventario = INVEN.id_producto_inventario AND  USU.id_usuario = HISTO.id_usuario AND INVEN.id_producto=PRO.id_produccto AND PRO.id_medicion = MED.id_medicion AND PRO.id_presentacion = PRE.id_presentacion AND PRO.id_marca = MARC.id_marca AND CAT.id_categoria= PRO.id_categoria \n"
                + " AND PRO.nombre_producto LIKE '"+texto+"%'ORDER BY INVEN.fecha_creacion  ");
        rh = consultaResusltados(sql);

        return rh;

    }

}
