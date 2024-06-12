
package form;

import connection.DatabaseConnect;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class AddCoupon extends javax.swing.JPanel {
    CallableStatement callableStatement = null;
     private Connection con;
    public AddCoupon() {
        initComponents();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCouponCode = new app.bolivia.swing.JCTextField();
        txtValue = new app.bolivia.swing.JCTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTotalUse = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        btbAdd = new Swing.ButtonOutLine();

        setBackground(new java.awt.Color(242, 242, 242));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 127, 127));
        jLabel2.setText("Add Coupon");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 255));
        jLabel3.setText("Coupon Code");

        txtCouponCode.setBackground(new java.awt.Color(244, 244, 244));
        txtCouponCode.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 204, 255)));
        txtCouponCode.setForeground(new java.awt.Color(102, 102, 102));
        txtCouponCode.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtCouponCode.setPlaceholder("VD : WINTERSALE2024");

        txtValue.setBackground(new java.awt.Color(244, 244, 244));
        txtValue.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 204, 255)));
        txtValue.setForeground(new java.awt.Color(102, 102, 102));
        txtValue.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtValue.setPlaceholder("VD : 20000");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 255));
        jLabel4.setText("Value");

        txtTotalUse.setBackground(new java.awt.Color(244, 244, 244));
        txtTotalUse.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 204, 255)));
        txtTotalUse.setForeground(new java.awt.Color(102, 102, 102));
        txtTotalUse.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTotalUse.setPlaceholder("VD : 100");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 255));
        jLabel5.setText("Total use");

        btbAdd.setBackground(new java.awt.Color(0, 204, 255));
        btbAdd.setForeground(new java.awt.Color(0, 0, 0));
        btbAdd.setText("Add");
        btbAdd.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btbAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCouponCode, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                            .addComponent(txtValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalUse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCouponCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalUse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btbAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbAddActionPerformed
        // TODO add your handling code here:
        String couponcode = txtCouponCode.getText().trim();
        Double value = Double.valueOf(txtValue.getText());
        int totaluse = Integer.parseInt(txtTotalUse.getText());
        con = DatabaseConnect.getInstance().getConnection();
         try {
//             PreparedStatement p = con.prepareStatement("INSERT INTO COUPON(CouponID, CouponNumber, Value, CreateDate, Total, Status) VALUES (?,?,?,CURRENT_DATE,?,?)");
             UUID uuid = UUID.randomUUID();
            String sql = "{CALL coupon_crt(?,?,?,?)}";
            String paymentID = UUID.randomUUID().toString();
            callableStatement = con.prepareCall(sql);              
            callableStatement.setString(1, uuid.toString());
            callableStatement.setString(2, couponcode);
            callableStatement.setDouble(3, value);
            callableStatement.setDouble(4, totaluse);
            callableStatement.execute();
            JOptionPane.showMessageDialog(null, "Add coupon success");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
    }//GEN-LAST:event_btbAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.ButtonOutLine btbAdd;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private app.bolivia.swing.JCTextField txtCouponCode;
    private app.bolivia.swing.JCTextField txtTotalUse;
    private app.bolivia.swing.JCTextField txtValue;
    // End of variables declaration//GEN-END:variables
}
