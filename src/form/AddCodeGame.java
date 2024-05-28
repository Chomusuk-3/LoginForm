
package form;

import Component.Message;
import connection.DatabaseConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class AddCodeGame extends javax.swing.JPanel {


    private Connection con;
    public AddCodeGame() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodenumber = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        txtValue = new app.bolivia.swing.JCTextField();
        btbAdd = new Swing.ButtonOutLine();
        txtCodenumber1 = new app.bolivia.swing.JCTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtQuantity = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        txtValue2 = new app.bolivia.swing.JCTextField();
        btbAddNum = new Swing.ButtonOutLine();

        setBackground(new java.awt.Color(242, 242, 242));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Add numerous code");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 255));
        jLabel3.setText("Code Number");

        txtCodenumber.setBackground(new java.awt.Color(244, 244, 244));
        txtCodenumber.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 204, 255)));
        txtCodenumber.setForeground(new java.awt.Color(102, 102, 102));
        txtCodenumber.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 255));
        jLabel5.setText("Value");

        txtValue.setBackground(new java.awt.Color(244, 244, 244));
        txtValue.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 204, 255)));
        txtValue.setForeground(new java.awt.Color(102, 102, 102));
        txtValue.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtValue.setPlaceholder("VD : 20000");

        btbAdd.setBackground(new java.awt.Color(0, 204, 255));
        btbAdd.setForeground(new java.awt.Color(0, 0, 0));
        btbAdd.setText("Add");
        btbAdd.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btbAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbAddActionPerformed(evt);
            }
        });

        txtCodenumber1.setBackground(new java.awt.Color(244, 244, 244));
        txtCodenumber1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 204, 255)));
        txtCodenumber1.setForeground(new java.awt.Color(102, 102, 102));
        txtCodenumber1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtCodenumber1.setPlaceholder("VD : 2738391298");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 127, 127));
        jLabel2.setText("Add Code");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 204, 255));
        jLabel6.setText("Quantity");

        txtQuantity.setBackground(new java.awt.Color(244, 244, 244));
        txtQuantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 204, 255)));
        txtQuantity.setForeground(new java.awt.Color(102, 102, 102));
        txtQuantity.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtQuantity.setPlaceholder("VD : 5");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 204, 255));
        jLabel8.setText("Value");

        txtValue2.setBackground(new java.awt.Color(244, 244, 244));
        txtValue2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 204, 255)));
        txtValue2.setForeground(new java.awt.Color(102, 102, 102));
        txtValue2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtValue2.setPlaceholder("VD : 20000");

        btbAddNum.setBackground(new java.awt.Color(0, 204, 255));
        btbAddNum.setForeground(new java.awt.Color(0, 0, 0));
        btbAddNum.setText("Add");
        btbAddNum.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btbAddNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbAddNumActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodenumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(98, 98, 98)
                .addComponent(txtCodenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtValue2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(btbAddNum, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValue2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btbAddNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodenumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(93, 110, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    private void btbAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbAddActionPerformed
        // TODO add your handling code here:
        String codenum = txtCodenumber1.getText().trim();
        Double value = Double.valueOf(txtValue.getText());
        con = DatabaseConnect.getInstance().getConnection();
        try {
            PreparedStatement p = con.prepareStatement("INSERT INTO CODEGAME(CODEID, CODENUMBER, CreateDate, Value, Status) VALUES(?,?,CURRENT_DATE,?,?)");
            UUID uuid = UUID.randomUUID();
            p.setString(1, uuid.toString());
            p.setString(2, codenum);
            p.setDouble(3, value);
            p.setString(4, "Active");
            p.execute();
            JOptionPane.showMessageDialog(null, "Add code success");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        txtCodenumber1.setText("");
        txtValue.setText("");
    }//GEN-LAST:event_btbAddActionPerformed

    private String generateCodeNum() throws SQLException {
        DecimalFormat df = new DecimalFormat("00000000");
        Random ran = new Random();
        String codenum = df.format(ran.nextLong(100000000));  //  Random from 0 to 99999999
        while (checkDuplicateCodeNumber(codenum)) {
            codenum = df.format(ran.nextLong(100000000));
        }
        return codenum;
    }
    
    private boolean checkDuplicateCodeNumber(String codenum) throws SQLException {
        boolean duplicate = false;
        con = DatabaseConnect.getInstance().getConnection();
        ResultSet rs = con.createStatement().executeQuery(String.format("select CODENUMBER from CODEGAME where CODENUMBER='%s' AND STATUS = 'Active' FETCH FIRST 1 ROW ONLY", codenum));
        if(rs.next()){
            duplicate = true;
        }
        rs.close();
        return duplicate;
    }

    
    private void btbAddNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbAddNumActionPerformed
        // TODO add your handling code here:
        String codenum;
        int cnt = Integer.parseInt(txtQuantity.getText());
        Double value = Double.valueOf(txtValue2.getText());
        con = DatabaseConnect.getInstance().getConnection();
        while(cnt != 0){
            try {
                PreparedStatement p = con.prepareStatement("INSERT INTO CODEGAME(CODEID, CODENUMBER, CreateDate, Value, Status) VALUES(?,?,CURRENT_DATE,?,?)");
                codenum = generateCodeNum();
                UUID uuid = UUID.randomUUID();
                p.setString(1, uuid.toString());
                p.setString(2, codenum);
                p.setDouble(3, value);
                p.setString(4, "Active");
                p.execute();
                cnt = cnt - 1;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(null, "Add code success");
        txtQuantity.setText("");
        txtValue2.setText("");
    }//GEN-LAST:event_btbAddNumActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.ButtonOutLine btbAdd;
    private Swing.ButtonOutLine btbAddNum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private app.bolivia.swing.JCTextField txtCodenumber;
    private app.bolivia.swing.JCTextField txtCodenumber1;
    private app.bolivia.swing.JCTextField txtQuantity;
    private app.bolivia.swing.JCTextField txtValue;
    private app.bolivia.swing.JCTextField txtValue2;
    // End of variables declaration//GEN-END:variables
}
