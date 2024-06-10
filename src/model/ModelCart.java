/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.text.DecimalFormat;
import java.util.ArrayList;
import model.ModelGame;
public class ModelCart {
    private ArrayList<ModelGame> games;
    private double Total = 0 ; 
    private double discount = 0;
    private double grantTotal = 0;
    private String userID;
    // Constructor của ModelCart

    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public boolean hasGame(String gameid) {
        for (ModelGame game : games) {
            if (game.getGameId().equals(gameid)) {
                return true;
            }
        }
        return false;
    }
    public void emptyCart(){
        Total = 0 ; 
        discount = 0;
        grantTotal = 0;
        games = new ArrayList<>();
    }
    public void setgrantTotal() {
        this.grantTotal = Total - discount;
        grandTotalCheck();
    }
    public void grandTotalCheck(){
        if(grantTotal <  0){
            grantTotal = 0;
        }
    }
    public double getGrantTotal() {
        return grantTotal;
    }

    public double getDiscount() {
        return discount;
    }
    
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
    public ModelCart() {
        // Khởi tạo ArrayList
        games = new ArrayList<>();
    }
    public void addGame(ModelGame game) {
        games.add(game);
        Total += game.getPrice();
        DecimalFormat df = new DecimalFormat("#.##");
        Total = Double.valueOf(df.format(Total)); 
    }
    public void reloadCart(){
        Total = 0;       
        for (ModelGame game : games) {
            Total+= game.getPrice();
        }
        setgrantTotal();
    }
    public ArrayList<ModelGame> getGames(){
        return games;
    }
    public double getTotal(){
        return Total;
    }
    public void printAllIds() {
        for (ModelGame game : games) {
            System.out.println(game.getGameName());
        }
    }
}
