
package model;

import com.sun.mail.handlers.image_gif;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class ModelGame {

    public ModelGame() {
    }

    public ModelGame(String gameId, String Description, String gameName, String Developer, Double Rating, Date releaseDay, int ageLim, int download, long size, double price) {
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
        System.out.println(gameName);
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
        return price;
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
//    private image_gif;
    
}
