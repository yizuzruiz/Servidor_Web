/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.objects.HTTP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Hashtable;
import roxtt.ToDo;
import roxtt.fw.DB.Connect;
import roxtt.fw.MVC.controller.Controller;
import roxtt.objects.Extension;
import roxtt.objects.HTTP.methods.HEAD;
import roxtt.objects.Imagen;
import roxtt.objects.html.form.Select;

/**
 *
 * @author jarm1987
 */
public class ResponseHTTP {

    public static void getResponse(String res, Socket client, PrintWriter out, Connect con, Hashtable s, boolean head) {
        String message = res;
        System.out.println("->"+res);
        File mySrc = null;

        if (res.startsWith("/")) {
            res = res.substring(1);
        }

        if (res.endsWith("/") || res.equals("")) {
            //res = res + "index.html";
            out.println("Hola mundo");
        }

        if (res.startsWith("-")) {
            res = res.substring(1);
        }

        try {

            // Ahora leemos el fichero y lo retornamos
            mySrc = new File("./htdocs/" + res);

            String ext = mySrc.toString();

            if (mySrc.exists() && (ext.endsWith(".html") || ext.endsWith(".x") || ext.endsWith(".css") || ext.endsWith(".js"))) {

                if (head) {
                    HEAD.get(out, mySrc);
                }

                BufferedReader readRes = new BufferedReader(new FileReader(mySrc));

                String linea;
                ToDo toDo = new ToDo(out, s);
                do {

                    linea = readRes.readLine();

                    if (linea != null) {

                        toDo.getLine(linea);

                    }

                } while (linea != null);

                readRes.close();

            } // fin de si el fiechero existe
            else if (mySrc.exists() && (ext.endsWith(".jpg") || ext.endsWith(".png") || ext.endsWith(".gif"))) {
                FileInputStream f = new FileInputStream(mySrc);
                Imagen.writeImage(f, client.getOutputStream(), Extension.getExtension(mySrc));
            } else if (mySrc.exists() && (ext.endsWith(".n"))) {

                if (head) {
                    HEAD.get(out, mySrc);
                }

                roxtt.js.Nashorn n = new roxtt.js.Nashorn();
                n.eval(mySrc, out,s);
               // while(true){

               // out.println("HOLA");
            } else if (mySrc.exists() && (ext.endsWith(".live"))) {

                if (head) {
                    HEAD.get(out, mySrc);
                }

                mySrc = new File("C:\\Users\\JesúsAlberto\\Pictures\\Roxette\\m.png");

                String[] f = {"botonfacebook.png", "botontwitter.png", "botonfacebook.png", "botontwitter.png", "botonfacebook.png", "botontwitter.png", "botonfacebook.png", "botontwitter.png", "botonfacebook.png", "botontwitter.png", "botonfacebook.png", "botontwitter.png", "botonfacebook.png", "botontwitter.png", "botonfacebook.png", "botontwitter.png"};
                out.println("<!DOCTYPE html>");
                out.println("<head>");
                out.println("<script>");
                out.println("function live(){");
                for (int i = 0; i < f.length; i++) {
                    out.println("   document.getElementById(\"live\").innerHTML =\"<img src='/Aquiez/imagenes/" + f[i].toString() + "'/>\";");
                }
                out.println("}");
                out.println("</script>");
                out.println("</head>");
                out.println("<body onload='live()'>");

                out.println("<div id='live'></div>");

                out.println("</body>");
                out.println("</html>");

            } else if (mySrc.exists() && (ext.endsWith(".sou"))) {

            } else {

                /*
                out.println("HTTP/1.0 400 ok");
                BufferedReader srcError = new BufferedReader(new FileReader(new File("./htdocs/error.html")));

                String line = "";

                do {
                    line = srcError.readLine();

                    if (line != null) {
                        // sleep(500);
                        out.println(line);
                    }
                } while (line != null);
                //out.close();
                        */

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void sendBytes(FileInputStream fis, OutputStream os, PrintWriter out)
            throws Exception {

        os.write("HTTP/1.1 200 OK\r\n".getBytes(Charset.forName("UTF-8")));
        os.write(("Date: " + new Date() + "").getBytes(Charset.forName("UTF-8")));
        os.write("Content-Type: image/png\r\n".getBytes(Charset.forName("UTF-8")));
        os.write("Cache-Control: max-age=1000\r\n".getBytes(Charset.forName("UTF-8")));
        os.write("\r\n".getBytes(Charset.forName("UTF-8")));
        
        byte[] buffer = new byte[1024];
        int bytes = 0;

        while (-1 != (bytes = fis.read(buffer))) {
            os.write(buffer, 0, bytes);
        }
    }
}
