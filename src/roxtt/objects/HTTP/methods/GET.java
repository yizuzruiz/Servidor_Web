/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.objects.HTTP.methods;

import java.util.Hashtable;

/**
 *
 * @author jarm1987
 *
 * Simple class request GET http header
 */
public class GET {

    /**
     *
     * get the variables of method GET of http header
     *
     * @param cadena
     * @return
     */
    public static Hashtable Query_String(String cadena) {
        String aux;
        java.util.Hashtable<String, String> varString = new java.util.Hashtable<String, String>();
        if (cadena.startsWith("&")) {
            aux = cadena.substring(1);
            // System.out.println("CADENA: "+aux);
        } else {
            aux = cadena;
        }
        String variables = aux.substring(aux.indexOf("?") + 1);
        java.util.StringTokenizer f = new java.util.StringTokenizer(variables, "&");

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

            varString.put(token1, token2);

        }
        return varString;

    }

}
