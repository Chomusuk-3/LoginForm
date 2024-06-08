
package Swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class Table extends JTable {

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean selected, boolean bln1, int i, int i1) {
                if(i1 != 4){
                    Component com = super.getTableCellRendererComponent(table, o, selected, bln1, i, i1);
                    com.setBackground(Color.WHITE);
                    setBorder(noFocusBorder);
                    if(selected){
                        com.setForeground(new Color(15, 89, 140));
                    }else{
                        com.setForeground(new Color(102, 102, 102));
                    }
                    return com;
                }
                return new JLabel("Testting");
            }
            
        });
    }
    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
    // Lấy TableCellRenderer mặc định của JTable
    TableCellRenderer renderer = super.getCellRenderer(row, column);
    // Nếu là cột đầu tiên, thì tăng kích thước phông chữ
    if (column == 0 && renderer instanceof JLabel) {
        // Ép kiểu renderer thành JLabel để có thể gọi phương thức getFont()
        JLabel labelRenderer = (JLabel) renderer;
        // Tạo một Font mới từ Font hiện tại và tăng kích thước phông chữ
        Font originalFont = labelRenderer.getFont();
        Font newFont = originalFont.deriveFont(originalFont.getSize() + 2f); // Tăng 2 đơn vị
        // Thiết lập Font mới cho renderer
        labelRenderer.setFont(newFont);
    }
    return renderer;
}
    
}
