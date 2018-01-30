/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.objects;

import java.io.File;

/**
 *
 * @author jarm1987
 *
 * Simple Class for get Extension of a file
 */
public class Extension {

    public static String getExtension(File file) {
        String ext = "";

        String s = file.getName();

        int pos = s.lastIndexOf('.');

        if (pos > 0 && pos < s.length() - 1) {
            ext = s.substring(pos + 1).toLowerCase();
        }
        return ext;
    }

}
