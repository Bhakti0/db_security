/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEngine;

import ClientOP.View_Data_Helper;
import adminOP.DBSecurityEngineFrame;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBEngineInit extends Thread
{
    public String tablename=null;
    
    
    public void run()
    {
       ArrayList columnname=new EngineDataProvider().getColumnName(tablename);
       ArrayList OrgData=new EngineDataProvider().getCompleteTableData(tablename,columnname.size());
        System.out.println("Original data fetched \n\n");
            
        //String columnName[]=new View_Data_Helper().getColumnName(tablename);

        while(true)
        {
            try
            {
                Thread.sleep(5000);
                System.out.println("Visited DataBase Table Name = "+tablename);
                ArrayList dbuserdata=new NewDBDriver().getDBManagerCredentials();
                String dbusername=(String) dbuserdata.get(0);
               // if(!dbusername.equals("root"))
                {
                  ArrayList currentdata=new EngineDataProvider().getCompleteTableData(tablename,columnname.size());
                    Date dt=new Date();
                     SimpleDateFormat sdf=new SimpleDateFormat("DD-MM-YYYY HH:MM:SS");
                     String currenttime=sdf.format(dt);
                     if(!OrgData.equals(currentdata))
                     {
                         ArrayList finaldata= new EngineDataProvider().getTamperFields(OrgData, currentdata, columnname);
                         ArrayList tamperfield=(ArrayList)finaldata.get(0);
                         ArrayList tamperid=(ArrayList)finaldata.get(1);
                         
                         String message1="ALERT ALERT a table name : "+tablename+" is Tampered on id "+tamperid;
                         String message2=" and on fields " +tamperfield+" by the database manager : " +dbusername+" on " +currenttime;
                         String message=message1+" "+message2;
                          System.out.println("Message : "+message);
                          String resultstr=DBSecurityEngineFrame.jTextArea1.getText()+"\n"+message;
                          DBSecurityEngineFrame.jTextArea1.setText(message);
                     }
                     System.out.println("Data Fetched at = "+currenttime);
                }
                
                
                
            }
            catch(Exception ex)
            {
                System.out.println("Exception at class DBEngineInit in method run() "+ex);
            }
            
            
            
        }
    }
            
}
