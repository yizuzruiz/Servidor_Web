/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.fw.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author j_ruiz_m
 */
public class Row {
    
    private String nombreTabla;
    private Connection conexion;
    public Statement sentencia;
    private ResultSet respuesta;
    
    public Row(String nombreTabla){
    this.nombreTabla=nombreTabla;
    }
    
    public void setConnection(Connection conexion){
    this.conexion=conexion;
    }
    
    public void consultar(String sql){
    try {

            sentencia = conexion.createStatement();

            respuesta = sentencia.executeQuery(sql);
            
            
            
        } catch (SQLException e) {
            System.err.println(e.toString());
             }
  
    }
    
     public ResultSet obtenRenglon() throws Exception{
         ResultSet aux=null;
        try {
            if (respuesta.next()) {
                aux= respuesta;

            } else {

                respuesta.close();
                sentencia.close();
                
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return aux;
    }
     
     public int actualiza(String sql) {
         int r = 0;
        try {
            sentencia = conexion.createStatement();
            r = sentencia.executeUpdate(sql);
            //System.out.println(i);
            sentencia.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return r;
    }

    /**
     * @return the nombreTabla
     */
    public String getNombreTabla() {
        return nombreTabla;
    }
}
