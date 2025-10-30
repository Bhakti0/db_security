/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientOP;

import db.DbDriver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;


/**
 *
 * @author User
 */
public class View_Data_Helper 
{
    public String[] getColumnName(String tablename)
    {
        String columnName[]=null;
        
        try
        {
            DbDriver dbd=new DbDriver();
            Statement st=dbd.getStatement();
            String query="select *from "+tablename;
            System.out.println("Query is = "+query);
            ResultSet rs= st.executeQuery(query);
            ResultSetMetaData rsmd=rs.getMetaData();
            int noc=rsmd.getColumnCount();
            columnName=new String[noc];  //no of column noc
            
            for (int i = 0; i < noc; i++) 
            {
                columnName[i]=rsmd.getColumnName(i+1);
            }
            
            
            
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class view_data_helper with method getColumnName() "+ex);
            
        }
        return columnName;
    }
    
    public String [][] getTableData(String tablename,int columncount)
    {
        String data[][]=null;
        try
        {
            DbDriver dbd=new DbDriver();
            Statement st1=dbd.getStatement();
              Statement st2=dbd.getStatement();
              String query="Select * from "+tablename;
              ResultSet rs1=st1.executeQuery(query);
              ResultSet rs2=st2.executeQuery(query);
              int rowcount=0;
              while(rs1.next())
                  rowcount++;
  
               data=new String [rowcount][columncount];       
               int i=0;
               while(rs2.next())
               {
                   for (int j = 0; j < columncount; j++) 
                   {
                      data[i][j]= rs2.getString(j+1);
                   }
                   i++;
               }
           
        }
        catch(Exception ex)
        {
           System.out.println("Exception at class view_data_helper with method getTableData() "+ex);

        }
               return data;
        
    }
}
