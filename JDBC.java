import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.Statement;
/**
 * Write a description of class JDBC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JDBC
{
    private Connection conn;
    
    public JDBC()
    {
       
    }
    
    public void insertMember() throws SQLException{
        // Get a connection to the database
        conn = getConnection();
        System.out.println(conn);
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        
        //Execute the query
        stmt.executeUpdate("insert into member(memberNumber, memberName) values ('10001', 'puspa')");
    }
   
    
    public Connection getConnection(){
        try{
            // Load the database driver
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        
        try{
            // Get a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/club?user=root&password=");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
