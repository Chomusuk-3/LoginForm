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
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelUser;
import service.topUpService;

/**
 *
 * @author khoa
 */
public class ServiceVoucher {
    private final Connection con;

    public ServiceVoucher() {
        con = DatabaseConnect.getInstance().getConnection();
    }
    
    public Long getCode(String CouponNumber) throws SQLException {   
        String query = "SELECT valuee,status FROM codegame WHERE codenumber = ?";
        PreparedStatement val = con.prepareStatement(query);
        val.setString(1, CouponNumber);
        ResultSet rs = val.executeQuery();
        
        if (rs.next()) {
            // Retrieve the value from the result set
            long value = Long.parseLong(rs.getString("valuee"));
//            String status = rs.getString("status");
//            Date expireDate = rs.getDate("expireDate");
//            JOptionPane.showMessageDialog(null, "Nạp thẻ thành công" + CouponNumber + "\nGiá trị: " + value + "VNĐ");
//            topUp(value, email,user);
//            updateCodeGame(codeNumber);
            return value;
        } else {
            JOptionPane.showMessageDialog(null, "Mã thẻ không hợp lệ!");
        }
        return 0L;
    }
//    public void topUp(Long value,String email,ModelUser user)throws SQLException{
//        String query = "Update users set balance = balance + '"+ value +"' where email= '"+ email + "'";
//        PreparedStatement val = con.prepareStatement(query);
//        user.balanceEdit(value);
//        ResultSet rs = val.executeQuery();
//    }
//    public void updateCodeGame(String codeNumber)throws SQLException{
//        String query = "Update codeGame set status = 'used' where codeNumber= '"+ codeNumber + "'";
//        PreparedStatement val = con.prepareStatement(query);
//        ResultSet rs = val.executeQuery();
//    }
}
