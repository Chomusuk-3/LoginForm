/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import javax.swing.*;
import Component.GameDetail;
import connection.DatabaseConnect;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.ModelGame;
import service.gameService;

/**
 *
 * @author khoa
 */
public class GameStore extends javax.swing.JPanel {
    private final Connection con;
    private DefaultTableModel model;
    private gameService service = new gameService();
    /**
     * Creates new form GameStore
     */
    public GameStore() {
        con = DatabaseConnect.getInstance().getConnection();
        initComponents(); // Gọi initComponents() trước để khởi tạo các thành phần giao diện
        initTableModel(); // Sau đó khởi tạo model cho bảng
        addData(); // Cuối cùng là thêm dữ liệu vào bảng
        addTableClickListener();
    }
    private void addTableClickListener() {
    storeTable1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int selectedRow = storeTable1.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    String gameId = (String) storeTable1.getValueAt(selectedRow, 0);
                   
                    ModelGame game = service.getGameDetail(gameId);
                    // Xử lý hành động khi nhấn vào hàng
                    GameDetail gameDetailFrame = new GameDetail(game);
                    gameDetailFrame.setVisible(true);                   
                } catch (SQLException ex) {
                    Logger.getLogger(GameStore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
}
    private void addData() {
    try {
        model = (DefaultTableModel) storeTable1.getModel();
        model.setRowCount(0);
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM GAMES");
        while (rs.next()) {
            String gameID = rs.getString(1);
            String gameName = rs.getString(2);
            String description = rs.getString(3);
            Double price = rs.getDouble(10);
            System.out.println(description);
            
            model.addRow(new Object[]{gameID, gameName, description, price});
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Xử lý ngoại lệ theo ý của bạn, ví dụ: hiển thị một thông báo lỗi
        JOptionPane.showMessageDialog(this, "Error occurred while fetching data from database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    private void initTableModel() {
        model = new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "No", "Name", "Description", "price","xem"
            }
        );
        storeTable1.setModel(model);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        storeTable1 = new Swing.StoreTable();
        jLabel1 = new javax.swing.JLabel();

        jScrollPane1.setBorder(null);

        storeTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Name", "Description", "price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(storeTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Game Store");

        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jDesktopPane1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Swing.StoreTable storeTable1;
    // End of variables declaration//GEN-END:variables
}
