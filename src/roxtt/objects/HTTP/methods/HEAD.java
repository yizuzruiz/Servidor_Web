/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.objects.HTTP.methods;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author j_ruiz_m
 */
public class HEAD {
    
    public static void get(PrintWriter out,File mifichero){
        
                    out.println("HTTP/1.1 200 ok");

                    out.println("Accept-Ranges: bytes");
                    out.println("Server: roxtt");
                    out.println("Date: " + new Date());
                    out.println("Connection: Keep-Alive");
                    out.println("Keep-Alive: timeout=5, max=100");
   //                 out.println("Set-Cookie: FOCASESSION=" + Math.random() * 100000);
                    //  out.println("Cache-Control: max-age=10000000000");



                    String extension = mifichero.toString();

                    if (extension.endsWith(".html")) {
                        out.println("Content-Type: text/html");

                    }
                    if (extension.endsWith(".css")) {
                        out.println("Content-Type: text/css");
                        out.println("Cache-Control: max-age=1000");

                    }
                    if (extension.endsWith(".js")) {
                        out.println("Content-Type: text/javascript");
                        out.println("Cache-Control: max-age=1000");


                    }
                    if (extension.endsWith(".jpg")) {
                        out.println("Content-Type: image/jpg");
                        out.println("Cache-Control: max-age=1000");


                    }
                    if (extension.endsWith(".png")) {
                        
                        out.println("Cache-Control: max-age=1000");
                        out.println("Content-Type: image/png");
                        //out.println("\n");
                    }
                    if (extension.endsWith(".n")) {
                        System.out.println("NASHORN!!");
                        out.println("Content-Type: text/html");
                        
                    }
                    if (extension.endsWith(".live")) {
                        System.out.println("NASHORN!!");
                        out.println("Content-Type: text/html");
                        
                    }


                    //out.println("X-Powered-By: Java");
                    out.print("\n");
    
    }
    
}
