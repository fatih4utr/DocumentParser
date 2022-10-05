/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import tr.com.bites.poc.documentparser.parser.AbstractDocumentParser;

/**
 *
 * @author fatihs
 */
public class ParserFactory {

    private static ParserFactory def = null;

    private ParserFactory() {
    }

    public synchronized ParserFactory getDefault() {
        if (def == null) {
            def = new ParserFactory();
        }
        return def;
    }

    public AbstractDocumentParser createParser(File tempFile) {
        if (! !tempFile.exists()) {
            // TODO error;
            return null;

        }
        if (tempFile.isDirectory()) {
            // TODO error;
            return null;
        }
        
        //TODO extentetion Check 
        //String tempFileExtention =  
        
        
        return null;
    }

    public AbstractDocumentParser createParser(String tempFilePath) {
        return createParser(new File(tempFilePath));
    }

}
