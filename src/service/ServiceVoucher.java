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
import java.sql.*;
/**
 *
 * @author khoa
 */
public class ServiceVoucher {
    private final Connection con;
    CallableStatement callableStatement = null;
    public ServiceVoucher() {
        con = DatabaseConnect.getInstance().getConnection();
    }
    
    public Long getCode(String CouponNumber) throws SQLException {   
        String query = "SELECT value,status FROM coupon WHERE CouponNumber = ?";
        PreparedStatement val = con.prepareStatement(query);
        val.setString(1, CouponNumber);
        ResultSet rs = val.executeQuery();
        if (rs.next()) {
            String status = rs.getString("status");
            // Retrieve the value from the result set
            long value = Long.parseLong(rs.getString("value"));
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
    public void updateCoupon(String couponNumber)throws SQLException{
        String sql = "{CALL increment_coupon_usage(?)}";
        callableStatement = con.prepareCall(sql);
        callableStatement.setString(1, couponNumber); // ID của coupon cần cập nhật
        callableStatement.execute();
        System.out.println("Coupon usage incremented successfully.");
    }
    public boolean couponCheck(String couponNumber) throws SQLException{
        String query = "SELECT value,status FROM coupon WHERE CouponNumber = ?";
        PreparedStatement val = con.prepareStatement(query);
        val.setString(1, couponNumber);
        ResultSet rs = val.executeQuery();
        if (rs.next()) {
            String status = rs.getString("status");
            if(status.equals("Used")){
                return true;
            }    
            return false;
        } 
        return false;
    }
}
