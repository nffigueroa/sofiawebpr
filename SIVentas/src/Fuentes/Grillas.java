
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fuentes;

import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Grillas {

  private javax.swing.JTable grd;
  private java.sql.ResultSetMetaData metaDatos;
  private int numCol;
public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;  //
}
  public Grillas() {
  }

  public DefaultTableModel CargarGrd(ResultSet rs, String nombreColumnas[],int ancho[] ) {
    try {
      grd = new javax.swing.JTable();

      // Definición del modelo de la tabla
      DefaultTableModel model = (DefaultTableModel) this.grd.getModel();
     
      
      // Traer la estructura del resultset
      metaDatos = rs.getMetaData();
      // Traer la cantidad de columnas del resultset
      numCol = metaDatos.getColumnCount();

      String[] NombresColumnasRS = new String[numCol];
      // Se obtiene cada una de las etiquetas para cada columna
      for (int i = 0; i < nombreColumnas.length; i++) {

          NombresColumnasRS[i] = nombreColumnas[i];
      }

      // Asignar al modelo los nombres de columnas
      model.setColumnIdentifiers(NombresColumnasRS);
      
      
      while (rs.next()) {
        Object[] objects = new Object[numCol];
        // tanks to umit ozkan for the bug fix!
        for (int i = 0; i < numCol; i++) {
          objects[i] = rs.getObject(i + 1);
        }
        model.addRow(objects);
        
      }
      return model;
    } catch (SQLException ex) {
        ex.printStackTrace();
//      Logger.getLogger(frm.class.getName()).log(Level.SEVERE, null, ex);
      return null;
    }

  }
  
 
}
