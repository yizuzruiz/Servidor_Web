/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.utils;

/**
 *
 * @author j_ruiz_m
 */
public enum Function {

    conectar,
    consultar_articulo,
    operacion_inventario,
    consultar_articulo_venta,
    imagen_articulo_venta,
    venta,
    agregar_articulo,
    eliminar_articulo,
    agregar_cliente,
    consultar_cliente,
    select,
    live,
    No_Function;//

    /**
     * 
     * @param line Evaluate this line for find a function
     * @return The Function which it look for
     */
    
    
    public static Function getFunction(String line){
    
     try {
            return valueOf(line);
        }
        catch (IllegalArgumentException e) {
            return No_Function;
        }
    
    }
    
}
