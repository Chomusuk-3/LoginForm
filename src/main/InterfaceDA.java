
package main;


import form.AddGame;
import Component.Edit;
import Component.Menu;
import connection.DatabaseConnect;
import event.EventMenuSelected;
import form.AddCodeGame;
import form.AddCoupon;
import form.CartForm;
import form.UserForm;
import form.Form_3;
import form.Form_4;
import form.Purchased;
import form.GameStore;
import form.LibraryForm;
import form.topup_form;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import model.ModelCart;
import model.ModelUser;
import service.ServiceUser;


public class InterfaceDA extends javax.swing.JFrame {
    private ModelCart Cart;
    private ModelUser user;
    private Edit edit;
    private ServiceUser service;
    
    public InterfaceDA(ModelUser user,ModelCart Cart)  {
        System.out.println(user.getRole());
        this.user = user;
        this.Cart = Cart;
        initComponents();
        getContentPane().setBackground(new Color(0,0,0,0));
        menu1.menuSetuser(user);
        menu1.init();
        menu1.initMoving(InterfaceDA.this);     
        menu1.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if(index == 0){
                    setForm(new GameStore(Cart));
                }else if(index == 1){
                        setForm(new UserForm(user));
                }else if(index == 2){
                    setForm(new LibraryForm(user));
                }else if(index ==3){
                    setForm(new CartForm(Cart,user));
                }else if(index == 4){
                    try {
                        setForm(new Purchased(user));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                else if(index == 5){
                    setForm(new topup_form(user));
                }else if(index == 6 && (user.getRole().equals("Admin") || user.getRole().equals("Developer"))){
                    setForm(new AddGame(user));
                }else if(index == 7 && user.getRole().equals("Admin")){
                    setForm(new AddCodeGame());
                }else if(index == 8 && user.getRole().equals("Admin")){
                    setForm(new AddCoupon());
                }else if(index == 13){
                    dispose();
                    new main().setVisible(true);
                }
            }
        });
        setForm(new GameStore(Cart)); // start system with form store
        
    }
    
   
    
    private void setForm(JComponent com){
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new Swing.PanelBorder();
        menu1 = new Component.Menu();
        mainPanel = new javax.swing.JPanel();
        header1 = new Component.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(242, 242, 242));

        mainPanel.setBackground(new java.awt.Color(242, 242, 242));
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());
        mainPanel.add(header1, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(ModelUser user,ModelCart Cart) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceDA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceDA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceDA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceDA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            DatabaseConnect.getInstance().connectToDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceDA(user,Cart).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Component.Header header1;
    private javax.swing.JPanel mainPanel;
    private Component.Menu menu1;
    private Swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
