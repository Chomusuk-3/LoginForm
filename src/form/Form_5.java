
package form;

import Swing.ScrollBar;
import connection.DatabaseConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import model.ModelUser;


public class Form_5 extends javax.swing.JPanel {
    private final Connection con;
    public Form_5(ModelUser user) throws SQLException {
        con =  DatabaseConnect.getInstance().getConnection();
        initComponents();
        spTable.setVerticalScrollBar(new ScrollBar());
        ResultSet rs = con.createStatement().executeQuery(String.format("SELECT PAYMENTDETAIL.PAYMENTID,GAMENAME,TOTAL,DATECREATE FROM PAYMENTDETAIL INNER JOIN PAYMENT ON PAYMENTDETAIL.PAYMENTID = PAYMENT.PAYMENTID INNER JOIN GAMES ON PAYMENTDETAIL.GAMEID = GAMES.GAMEID WHERE USERID = '%s'", user.getUserID()));
        while(rs.next()){
            System.out.println("ngu");
            String id = rs.getString(1);
            String game = rs.getString(2);
            String total = String.valueOf(rs.getDouble(3));
            String date = String.valueOf(rs.getDate(4));
            
            String tbData[] = {id, game, total + "00đ", date};
            DefaultTableModel tblModel =(DefaultTableModel)table.getModel();
            tblModel.addRow(tbData);
        }
        rs.close();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new Swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new Swing.Table();

        setBackground(new java.awt.Color(242, 242, 242));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Purchase History");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Payment ID", "Game", "Total", "Date Create"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private Swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private Swing.Table table;
    // End of variables declaration//GEN-END:variables
}
