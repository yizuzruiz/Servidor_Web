/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt;

import java.io.PrintWriter;
import java.util.Hashtable;
import roxtt.fw.DB.Connect;
import roxtt.fw.DB.conf.Conf;
import roxtt.fw.MVC.controller.Controller;
import roxtt.live.Live;
import roxtt.utils.Function;

/**
 *
 * @author j_ruiz_m
 */
public class ToDo {

    String line = "";
    PrintWriter out;
    Hashtable s;
  Connect con = null;
    String parameter="";
    
    public ToDo(PrintWriter out, Hashtable s) {

        this.out = out;
        this.s = s;
    }

    public void getLine(String line) {
        this.line = line;
        Controller consulta = new Controller();
        switch (Function.getFunction(extraction(this.line))) {
            
            //////////////////////////////////////////
            /////////TTTTT       DDDD        /////////
            ////////   T         D   D       /////////
            ////////   T   ooooo D   D ooooo /////////
            ////////   T   o   o D   D o   o /////////
            ////////   T   ooooo DDDDD ooooo /////////
            //////////////////////////////////////////
            
            case conectar:
                String dir = Conf.DATA_BASE;
                String admin = Conf.ADMIN;
                String pass = Conf.PASSWORD;
                con = new Connect(dir, admin, pass);
                break;

            case consultar_articulo:
                out.println(consulta.consultar_articulo(this.s.get("codigo").toString(), Integer.parseInt(parameter)));
                
                break;

            case operacion_inventario:
                consulta.operacion_inventario(con, out, s.get("codigo").toString(), Integer.parseInt(s.get("cantidad").toString()), parameter);
                
                break;

            case consultar_articulo_venta:
                out.println(consulta.consultar_articulo_venta(con, out, s.get("campo").toString()));
               
                break;

            case imagen_articulo_venta:
                consulta.imagen_articulo_venta(con, out, s.get("campo").toString());
                
                break;

            case venta:
                consulta.venta(con, out, s);
                
                break;

            case agregar_articulo:
                consulta.agregar_articulo(con, s);
                
                break;

            case eliminar_articulo:
                consulta.eliminar_articulo(con, s);
                
                break;

            case agregar_cliente:
                consulta.agregar_cliente(con, s);
                
                break;

            case consultar_cliente:
                String numcliente = s.get("codigo").toString();
                consulta.consultar_cliente(con, out, numcliente);
               
                break;
                
            case live:
                new Live(out,s.get("mensaje").toString());

            case select:
                //TODO

                break;

            default:
                out.print(this.line);
                out.print("\n");
        }
      
    }

    
    
    private String extraction(String line) {
        
        if (line.startsWith("<?xp") && line.endsWith("?>")) {
            String[] matc = line.split(" ");
            parameter = matc[2];
            return matc[1];
            
        } else {
            return line;
        }
        
    }

}
