/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientOP;

import java.io.File;
import java.util.ArrayList;
import jxl.Workbook;
import jxl.write.WritableWorkbook;
import java.sql.*;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;

/**
 *
 * @author User
 */
public class ExcelFileDownloader 
{
    public boolean isDownload(ArrayList completedata,String finalpath)
    {
        boolean flag=false;
        try
        {
            File file=new File(finalpath);
            WritableWorkbook workbook=Workbook.createWorkbook(file);
            WritableSheet sheet=workbook.createSheet("Sheet 1", 0);
            WritableFont cellfont=new WritableFont(WritableFont.TIMES,12);
            WritableCellFormat cellformat= new WritableCellFormat(cellfont);
            Label lbl=null;
            
            for (int i = 0; i < completedata.size(); i++) 
            {
                ArrayList row=(ArrayList)completedata.get(i);
                for (int j = 0; j < row.size(); j++)
                {
                    String content=(String)row.get(j);
                    lbl=new Label(j,i,content,cellformat);
                    sheet.addCell(lbl);
                }
            }
         workbook.write();
         workbook.close();
          
            
            flag=true;
            
            
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class ExcelFileDownloader in function isdownload() = "+ex);
            flag=false;
        }
        return flag;
    }
}
