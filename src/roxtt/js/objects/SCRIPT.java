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
public class SCRIPT {
 private PrintWriter out;
    public SCRIPT(PrintWriter out){
        this.out = out;
    }
    
    public void $(String type){
        if(type != null)
            this.out.println("<script type='"+type+"' >");
        else{
            this.out.println("<script>");
        }
    }
    
    public void $(){
        this.out.println("</script>");
    }
        
}
