/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientOP;

/**
 *
 * @author User
 */
public class validator 
{   
    public boolean isDigit(String str)
    {
        boolean flag=true;
        try
        {
           long x=Long.parseLong(str);
           flag=true;
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class validator "+ex);
            flag=false;
        }
        return flag;
    }
    
}
