/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tr.com.bites.poc.documentparser.type.DocumentElement;

/**
 *
 * @author fatihs
 */
public abstract  class AbstractDocumentParser {
    File tempFile; 
    File targetFile;
    List<DocumentParserListener > listeners = new ArrayList<>();
    
    public abstract boolean parseDocument();
    
    public void addParserListener(DocumentParserListener listener) {
        this.listeners.add(listener);
    }
    
    public void removeListener(DocumentParserListener listener) {
        this.listeners.remove(listener);
    }
    
    protected void notifyOnSucces(Map<String , ?  extends DocumentElement> keyTypeMap){
        for (DocumentParserListener listener : listeners){
            listener.onSuccess(keyTypeMap);
        }
    }
    
    protected  void notifyOnError(String msg) {
        for (DocumentParserListener listener : listeners) {
            listener.onError(msg);
        }
    }
    
    public interface DocumentParserListener {
        void onError(String errorMsg);
        void onSuccess(Map<String , ?  extends DocumentElement> keyTypeMap);
    }
}