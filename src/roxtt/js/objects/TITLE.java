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
public class TITLE {
 private PrintWriter out;
    public TITLE(PrintWriter out){
        this.out = out;
    }
    
    public void $(String elements){
        if(elements != null)
            this.out.println("<title "+elements+" >");
        else{
            this.out.println("<title>");
        }
    }
    
    public void $(){
        this.out.println("</title>");
    }
    
    
    
}
