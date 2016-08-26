/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Fuentes.Grillas;
import Modelo.Consultas_Generales;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_medicion extends Modelo.consultas_medicion{
     Date now = new Date(System.currentTimeMillis());
    private java.sql.ResultSetMetaData meta;
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    
    public TableModel llenarMedicion(int id_sucursal,String[] nombreColumnas, int[] ancho)
   {
       Grillas gr = new Grillas();
       return gr.CargarGrd(llenarTabla_Medicion(id_sucursal), nombreColumnas, ancho);
   }
   
    public boolean insertarMedicion(int id_sucursal,String medicion,int id_usuario)
    {
        Consultas_Generales con_generales = new Consultas_Generales();
        con_generales.registrarHistorial("insertarMedicion", id_usuario, date.format(now),hora.format(now), "Se registra categoria "+medicion+"");
        consultaRegistrarMedicion(id_sucursal,medicion,id_usuario);
        return false;
    }
    
}
    

