/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientOP;

import db.DbDriver;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jxl.*;
import java.util.ArrayList;


public class ExcelOperation 
{
    public ArrayList<String> getColumnNames(String filepath)
    {
        ArrayList<String> columnNames=new ArrayList<String>();
        try
        {
            File file=new File(filepath);
            Workbook wobj=Workbook.getWorkbook(file);
             Sheet sht=wobj.getSheet(0);
             int rows=sht.getRows();
             int column=sht.getColumns();
             System.out.println("Rows = " +rows+ "column = " +column);
                     
             for (int j = 0; j < column; j++) 
             {
                Cell cle=sht.getCell(j,0);
                String content=cle.getContents();
                columnNames.add(content);
                
            }
            
            wobj.close();
            
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class Exceloperation in method getColumnNames () "+ex);
        }
        return columnNames;
    }
    
    public boolean isdataStored(String filepath,ArrayList<String> columnnames,String tablename)
    {
        boolean flag=true;
        try
        {
            DbDriver dbd=new DbDriver();
                    dbd.getStatement();
            Connection conn=dbd.conn;
            
            String query1="INSERT INTO "+tablename+"(";
            String query2="";
            String query3=") VALUES (";
                for (int i = 0; i < columnnames.size(); i++) 
                {
                    query2=query2+columnnames.get(i)+", ";
                    query3=query3+"?, ";
                }
            query2=query2.substring(0, query2.length()-2);
            query3=query3.substring(0, query3.length()-2)+")";
            String finalquery=query1+query2+query3;
            PreparedStatement ps=conn.prepareStatement(finalquery);
            
                    
            File file=new File(filepath);
            Workbook wobj=Workbook.getWorkbook(file);
             Sheet sht=wobj.getSheet(0);
             int rows=sht.getRows();
             int column=sht.getColumns();
             
             for (int i = 0; i < rows; i++) 
             {
                 for (int j = 0; j < column; j++)
                 {
                     Cell cl=sht.getCell(j,i);
                      String Cell_Content= cl.getContents();
                      ps.setString(j+1,Cell_Content);
                 }
                 ps.addBatch();
             }
             ps.executeBatch();
             wobj.close();
             conn.close();
            
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class Exceloperation in method isdatastored () "+ex);
            //flag=false;
        }
        return flag;
    }
}
