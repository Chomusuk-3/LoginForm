
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
//            System.out.println("Game ID: " + gameID);
//System.out.println("Game Name: " + gameName);
//System.out.println("Description: " + description);
//System.out.println("Developer ID: " + developerid);
//System.out.println("Rating: " + rating);
//System.out.println("Release Day: " + reday);
//System.out.println("Age Limit: " + agelimit);
//System.out.println("Downloaded: " + download);
//System.out.println("Game Size: " + gamesize);
//System.out.println("Price: " + price);
            } catch (IOException ex) {
                Logger.getLogger(gameService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return game;
    }
   
}
