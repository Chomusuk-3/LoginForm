
package model;

import com.sun.mail.handlers.image_gif;
import connection.DatabaseConnect;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ModelGame {
    CallableStatement callableStatement = null;
    private Connection con = DatabaseConnect.getInstance().getConnection();
    public ModelGame() {
    }

    public ModelGame(String gameId, String Description, String gameName, String Developer, Double Rating, Date releaseDay, int ageLim, int download, long size, double price,ImageIcon image) {
        this.gameId = gameId;
        this.Description = Description;
        this.gameName = gameName;
        this.Developer = Developer;
        this.Rating = Rating;
        this.releaseDay = releaseDay;
        this.ageLim = ageLim;
        this.download = download;
        this.size = size;
        this.price = price;
        this.image = image;
    }
    public void setAgeLim(int ageLim) {
        this.ageLim = ageLim;
    }

    public int getAgeLim() {
        return ageLim;
    }

    public String getDescription() {
        return Description;
    }

    public String getDeveloper() {
        return Developer;
    }

    public int getDownload() {
        return download;
    }

    public String getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public double getPrice() {
        try {
            String sql = "{ ? = call game_price_cal(?) }";
            callableStatement = con.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.DOUBLE);
            callableStatement.setString(2, gameId);
            callableStatement.execute();
            double result = callableStatement.getDouble(1);
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ModelGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Double getRating() {
        return Rating;
    }

    public Date getReleaseDay() {
        return releaseDay;
    }

    public String getShortDescrition() {
        return shortDescrition;
    }

    public long getSize() {
        return size;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setDeveloper(String Developer) {
        this.Developer = Developer;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(Double Rating) {
        this.Rating = Rating;
    }

    public void setReleaseDay(Date releaseDay) {
        this.releaseDay = releaseDay;
    }

    public void setShortDescrition(String shortDescrition) {
        this.shortDescrition = shortDescrition;
    }

    public void setSize(long size) {
        this.size = size;
    }
    public void setImageIcon(ImageIcon imageIcon) {
        this.image = imageIcon;
    }

    public ImageIcon getImage() {
        return image;
    }
    
    
    
    private String gameId;
    private String shortDescrition;
    private String Description;
    private String gameName;
    private String Developer;
    private Double Rating;
    private Date releaseDay;
    private int ageLim;
    private int download;
    private long size;
    double price;
    private ImageIcon image;
//    private image_gif;
    
}
