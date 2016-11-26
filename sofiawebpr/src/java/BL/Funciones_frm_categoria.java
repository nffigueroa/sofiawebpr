/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

//import Fuentes.Grillas;
import DA.Consultas_Generales;
import BL.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_categoria extends DA.consultas_categoria{
     Date now = new Date(System.currentTimeMillis());
    private java.sql.ResultSetMetaData meta;
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Funciones_Generales general = new Funciones_Generales();
    private ResultSet rs = null;
    
    public ArrayList llenarTabaCategoria()
   {
          try {
            rs = llenarTabla_Categoria();
            return general.resultSetToArrayList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
//       Grillas gr = new Grillas();
//       return gr.CargarGrd(llenarTabla_Categoria(), nombreColumnas, ancho);
   }
   
    public boolean InsertarCategoria(String categoria,int id_usuario)
    {
        categoria = categoria.toUpperCase();
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("InsertarCategoria", id_usuario, date.format(now),hora.format(now), "Se registra categoria "+categoria+"");
        consultaRegistrarCategoria(categoria,id_usuario);
        return false;
    }
    
}
    

