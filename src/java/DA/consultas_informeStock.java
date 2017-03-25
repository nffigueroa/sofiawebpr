/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DA;

import Constructores.Constructor_Proveedor;
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
public class consultas_informeStock extends Conexion{
    
       private ResultSet rh = null;
        Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_informeStock()
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
       public ResultSet consultaPosiblesProveedores(int idProductoInventario)
       {
           try {
               CallableStatement st = conex.prepareCall("Call GEN_LlenarCoincidenciasProveedor(?)");
               st.setInt("idProductoInventario", idProductoInventario);
               return st.executeQuery();
           } catch (Exception e) {
               e.printStackTrace();
               return null;
           }
          /// return consultaResusltados(sql);
       }
       
       public ResultSet consultaDatosProveedor(Object id_proveedor)
       {
           sql=("SELECT PRO.id_proveedor,PRO.empresa,PRO.nit_proveedor,CIU.ciudad,"
                   + "PRO.id_ciudad,CIU.id_ciudad,PRO.mail_proveedor,PRO.direccion_proveedor,PRO.telefono_proveedor,PRO.contaco_empresa FROM "
                   + "proveedor AS PRO, ciudad AS CIU WHERE PRO.id_proveedor="+id_proveedor+" "
                   + "AND PRO.id_ciudad=CIU.id_ciudad ");
           return consultaResusltados(sql);
       }
       
       public int consultaIdCategoria(Object id_producto)
       {
           int idCategoria=0;
           sql=("SELECT id_categoria FROM producto  WHERE id_produccto ="+id_producto+"");
           rh = consultaResusltados(sql);
           try {
               while(rh.next())
                   idCategoria=rh.getInt("id_categoria");
           } catch (SQLException ex) {
               Logger.getLogger(consultas_informeStock.class.getName()).log(Level.SEVERE, null, ex);
           }
           return idCategoria;
       }
       
       public int consultaIdProducto(Object id_producto_inventario)
       {
           int id_producto=0;
           sql=("SELECT id_producto FROM producto_inventario  WHERE id_producto_inventario ="+id_producto_inventario+"");
           rh = consultaResusltados(sql);
            try {
               while(rh.next())
                   id_producto=rh.getInt("id_producto");
           } catch (SQLException ex) {
               Logger.getLogger(consultas_informeStock.class.getName()).log(Level.SEVERE, null, ex);
           }
           return id_producto;
       }
       
       public ResultSet llenarTabla_inventario(int id_empresa)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call IVN_llenarTabla_inventarioStock(?)");
            cst.setInt("idEmpresa", id_empresa);
            return cst.executeQuery();
        } catch (Exception e) {
            return null;
        }
