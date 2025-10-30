/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientOP;

import db.DbDriver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDataDBOP 
{
    
    public boolean isTableCretaed(ArrayList<String> columnname,String tablename)
    {
        boolean flag=true;
        try
        {
            
            DbDriver dbd=new DbDriver();
            Statement st=dbd.getStatement();
            String query1 = "create table IF NOT EXISTS "+tablename+"(";
            String query2=" ";
            for (int i = 0; i < columnname.size(); i++)
            {
             String singlecolumn=columnname.get(i);
             query2=query2+singlecolumn+" VARCHAR(45),";
            }
            
            System.out.println("Query 1 : "+query1);
            System.out.println("Query 2 : "+query2);
            
        String finalquery=query1+query2+" PRIMARY KEY ("+ columnname.get(0)+"))";
            System.out.println("Final query to create a table is " +finalquery);
             int x= st.executeUpdate(finalquery);
             
        }
        catch(Exception ex)
        {
            System.out.println("Exception at ClientDataDBOP in method isTableCretaed() "+ex);
            flag=false;
        }
        return flag;
    }
    
    
    
    
    
    
    
    public boolean isdatainfostored(String clientname,String filename,String date_time)
    {
        //clientname, filename, date_time
        boolean flag=false;
        try
        {
            DbDriver dbd=new DbDriver();
            Statement st=dbd.getStatement();
            String query="insert into client_data_info values ( '"+clientname+"','"+filename+"','"+date_time+"')";
             if(st.executeUpdate(query)>0)
             {
                 flag=true;
             }
             
             
            dbd.st.close();
            dbd.conn.close();
           
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at ClientDataDBOP with method iddatainfostored() "+ex);
            flag=false;
        }
        
        return flag;
    }
    
    public ArrayList getStoredFileInfo(String client_name)
    {
        ArrayList data=new ArrayList();
        try
        {
            DbDriver dbd=new DbDriver();
            Statement st=dbd.getStatement();
            String query="select * from client_data_info where clientname = '"+client_name+"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                String filename=rs.getString(2);
                String date_time=rs.getString(3);
                ArrayList row=new ArrayList();
                row.add(filename);
                row.add(date_time);
                data.add(row);
            }
            
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at ClientDataDBOP with method getStoredfileinfo() "+ex);
        }
        return data;
    }
}
