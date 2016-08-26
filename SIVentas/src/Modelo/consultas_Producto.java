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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nestor1
 */
public class consultas_Producto extends Conexion{
    
    private ResultSet rh= null;
     Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_Producto()
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
    public ResultSet llenarTabla_Producto(int sucursal)
    {
        
       sql=("select P.id_produccto,P.nombre_producto,P.id_categoria,P.id_medicion,P.id_presentacion,"
                + "P.id_marca,C.id_categoria,C.categoria,ME.id_medicion,ME.medicion, PRE.id_presentacion,"
                + "PRE.presentacion,M.id_marca,M.marca,P.id_sucursal FROM producto AS P, categoria AS C, medicion AS ME , "
                + "presentacion AS PRE, marca AS M WHERE P.id_sucursal ="+sucursal+" AND P.id_categoria = C.id_categoria AND "
                + "P.id_medicion = ME.id_medicion AND P.id_presentacion = PRE.id_presentacion AND P.id_marca = M.id_marca ORDER BY nombre_producto");
        return consultaResusltados(sql);
        
    }
    public boolean eliminar_Producto(int ide)
    {
        sql =("DELETE FROM producto WHERE id_produccto="+ide+"");
        if(insertarResultados(sql))
        {
            return true;}
        else{
            return false;}   
    }
    public ResultSet consultaLLenarComboCategoria()
    {
        sql=("SELECT categoria FROM CATEGORIA");
        return consultaResusltados(sql);
    }
    public ResultSet consultaLLenarComboMedicion()
    {
       sql=("SELECT medicion FROM MEDICION");
        return consultaResusltados(sql);
    }
    public ResultSet consultaLLenarComboMarca()
    {
        sql=("SELECT marca FROM MARCA");
        return consultaResusltados(sql);
    }
    public ResultSet consultaLLenarComboPresentacion()
    {
        sql=("SELECT presentacion FROM PRESENTACION");
        return consultaResusltados(sql);
    }
    
