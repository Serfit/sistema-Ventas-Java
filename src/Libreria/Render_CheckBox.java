/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Libreria;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author sa_fi
 */
public class Render_CheckBox extends JCheckBox implements TableCellRenderer{
    private JComponent component = new JCheckBox();
    public Render_CheckBox(){
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ((JCheckBox) component).setBackground(new Color(0,200,0));
        //Obtiene el valor boolean y coloca valor en el checkBox
        boolean b =((Boolean) value).booleanValue();
        ((JCheckBox) component).setSelected(b);
        return ((JCheckBox) component);
    }
    
}
