/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roxtt.fw.MVC.controller;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import roxtt.fw.DB.Connect;
import roxtt.fw.DB.Row;
import roxtt.fw.MVC.models.Inventario;
import roxtt.fw.Model;

/**
 *
 * @author JesúsAlberto
 */
public class Controller {

    public Controller() {}
    
    

    public String consultar_articulo(String clave, int opcion) {
        
        String consulta = "";
        Inventario inv = new Inventario();
        Model selectArticulo = new Model(inv);
        
        LinkedHashMap<String,Object> values = selectArticulo.get("codigo='"+clave+"'");
        
        inv.codigo = (String) values.get("codigo");
        inv.articulo = (String) values.get("articulo");
        inv.descripcion = (String) values.get("descripcion");
        inv.unidad = (String) values.get("unidad");
        inv.cantidad = Integer.parseInt(String.valueOf(values.get("cantidad")));
        inv.precio = Double.parseDouble(String.valueOf(values.get("precio")));

        //String m="javascript:abrir('agregarinventario.html?"+resultado.getString("codigo")+"')";
        switch (opcion) {
            case 1:
                consulta += "<tr>";
                consulta += "<td>" + "<input id='codigo' type='text' value='" + inv.codigo + "'></td>";
                consulta += "<td>" + inv.articulo + "</td>";
                consulta += "<td>" + inv.descripcion + "</td>";
                consulta += "<td>" + inv.unidad + "</td>";
                consulta += "<td>" + inv.cantidad + "</td>";
                consulta += "<td>" + inv.precio + "</td>";
                consulta += "</tr>";
                break;

            case 2:
                consulta += "<tr>";
                consulta += "<td>" + inv.codigo + "</td>";
                consulta += "<td>" + inv.articulo + "</td>";
                consulta += "<td>" + inv.descripcion + "</td>";
                consulta += "<td>" + inv.unidad + "</td>";
                consulta += "<td>" + inv.cantidad + "</td>";
                consulta += "<td>" + inv.precio + "</td>";
                consulta += "<td>" + "<a href=" + "javascript:abrir('agregarinventario.html?codigo=" + inv.codigo + "','container','GET')" + ">Agregar</a></td>";
                consulta += "<td>" + "<a href=" + "javascript:abrir('eliminarinventario.html?codigo=" + inv.codigo + "','container','GET')" + ">Eliminar</a></td>";
                consulta += "</tr>";
                break;

        }
        
        return consulta;

    }

    public void operacion_inventario(Connect con, PrintWriter out, String clave, int cantidad, String operacion) {
        
        Inventario inv = new Inventario();
        Model selectArticulo = new Model(inv);
        LinkedHashMap<String,Object> values = selectArticulo.get("codigo='"+clave+"'");
        
        inv.id = (Integer) values.get("id");
        inv.codigo = (String) values.get("codigo");
        inv.articulo = (String) values.get("articulo");
        inv.descripcion = (String) values.get("descripcion");
        inv.unidad = (String) values.get("unidad");
        
        if(operacion.equals("+"))
            inv.cantidad = Integer.parseInt(String.valueOf(values.get("cantidad"))) + cantidad;
        else
            inv.cantidad = Integer.parseInt(String.valueOf(values.get("cantidad"))) - cantidad;
            
        inv.precio = Double.parseDouble(String.valueOf(values.get("precio")));
        
        selectArticulo.update();
        
    }

    public void agregar_articulo(Connect con, java.util.Hashtable s) {
        Inventario inv = new Inventario();
        inv.codigo = s.get("codigo").toString();
        inv.articulo = s.get("articulo").toString();
        inv.descripcion = s.get("descripcion").toString();
        inv.unidad = s.get("unidad").toString();
        inv.cantidad = Integer.parseInt(s.get("cantidad").toString());
        inv.precio = Double.parseDouble(s.get("precio").toString());
        inv.imagen = "";
        new Model(inv).save();

    }

