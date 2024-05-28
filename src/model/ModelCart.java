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
    // Constructor của ModelCart
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
    public ArrayList<ModelGame> getGames(){
        return games;
    }
    public double getTotal(){
        return Total;
    }
    public void printAllIds() {
        System.out.println("day la cart");
        for (ModelGame game : games) {
            System.out.println(game.getGameName());
        }
    }
}
