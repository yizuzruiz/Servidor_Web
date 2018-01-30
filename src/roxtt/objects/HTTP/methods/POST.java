/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.objects.HTTP.methods;

import java.util.logging.Level;
import java.util.logging.Logger;
import roxtt.objects.HTTP.RequestHTTP;
/**
 *
 * @author jarm1987
 */
public class POST {

    public static java.util.Hashtable Query_String(java.io.BufferedReader in, java.util.StringTokenizer n) {

        java.util.Hashtable<String, String> varString = new java.util.Hashtable<String, String>();
        int contLength = -1;
        char[] variables = null;
        contLength = Integer.parseInt(n.nextToken());
        variables = new char[contLength];
        String fg = "";
   
        try {
            in.read(variables);
            fg = new String(variables);
           

            java.util.StringTokenizer f = new java.util.StringTokenizer(fg, "&");

            int length = f.countTokens();
            String[] arrVar = new String[length];
            for (int i = 0; i < length; i++) {
                arrVar[i] = f.nextToken().replace("+", " ");
            }

            java.util.StringTokenizer g = null;

            for (int i = 0; i < arrVar.length; i++) {
                g = new java.util.StringTokenizer(arrVar[i], "=");
                String token1 = g.nextToken();

                String token2 = g.nextToken();

              System.out.println(token1 + " = " + token2);

                varString.put(token1, token2);

            }

        } catch (Exception ex) {
            Logger.getLogger(RequestHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        return varString;
    }
}
