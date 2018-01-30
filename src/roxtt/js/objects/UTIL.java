/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roxtt.js.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JesúsAlberto
 */
public class UTIL {
    
    private PrintWriter out;
    
    public UTIL(PrintWriter out){
        this.out = out;
    }
    
    
    public void insertSource(String path){
        URL url = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException ex) {
            Logger.getLogger(UTIL.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream in=null;
        try {
            in = url.openStream();
        } catch (IOException ex) {
            Logger.getLogger(UTIL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedReader readRes = new BufferedReader(new InputStreamReader(in));
        String line = "";
        do {
            
            try {
                line = readRes.readLine();
            } catch (IOException ex) {
                Logger.getLogger(UTIL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (line != null) {
                
                out.println(line);
                
            }
            
        } while (line != null);
    }
    
    
    
}
