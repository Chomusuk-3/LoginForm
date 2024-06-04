
package service;

import connection.DatabaseConnect;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.ImageIcon;
import model.ModelGame;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class gameService {
    private final Connection con;
    private ModelGame game;
    public gameService() {
        con = DatabaseConnect.getInstance().getConnection();
    }
    public ModelGame getGameDetail(String gameId) throws SQLException{
        
        ResultSet rs = con.createStatement().executeQuery("SELECT GameID, GameName,developername,rating,releaseday,agelimit,downloaded,Description,gamesize,price,image FROM GAMES where gameid = '"+ gameId +"'");
        while (rs.next()) {
            
            try {
                String gameID = rs.getString("GameID");
                String gameName = rs.getString("GameName");
                String description = rs.getString("Description");
                String developerid = rs.getString("developername");
                Double rating = rs.getDouble("rating");
                Date reday = rs.getDate("releaseday");
                int agelimit = rs.getInt("agelimit");
                int download = rs.getInt("downloaded");
                long gamesize = rs.getLong("gamesize");
                double price = rs.getDouble("price");
                Blob imageBlob = rs.getBlob("image");
                ImageIcon imageIcon = null;
                if(imageBlob != null){
                    InputStream imageInputStream = imageBlob.getBinaryStream();
                    Image image = ImageIO.read(imageInputStream);
                    imageIcon = new ImageIcon(image);
                }   
                game = new ModelGame(gameID,description,gameName,developerid,rating,reday,agelimit,download,gamesize,price,imageIcon);
            } catch (IOException ex) {
                Logger.getLogger(gameService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return game;
    }
    public boolean libraryCheck(String userid,String gameid){
        boolean exists = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT COUNT(*) AS count FROM library WHERE userid = ? and gameid = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, userid);
            statement.setString(2, gameid);
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
    public void downloadUpdate(String gameID){
        try {
            String query = "Update games set downloaded = downloaded + 1   where gameid= '"+ gameID + "'";
            PreparedStatement val = con.prepareStatement(query);
            ResultSet rs = val.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(gameService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
