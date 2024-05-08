package service;

import connection.DatabaseConnect;
import model.ModelUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class ServiceUser {

    private final Connection con;

    public ServiceUser() {
        con = DatabaseConnect.getInstance().getConnection();
    }

    public void insertUser(ModelUser user) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into USERS (USERID,USERNAME, EMAIL, PASSWORD, VERIFYCODE) values (SYS_GUID(),?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        String code = generateVerifyCode();
        UUID uuid = UUID.randomUUID();
        p.setString(1, user.getUserName());
        p.setString(2, user.getEmail());
        p.setString(3, user.getPassword());
        p.setString(4, code);
        p.execute();
       /* ResultSet r = p.getGeneratedKeys();
        r.first();
        int userID = r.getInt(1);
        r.close();*/
        p.close();
        user.setUserID(uuid);
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

    public void doneVerify(String userName) throws SQLException {
        PreparedStatement p = con.prepareStatement("update USERS set VERIFYCODE='', STATUS='Verified' where USERNAME=?");
        p.setString(1, userName);
        p.execute();
        p.close();
    }

    public boolean verifyCodeWithUser(String userName, String code) throws SQLException {
            boolean verify = false;
            ResultSet rs = con.createStatement().executeQuery(String.format("select USERID from USERS where USERNAME='%s' and VERIFYCODE='%s' FETCH FIRST 1 ROW ONLY",userName,code));
               if(rs.next()){
                verify = true;
            }
            
//        PreparedStatement p = con.prepareStatement("select USERID from USERS where USERNAME=? and VERIFYCODE=? FETCH FIRST 1 ROW ONLY");
//        p.setString(1, userName);
//        p.setString(2, code);
//        ResultSet r = p.executeQuery();
//        if (r.next()) {
//            verify = true;
//        }
//        r.close();
//        p.close();
        rs.close();
        return verify;
    }
}