    //CONSULTAR LOS IDS DE LOS COMBOBOX DEL FORMULARIO DE PRODUCTO 
    public Object consultaIDComboCategoria(Object categoria)
    {
        Object id_categoria = null;
        sql=("SELECT id_categoria FROM CATEGORIA WHERE categoria='"+categoria+"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            id_categoria = rh.getInt("id_categoria");
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_categoria;
    }
    public Object consultaIDComboPresentacion(Object presentacion)
    {
        Object id_presentacion = null;
        sql=("SELECT id_presentacion FROM PRESENTACION WHERE presentacion='"+presentacion+"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            id_presentacion = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_presentacion;
    }
    public Object consultaIDComboMedicion(Object medicion)
    {
        Object id_medicion = null;
        sql=("SELECT id_medicion FROM MEDICION WHERE medicion='"+medicion+"'");
        consultaResusltados(sql);
        try {
            while(rh.next())
            id_medicion = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id_medicion;
    }
    public Object consultaIDComboMarca(Object marca)
    {
        Object id_marca = null;
       sql=("SELECT id_marca FROM MARCA WHERE marca='"+marca+"'");
       rh = consultaResusltados(sql);
        try {
            while(rh.next())
            id_marca = rh.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(consultas_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_marca;
    }
    
    public boolean registrarProducto(String nombre,String fecha_creacion,Object usuarioCreacion, Object categoria , Object marca, Object medicion , Object presentacion,Object id_sucursal)
    {
        sql=("INSERT INTO PRODUCTO (nombre_producto,fecha_creacion,id_usuario_creacion,"
                + "id_categoria,id_medicion,id_presentacion,id_marca,id_sucursal) VALUES ('"+nombre+"','"+fecha_creacion+"',"
                + usuarioCreacion+","+categoria+","+medicion+","+presentacion+","+marca+","+id_sucursal+"   )");
        return insertarResultados(sql);
    }
    
    public boolean consultaActualizarProducto(Object id_producto,String nombre, Object categoria , Object marca, Object medicion , Object presentacion)
    {
        
        try{
        sql=("UPDATE PRODUCTO SET nombre_producto='"+nombre+"',id_categoria="+categoria+",id_medicion="+medicion+","
                + "id_presentacion="+presentacion+", id_marca="+marca+" WHERE id_produccto="+id_producto+"");
        
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return insertarResultados(sql);
    }
    
    public ResultSet consultaBuscarProducto(int sucursal,String nombre,String opcionBuscar)
    {
        
        switch(opcionBuscar){
                case "Categoria":
                sql=("select P.id_produccto,P.nombre_producto,P.id_categoria,P.id_medicion,P.id_presentacion,"
                + "P.id_marca,C.id_categoria,C.categoria,ME.id_medicion,ME.medicion, pre.id_presentacion,"
                + "pre.presentacion,M.id_marca,M.marca FROM PRODUCTO AS P, CATEGORIA AS C, MEDICION AS ME , "
                + "PRESENTACION AS PRE, MARCA AS M WHERE P.id_categoria = C.id_categoria AND "
                + "P.id_medicion = ME.id_medicion AND P.id_sucursal="+sucursal+" AND P.id_presentacion = PRE.id_presentacion AND "
                + "P.id_marca = M.id_marca AND C.categoria LIKE '"+nombre+"%' ORDER BY C.categoria ");   
                rh = consultaResusltados(sql);
                    break;
                   
                case "Marca":
                    sql=("select P.id_produccto,P.nombre_producto,P.id_categoria,P.id_medicion,P.id_presentacion,"
                + "P.id_marca,C.id_categoria,C.categoria,ME.id_medicion,ME.medicion, pre.id_presentacion,"
                + "pre.presentacion,M.id_marca,M.marca FROM PRODUCTO AS P, CATEGORIA AS C, MEDICION AS ME , "
                + "PRESENTACION AS PRE, MARCA AS M WHERE P.id_categoria = C.id_categoria AND "
                + "P.id_medicion = ME.id_medicion AND P.id_sucursal="+sucursal+" AND P.id_presentacion = PRE.id_presentacion AND "
                + "P.id_marca = M.id_marca AND M.marca LIKE '"+nombre+"%' ORDER BY M.marca ");   
                    rh = consultaResusltados(sql);
                    
                    break;
                    
                case "Nombre":
                sql=("select P.id_produccto,P.nombre_producto,P.id_categoria,P.id_medicion,P.id_presentacion,"
                + "P.id_marca,C.id_categoria,C.categoria,ME.id_medicion,ME.medicion, pre.id_presentacion,"
                + "pre.presentacion,M.id_marca,M.marca FROM PRODUCTO AS P, CATEGORIA AS C, MEDICION AS ME , "
                + "PRESENTACION AS PRE, MARCA AS M WHERE P.id_categoria = C.id_categoria AND "
                + "P.id_medicion = ME.id_medicion AND P.id_sucursal="+sucursal+" AND P.id_presentacion = PRE.id_presentacion AND "
                + "P.id_marca = M.id_marca AND P.nombre_producto LIKE '"+nombre+"%' ORDER BY P.nombre_producto ");                    
                rh = consultaResusltados(sql);
                    break;
                case "Medicion":
                    sql=("select P.id_produccto,P.nombre_producto,P.id_categoria,P.id_medicion,P.id_presentacion,"
                + "P.id_marca,C.id_categoria,C.categoria,ME.id_medicion,ME.medicion, pre.id_presentacion,"
                + "pre.presentacion,M.id_marca,M.marca FROM PRODUCTO AS P, CATEGORIA AS C, MEDICION AS ME , "
                + "PRESENTACION AS PRE, MARCA AS M WHERE P.id_categoria = C.id_categoria AND "
                + "P.id_medicion = ME.id_medicion AND P.id_sucursal="+sucursal+" AND P.id_presentacion = PRE.id_presentacion AND "
                + "P.id_marca = M.id_marca AND ME.medicion LIKE '"+nombre+"%' ORDER BY ME.medicion ");   
                    rh = consultaResusltados(sql);
                    break;
                case "Presentacion":
                    sql=("select P.id_produccto,P.nombre_producto,P.id_categoria,P.id_medicion,P.id_presentacion,"
                + "P.id_marca,C.id_categoria,C.categoria,ME.id_medicion,ME.medicion, pre.id_presentacion,"
                + "pre.presentacion,M.id_marca,M.marca FROM PRODUCTO AS P, CATEGORIA AS C, MEDICION AS ME , "
                + "PRESENTACION AS PRE, MARCA AS M WHERE P.id_categoria = C.id_categoria AND "
                + "P.id_medicion = ME.id_medicion AND P.id_sucursal="+sucursal+" AND P.id_presentacion = PRE.id_presentacion AND "
                + "P.id_marca = M.id_marca AND PRE.presentacion LIKE '"+nombre+"%' ORDER BY PRE.presentacion ");   
                    rh = consultaResusltados(sql);
                    break;
        }
        
        return rh;
    }
    
    
}
