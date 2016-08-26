/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fuentes;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nestor1
 */
public class Modelo_Tabla extends DefaultTableModel{
    
    public  Modelo_Tabla (Object [][]data, Object[] columNames)
    {
        super(data,columNames);
    }    
}
