/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roxtt.fw;

/**
 *
 * @author JesúsAlberto
 */
public class Model extends Core {
    
    public Model(Object o){
        super(o);
    System.err.println(o.getClass().getPackage());
    }
    
    public Model(){
        super();
    }
    
}
