/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roxtt.fw.DB.query;

import roxtt.utils.Function;
import static roxtt.utils.Function.No_Function;
import static roxtt.utils.Function.valueOf;

/**
 *
 * @author JesúsAlberto
 */
public enum Types {
    
    STRING,INT,DOUBLE,FLOAT,DATE,NO_TYPES;
    
     public static Types getFunction(String value){
    
     try {
            return valueOf(value);
        }
        catch (IllegalArgumentException e) {
            return NO_TYPES;
        }
    
    }
    
    
}
