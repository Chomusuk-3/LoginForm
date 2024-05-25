
package form;

import connection.DatabaseConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class AddCoupon extends javax.swing.JPanel {

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
        txtCodenumber = new app.bolivia.swing.JCTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCouponCode2 = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        txtValue2 = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTotalUse2 = new app.bolivia.swing.JCTextField();
        btbAdd2 = new Swing.ButtonOutLine();

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

        txtCodenumber.setBackground(new java.awt.Color(244, 244, 244));
        txtCodenumber.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 204, 255)));
        txtCodenumber.setForeground(new java.awt.Color(102, 102, 102));
        txtCodenumber.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(127, 127, 127));
        jLabel6.setText("Add Numerous Coupon");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 204, 255));
        jLabel7.setText("Quantity Code");

        txtCouponCode2.setBackground(new java.awt.Color(244, 244, 244));
        txtCouponCode2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 204, 255)));
        txtCouponCode2.setForeground(new java.awt.Color(102, 102, 102));
        txtCouponCode2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtCouponCode2.setPlaceholder("VD : 100");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 204, 255));
        jLabel8.setText("Value");

        txtValue2.setBackground(new java.awt.Color(244, 244, 244));
        txtValue2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 204, 255)));
        txtValue2.setForeground(new java.awt.Color(102, 102, 102));
        txtValue2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtValue2.setPlaceholder("VD : 20000");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 255));
        jLabel9.setText("Total use");

        txtTotalUse2.setBackground(new java.awt.Color(244, 244, 244));
        txtTotalUse2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 204, 255)));
        txtTotalUse2.setForeground(new java.awt.Color(102, 102, 102));
        txtTotalUse2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTotalUse2.setPlaceholder("VD : 100");

        btbAdd2.setBackground(new java.awt.Color(0, 204, 255));
        btbAdd2.setForeground(new java.awt.Color(0, 0, 0));
        btbAdd2.setText("Add");
        btbAdd2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btbAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbAdd2ActionPerformed(evt);
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
                        .addComponent(jLabel2)
                        .addGap(186, 186, 186)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotalUse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCouponCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(btbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(52, 52, 52)
                        .addComponent(txtCodenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValue2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalUse2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btbAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53))
                            .addComponent(txtCouponCode2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                        .addGap(18, 18, 18)
                        .addComponent(btbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCodenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCouponCode2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValue2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalUse2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btbAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btbAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbAddActionPerformed
        // TODO add your handling code here:
        String couponcode = txtCouponCode.getText().trim();
        Double value = Double.valueOf(txtValue.getText());
        int totaluse = Integer.parseInt(txtTotalUse.getText());
        con = DatabaseConnect.getInstance().getConnection();
         try {
             PreparedStatement p = con.prepareStatement("INSERT INTO COUPON(CouponID, CouponNumber, Valuee, CreateDate, Total, Status) VALUES (?,?,?,CURRENT_DATE,?,?)");
             UUID uuid = UUID.randomUUID();
             p.setString(1, uuid.toString());
             p.setString(2, couponcode);
             p.setDouble(3, value);
             p.setInt(4, totaluse);
             p.setString(5, "Active");
             p.execute();
             JOptionPane.showMessageDialog(null, "Add coupon success");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
    }//GEN-LAST:event_btbAddActionPerformed

    private void btbAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbAdd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btbAdd2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.ButtonOutLine btbAdd;
    private Swing.ButtonOutLine btbAdd2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private app.bolivia.swing.JCTextField txtCodenumber;
    private app.bolivia.swing.JCTextField txtCouponCode;
    private app.bolivia.swing.JCTextField txtCouponCode2;
    private app.bolivia.swing.JCTextField txtTotalUse;
    private app.bolivia.swing.JCTextField txtTotalUse2;
    private app.bolivia.swing.JCTextField txtValue;
    private app.bolivia.swing.JCTextField txtValue2;
    // End of variables declaration//GEN-END:variables
}
