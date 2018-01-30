/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.live;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JesúsAlberto
 */
public class Live {

    Socket client;
    OutputStreamWriter outputStream;
    BufferedWriter writer;
    PrintWriter salida;
    InputStreamReader inputStream;
    BufferedReader reader;
    String txt ="";

    public Live(PrintWriter out,String message) {
        try {
            try {
                this.client = new Socket("localhost", 8080);
                this.inputStream = new InputStreamReader(client.getInputStream());
                this.outputStream = new OutputStreamWriter(client.getOutputStream());
                
            }
            catch (IOException ex) {
            }
            writer = new BufferedWriter(outputStream);
            salida = new PrintWriter(writer, true);
            reader = new BufferedReader(inputStream);
            salida.println("Live "+message);
            String in;
            int ch=0;
            StringBuffer sb=new StringBuffer();
            while ((ch = reader.read()) != 13) {
               sb.append((char)ch);
                sb.toString();
                //System.out.println(sb);
            }
            txt = sb.toString();
            out.println(txt);
            
           // client.close();
           // reader.close();
           // writer.close();
            
            
            
        }
         catch (IOException ex) {
            Logger.getLogger(Live.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

}
