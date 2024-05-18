
package model;

import java.util.Date;
import java.util.UUID;


public class ModelUser {

    public double getBalance() {
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

    public ModelUser() {
    }

    public ModelUser(String userID, String userName, String email, String password, String verifyCode) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.verifyCode = verifyCode;
    }
    public ModelUser( String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    public ModelUser(String userID, String userName, String email, String password) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public ModelUser(String userID, String userName, String email, String password, String firstname, String lastname, Date dob, String Phone, double balance) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.Phone = Phone;
        this.balance = balance;
    }
    public ModelUser(String username, String firstname, String lastname, Date dob, String Phone, double balance) {
        this.userName = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.Phone = Phone;
        this.balance = balance;
    }

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
}
