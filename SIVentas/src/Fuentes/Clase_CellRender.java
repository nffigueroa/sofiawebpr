package Fuentes;


import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JEN
 */
public class Clase_CellRender extends JCheckBox implements TableCellRenderer{
    private  JComponent component = new JCheckBox();

    
    public Clase_CellRender() {
        setOpaque(true);
    }

    @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    
      ( (JCheckBox) component).setBackground( new Color(255,255,255) );
      
      boolean b = ((Boolean) value);
      ( (JCheckBox) component).setSelected( b );
      
      return ( (JCheckBox) component);
  }
}
