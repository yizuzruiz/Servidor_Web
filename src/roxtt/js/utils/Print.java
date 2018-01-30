/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roxtt.js.utils;

import java.io.PrintWriter;
import java.util.TimerTask;

/**
 *
 * @author JesúsAlberto
 */
public class Print extends TimerTask {
 PrintWriter out=null;
 int cont=0;
    public Print(PrintWriter out){
    this.out=out;
    }
  
                @Override
		public void run() {
//			if(!out.equals(null))
//                            System.err.println("nulo");
			this.out.println("Hola!!"+String.valueOf(cont));
                        //System.out.println("Hola!!"+String.valueOf(cont));
                        cont++;
                       // out.close();
                        //System.out.println("---");
			// aqui se puede escribir el codigo de la tarea que necesitamos ejecutar
		}// end run()
                
	}
