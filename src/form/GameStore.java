/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import javax.swing.JScrollPane;
import javax.swing.*;
import java.sql.Blob;
import Component.GameDetail;
import connection.DatabaseConnect;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.ModelGame;
import service.gameService;
import model.ModelCart;
/**
 *
 * @author khoa
 */
public class GameStore extends javax.swing.JPanel {
    private final Connection con;
    private DefaultTableModel model;
    private ModelCart Cart = new ModelCart();
    private gameService service = new gameService();
    /**
     * Creates new form GameStore
     */
    public GameStore(ModelCart Cart) {
        try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
        e.printStackTrace();
        }
        this.Cart = Cart;
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
                    String gameId = null;
                    if (selectedRow != -1) {
                        // Chuyển đổi hàng được chọn trong JTable sang hàng trong TableModel
                        int modelRow = storeTable1.convertRowIndexToModel(selectedRow);

                        // Lấy giá trị gameId từ mô hình bảng
                        gameId = (String) model.getValueAt(modelRow, 5);
                        System.out.println("Selected Game ID: " + gameId);
                    } else {
                        System.out.println("No row selected");
                    }
                    System.out.println("hehe");
//                    String gameId = (String) storeTable1.getValueAt(selectedRow, 5);
                    System.out.println("haha");
                    ModelGame game = service.getGameDetail(gameId);
                    // Xử lý hành động khi nhấn vào hàng
                    GameDetail gameDetailFrame = new GameDetail(game,Cart);
                    gameDetailFrame.setVisible(true);                   
                } catch (SQLException ex) {
                    Logger.getLogger(GameStore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
}
        private void addData() {
            storeTable1.setBorder(null);
            storeTable1.setShowHorizontalLines(false);
            storeTable1.setShowVerticalLines(false);
        try {
            model = (DefaultTableModel) storeTable1.getModel();
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM GAMES");
            int row = 1;
            while (rs.next()) {
                String gameID = rs.getString(1);
                String gameName = rs.getString(2);
                String description = rs.getString(4);
                Double price = rs.getDouble(11);
                Blob imageBlob = rs.getBlob(12);
                ImageIcon imageIcon = null;

                // Handle null imageBlob
                if (imageBlob != null) {
                    try (InputStream inputStream = imageBlob.getBinaryStream()) {
                        BufferedImage bufferedImage = ImageIO.read(inputStream);
                        if (bufferedImage != null) {
                            imageIcon = resizeImage(bufferedImage, 150, 80); // Resize to fit the table cell
                        } else {
                            System.err.println("BufferedImage is null for game " + gameID);
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading image for game " + gameID + ": " + e.getMessage());
                    }
                }

                // Use a placeholder image if imageIcon is nul
                model.addRow(new Object[]{row++, imageIcon != null ? imageIcon : "No image", gameName, description, price,gameID});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while fetching data from database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private ImageIcon resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resultingImage);
    }
    private void initTableModel() {
    model = new DefaultTableModel(
        new Object[][] {},
        new String[] {"No", "", "Name", "Description", "Price", "GameID"}
    ) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 1) {
                return ImageIcon.class;
            }
            return String.class;
        }
    };
    storeTable1.setModel(model);
    storeTable1.getColumnModel().getColumn(0).setPreferredWidth(2);
    storeTable1.getColumnModel().getColumn(4).setPreferredWidth(2);
    // Set the custom renderer for the image column
    storeTable1.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                setIcon((ImageIcon) value);
                setText("");
            } else {
                setIcon(null);
            }
            return this;
        }
    });
    // Hide the GameID column
    TableColumnModel columnModel = storeTable1.getColumnModel();
    TableColumn gameIDColumn = columnModel.getColumn(5);
    columnModel.removeColumn(gameIDColumn);
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

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);

        storeTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Image", "Name", "Description", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        storeTable1.setRowHeight(100);
        storeTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(storeTable1);
        if (storeTable1.getColumnModel().getColumnCount() > 0) {
            storeTable1.getColumnModel().getColumn(0).setMinWidth(0);
            storeTable1.getColumnModel().getColumn(0).setMaxWidth(5);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Game Store");

        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Swing.StoreTable storeTable1;
    // End of variables declaration//GEN-END:variables
}
