/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacontable;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Ricky
 */
public class ColorTabla extends DefaultTableCellRenderer{
    @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        if(table.isColumnSelected(2)){
            Color c = new Color(72,165,234);
            setBackground(c);
            System.out.print(table.getCellSelectionEnabled());
            if(table.isColumnSelected(2)){
                setBackground(Color.WHITE);
            }
        }else{
        }
        return super.getTableCellRendererComponent(table, value, hasFocus, hasFocus, row, row);
        
    }
    
}
