/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import Component.GameDetail;
import Component.LibraryDetail;
import connection.DatabaseConnect;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.ModelCart;
import model.ModelGame;
import model.ModelUser;
import service.gameService;

/**
 *
 * @author khoa
 */
public class LibraryForm extends javax.swing.JPanel {
    private final Connection con;
    private DefaultTableModel model;
    private gameService service = new gameService();
    public LibraryForm(ModelUser user) {
        con = DatabaseConnect.getInstance().getConnection();
        initComponents(); // Gọi initComponents() trước để khởi tạo các thành phần giao diện
        initTableModel(); // Sau đó khởi tạo model cho bảng
        addData(); // Cuối cùng là thêm dữ liệu vào bảng
        addTableClickListener();
    }
    private void addData() {
            table1.setBorder(null);
            table1.setShowHorizontalLines(false);
            table1.setShowVerticalLines(false);
        try {
            model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT library.gameid,gamename,developername,description,SDescription,rating,releaseDay,agelimit,downloaded,gamesize,price,image FROM library join games on library.gameid = games.gameid");
            int row = 1;
            while (rs.next()) {
                String gameID = rs.getString(1);
                String gameName = rs.getString(2);
                String developer = rs.getString("developername");
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
                model.addRow(new Object[]{row++, imageIcon != null ? imageIcon : "No image", gameName,developer,gameID});
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
        new String[] {"No", "", "Game", "Developer" , "Gameid"}
    ) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 1) {
                return ImageIcon.class;
            }
            return String.class;
        }
    };
    table1.setModel(model);
    table1.getColumnModel().getColumn(0).setPreferredWidth(2);
    table1.getColumnModel().getColumn(4).setPreferredWidth(2);
    // Set the custom renderer for the image column
    table1.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
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
    TableColumnModel columnModel = table1.getColumnModel();
    TableColumn gameIDColumn = columnModel.getColumn(4);
    columnModel.removeColumn(gameIDColumn);
    }
    private void addTableClickListener() {
    table1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    String gameId = null;
                    if (selectedRow != -1) {
                        // Chuyển đổi hàng được chọn trong JTable sang hàng trong TableModel
                        int modelRow = table1.convertRowIndexToModel(selectedRow);

                        // Lấy giá trị gameId từ mô hình bảng
                        gameId = (String) model.getValueAt(modelRow, 4);
                        System.out.println("Selected Game ID: " + gameId);
                    } else {
                        System.out.println("No row selected");
                    }
                    System.out.println("hehe");
//                    String gameId = (String) storeTable1.getValueAt(selectedRow, 5);
                    System.out.println("haha");
                    ModelGame game = service.getGameDetail(gameId);
                    // Xử lý hành động khi nhấn vào hàng
                    LibraryDetail gameDetailFrame = new LibraryDetail(game);
                    gameDetailFrame.setVisible(true);                   
                } catch (SQLException ex) {
                    Logger.getLogger(GameStore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new Swing.Table();

        jLabel1.setText("Library");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "", "Game", "Developer"
            }
        ));
        table1.setRowHeight(100);
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Swing.Table table1;
    // End of variables declaration//GEN-END:variables
}
