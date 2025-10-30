/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEngine;

import db.DbDriver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;


public class EngineDataProvider 
{
     public ArrayList getColumnName(String tablename)
    {
        ArrayList columnname=new ArrayList();  
        try
        {
            DbDriver dbd=new DbDriver();
            Statement st=dbd.getStatement();
            String query="select *from "+tablename;
            System.out.println("Query is = "+query);
            ResultSet rs= st.executeQuery(query);
            ResultSetMetaData rsmd=rs.getMetaData();
            int noc=rsmd.getColumnCount();
            //columnName=new String[noc];  //no of column noc
            
            for (int i = 0; i < noc; i++) 
                columnname.add(rsmd.getColumnName(i+1));
            
            
            
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class EngineDataProvider with method getColumnName() "+ex);
            
        }
        return columnname;
    }
     
     public ArrayList getCompleteTableData(String tablename,int columnCount)
     {
         
         ArrayList CompleteTableData=new ArrayList();
         
         try
         {
             DbDriver dbd=new DbDriver();
            Statement st=dbd.getStatement();
            String query="select *from "+tablename;
            ResultSet rs= st.executeQuery(query);
            while(rs.next())
            {
                ArrayList row=new ArrayList();
                
                for (int i = 1; i <= columnCount; i++) 
                {
                    String fill_data=rs.getString(i);
                    row.add(fill_data);
                }
                CompleteTableData.add(row);
            }
             
             
         }
         catch(Exception ex)
         {
           System.out.println("Exception at class EngineDataProvider with method getCompleteTableData() is "+ex);

         }
         return CompleteTableData;
         
     }
     
     public ArrayList getTamperFields(ArrayList orgdata,ArrayList currentdata,ArrayList columnnames)
     {
         ArrayList <String> tamperfields=new ArrayList<String>();
         String id=null;
         
         for (int i = 0; i < orgdata.size(); i++) 
         {
            ArrayList orgrow=(ArrayList) orgdata.get(i);
           
            ArrayList currentrow=(ArrayList) currentdata.get(i);
            
            if(!orgdata.equals(currentdata))
            {
                for (int j = 0; j < orgrow.size(); j++) 
                {
                    String orgfield=(String)orgrow.get(j);
                    String currentfield=(String)currentrow.get(j);
                    if(!orgfield.equals(currentfield))
                    {
                        String field=(String)columnnames.get(j);
                        tamperfields.add(field);
                        id=(String)orgrow.get(0);
                    }
                }
            }
             
         }
         ArrayList finaldata=new ArrayList();
         finaldata.add(tamperfields);
         finaldata.add(id);
         
         
         
         return tamperfields;
     }
}
