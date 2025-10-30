/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientOP;

import java.sql.*;
import db.DbDriver;
import java.util.ArrayList;


public class clientDBOP
{
    public boolean isclientRegister(String name,String dob,String mobile,String emailid,String username,String password,String Date_time)
    {
        //username, name, dob, mobile, emailid, password, date_time
        boolean flag=false;
        try
        {
            DbDriver dbd=new DbDriver();
            Statement st=dbd.getStatement();
            String query="insert into client_info values ( '"+username+"','"+name+"','"+dob+"','"+mobile+"','"+emailid+"','"+password+"','"+Date_time+"' )";
             if(st.executeUpdate(query)>0)
             {
                 flag=true;
             }
             
             
            dbd.st.close();
            dbd.conn.close();
           
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at clientDBOP with method isclientRegister() "+ex);
            flag=false;
        }
        
        return flag;
    }
    
    public boolean isExisted(String username,String password)
    {
        boolean flag=false;
        
        try
        {
            DbDriver dbd=new DbDriver();
            Statement st=dbd.getStatement();
            String query="select * from client_info where username = '"+username+"' and password ='"+password+"'";
            System.out.println("Query is "+query);
            ResultSet rs=st.executeQuery(query);
            if(rs.next())
                flag=true;
          
            dbd.st.close();
            dbd.conn.close();
            
            
            
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at clientDBOP with method isExisted() "+ex);
            flag=false;
        }
        return flag;
    }
    
public ArrayList getClientdata(String clientname)
{
    ArrayList<String> data=new ArrayList<String>();
    
    try
    {
        DbDriver dbd=new DbDriver();
        Statement st=dbd.getStatement();
        String query= " Select * from client_info where username = '"+clientname+"'";
        System.out.println("Query is = "+query);
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
            data.add(rs.getString(1));
            data.add(rs.getString(2));
            data.add(rs.getString(3));
            data.add(rs.getString(4));
            data.add(rs.getString(5));
            data.add(rs.getString(6));
        }
        
        st.close();
        dbd.st.close();
        dbd.conn.close();
        
    }
    catch(Exception ex)
    {
        System.out.println("Exception at clientDBOP with method getClientdata() "+ex);
        
    }
return data;
}

public boolean isEdited(String fname,String fdob,String fmobile,String femailid,String fusername,String fpassword)
{
    boolean flag=false;
    try
    {
        DbDriver dbd=new DbDriver();
        Statement st=dbd.getStatement();
        String query="update client_info set name= '" +fname+ "',dob= '" +fdob+ "' ,mobile= '" +fmobile+ "',emailid= '" +femailid+ "',password= '" +fpassword+ "'where username= '"+fusername+"'" ;
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
        System.out.println("Exception at clientDBOP with method isEdited() "+ex);
        flag=false;
    }
    return flag;
    
}
}

