/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.objects.html.form;

import java.io.PrintWriter;
import java.util.Hashtable;

/**
 *
 * @author jarm1987
 * 
 * Simple Class for get a select option (combobox) html
 */


public class Select {
    
/**
 * Get a select html
 * @param out
 * @param options 
 */
     public static void getSelect(PrintWriter out,Hashtable<Integer, String> options){

    out.println("<select>");
    for(int i=0;i<options.size();i++){    
        out.println("<option>"+options.get(i)+"</option>");
    }
    
    out.print("</select>");
    
    }
    
}
