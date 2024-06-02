
package service;

import connection.DatabaseConnect;
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
    public ServicePayment(){
        con = DatabaseConnect.getInstance().getConnection();
    }
    public void MakePayment(String userID,double grantTotal,double Discounted, double subTotal,ArrayList<ModelGame> games){
        //Make payment
        try {
            String query = "insert into payment(PaymentID,UserID,DateCreate,SubTotal,DiscountedTotal,GrandTotal) values(?,?,?,?,?,?)";
            PreparedStatement val = con.prepareStatement(query);
            // Generate a UUID for the PaymentID
            String paymentID = UUID.randomUUID().toString();
            
            // Get the current date
            Date currentDate = Date.valueOf(LocalDate.now());
            
            // Set the values in the PreparedStatement
            val.setString(1, paymentID);
            val.setString(2, userID);
            val.setDate(3, currentDate);
            val.setDouble(4, subTotal);
            val.setDouble(5, Discounted);
            val.setDouble(6, grantTotal);
            System.out.println(userID);
            ResultSet rs = val.executeQuery();
            for (ModelGame game : games) {
                MakePaymentDetail(game,paymentID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Make PaymentDetail
        
        
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
