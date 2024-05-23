package Swing;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
public class StoreTable extends JTable {

    public StoreTable(){
        setShowHorizontalLines(true);
        setRowHeight(40);
        setGridColor(new Color(230,230,230));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                StoreTableHeader header = new StoreTableHeader(value + "");
                if(column == 4){
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
            
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if(column != 4){
                    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    com.setBackground(Color.WHITE);
                    setBorder(noFocusBorder);
                    if(isSelected){
                        com.setForeground(new Color(13,113,182));
                    }else{
                        com.setForeground(new Color(102,102,102));
                    }
                    return com;
                }
                return new JLabel("Testing");
            }
        });
    }
    
    
}
