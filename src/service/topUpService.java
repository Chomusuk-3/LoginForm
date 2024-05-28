/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import connection.DatabaseConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelUser;
import service.topUpService;

/**
 *
 * @author khoa
 */
public class topUpService {
    private final Connection con;

    public topUpService() {
        con = DatabaseConnect.getInstance().getConnection();
    }
    
    public double getCode(String codeNumber,String email,ModelUser user) throws SQLException {   
        String query = "SELECT value,expireDate,status FROM codegame WHERE codenumber = ?";
        PreparedStatement val = con.prepareStatement(query);
        val.setString(1, codeNumber);
        ResultSet rs = val.executeQuery();
        
        if (rs.next()) {
            // Retrieve the value from the result set
            double value = Long.parseLong(rs.getString("value"));
            String status = rs.getString("status");
            if(status.equals("used")){
               JOptionPane.showMessageDialog(null, "Thẻ đã được sử dụng");
                
            }else{
                JOptionPane.showMessageDialog(null, "Nạp thẻ thành công" + codeNumber + "\nGiá trị: " + value + "VNĐ");
                topUp(value, email,user);
                updateCodeGame(codeNumber);
            }
            
            return value;
        } else {
            JOptionPane.showMessageDialog(null, "Mã thẻ không hợp lệ!");
        }
        return 0L;
    }
    public void topUp(double value,String email,ModelUser user)throws SQLException{
        String query = "Update users set balance = balance + '"+ value +"' where email= '"+ email + "'";
        PreparedStatement val = con.prepareStatement(query);
        user.balanceEdit(value);
        ResultSet rs = val.executeQuery();
    }
    public void updateCodeGame(String codeNumber)throws SQLException{
        String query = "Update codeGame set status = 'used' where codeNumber= '"+ codeNumber + "'";
        PreparedStatement val = con.prepareStatement(query);
        ResultSet rs = val.executeQuery();
    }
    public static String generateRandomCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            code.append(chars.charAt(index));
        }
        return code.toString();
    }

    // Hàm kiểm tra xem mã đã tồn tại trong cơ sở dữ liệu chưa
    public boolean isCodeExists(String code) {
        boolean exists = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT COUNT(*) AS count FROM codegame WHERE code = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, code);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }

    // Hàm tạo mã thẻ cào không trùng lặp
    public  String generateUniqueCode(int length) {
        String code;
        do {
            code = generateRandomCode(length);
        } while (isCodeExists(code));
        return code;
    }

    // Phương thức main để kiểm tra hàm tạo mã thẻ cào không trùng lặp
    public void CodesGenerator(int number,double value) throws SQLException{
        for (int i = 0; i < number; i++) {
            String code = generateUniqueCode(12);
            try (PreparedStatement p = con.prepareStatement("insert into codegame (codeid,codenumber,value,status) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            UUID uuid = UUID.randomUUID();
            p.setString(1, uuid.toString());
            p.setString(2, code);
            p.setDouble(3, value);
            p.setString(4, "Active");
            p.execute();
        }
        }
    }
}
