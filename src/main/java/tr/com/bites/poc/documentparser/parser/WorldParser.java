/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.parser;

import java.io.File;
import java.util.HashMap;
import tr.com.bites.poc.documentparser.type.DocumentElement;

/**
 *
 * @author fatihs
 */
public class WorldParser  extends AbstractDocumentParser{

    public WorldParser() {
    }
    
    
    
    
    @Override
    public boolean parseDocument() {
        HashMap<String , ? extends  DocumentElement> keyDocumentElementMap = new HashMap<>();
        
        
        this.notifyOnSucces(keyDocumentElementMap);
        return false;
    }

    
 
}
