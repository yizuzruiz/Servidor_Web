/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author yizuzruiz
 *
 * Class to write a image
 */
public class Imagen {

    public static void writeImage(FileInputStream fis, OutputStream os, String ext) throws Exception {

        
        os.write("HTTP/1.1 200 OK\r\n".getBytes(Charset.forName("UTF-8")));
        os.write(("Date: " + new Date() + "").getBytes(Charset.forName("UTF-8")));
        os.write(("Content-Type: image/" + ext + "\r\n").getBytes(Charset.forName("UTF-8")));
        os.write("Cache-Control: max-age=1000\r\n".getBytes(Charset.forName("UTF-8")));
        os.write("\r\n".getBytes(Charset.forName("UTF-8")));

        byte[] buffer = new byte[1024];
        int bytes = 0;

        while (-1 != (bytes = fis.read(buffer))) {
            os.write(buffer, 0, bytes);
        }
    }
    
    /**
 *
 * @param file File image for write, request the server
 * @param scliente Channel of comunication between client and server
 * @throws IOException
 */
public static void writeImage(File file, Socket scliente) {
      
        
        //Initialize the stream
        java.io.BufferedOutputStream outputStream;
        try {
            outputStream = new java.io.BufferedOutputStream(scliente.getOutputStream());
       //Reading image
            BufferedImage bufferedImage = ImageIO.read(file);
              
      //Writing the imagen and send to server in the stream
           System.out.println(ImageIO.write(bufferedImage, Extension.getExtension(file), outputStream));

      //closed the stream
        outputStream.close();
        



} catch (IOException ex) {
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}