    public String consultar_articulo_venta(Connect con, PrintWriter out, String clave) {
        
        String consulta = "";
        Inventario inv = new Inventario();
        Model selectArticulo = new Model(inv);
        
        LinkedHashMap<String,Object> values = selectArticulo.get("codigo='"+clave+"'");
        
        inv.codigo = (String) values.get("codigo");
        inv.articulo = (String) values.get("articulo");
        inv.descripcion = (String) values.get("descripcion");
        inv.unidad = (String) values.get("unidad");
        inv.cantidad = Integer.parseInt(String.valueOf(values.get("cantidad")));
        inv.precio = Double.parseDouble(String.valueOf(values.get("precio")));
        inv.imagen = (String) values.get("imagen");

        //String m="javascript:abrir('agregarinventario.html?"+resultado.getString("codigo")+"')";
      
                consulta += "<tr>";
                consulta += "<td>"  + "<input id='fg1' readonly='true' type='text' value='"  + inv.codigo + "'/></td>";
                consulta += "<td>" + "<input id='fg2' readonly='true' type='text' value='" + inv.articulo + "'/></td>";
                consulta += "<td>" + "<input id='fg3' readonly='true' type='text' value='" + inv.cantidad + "'/></td>";
                consulta += "<td>" + "<input id='fg4' readonly='true' type='text' value='" + inv.precio + "'/></td>";
                consulta += "<td>" + "<input onkeypress='' id='sub' readonly='true' type='text' size='10' maxlength='10' />" + "</td>";
                consulta += "<td><img src='" + inv.imagen + "' width='100' height='100'/></td>";
                consulta += "</tr>";
              
        
        return consulta;
        
    }

    public void imagen_articulo_venta(Connect con, PrintWriter out, String clave) {
        Row renglon = new Row("inventario");
        renglon.setConnection(con.getConnection());

        ResultSet resultado;

        String sql = "SELECT imagen FROM " + renglon.getNombreTabla() + " WHERE codigo='" + clave + "'";

        renglon.consultar(sql);

        try {

            while ((resultado = renglon.obtenRenglon()) != null) {

                out.println("<img src='" + resultado.getString("imagen") + "' alt='' width='100' height='100' />");
            }
        } catch (Exception e) {

        }

    }

