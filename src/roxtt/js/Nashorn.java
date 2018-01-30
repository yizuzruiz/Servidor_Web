/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roxtt.js;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import roxtt.js.objects.BODY;
import roxtt.js.objects.CONTENT;
import roxtt.js.objects.DIV;
import roxtt.js.objects.DOC;
import roxtt.js.objects.HEAD;
import roxtt.js.objects.HTML;
import roxtt.js.objects.IMG;
import roxtt.js.objects.LINK;
import roxtt.js.objects.META;
import roxtt.js.objects.SCRIPT;
import roxtt.js.objects.TITLE;
import roxtt.js.objects.UTIL;

/**
 *
 * @author j_ruiz_m
 */
public class Nashorn {
    
    File file = null;
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = null;
    PrintWriter out = null;
    
    public Nashorn(){
   
        this.engine = this.manager.getEngineByName("nashorn");
    
    }
    
    public void eval(File file, PrintWriter out,Hashtable s){
        
        this.out=out;
        Hashtable params = s;
        DOC doc = new DOC(this.out);
        HTML html = new HTML(this.out);
        HEAD head = new HEAD(this.out);
        META meta = new META(this.out);
        TITLE title = new TITLE(this.out);
        LINK link = new LINK(this.out);
        SCRIPT script = new SCRIPT(this.out);
        BODY body = new BODY(this.out);
        IMG img = new IMG(this.out);
        DIV div = new DIV(this.out);
        CONTENT content = new CONTENT(this.out);
        UTIL util = new UTIL(this.out);
        this.engine.put("params",params);
        this.engine.put("out", this.out);
        this.engine.put("doc", doc);
        this.engine.put("html", html);
        this.engine.put("head", head);
        this.engine.put("meta", meta);
        this.engine.put("title",title);
        this.engine.put("link", link);
        this.engine.put("script", script);
        this.engine.put("body", body);
        this.engine.put("div",div);
        this.engine.put("content", content);
        this.engine.put("util", util);
        this.engine.put("img", img);
        this.file = file;
        try {
            try {
                this.engine.eval(new java.io.FileReader(this.file));
                
            } catch (ScriptException ex) {
                Logger.getLogger(Nashorn.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Nashorn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
    }
    
}
