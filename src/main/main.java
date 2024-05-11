
package main;

import Component.Message;
import Component.PanelCover;
import Component.PanelForgetPassword;
import Component.PanelLoading;
import Component.PanelLoginAndRegister;
import Component.PanelVerifyCode;
import Component.PanelVerifyCode2;
import connection.DatabaseConnect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;
import model.ModelLogin;
import model.ModelMessage;
import model.ModelUser;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import service.ServiceMail;
import service.ServiceUser;


public class main extends javax.swing.JFrame {

    private MigLayout layout;
    private PanelCover cover;
    private PanelLoading loading;
    private PanelVerifyCode verifyCode;
    private PanelVerifyCode2 verifyCode2;
    private PanelForgetPassword forget;
    private boolean isLogin;
    private PanelLoginAndRegister LoginAndRegister;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize =60;
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private ServiceUser service;
    public main() {
        initComponents();
        init();
    }
    
    private void init(){
        service = new ServiceUser();
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        loading = new PanelLoading();
        verifyCode = new PanelVerifyCode();
        verifyCode2 = new PanelVerifyCode2();
        forget = new PanelForgetPassword();
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        };
        ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        };
        ActionListener eventForget = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forget();
            }
        };
        LoginAndRegister = new PanelLoginAndRegister(eventRegister, eventLogin, eventForget);
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                if(isLogin){
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if(fraction >= 0.5f){
                        cover.registerRight(fractionCover * 100);
                    }else{
                        cover.loginRight(fractionLogin * 100);
                    }
                }else{
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if(fraction <= 0.5f){
                        cover.registerLeft(fraction * 100);
                    }else{
                        cover.loginLeft((1f-fraction) * 100);
                    }
                }
                if(fraction >= 0.5f){
                    LoginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos "+ fractionCover +"al 0 n 100%");
                layout.setComponentConstraints(LoginAndRegister, "width " + loginSize + "%, pos "+ fractionLogin +"al 0 n 100%");
                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin; 
            } 
        };
        Animator animator = new Animator(800,target); 
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0); // for smooth
        bg.setLayout(layout);
        bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        bg.setLayer(verifyCode, JLayeredPane.POPUP_LAYER);
        bg.setLayer(forget, JLayeredPane.POPUP_LAYER);
        bg.setLayer(verifyCode2, JLayeredPane.POPUP_LAYER);
        bg.add(loading, "pos 0 0 100% 100%");
        bg.add(verifyCode, "pos 0 0 100% 100%");
        bg.add(verifyCode2, "pos 0 0 100% 100%");
        bg.add(forget, "pos 0 0 100% 100%");
        bg.add(cover,"Width "+ coverSize + "%, pos 0al 0 n 100%");
        bg.add(LoginAndRegister,"Width "+loginSize+"%, pos 1al 0 n 100%");//lal as 100%
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!animator.isRunning()){
                    animator.start();
                }
            }
        });
        verifyCode2.addEventButtonOK2(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                ModelUser user =service.setUser(forget.getEmailCode());
                System.out.println(verifyCode2.getInputCode2()+ user.getVerifyCode());
                if(service.verifyCodeWithUser(user.getUserName(), verifyCode2.getInputCode2())){
                        service.doneVerify(user.getUserName());
                        showMessage(Message.MessageType.SUCCESS, "Change password success");
                        verifyCode2.setVisible(false);
                    }else{
                        showMessage(Message.MessageType.ERROR, "Verify code incorrect");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    showMessage(Message.MessageType.ERROR, "Error");
                }
            }
        });
        forget.addEventButtonOKForget(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ModelUser user = new ModelUser();
                    if(service.checkEmail(forget.getEmailCode())){
                        service.updateForget(forget.getEmailCode(), forget.getNewPass());
                        user = service.setUser(forget.getEmailCode());
                        forget.setVisible(false);
                        sendMain2(user);
                        System.out.println(user.getVerifyCode());
                    }
                    else{
                        showMessage(Message.MessageType.ERROR, "Email don't exist");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    showMessage(Message.MessageType.ERROR, "Error");
                }
            }
        });
        verifyCode.addEventButtonOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    ModelUser user = LoginAndRegister.getUser();
                     System.out.println(user.getUserID());
                    if(service.verifyCodeWithUser(user.getUserName(), verifyCode.getInputCode())){
                        service.doneVerify(user.getUserName());
                        service.deleteUsers(user.getEmail());
                        showMessage(Message.MessageType.SUCCESS, "Register success");
                        verifyCode.setVisible(false);
                    }else{
                        showMessage(Message.MessageType.ERROR, "Verify code incorrect");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    showMessage(Message.MessageType.ERROR, "Error");
                }
            }
        });
    }
    
    
    private void register(){
        ModelUser user = LoginAndRegister.getUser();
        try {
            if(service.checkDuplicateUser(user.getUserName())){
                showMessage(Message.MessageType.ERROR, "UserName already exit");
            }else if(service.checkDuplicateEmail(user.getEmail())){
                showMessage(Message.MessageType.ERROR, "Email already exit");
            }else{
                service.insertUser(user);
                sendMain(user);
            }
        } catch (SQLException e) {
            
            showMessage(Message.MessageType.ERROR, "Error Register");
        }
        
    }
    
    
    
    private void login(){
        ModelLogin data = LoginAndRegister.getDataLogin();
        try {
            ModelUser user = service.login(data);
            if(user != null){
                this.dispose();
                MainSystem.main(user);
            }else{
                 showMessage(Message.MessageType.ERROR, "Email or Password incorrect");
            }
        } catch (SQLException e) {
            showMessage(Message.MessageType.ERROR, "Error Login");
        }
    }
    
    private void forget(){
        forget.setVisible(true);
    }
    
    private void sendMain(ModelUser user){
        new Thread(new Runnable() {
            @Override
            public void run() {
               loading.setVisible(true);
               ModelMessage ms = new ServiceMail().sendMain(user.getEmail(), user.getVerifyCode());
               if(ms.isSuccess()){
                   loading.setVisible(false);
                   verifyCode.setVisible(true);
               }else{
                   loading.setVisible(false);
                   showMessage(Message.MessageType.ERROR, ms.getMessage());
               }
            }
        }).start();
    }
    
    private void sendMain2(ModelUser user){
        new Thread(new Runnable() {
            @Override
            public void run() {
               loading.setVisible(true);
               ModelMessage ms = new ServiceMail().sendMain(user.getEmail(), user.getVerifyCode());
               if(ms.isSuccess()){
                   loading.setVisible(false);
                   verifyCode2.setVisible(true);
               }else{
                   loading.setVisible(false);
                   showMessage(Message.MessageType.ERROR, ms.getMessage());
               }
            }
        }).start();
    }
    
    private void showMessage(Message.MessageType messageType, String message){
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void begin() {
              if(!ms.isShow()){
                  bg.add(ms,"pos 0.5al -30", 0);//insert to bg fist index 0z
                  ms.setVisible(true);
                  bg.repaint();
              }
            }

            @Override
            public void timingEvent(float fraction) {
               float f;
               if(ms.isShow()){
                   f = 40 * (1f - fraction);
               }else{
                   f = 40 * fraction;
               }
               layout.setComponentConstraints(ms, "pos 0.5al "+ (int)(f - 30));
               bg.repaint();
               bg.revalidate();
            }

            @Override
            public void end() {
                if(ms.isShow()){
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                }else{
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                    animator.start();
                }catch(Exception e){
                    System.err.println(e);
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

  
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try {
            DatabaseConnect.getInstance().connectToDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