    public void venta(Connect con, PrintWriter out, java.util.Hashtable s) {

        Row renglon = new Row("inventario");
        renglon.setConnection(con.getConnection());

        ResultSet resultado = null;
        String[] codigo = new String[s.size()];
        int[] cantidad = new int[s.size()];
        String[] articulo = new String[s.size()];
        Double[] precio = new Double[s.size()];
        String aux;
        int cant = 0;
        int suma;
        Double total = 0.0;

        for (int i = 1; i <= Integer.parseInt(s.get("lenght").toString()); i++) {
            String j = "codigo" + String.valueOf(i);

            codigo[i] = s.get(j).toString();
            cantidad[i] = Integer.parseInt(s.get("cantidad" + String.valueOf(i)).toString());
            articulo[i] = s.get("articulo" + String.valueOf(i)).toString();
            precio[i] = Double.valueOf(s.get("precio" + String.valueOf(i)).toString());
            aux = codigo[i];

            String query = "SELECT * FROM " + renglon.getNombreTabla() + " WHERE codigo= " + aux;
            renglon.consultar(query);

            try {

                while ((resultado = renglon.obtenRenglon()) != null) {

                    cant = resultado.getInt("cantidad");

                }

            } catch (Exception e) {

            }

            suma = cant - cantidad[i];

            String query2 = "UPDATE " + renglon.getNombreTabla() + " SET cantidad= " + suma + " WHERE codigo= '" + codigo[i] + "'";
            renglon.actualiza(query2);

        }

        for (int i = 1; i <= Integer.parseInt(s.get("lenght").toString()); i++) {
            Double auxd = 0.0;
            try {
                out.println("<tr>");
                out.println("<td>" + "<input id='codigov" + String.valueOf(i) + "' readonly='true' type='text' value='" + codigo[i] + "' size='20' maxlength='20'  ></td>");
                out.println("<td>" + "<input id='articulov" + String.valueOf(i) + "' readonly='true' type='text' value='" + articulo[i].replace("%20", " ") + "' size='20' maxlength='20'  ></td>");
                out.println("<td>" + "<input id='cantidadv" + String.valueOf(i) + "' readonly='true' type='text' value='" + String.valueOf(cantidad[i]) + "' size='20' maxlength='20'  ></td>");
                out.println("<td>" + "<input id='preciov" + String.valueOf(i) + "' readonly='true' type='text' value='" + String.valueOf(precio[i]) + "' size='20' maxlength='20'  ></td>");
                out.println("<td>" + "<input id='importev" + String.valueOf(i) + "' readonly='true' type='text' value='" + String.valueOf(precio[i] * cantidad[i]) + "' size='20' maxlength='20'  ></td>");
                out.println("</tr>");
                auxd = precio[i] * cantidad[i];
                total = total + auxd;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        out.println("<tr>");
        out.println("<td></td>");
        out.println("<td></td>");
        out.println("<td></td>");
        out.println("<td>Total:</td>");
        out.println("<td>" + "<input id='totalventa'" + "readonly='true' type='text' value='" + (total + (total * .16)) + "' size='20' maxlength='20'  ></td>");
        out.println("</tr>");

    }

    public void eliminar_articulo(Connect con, java.util.Hashtable s) {

        
        String codigo = s.get("codigo").toString();

        Inventario inv = new Inventario();
        Model selectArticulo = new Model(inv);
        LinkedHashMap<String,Object> values = selectArticulo.get("codigo='"+codigo+"'");
        
        inv.codigo = (String) values.get("codigo");
        
        selectArticulo.delete("codigo='"+inv.codigo+"'");

    }

    public void agregar_cliente(Connect con, java.util.Hashtable s) {
        ResultSet r;
        Row renglon = new Row("clientes");
        renglon.setConnection(con.getConnection());

        String cliente = s.get("cliente").toString();
        String direccion = s.get("direccion").toString();
        String codigopostal = s.get("codigopostal").toString();
        String rfc = s.get("rfc").toString();
        String telefono = s.get("telefono").toString();

        String sql = "Insert into " + renglon.getNombreTabla() + " (nombreCliente,direccion,codigoPostal,rfc,telefono) values ('" + cliente + "','" + direccion + "','" + codigopostal + "','" + rfc + "','"
                + telefono + "')";

        //System.out.println(sql);
        renglon.actualiza(sql);

        String query = "SELECT * FROM " + renglon.getNombreTabla() + " WHERE nombreCliente= '" + cliente + "'";

        renglon.consultar(query);

        int numero_cliente = 0;

        try {
            while ((r = renglon.obtenRenglon()) != null) {
                numero_cliente = r.getInt("numCliente");
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        String insertar_adeudo = "Insert into credito (numCliente, adeudo) values (" + numero_cliente + "," + 0.0 + ");";
        renglon.actualiza(insertar_adeudo);

    }

    public void consultar_cliente(Connect conn, PrintWriter out, String clave) {

        Row renglon = new Row("clientes");
        renglon.setConnection(conn.getConnection());

        ResultSet resultado;

        String sql = "SELECT * FROM " + renglon.getNombreTabla() + " WHERE numCliente='" + clave + "'";

        renglon.consultar(sql);

        try {
            while ((resultado = renglon.obtenRenglon()) != null) {
                //String m="javascript:abrir('agregarinventario.html?"+resultado.getString("codigo")+"')";

                out.println("<tr>");
                out.println("<td>" + "<input id='numcliente' type='text' value='" + resultado.getString("numCliente") + "'></td>");
                out.println("<td>" + "<input id='nombrecliente' type='text' value='" + resultado.getString("nombreCliente") + "'></td>");
                out.println("<td>" + "<input id='direccion' type='text' value='" + resultado.getString("direccion") + "'></td>");
                out.println("<td>" + "<input id='codigopostal' type='text' value='" + resultado.getString("codigoPostal") + "'></td>");
                out.println("<td>" + "<input id='rfc' type='text' value='" + resultado.getString("rfc") + "'></td>");
                out.println("<td>" + "<input id='telefono' type='text' value='" + resultado.getString("telefono") + "'></td>");

            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql2 = "SELECT adeudo FROM credito WHERE numCliente='" + clave + "'";
        renglon.consultar(sql2);

        try {
            while ((resultado = renglon.obtenRenglon()) != null) {
                //String m="javascript:abrir('agregarinventario.html?"+resultado.getString("codigo")+"')";

                out.println("<td>" + "<input id='adeudo' type='text' value='" + resultado.getString("adeudo") + "'></td>");
                out.println("</tr>");

            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
