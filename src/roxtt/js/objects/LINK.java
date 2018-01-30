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
public class LINK {
    private PrintWriter out;
    public LINK(PrintWriter out){
        this.out = out;
    }
    
    public void $(String rel, String type, String href){
            this.out.println("<link rel='"+rel+"' type='"+type+"' href='"+href+"' >");
    }
    
}
