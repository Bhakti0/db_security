/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class NewDBDriver 
{
    public Statement st;
    public   Connection conn;
    public Statement getStatement()
    {
        ArrayList data=getDBManagerCredentials();
        String dbusername=(String) data.get(0);
        String dbpassword=(String) data.get(1);
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
             conn=DriverManager.getConnection("jdbc:mysql://localhost:3303/db_security",dbusername,dbpassword);
             st=conn.createStatement();
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class DbDriver with method getstatement() "+ex);
        }
        return st;
    }    
    
    public ArrayList getDBManagerCredentials()
    {
        ArrayList data=new ArrayList();
        
        
        
        
        
        
        data.add("root");
        data.add("root");
        
        
        return data;
        
        
        
    }
}
