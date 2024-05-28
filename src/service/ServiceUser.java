package service;

import connection.DatabaseConnect;
import model.ModelUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import javax.management.Query;
import model.ModelLogin;

public class ServiceUser {

    private final Connection con;
    public ServiceUser() {
        con = DatabaseConnect.getInstance().getConnection();
    }

    public void insertUser(ModelUser user) throws SQLException {
        String code;
        UUID uuid = UUID.randomUUID();
        try (PreparedStatement p = con.prepareStatement("insert into USERS (USERID,USERNAME, EMAIL, PASSWORD, VERIFYCODE,RROLE) values (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            code = generateVerifyCode();
            
            p.setString(1, uuid.toString());
            p.setString(2, user.getUserName());
            p.setString(3, user.getEmail());
            p.setString(4, user.getPassword());
            p.setString(5, code);
            p.setString(6, "User");
            p.execute();
        }
        user.setUserID(uuid.toString());
        user.setVerifyCode(code);
    }
    
    public void insertDev(ModelUser user) throws SQLException {
        UUID uuid = UUID.randomUUID();
        String code;
        try (PreparedStatement p = con.prepareStatement("insert into USERS (USERID,USERNAME, EMAIL, PASSWORD, VERIFYCODE,RROLE) values (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            code = generateVerifyCode();
            p.setString(1, uuid.toString());
            p.setString(2, user.getUserName());
            p.setString(3, user.getEmail());
            p.setString(4, user.getPassword());
            p.setString(5, code);
            p.setString(6, "Developer");
            p.execute();
        }
        user.setUserID(uuid.toString());
        user.setVerifyCode(code);
    }

    private String generateVerifyCode() throws SQLException {
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        String code = df.format(ran.nextInt(1000000));  //  Random from 0 to 999999
        while (checkDuplicateCode(code)) {
            code = df.format(ran.nextInt(1000000));
        }
        return code;
    }

    private boolean checkDuplicateCode(String code) throws SQLException {
        boolean duplicate = false;
        ResultSet rs = con.createStatement().executeQuery(String.format("select USERID from USERS where VERIFYCODE='%s' FETCH FIRST 1 ROW ONLY",code));
        if(rs.next()){
            duplicate = true;
        }
        rs.close();
        return duplicate;
    }

    public boolean checkDuplicateUser(String user) throws SQLException {
        boolean duplicate = false;
        ResultSet rs = con.createStatement().executeQuery(String.format("select USERID from USERS where USERNAME='%s' and STATUS='Verified' FETCH FIRST 1 ROW ONLY",user));
         if(rs.next())
        {
            duplicate = true;
        }
         rs.close();
         return duplicate;
    }

    public boolean checkDuplicateEmail(String user) throws SQLException {
        boolean duplicate = false;
        ResultSet rs = con.createStatement().executeQuery(String.format("select USERID from USERS where EMAIL='%s' and STATUS='Verified' FETCH FIRST 1 ROW ONLY",user));
        if(rs.next())
        {
            duplicate = true;
        }
//        PreparedStatement p = con.prepareStatement("select USERID from USERS where EMAIL=? and STATUS='Verified' FETCH FIRST 1 ROW ONLY");
//        p.setString(1, user);
//        ResultSet r = p.executeQuery();
//        if (r.next()) {
//            duplicate = true;
//        }
//        r.close();
//        p.close();
         rs.close();
         return duplicate;
    }
    
     public void deleteUsers(String Email) throws SQLException{
        PreparedStatement p = con.prepareStatement("Delete from USERS where EMAIL=? AND STATUS is null");
        p.setString(1,Email);
        p.execute();
        p.close();
    }

    public void doneVerify(String userID) throws SQLException {
        PreparedStatement p = con.prepareStatement("update USERS set VERIFYCODE='', STATUS='Verified' where USERID=?");
        p.setString(1, userID);
        p.execute();
        p.close();
    }
    
    public boolean verifyCodeWithUser(String userID, String code) throws SQLException {
            boolean verify = false;
           
            ResultSet rs = con.createStatement().executeQuery(String.format("select USERID from USERS where USERID='%s' and VERIFYCODE='%s'",userID,code));
               if(rs.next()){
                verify = true;
            }
        rs.close();
        return verify;
    }
    
    public void doneVerify2(String userName) throws SQLException {
        PreparedStatement p = con.prepareStatement("update USERS set VERIFYCODE='', STATUS='Verified' where USERName=?");
        p.setString(1, userName);
        p.execute();
        p.close();
    }
    
    public boolean verifyCodeWithUser2(String userName, String code) throws SQLException {
            boolean verify = false;
           
            ResultSet rs = con.createStatement().executeQuery(String.format("select USERID from USERS where USERName='%s' and VERIFYCODE='%s'",userName,code));
               if(rs.next()){
                verify = true;
            }
        rs.close();
        return verify;
    }
    
    
    public ModelUser login(ModelLogin login) throws SQLException{
        ModelUser data = null;
        ResultSet rs = con.createStatement().executeQuery(String.format("select USERID, USERNAME, EMAIL, FIRSTNAME, LASTNAME, DATEOFBIRTH, PHONE, BALANCE, RROLE from USERS where EMAIL='%s' and PASSWORD='%s' AND STATUS='Verified' FETCH FIRST 1 ROW ONLY", login.getEmail(),login.getPassword()));
        if(rs.next()){
            String userID = rs.getString(1);
            String userName = rs.getString(2);
            String Email = rs.getString(3);
            String First = rs.getString(4);
            String Last = rs.getString(5);
            Date Dob = rs.getDate(6);
            String Phone = rs.getString(7);
            Double Balance = rs.getDouble(8);
            String Role = rs.getString(9);
            data = new ModelUser(userID, userName, Email, "", First, Last, Dob, Phone, Balance, Role);
        }
        rs.close();
        return data;
        
    
    }
    
    public boolean checkEmail(String email)throws SQLException{
        boolean check = false;
        ResultSet rs = con.createStatement().executeQuery(String.format("select USERID from USERS where EMAIL='%s' and STATUS='Verified' FETCH FIRST 1 ROW ONLY",email));
        if(rs.next()){
            check = true;
        }
        return check;
    }
    
    public void updateForget(String email, String newpass)throws SQLException{
        String code = generateVerifyCode();
        //System.out.println(code + email + newpass);
        con.createStatement().execute(String.format("update USERS set VERIFYCODE='%s', PASSWORD='%s' where EMAIL='%s'",code,newpass,email));   
    }
    
    public ModelUser setUser(String email) throws SQLException{
        ModelUser user = null;
        ResultSet rs = con.createStatement().executeQuery(String.format("select  USERNAME, EMAIL, PASSWORD, VERIFYCODE from USERS where EMAIL='%s'",email));
        if(rs.next()){
            String userName = rs.getString(1);
            String userEmail = rs.getString(2);
            String userPass = rs.getString(3);
            String userCode = rs.getString(4);
            user = new ModelUser( userName, userEmail, userPass, userCode);
        }
        rs.close();
        return user;
    }
   
    
    public void updateEdit(String Firstname, String Lastname, String Dob, String Phone, String Username)throws SQLException{
        con.setAutoCommit(false);
        con.createStatement().executeQuery(String.format("update USERS set FirstName='%s', LastName='%s', DATEOFBIRTH = TO_DATE('%s','dd/MM/yyyy'), Phone='%s' where USERNAME='%s'",Firstname,Lastname,Dob,Phone,Username));
        con.commit();
    }
    
}