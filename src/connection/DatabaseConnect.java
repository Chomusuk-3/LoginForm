
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
    private static DatabaseConnect instance;
    private Connection connection;
    
    public static DatabaseConnect getInstance(){
        if(instance == null)
        {
            instance = new DatabaseConnect();
        }
        return instance;
    }
    private DatabaseConnect(){
        
    }
    public void connectToDataBase() throws SQLException{
        String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
        String username = "DoAn";
        String password = "password";
        connection = DriverManager.getConnection(dbURL, username, password);
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public void setConnection(Connection connection){
        this.connection = connection;
    }
}
