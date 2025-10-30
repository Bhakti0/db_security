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

public class ClientInfofetcher 
{
    public ArrayList getClientinfo()
    {
        ArrayList clientdata=new ArrayList();
        
        try
        {
            DbDriver dbd=new DbDriver();
            Statement st=dbd.getStatement();
            String query="select * from client_data_info";
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                String clientname=rs.getString(1);
                String filename=rs.getString(2);
                String date_time=rs.getString(3);
                ArrayList row=new ArrayList();
                row.add(clientname);
                row.add(filename);
                clientdata.add(row);
            }
            
            
            
            
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class ClientInfofetcher in method getClientinfo() "+ex);
        }
        return clientdata;
    }
}
