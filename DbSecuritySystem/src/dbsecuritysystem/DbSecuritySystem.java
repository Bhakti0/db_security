
package dbsecuritysystem;

import java.awt.Dimension;
import java.awt.Toolkit;


public class DbSecuritySystem
{

    public static void main(String[] args) 
    {
       LoginFrame lf=new LoginFrame();
       Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
       lf.setVisible(true);
       lf.setSize(d);
       
    }
    
}
