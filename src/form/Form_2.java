
package form;

import Component.Edit;
import Component.Message;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.AncestorListener;
import main.main;
import model.ModelUser;
import service.ServiceUser;



public class Form_2 extends javax.swing.JPanel {

    Edit edit = new Edit();
    private ServiceUser service;
    public Form_2(ModelUser user)  {
        service = new ServiceUser();
        initComponents();
        PanelCus.setBackground(new Color(10,209,214));
        txtUsername.setText("Hi, " + user.getUserName()); 
        txtBalance.setText("balance : " + user.getBalance() + "đ");
        lbFirstname.setText(user.getFirstname());
        lbLastname.setText(user.getLastname());
        lbDob.setText(user.getDob().toString());
        lbPhone.setText(user.getPhone());
        edit.addEventButtonSave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(edit.getDob());
                    service.updateEdit(edit.getFirstName(), edit.getLastName(), edit.getDob(), edit.getPhone(), user.getUserName());
                    edit.setVisible(false);
                    lbFirstname.setText(edit.getFirstName());
                    lbLastname.setText(edit.getLastName());
                    lbDob.setText(edit.getDob());
                    lbPhone.setText(edit.getPhone());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            
            }
        });
    }
  
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelProfile = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbFirstname = new javax.swing.JLabel();
        lbLastname = new javax.swing.JLabel();
        lbPhone = new javax.swing.JLabel();
        lbDob = new javax.swing.JLabel();
        PanelCus = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JLabel();
        txtBalance = new javax.swing.JLabel();
        btbEdit = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 102));
        setForeground(new java.awt.Color(102, 102, 102));

        PanelProfile.setBackground(new java.awt.Color(255, 255, 255));
        PanelProfile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        PanelProfile.setForeground(new java.awt.Color(245, 245, 245));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("First Name :");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Date of Birth :");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Last Name :");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Phone :");

        lbFirstname.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lbLastname.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lbPhone.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lbDob.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout PanelProfileLayout = new javax.swing.GroupLayout(PanelProfile);
        PanelProfile.setLayout(PanelProfileLayout);
        PanelProfileLayout.setHorizontalGroup(
            PanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProfileLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(PanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(PanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        PanelProfileLayout.setVerticalGroup(
            PanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFirstname, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(PanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(PanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDob, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(PanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(74, 74, 74))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-user-90 (1).png"))); // NOI18N

        txtUsername.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtBalance.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout PanelCusLayout = new javax.swing.GroupLayout(PanelCus);
        PanelCus.setLayout(PanelCusLayout);
        PanelCusLayout.setHorizontalGroup(
            PanelCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCusLayout.createSequentialGroup()
                .addGroup(PanelCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCusLayout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel1))
                    .addGroup(PanelCusLayout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addGroup(PanelCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelCusLayout.setVerticalGroup(
            PanelCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBalance, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addContainerGap())
        );

        btbEdit.setBackground(new java.awt.Color(255, 255, 255));
        btbEdit.setForeground(new java.awt.Color(0, 0, 0));
        btbEdit.setText("Edit profile");
        btbEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(btbEdit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(PanelProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btbEdit)
                .addGap(41, 41, 41))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btbEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbEditActionPerformed
        // TODO add your handling code here:
        edit.setVisible(true);
    }//GEN-LAST:event_btbEditActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCus;
    private javax.swing.JPanel PanelProfile;
    private javax.swing.JButton btbEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lbDob;
    private javax.swing.JLabel lbFirstname;
    private javax.swing.JLabel lbLastname;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel txtBalance;
    private javax.swing.JLabel txtUsername;
    // End of variables declaration//GEN-END:variables
}
