
package db;

import java.sql.*;
public class DbDriver
{
    
    public Statement st;
    public   Connection conn;
    public Statement getStatement()
    {
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
             conn=DriverManager.getConnection("jdbc:mysql://localhost:3303/db_security","root","root");
             st=conn.createStatement();
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class DbDriver with method getstatement() "+ex);
        }
        return st;
    }
}
