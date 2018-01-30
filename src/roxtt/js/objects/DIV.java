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
public class DIV {
    private PrintWriter out;
    public DIV(PrintWriter out){
        this.out = out;
    }
    
    public void $(String attributes){
        if(attributes != null)
            this.out.println("<div "+attributes+" >");
        else{
            this.out.println("<div>");
        }
    }
    public void $(){
        this.out.println("</div>");
    }
    
}
