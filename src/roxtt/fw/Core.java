/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roxtt.fw;


import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import roxtt.fw.DB.Connect;
import roxtt.fw.DB.Row;
import roxtt.fw.DB.conf.Conf;
import roxtt.fw.DB.query.SimpleQuery;

/**
 *
 * @author Jesús Alberto
 */
public class Core {
    
    protected Class c;
    protected Connect con;
    protected Object o;
    protected  LinkedHashMap<String,Object> valuesColumn = new LinkedHashMap<String,Object>();
    protected  SimpleQuery sq = null;

    
    public Core(Object o){
        
        this.c = o.getClass();
        this.o = o;
        
        try {
            //printFields(this.c.getFields());
            connectDB(Conf.DATA_BASE, Conf.ADMIN, Conf.PASSWORD);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
    }
    
    public Core(){
    
    }
    
   
    
    
    
    public void save(){
        sq.insertValues(valuesColumn, c.getSimpleName());
        con.close();
    }
    
    public LinkedHashMap<String,Object> get(String where){
       return sq.selectValuesWhere(valuesColumn, c.getSimpleName(), where);
     //  con.close();
    }
    
    /*
    public void update(String where){
        sq.updateValue(this.o, where);
    }
    */
    public void update(){
        sq.updateValue(this.o);
    }
    
    public void delete(String where){
        sq.eliminate(where);
    }
    
    
    
    private void connectDB(String dataBase, String admin, String password) throws IllegalArgumentException, IllegalAccessException{
        con = new Connect(dataBase, admin, password);
        Row row = new Row(this.c.getSimpleName());
        row.setConnection(this.con.getConnection());
        sq = new SimpleQuery(this.con,row);
        
       //if(sq.existTable(row.getNombreTabla())==1)
        sq.createTable(getFields(this.c.getFields()), row.getNombreTabla());
        
    }

    private LinkedHashMap<String,Object> getFields(Field[] fields) throws IllegalArgumentException, IllegalAccessException{
        LinkedHashMap<String,Object> columns = new LinkedHashMap<String,Object>();
        for(Field field : fields){
            columns.put(field.getName(), field.getType().getSimpleName());
            field.setAccessible(true);
            Object value = field.get(this.o);
            valuesColumn.put(field.getName(), value);
            //System.out.println(value);
        }
        return columns;
    }
    
    private void printFields(Field[] fields){
        for(Field field : fields)
            System.out.println(field.getName() + " = " + field.getType().getSimpleName());
    }
    
    
}
