/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.fw.DB;

import java.sql.ResultSet;

/**
 *
 * @author wero
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connect c=new Connect("jdbc:mysql://localhost/its-on","root","");
        ResultSet r;
        Row renglon=new Row("usuario");
        
        renglon.setConnection(c.getConnection());
        renglon.consultar("SELECT * FROM "+renglon.getNombreTabla()+"");
        try {
           while((r=renglon.obtenRenglon())!=null){
            System.out.println("ID: "+r.getInt("id"));
            System.out.println("Nombre: "+r.getString("nombre"));
            System.out.println("Email: "+r.getString("email"));
            System.out.println("Contraseña: "+r.getString("contraseña"));
           }
      
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
 
      //  renglon.actualiza("INSERT INTO usuario (id,nombre,email,contraseña) values (NULL,'DELL','info@dell.com','xzxzx');");
    }
}
