
package model;

import connection.DatabaseConnect;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import service.gameService;



public class ModelUser {

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getBalance() {
        double balance = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT balance from users where userid = '"+ userID+ "'");
            while (rs.next()) {
                balance = rs.getDouble("balance");
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
 
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
    
     public void balanceEdit(double value) {
        this.balance += value;
    }

    public ModelUser() {
    }

    public ModelUser( String userName, String email, String password, String verifyCode) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.verifyCode = verifyCode;
    }
    public ModelUser( String userID,String userName, String email, String password, String role) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    

    public ModelUser(String userID, String userName, String email, String password, String firstname, String lastname,Date dob, String Phone, double balance, String role) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.Phone = Phone;
        this.balance = balance;
        this.role = role;
        con = DatabaseConnect.getInstance().getConnection();
    }

    public ModelUser(String firstname, String lastname, Date dob, String Phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.Phone = Phone;
    }
    
    
    private Connection con;
    private String userID;
    private String userName;
    private String email;
    private String password;
    private String verifyCode;
    private String firstname;
    private String lastname;
    private Date dob;
    private String Phone;
    private double balance;
    private String role;
}
