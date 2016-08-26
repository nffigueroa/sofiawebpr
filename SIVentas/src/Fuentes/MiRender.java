/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fuentes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JOptionPane;

/**
 *
 * @author Nestor1
 */
public class MiRender extends DefaultTableCellRenderer{
     float cantidad =0,stock=0;
    
     @Override
    public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected,boolean hasFocus,int row, int column)
    {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(isSelected)
                {
                    cell.setBackground(Color.cyan);
                    cell.setForeground(Color.black);
                }
        if(column == 8){
            cantidad = Float.parseFloat(value.toString());
                    
        }
        else{
            if(column==9)
            {
                stock = Float.parseFloat(value.toString());
                    if(cantidad==stock)
                    {
                        cell.setBackground(Color.ORANGE);
                        cell.setForeground(Color.BLACK);
                        cantidad=0;
                        stock=0;
                    }
                    else
                    {
                        if(cantidad<stock)
                        {
                        cell.setBackground(Color.RED);
                        cell.setForeground(Color.WHITE);
                        cantidad=0;
                        stock=0;
                        }
                    }
            }
            else
            {
                cell.setBackground(Color.WHITE);
                cell.setForeground(Color.BLACK);
                if(isSelected)
                {
                    cell.setBackground(Color.cyan);
                    cell.setForeground(Color.black);
                }
            }
        }
        return cell;
    }
    
}
