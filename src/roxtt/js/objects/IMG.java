/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roxtt.js.objects;

import java.io.PrintWriter;

/**
 *
 * @author JesúsAlberto
 */
public class IMG {
    
    private PrintWriter out;
    public IMG(PrintWriter out){
        this.out = out;
    }
    
    public void $(String src, String elements){
        if(elements != null)
            this.out.println("<img src='"+src+"' "+elements+" />");
        else{
            this.out.println("<img src='"+src+"' />");
        }
    }
    
}
