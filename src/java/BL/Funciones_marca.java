/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

//import Fuentes.Grillas;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.table.TableModel;
import BL.*;
import DA.Consultas_Generales;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Nestor1
 */
public class Funciones_marca extends DA.consultas_Forma_Pago{
    
   Date now = new Date(System.currentTimeMillis());
   private java.sql.ResultSetMetaData meta;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
   private ResultSet rs= null;
   Funciones_Generales general = new Funciones_Generales();
   
   public ArrayList llenarTablaMarca()
   {
    try {
           rs = llenarTabla_Marca();
           return general.resultSetToArrayList(rs);
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
       //Grillas gr = new Grillas();
      // return gr.CargarGrd(llenarTabla_Marca(), nombreColumnas, ancho);
   }
   
    public ArrayList llenarTablaMarcaRelacion(int idSucursal)
   {
    try {
           rs = llenarTabla_MarcaRelacionSucursal(idSucursal);
           return general.resultSetToArrayList(rs);
       } catch (SQLException ex) {
           Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
       //Grillas gr = new Grillas();
      // return gr.CargarGrd(llenarTabla_Marca(), nombreColumnas, ancho);
   }
   
    public boolean insertarMarca(String marca, int id_usuario)
    {
        consultaRegistrarMarca(marca,id_usuario);
        return true;
    }
    
    public ArrayList InsertarRelacionMarcaSucursal(JSONObject marca,int idSucursal)
    {
        //categoria = categoria.toUpperCase();
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("InsertarRelacionMarcaSucursal", idSucursal, date.format(now),hora.format(now), "Se registra Relacion de marca "+marca+"");
         try {
            rs = consultaRegistrarRelacionSucursalMarca(marca,idSucursal);
            return general.resultSetToArrayList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
