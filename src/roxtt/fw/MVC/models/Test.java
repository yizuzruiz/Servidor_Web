/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roxtt.fw.MVC.models;

import java.util.LinkedHashMap;
import roxtt.fw.Model;


/**
 *
 * @author Jesús Alberto
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       /* 
        Inventario inv = new Inventario();
      
        inv.articulo = "Roxette";
        inv.codigo = "123456";
        inv.unidad = "pza";
        inv.descripcion = "Look Sharp!";
        
        inv.cantidad = 170;
        inv.precio=156.00;
        new Model(inv).save(); 
       // Cliente cliente = new Cliente();
       // User user = new User();
        /*
        
        Inventario inv2 = new Inventario();
        
        Model selectInventario = new Model(inv2);
        
        LinkedHashMap<String,Object> values = selectInventario.get("codigo='123456'");
        
        inv2.articulo = (String) values.get("articulo");
        inv2.cantidad = Integer.parseInt(String.valueOf(values.get("cantidad")));
        
        System.out.println(inv2.articulo+" "+inv2.cantidad);
                */
        
        
        Inventario inv = new Inventario();
        Model selectArticulo = new Model(inv);
        LinkedHashMap<String,Object> values = selectArticulo.get("codigo='123456'");
        inv.codigo = (String) values.get("codigo");
        inv.articulo = (String) values.get("articulo");
        inv.descripcion = (String) values.get("descripcion");
        inv.unidad = (String) values.get("unidad");
        inv.cantidad = Integer.parseInt(String.valueOf(values.get("cantidad")))-10;
        inv.precio = Double.parseDouble(String.valueOf(values.get("precio")));
        
       
        //selectArticulo.update("codigo='123456'");
        
        
    }

   
    
}