//        sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
//                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
//                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
//"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
//                + "SUC.nombre_sucursal,PRO.utilidad,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
//"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
//",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
//"PRE.id_presentacion,SUC.id_sucursal,SUC.id_empresa,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
//                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
//                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
//                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
//                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
//                + "AND PR.id_marca = MAR.id_marca AND SUC.id_empresa="+id_empresa+" AND PRO.id_sucursal=SUC.id_sucursal AND PR.id_presentacion = PRE.id_presentacion AND PRO.id_sucursal = SUC.id_sucursal AND PRO.id_proveedor" +
//"= PROVE.id_proveedor ORDER BY PR.nombre_producto");
    //    return consultaResusltados(sql);
        
    }
       
       
       public ResultSet consultaBuscarProductoInventario(String nombre,String opcionBuscar,int id_sucursal)
    {
        
        switch(opcionBuscar){
                case "Identificacion":
                sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+" = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND PRO.id_producto_inventario LIKE '"+nombre+"%' ORDER BY PRO.id_producto_inventario ");   
                rh = consultaResusltados(sql);
                    break;
                   
                case "Nombre":
                    sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND PR.nombre_producto LIKE '"+nombre+"%' ORDER BY PR.nombre_producto ");   
                    rh = consultaResusltados(sql);
                    break;
                    
                case "Iva":
                sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND PRO.iva_producto_inventario LIKE '"+nombre+"%' ORDER BY PRO.iva_producto_inventario ");                    
                rh = consultaResusltados(sql);
                    break;
                case "Marca":
                sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND MAR.marca LIKE '"+nombre+"%' ORDER BY MAR.marca ");
                rh = consultaResusltados(sql);
                    break;
                case "Categoria":
                sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND CAT.categoria LIKE '"+nombre+"%' ORDER BY CAT.categoria ");
                rh = consultaResusltados(sql);
                    break;
                case "Presentacion":
                    sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND PRE.presentacion LIKE '"+nombre+"%' ORDER BY PRE.presentacion ");
                    rh = consultaResusltados(sql);
                    break;
                case "Expiracion":
                    sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND PRO.expiracion LIKE '"+nombre+"%' ORDER BY PRO.expiracion ");
                    rh = consultaResusltados(sql);
                    break;
                case "Medicion":
                    sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND MED.medicion LIKE '"+nombre+"%' ORDER BY MED.medicion ");
                    rh = consultaResusltados(sql);
                    break;
                case "Cantidad":
                    sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND PRO.cantidad_producto LIKE '"+nombre+"%' ORDER BY PRO.cantidad_producto ");
                    rh = consultaResusltados(sql);
                    break;
                case "Stock":
                    sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND PRO.stock_producto_inventario LIKE '"+nombre+"%' ORDER BY PRO.stock_producto_inventario ");
                    rh = consultaResusltados(sql);
                    break;
                case "Barras":
                    sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND PRO.barras_producto_inventario LIKE '"+nombre+"%' ORDER BY PRO.barras_producto_inventario ");
                    rh = consultaResusltados(sql);
                    break;
                case "Precio1":
                    sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND PRO.precio_producto_inventario LIKE '"+nombre+"%' ORDER BY PRO.precio_producto_inventario ");
                    rh = consultaResusltados(sql);
                    break;
                 case "Precio2":
                    sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND PRO.precio_secundario_producto_inventario LIKE '"+nombre+"%' ORDER BY PRO.precio_secundario_producto_inventario ");
                    rh = consultaResusltados(sql);
                    break;
                case "Sucursal":
                    sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND SUC.nombre_sucursal LIKE '"+nombre+"%' ORDER BY SUC.nombre_sucursal");
                    rh = consultaResusltados(sql);
                    break;
                case "Proveedor":
                    sql=("SELECT DISTINCT PRO.id_producto_inventario,PR.nombre_producto,PRO.iva_producto_inventario,MAR.marca,"
                + "CAT.categoria,PRE.presentacion,PRO.expiracion_producto_inventario,MED.medicion,"
                + "PRO.cantidad_producto_inventario,PRO.stock_producto_inventario," +
"PRO.barras_producto_inventario,PRO.precio_producto_inventario,PRO.precio_secundario_producto_inventario,"
                + "SUC.nombre_sucursal,PROVE.empresa,PRO.id_proveedor,PRO.id_producto,PRO.id_sucursal,PRO.iva_producto_inventario," +
"PRO.id_sucursal,PR.id_produccto,PR.id_categoria,PR.id_medicion" +
",PRO.id_proveedor,PR.id_presentacion,PR.id_marca,MAR.id_marca,CAT.id_categoria,MED.id_medicion," +
"PRE.id_presentacion,SUC.id_sucursal,PROVE.id_proveedor FROM PRODUCTO_INVENTARIO AS PRO, "
                + "PRODUCTO AS PR , CATEGORIA AS CAT , SUCURSAL AS SUC,MARCA AS MAR, PROVEEDOR AS PROVE ,"
                + "MEDICION AS MED,PRESENTACION AS PRE WHERE PRO.id_producto = PR.id_produccto"
                + " AND PRO.id_proveedor = PROVE.id_proveedor AND PR.id_presentacion = PRE.id_presentacion "
                + "AND PR.id_categoria = CAT.id_categoria AND PR.id_medicion = MED.id_medicion "
                + "AND PR.id_marca = MAR.id_marca AND PR.id_presentacion = PRE.id_presentacion AND "+id_sucursal+"  = SUC.id_sucursal AND PRO.id_proveedor" +
"= PROVE.id_proveedor AND PROVE.empresa LIKE '"+nombre+"%' ORDER BY PROVE.empresa");
                    rh = consultaResusltados(sql);
                    break;
        }
        
        return rh;
    }
       
       
    
}
