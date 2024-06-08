
package service;

import connection.DatabaseConnect;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelGame;

public class ServicePayment {
    private final Connection con;
    CallableStatement callableStatement = null;
    public ServicePayment(){
        con = DatabaseConnect.getInstance().getConnection();
    }
    public void MakePayment(String userID,double grantTotal,double Discounted, double subTotal,ArrayList<ModelGame> games){
        try {
            String sql = "{CALL payment_create(?,?,?)}";
            String paymentID = UUID.randomUUID().toString();
            callableStatement = con.prepareCall(sql);
            callableStatement.setString(1, paymentID); // ID của coupon cần cập nhật
            callableStatement.setString(2, userID);
            callableStatement.setDouble(3, Discounted);
            callableStatement.execute();
            for (ModelGame game : games) {
                MakePaymentDetail(game,paymentID);
            }
            sql = "{CALL user_edit_balance(?,?)}";
            callableStatement = con.prepareCall(sql);
            callableStatement.setString(1, paymentID); // ID của coupon cần cập nhật
            callableStatement.setString(2, userID);
            callableStatement.execute();
//        try {
//            String query = "insert into payment(PaymentID,UserID,DateCreate,SubTotal,DiscountedTotal,GrandTotal) values(?,?,?,?,?,?)";
//            PreparedStatement val = con.prepareStatement(query);
//            // Generate a UUID for the PaymentID
//            String paymentID = UUID.randomUUID().toString();
//            
//            // Get the current date
//            Date currentDate = Date.valueOf(LocalDate.now());
//            
//            // Set the values in the PreparedStatement
//            val.setString(1, paymentID);
//            val.setString(2, userID);
//            val.setDate(3, currentDate);
//            val.setDouble(4, subTotal);
//            val.setDouble(5, Discounted);
//            val.setDouble(6, grantTotal);
//            System.out.println(userID);
//            ResultSet rs = val.executeQuery();
//            for (ModelGame game : games) {
//                MakePaymentDetail(game,paymentID);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ServicePayment.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //Make PaymentDetail
        } catch (SQLException ex) {
            Logger.getLogger(ServicePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void MakePaymentDetail(ModelGame game,String PaymemtID){
        try {
            String query = "insert into PaymentDetail(PaymentID,GameID,total) values(?,?,?)";
            PreparedStatement val = con.prepareStatement(query);            
            val.setString(1, PaymemtID);
            val.setString(2, game.getGameId());
            val.setDouble(3, game.getPrice());
            ResultSet rs = val.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public void BalanceUpdate(){
//        String query = "Update users set balance = balance + '"+ value +"' where email= '"+ email + "'";
//        PreparedStatement val = con.prepareStatement(query);
//        user.balanceEdit(value);
//        ResultSet rs = val.executeQuery();
//    }   
}
