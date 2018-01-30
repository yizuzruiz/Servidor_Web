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
public class DOC {
    private PrintWriter out;
    public DOC(PrintWriter out){
        this.out = out;
    }
    
    public void $(){
        this.out.println("<!DOCTYPE html>");
    }
    
}
