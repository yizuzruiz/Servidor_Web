/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.fw.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author j_ruiz_m
 */
public class Connect {

    private Connection conexion;
    private String url;
    private String usuario;
    private String contraseña;

    public Connect(String url, String usuario, String contraseña) {
        this.usuario = usuario;
        this.url = url;
        this.contraseña = contraseña;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
            conexion = DriverManager.getConnection(this.url, this.usuario, this.contraseña);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public Connection getConnection() {
        return conexion;
    }

    public void close() {

        try {
            conexion.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
        }

    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }
}
