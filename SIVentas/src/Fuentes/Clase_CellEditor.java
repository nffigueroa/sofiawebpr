
package Fuentes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author JEN
 */
public class Clase_CellEditor extends DefaultCellEditor implements TableCellRenderer{
    private final JComponent component = new JCheckBox();    
    private boolean value = false; 
    
    
    public Clase_CellEditor() {
        super( new JCheckBox() );
    }

 
    @Override
    public Object getCellEditorValue() {
        return ((JCheckBox)component).isSelected();        
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
       
        ( (JCheckBox) component).setBackground( new Color(200,200,0) );
    
        boolean b = ((Boolean) value).booleanValue();
        ( (JCheckBox) component).setSelected( b );
        return ( (JCheckBox) component);     
    }

    
    @Override
    public boolean stopCellEditing() {        
        value = ((Boolean)getCellEditorValue()).booleanValue() ;
        ((JCheckBox)component).setSelected( value );
        return super.stopCellEditing();
    }

  
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         if (value == null)
            return null;         
         return ( (JCheckBox) component );
    }
}
