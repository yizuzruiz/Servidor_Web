/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roxtt.fw.DB.query;


import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import roxtt.fw.DB.Connect;
import roxtt.fw.DB.Row;

/**
 *
 * @author Jesús Alberto
 */
public class SimpleQuery {
    
    private Connect connection;
    private Row row;
      
    
    public SimpleQuery(Connect connection,Row row){
        
        this.connection = connection;
        this.row = row;
    
    }
    
    public void createTable(LinkedHashMap<String,Object> fields,String nameTable){
        String sql = "CREATE TABLE IF NOT EXISTS " + nameTable + "(";
        String field;
        String pk = "";
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            field = entry.getKey();
            if(detectPrimaryKey(entry.getKey())){
                sql += field + " " + detectType(String.valueOf(entry.getValue())) + " auto_increment,\n";
                pk = field;
            }
            else
                sql += field + " " + detectType(String.valueOf(entry.getValue())) + ",\n";
        }
        sql += "primary key("+pk+"));";
        row.actualiza(sql);
    }
    
    
    public void insertValues(LinkedHashMap<String,Object> fields,String nameTable){
        String sql = "INSERT INTO " + nameTable;
        String field = "(";
        String value = "";
        String renderSQL = "";
        System.err.println(fields.size());
        int len = 1;
        for (Map.Entry<String, Object> entry : fields.entrySet()){
            if(len==fields.size()){
                field += entry.getKey() + ") VALUES (";
                if(entry.getValue().getClass().equals(String.class) || entry.getValue().getClass().equals(Date.class))
                    value += "'"+entry.getValue() + "');";
                else
                    value += entry.getValue() + ");";
            }
            else{
                field += entry.getKey() + ",";
                if(entry.getValue().getClass().equals(String.class) || entry.getValue().getClass().equals(Date.class))
                    value += "'"+entry.getValue() + "', ";
                else
                    value += entry.getValue() + ", ";
            }
            
            len++;
        }
        renderSQL=sql+" "+field+value;
        row.actualiza(renderSQL);
    }
    
    public LinkedHashMap<String,Object> selectValuesWhere(LinkedHashMap<String,Object> fields,String nameTable,String where){
        LinkedHashMap<String,Object> columns = new LinkedHashMap<String,Object>();
        row.setConnection(connection.getConnection());
        ResultSet result;
        String sql="SELECT * FROM "+row.getNombreTabla()+" WHERE "+where+";";
        //String k = "";
        row.consultar(sql);
        
        try{
            while((result = row.obtenRenglon()) != null){
                for(Map.Entry<String, Object> entry : fields.entrySet()){
                    columns.put(entry.getKey(), result.getObject(entry.getKey()));
                    //k = entry.getKey();
                    //System.out.println("  "+columns.get(k));
                } 
            }
        }catch(Exception e){
        
        }
    
        return columns;   

    }
    
    public void updateValue(LinkedHashMap<String,Object> fields, String where){
        String values = " ";
        int len = 1;
        for(Map.Entry<String, Object> entry : fields.entrySet()){
            if(len==fields.size()){
                if(entry.getKey().getClass().equals(String.class) || entry.getKey().getClass().equals(Date.class))
                    values += entry.getKey() + "='" +entry.getValue().toString()+"' ";
                else
                    values += entry.getKey() + "=" +entry.getValue().toString()+" ";
            }else{
                if(entry.getKey().getClass().equals(String.class) || entry.getKey().getClass().equals(Date.class))
                    values += entry.getKey() + "='" +entry.getValue().toString()+"', ";
                else
                    values += entry.getKey() + "=" +entry.getValue().toString()+", ";
            }
        }
        
        String sql = "UPDATE " + row.getNombreTabla() + "SET " + values + "WHERE "+where ;
        System.out.println(sql);
    
    
    }
    
    public void updateValue(Object o){
        String values = " ";
        int len = 1;
        Field[] fields = o.getClass().getFields();
        int id = 0;
        for(Field field : fields){
            
            try {
                Object value = field.get(o);
                if(len==fields.length){
                    if(field.getName().equals("id")){
                            id = Integer.parseInt(value.toString());
                            System.err.println("Valor de Id "+value.toString());
                        }
                    if(field.getType().equals(String.class) || field.getType().equals(Date.class) ){
                        values += field.getName() + " = '" + value + "' ";
                        
                    }else{
                        values += field.getName() + " = " + value + " ";
                    }
                }else{
                    if(field.getName().equals("id")){
                            id = Integer.parseInt(value.toString());
                            System.err.println("Valor de Id "+value.toString());
                        }
                    if(field.getType().equals(String.class) || field.getType().equals(Date.class) ){
                        values += field.getName() + " = '" + value + "', ";
                    }else{
                        values += field.getName() + " = " + value + ", ";
                    }
                }
            } catch (IllegalArgumentException ex) {            
                Logger.getLogger(SimpleQuery.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SimpleQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
            len++;
        }
        
        String sql = "UPDATE " + row.getNombreTabla() + " SET " + values + "WHERE id= "+id +";";
        System.err.println(sql);
        int a = row.actualiza(sql);
        System.out.println(a);
    }
    
    public void eliminate(String where){
        String sql = "DELETE FROM " + row.getNombreTabla() + " WHERE " + where +"; ";
        int a = row.actualiza(sql);
    
    }
    
    
    public int existTable(String nameTable){
        return row.actualiza("SHOW TABLES LIKE \"puntodeventa.inventario\"");
    }
    
    
    private String detectType(String value){
        String type="";
      //  System.out.println(value.toUpperCase());
            switch(Types.getFunction(value.toUpperCase())){
            
                case STRING :
                    type = "VARCHAR (100)";
                    break;
                    
                case INT :
                    type = "INT (255)";
                    break;
                    
                case DOUBLE :
                    type = "DECIMAL (65,2)";
                    break;
            }
    
        return type;
    }
    
    private boolean detectPrimaryKey(String value){
        boolean pk = false;
        
        if(value.startsWith("id"))
            pk=true;
        
        return pk;
    }
    
    private boolean detectForeignKey(){
        return true;
    }
    
   
    
}
