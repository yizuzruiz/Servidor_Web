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
public class META {
    private PrintWriter out;
    public META(PrintWriter out){
        this.out = out;
    }
    
    public void $(String elements){
        this.out.println("<meta "+elements+">");
    }
    
   
    
}
