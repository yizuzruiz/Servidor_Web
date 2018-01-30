/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.objects.HTTP;

import extensiones.Login;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import roxtt.fw.DB.Connect;
import roxtt.live.Live;
import roxtt.objects.HTTP.methods.GET;
import roxtt.objects.HTTP.methods.POST;

/**
 *
 * @author jarm1987
 */
public class RequestHTTP extends Thread {

    private int contador = 0;
    private Socket scliente;
    private PrintWriter out;
    private Connect con;
    private java.util.Hashtable s;
    String txt="";

    public RequestHTTP(Socket ps) {

        contador++;
        scliente = ps;
        setPriority(NORM_PRIORITY - 1);
        
    }

    @Override
    public void run() {
        console("Request from " + scliente.getInetAddress());
        String m = "";
        BufferedReader in = null;
        String post="";
        String path_post="";
        
        try {

            in = new BufferedReader(new InputStreamReader(scliente.getInputStream()));

            java.io.BufferedWriter jh = new java.io.BufferedWriter(new java.io.OutputStreamWriter(scliente.getOutputStream()));

            out = new java.io.PrintWriter(jh, true);

            String cadena = "";
            int i = 0;

            do {
                cadena = in.readLine();

                if (i == 0) {

                    i++;
                    StringTokenizer st = new StringTokenizer(cadena);
                    String stMetodo = st.nextToken();
                    
                    System.err.println(stMetodo);

                    if ((st.countTokens() >= 2) && (stMetodo.equals("GET"))) {

                        String auxToken = st.nextToken();

                        if (auxToken.indexOf("?") > 0) {
                            s = GET.Query_String(auxToken);

                            String aux = auxToken.substring(1, auxToken.indexOf("?"));

                            ResponseHTTP.getResponse(aux, scliente, out, con, s, true);

                        } else {

                            ResponseHTTP.getResponse(auxToken, scliente, out, con, s, true);

                        }
                    } else if ((st.countTokens() >= 2) && stMetodo.equals("POST")) {
                        post = stMetodo;
                        String auxToken = st.nextToken();
                        path_post = auxToken;
                        

                    }  else if (cadena.startsWith("Live")) {
                       
                        while(true){
                        txt += cadena.substring(cadena.indexOf(" "));
                                
                        out.println(txt);
                        }

                    } 
                    
                    else {
                        out.println("400 Bad Request");
                    }

                }

                System.out.println(cadena);
                m += cadena + " ";

            } while (cadena != null && cadena.length() != 0);

        } catch (Exception e) {
            console("Error at Server, see the Exception: ");
            e.printStackTrace();
        }
        //out.println(m);
         StringTokenizer n = new StringTokenizer(m);


        while(n.hasMoreTokens()) {
            if (n.nextToken().equals("Content-Length:")) {
                s = POST.Query_String(in, n);
                ResponseHTTP.getResponse(path_post, scliente, out, con, s, true);
            }
        }

        out.close();
        //out.flush();
    }

    java.util.Hashtable variables(String cod, String val) {

        java.util.Hashtable<String, String> var = new java.util.Hashtable<String, String>();
        var.put(cod, val);
        return var;

    }

    void console(String message) {
        System.out.println(currentThread().toString() + " - " + message);
    }

    /**
     * @return the contador
     */
    public int getContador() {
        return contador;
    }
}
