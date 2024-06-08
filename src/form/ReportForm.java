/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import connection.DatabaseConnect;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelCart;
import model.ModelUser;
import java.lang.String;
import java.sql.CallableStatement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khoa
 */
public class ReportForm extends javax.swing.JPanel {
    private ModelUser User = new ModelUser();
    private DefaultTableModel model;
    private final Connection con;
    CallableStatement callableStatement = null;
    String gamename;
    String year;
    String year2;
    public ReportForm(ModelUser user) {
        con = DatabaseConnect.getInstance().getConnection();
        this.User = user;          
        initComponents(); 
        initTableModel();
        setGameName();      
    }
    public void setGameName(){
        try {

            ResultSet rs = con.createStatement().executeQuery("SELECT gamename from games where userid = '"+ User.getUserID()+ "'");
            while (rs.next()) {
                String gamename = rs.getString(1);
                Game.addItem(gamename);
                // Use a placeholder image if imageIcon is nul
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while fetching data from database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        for(int i = currentYear; i >= currentYear -10; i--) {
            Year.addItem(String.valueOf(i));
            Year1.addItem(String.valueOf(i));
        }
    }
    private void addData(String gamename,double sum,double prevsum) {
        double grow = 0;
        DecimalFormat df = new DecimalFormat("#.#");
        try {
            model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);
            double sumOfSold = 0;
            if(sum == 0){
                JOptionPane.showMessageDialog(null, "Không có dữ liệu");
            }else{
                ResultSet rs = con.createStatement().executeQuery(
            "WITH months AS ( SELECT LEVEL AS month FROM DUAL CONNECT BY LEVEL <= 12 ) SELECT m.month, NVL(p.sum_total, 0)*0.8 AS sum_grandtotal,nvl(sl,0) FROM months m LEFT JOIN ( SELECT EXTRACT(MONTH FROM datecreate) AS month, SUM(total) AS sum_total, count(*) as sl FROM paymentdetail JOIN payment ON paymentdetail.paymentid = payment.paymentid JOIN games ON paymentdetail.gameid = games.gameid WHERE EXTRACT(YEAR FROM datecreate) = '" + year + "' and gamename = '" + gamename + "' GROUP BY EXTRACT(MONTH FROM datecreate) ) p ON m.month = p.month ORDER BY m.month");
            
            if(prevsum == 0 && sum!=0){
                grow = 100;
            }else if (sum==0) {
                grow = 0;
            } else{
                grow = (sum - prevsum)/prevsum*100;
            }
            int row = 1;
            while (rs.next()) {
                String month = rs.getString(1);
                double income = rs.getDouble(2);
                int sold = rs.getInt(3);
                double percent  = 0;
                sumOfSold += sold;
                if(sold != 0){
                    percent  = income/sum*100;                  
                }
                
                String formattedPercent = df.format(percent);
                String incomeStr = Double.toString(income) + '$';
                String soldStr = formattedPercent + '%';

                // Use a placeholder image if imageIcon is nul
                model.addRow(new Object[]{month,sold,incomeStr,soldStr});
                }                 
            }    
            SOV.setText(String.valueOf(sum) + '$');
            SOS.setText(String.valueOf(sumOfSold)+ "Bản");
            GROW.setText(df.format(grow)+ "%");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while fetching data from database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void initTableModel() {
    model = new DefaultTableModel(
        new Object[][] {},
        new String[] {"GameName", "Số bản được bán", "Doanh thu", "Chiếm tỉ lệ"}
    );
  }
    private void addData2(String gamename,double sum,double prevsum) {
        DecimalFormat df = new DecimalFormat("#.#");
        try {
            
            model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);
            double sumOfSold = 0;
            double grow = 0;
            if(prevsum == 0 && sum!=0){
                grow = 100;
            }else if (sum==0 && prevsum != 0) {
                grow = -100;
            }else if(sum==0){
                grow = 0;
            }
            else{
                grow = (sum - prevsum)/prevsum*100;
            }
            ResultSet rs = con.createStatement().executeQuery(
            "SELECT gamename,nvl(SUM(total),0)*0.8,nvl(count(*),0) FROM games JOIN paymentdetail ON games.gameid=paymentdetail.gameid  JOIN payment ON paymentdetail.paymentid =   payment.paymentid WHERE games.userid = '"+User.getUserID()+"' AND EXTRACT(YEAR FROM datecreate) = '"+year2+"' GROUP BY gamename");
            int row = 1;
            if(sum == 0){
                JOptionPane.showMessageDialog(null, "Không có dữ liệu");
            }else{
                while (rs.next()) {
                String gamename2 = rs.getString(1);
                double income = rs.getDouble(2);
                int sold = rs.getInt(3);
                double percent  = 0;
                sumOfSold += sold;
                if(sold != 0){
                    percent  = income/sum*100;                  
                }
                
                String formattedPercent = df.format(percent);
                String incomeStr = Double.toString(income) + '$';
                String soldStr = formattedPercent + '%';
                // Use a placeholder image if imageIcon is nul
                model.addRow(new Object[]{gamename2,sold,incomeStr,soldStr});
            }            
            }        
            SOV.setText(String.valueOf(sum) + '$');
            SOS.setText(String.valueOf(sumOfSold)+ "Bản");
            GROW.setText(df.format(grow)+ "%");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while fetching data from database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void addData3(String gamename,double sum) {
        try {
            
            model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);
            double sumOfSold = 0;
            ResultSet rs = con.createStatement().executeQuery(
            "SELECT gamename,nvl(SUM(total),0)*0.8,nvl(count(*),0) FROM games JOIN paymentdetail ON games.gameid=paymentdetail.gameid  JOIN payment ON paymentdetail.paymentid =   payment.paymentid WHERE games.userid = '"+User.getUserID()+"' GROUP BY gamename");
            int row = 1;
            if(sum == 0){
                JOptionPane.showMessageDialog(null, "Không có dữ liệu");
            }else{
                while (rs.next()) {
                    System.out.println("form.ReportForm.addData3()");
                String gamename2 = rs.getString(1);
                double income = rs.getDouble(2);
                int sold = rs.getInt(3);
                double percent  = 0;
                sumOfSold += sold;
                if(sold != 0){
                    percent  = income/sum*100;                  
                }
                DecimalFormat df = new DecimalFormat("#.#");
                String formattedPercent = df.format(percent);
                String incomeStr = Double.toString(income) + '$';
                String soldStr = formattedPercent + '%';

                // Use a placeholder image if imageIcon is nul
                model.addRow(new Object[]{gamename2,sold,incomeStr,soldStr});
            }            
            }        
            SOV.setText(String.valueOf(sum) + '$');
            SOS.setText(String.valueOf(sumOfSold)+ "Bản");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while fetching data from database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSButtonPane1 = new rojeru_san.complementos.RSButtonPane();
        rSButtonHover1 = new rojeru_san.complementos.RSButtonHover();
        jCTextField1 = new app.bolivia.swing.JCTextField();
        jDialog1 = new javax.swing.JDialog();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        Show = new javax.swing.JButton();
        Game = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Year = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Year1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        Show1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        Show2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new Swing.Table();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        SOV = new javax.swing.JLabel();
        SOS = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        GROW = new javax.swing.JLabel();

        javax.swing.GroupLayout rSButtonPane1Layout = new javax.swing.GroupLayout(rSButtonPane1);
        rSButtonPane1.setLayout(rSButtonPane1Layout);
        rSButtonPane1Layout.setHorizontalGroup(
            rSButtonPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        rSButtonPane1Layout.setVerticalGroup(
            rSButtonPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        rSButtonHover1.setText("rSButtonHover1");

        jCTextField1.setText("jCTextField1");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jToggleButton1.setText("jToggleButton1");

        setBackground(java.awt.Color.white);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Show.setBackground(new java.awt.Color(0, 153, 255));
        Show.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Show.setForeground(new java.awt.Color(255, 255, 255));
        Show.setText("Xem");
        Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowActionPerformed(evt);
            }
        });

        Game.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Tên game");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Năm");

        Year.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setText("Tùy chỉnh báo cáo:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel11.setText("Báo cáo tổng kết theo năm:");

        Year1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Năm");

        Show1.setBackground(new java.awt.Color(0, 153, 255));
        Show1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Show1.setForeground(new java.awt.Color(255, 255, 255));
        Show1.setText("Xem");
        Show1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Show1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel13.setText("Báo cáo tổng:");

        Show2.setBackground(new java.awt.Color(0, 153, 255));
        Show2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Show2.setForeground(new java.awt.Color(255, 255, 255));
        Show2.setText("Xem");
        Show2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Show2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Show1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Show, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Game, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(Show2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Year1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Game, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Show)
                .addGap(44, 44, 44)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Year1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Show2)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(Show1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(java.awt.Color.white);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tháng", "Số bản được bán", "Doanh thu", "Tỉ lệ"
            }
        ));
        table1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        table1.setRowHeight(30);
        table1.setSelectionBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(table1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("Báo cáo");

        jPanel3.setBackground(java.awt.Color.white);

        jPanel4.setBackground(java.awt.Color.white);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Tổng doanh thu:");

        SOV.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        SOV.setForeground(new java.awt.Color(0, 255, 51));
        SOV.setText("0$");

        SOS.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        SOS.setForeground(new java.awt.Color(255, 153, 51));
        SOS.setText("0 Bản");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Doanh số bán hàng:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SOV, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SOS, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SOS, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SOV)
                    .addComponent(jLabel2)))
        );

        jPanel5.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Tăng trưởng so với kì trước:");

        GROW.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        GROW.setForeground(new java.awt.Color(0, 102, 255));
        GROW.setText("0%");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GROW, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(GROW))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowActionPerformed
        jLabel3.setVisible(true);
        model.setColumnIdentifiers(new Object[]{"Tháng", "Số bản bán ra", "Doanh thu", "Tỉ lệ"});
        gamename = (String) Game.getSelectedItem();
        year  = (String) Year.getSelectedItem();
        int prev = Integer.parseInt(year)-1;
        try {
            String sql = "{ ? = call sum_game_income_month(?,?) }";
            callableStatement = con.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.DOUBLE);
            callableStatement.setString(2, gamename);
            callableStatement.setString(3, year);
            callableStatement.execute();
            double result = callableStatement.getDouble(1);
            callableStatement = con.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.DOUBLE);
            callableStatement.setString(2, gamename);
            callableStatement.setInt(3, prev);
            callableStatement.execute();
            double result1 = callableStatement.getDouble(1);
            System.out.println(result1);
            addData( gamename,result,result1);
        } catch (SQLException ex) {
            Logger.getLogger(ReportForm.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }//GEN-LAST:event_ShowActionPerformed

    private void Show1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Show1ActionPerformed
        jLabel3.setVisible(false);
        GROW.setText("");
        model.setColumnIdentifiers(new Object[]{"Tên game", "Số bản bán ra", "Doanh thu", "Tỉ lệ"});
        try {
            String sql = "{ ? = call sum_game_total_income(?) }";
            callableStatement = con.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.DOUBLE);
            callableStatement.setString(2, User.getUserID());
            callableStatement.execute();
            double result = callableStatement.getDouble(1);
            System.out.println(result);
            addData3(gamename,result);
        } catch (SQLException ex) {
            Logger.getLogger(ReportForm.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_Show1ActionPerformed

    private void Show2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Show2ActionPerformed
        jLabel3.setVisible(true);
        year2  = (String) Year1.getSelectedItem();
        int prev = Integer.parseInt(year2)-1;
        model.setColumnIdentifiers(new Object[]{"Tên game", "Số bản bán ra", "Doanh thu", "Tỉ lệ"});
        try {
            String sql = "{ ? = call sum_game_income_year(?,?) }";
            callableStatement = con.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.DOUBLE);
            callableStatement.setString(2, User.getUserID());
            callableStatement.setString(3, year2);
            callableStatement.execute();
            double result = callableStatement.getDouble(1);
            callableStatement = con.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.DOUBLE);
            callableStatement.setString(2, User.getUserID());
            callableStatement.setInt(3, prev);
            callableStatement.execute();
            double result1 = callableStatement.getDouble(1);
            addData2(gamename,result,result1);
        } catch (SQLException ex) {
            Logger.getLogger(ReportForm.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_Show2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GROW;
    private javax.swing.JComboBox<String> Game;
    private javax.swing.JLabel SOS;
    private javax.swing.JLabel SOV;
    private javax.swing.JButton Show;
    private javax.swing.JButton Show1;
    private javax.swing.JButton Show2;
    private javax.swing.JComboBox<String> Year;
    private javax.swing.JComboBox<String> Year1;
    private app.bolivia.swing.JCTextField jCTextField1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover1;
    private rojeru_san.complementos.RSButtonPane rSButtonPane1;
    private Swing.Table table1;
    // End of variables declaration//GEN-END:variables
}
