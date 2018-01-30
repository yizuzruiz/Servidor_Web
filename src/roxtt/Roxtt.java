/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt;

import roxtt.objects.HTTP.RequestHTTP;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import roxtt.CONF.Conf;

/**
 *
 * @author Jesús Alberto Ruiz Moreno
 */
public class Roxtt {

    int port = Conf.port;

    Roxtt() {
    }

    void run() throws IOException {
        System.out.println("Starting Server...");

        try {

            ServerSocket s = new ServerSocket(port);

            System.out.println("Servidor iniciado: " + s.getInetAddress().getHostAddress());

            while (true) {
                Socket client = s.accept();
                RequestHTTP request;
                request = new RequestHTTP(client);
                request.start();

            }

        } catch (BindException e) {
            System.out.println("Error: " + e.toString());
        }

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] array) throws IOException {
        Roxtt roxtt = new Roxtt();
        roxtt.run();
    }
}
