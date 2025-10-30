/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminOP;

import db.DbDriver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class AdminOP 
{
    public boolean isAdminExisted(String user_name,String pass_word)
    {
        boolean flag=false;
        
        try
        {
             DbDriver dbd=new DbDriver();
            Statement st=dbd.getStatement();
            String query="select * from admin_info where username = '"+user_name+"' and password ='"+pass_word+"'";
            System.out.println("Query is "+query);
            ResultSet rs=st.executeQuery(query);
            if(rs.next())
                flag=true;
          
            dbd.st.close();
            dbd.conn.close();
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at AdminOP with method isAdminExisted() "+ex);
            flag=false;
            
        }
        return flag;
    }
    
    public ArrayList getAdminCredentials()
    {
        ArrayList<String> admindata=new ArrayList<String>();
        
        try
        {
            String adminname="admin";
            DbDriver dbd=new DbDriver();
            Statement st=dbd.getStatement();
            String query="select * from admin_info where username = '"+adminname+"'";
            System.out.println("Query is "+query);
            ResultSet rs=st.executeQuery(query);
            if(rs.next())
            {
                admindata.add(rs.getString(1));
                admindata.add(rs.getString(2));
                admindata.add(rs.getString(3));
                
            }
          
            dbd.st.close();
            dbd.conn.close();
            
        }
        catch(Exception ex)
        {
         System.out.println("Exception at AdminOP with method getAdminCredentials() "+ex);

        }
        return admindata;
    }
    public boolean isAdminUpdated(String username,String emailid,String password)
    {
        boolean flag=false;
        try
        {
            //username, password, emailid
            DbDriver dbd=new DbDriver();
            Statement st=dbd.getStatement();
            String query="update admin_info set password= '" +password+ "',emailid= '" +emailid+ "' where username= '"+username+"'" ;
            System.out.println("Query is = "+query);
            int x=st.executeUpdate(query);
            if(x>0)
            {
                flag=true;
            }
            st.close();
            dbd.st.close();
            dbd.conn.close();

            
            
        }
        catch(Exception ex)
        {
          System.out.println("Exception at AdminOP with method isAdminUpdated() "+ex);
           flag=false;
        }
        return flag;
    }
}
