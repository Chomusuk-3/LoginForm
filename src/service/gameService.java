
package service;

import connection.DatabaseConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import model.ModelGame;



public class gameService {
    private final Connection con;
    private ModelGame game;
    public gameService() {
        con = DatabaseConnect.getInstance().getConnection();
    }
    public ModelGame getGameDetail(String gameId) throws SQLException{
        ResultSet rs = con.createStatement().executeQuery("SELECT GameID, GameName,developername,rating,releaseday,agelimit,downloaded,Description,gamesize,price FROM GAMES where gameid = '"+ gameId +"'");
        while (rs.next()) {
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
            game = new ModelGame(gameID,description,gameName,developerid,rating,reday,agelimit,download,gamesize,price);
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
        }
        return game;
    }
   
}
