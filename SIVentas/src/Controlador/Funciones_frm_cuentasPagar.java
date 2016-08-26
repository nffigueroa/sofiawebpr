/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Fuentes.Grillas;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class Funciones_frm_cuentasPagar extends Modelo.consultas_cuentasPagar{
    
    public TableModel funcionLlenarProductosEnStock(int id_empresa,String[] nombreColumnas,int[] ancho)
    {
        Grillas gr = new Grillas();
        return gr.CargarGrd(consultaProductosEnStok(id_empresa), nombreColumnas, ancho);
    }
    
}
