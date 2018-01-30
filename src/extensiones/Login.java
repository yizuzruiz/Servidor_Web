/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extensiones;
import java.io.PrintWriter;
import java.sql.ResultSet;
import roxtt.fw.DB.Connect;
import roxtt.fw.DB.Row;
/**
 *
 * @author jarm1987
 */
public final class Login {
    private int id;
    private String nombre;
    private String email;
    private String contraseña;
    private Connect conexion;
    private String[] variables=new String[2];
    
    public Login(String email, String pass, Connect conexion,PrintWriter out){
    contraseña=pass;
   this.conexion=conexion;
   validar(email,out);
    }
    
    public void validar(String email,PrintWriter out){
    Row renglon=new Row("usuario");
    renglon.setConnection(this.conexion.getConnection());
    ResultSet r;
    String sql="SELECT * FROM "+renglon.getNombreTabla()+" WHERE email='"+email+"'";
       
        renglon.consultar(sql);
        int i=0; 
       
        try {
            
           while((r=renglon.obtenRenglon())!=null){
              
            this.id=r.getInt("id");
            this.nombre=r.getString("nombre");
            this.email=r.getString("email");
              
            if(this.contraseña.equals(r.getString("contraseña"))){
              //System.err.println("Contraseña Coincide");
              
              //PROCESO PARA SEGUIR ADELANTE//
              out.print("Bienvenido "+this.nombre);
              
                    getVariables()[0]=this.nombre;
                    getVariables()[1]=this.email;
              
              }else{
             // System.err.println("Contraseña Incorrecta!!");
              out.println("Contraseña Incorrecta!!");
              }
            i++;
           }
           
         if(i==0){
        // System.out.println("El usuario no existe");
         out.println("El usuario no existe");
         }
      
        } catch (Exception ex) {
            System.err.println(ex.fillInStackTrace());
            out.println(ex.fillInStackTrace());
        }
       
        }

    /**
     * @return the variables
     */
    public String[] getVariables() {
        return variables;
    }

    /**
     * @param variables the variables to set
     */
    public void setVariables(String[] variables) {
        this.variables = variables;
    }
        
        
        
        
        
    
    }