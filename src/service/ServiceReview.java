package service;

import connection.DatabaseConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelGame;

public class ServiceReview {
    private final Connection con;
    public ServiceReview() {
        con = DatabaseConnect.getInstance().getConnection();
    }
    public void saveReviewSection(String userID,String gameID,String commentText,int rating){
        try {
            String query = "insert into GAMEREVIEW(gamereviewid,userid,gameid,commenttext,rating) values(?,?,?,?,?)";
            PreparedStatement val = con.prepareStatement(query);
            // Generate a UUID for the PaymentID
            String gamereviewid = UUID.randomUUID().toString();
            val.setString(1, gamereviewid);
            val.setString(2, userID);
            val.setString(3, gameID);
            val.setString(4, commentText);
            val.setInt(5, rating);
            ResultSet rs = val.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
