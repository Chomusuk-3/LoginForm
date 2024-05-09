
package main;

import java.awt.Color;
import model.ModelUser;


public class MainSystem extends javax.swing.JFrame {

    private final ModelUser user;
    public MainSystem(ModelUser user) {
        this.user = user;
        initComponents();
        getContentPane().setBackground(new Color(255,255,255));
        lbUser.setText(user.getUserName());
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUser = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbUser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUser.setText("User Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(301, 301, 301)
                .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(301, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(367, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(ModelUser user) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainSystem(user).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbUser;
    // End of variables declaration//GEN-END:variables
}
