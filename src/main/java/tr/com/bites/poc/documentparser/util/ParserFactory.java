/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import tr.com.bites.poc.documentparser.annotation.ParserService;
import tr.com.bites.poc.documentparser.parser.AbstractDocumentParser;

/**
 *
 * @author fatihs
 */
public class ParserFactory {

    private static ParserFactory def = null;

    private ParserFactory() {
    }

    public static synchronized ParserFactory getDefault() {
        if (def == null) {
            def = new ParserFactory();
        }
        return def;
    }

    public AbstractDocumentParser selectParser(File tempFile, HashMap<String, AbstractDocumentParser> activeParsers) {
        if (! tempFile.exists()) {
            // TODO error;
            return null;

        }
        if (tempFile.isDirectory()) {
            // TODO error;
            return null;
        }
        for (Map.Entry<String, AbstractDocumentParser> entry : activeParsers.entrySet()) {
            String key = entry.getKey();
            AbstractDocumentParser parser = entry.getValue();
            if(checkParserSupportedFileExtention(tempFile, parser)){
                parser.setTempFile(tempFile);
                
                return parser;
            }
        }
        
        
        return null;
    }

    private boolean checkParserSupportedFileExtention(File tempFile, AbstractDocumentParser parser) {

        ParserService annotation = parser.getClass().getAnnotation(ParserService.class);
        

        String[] fileExtentions = annotation.fileExtention();
        String tempfileExtention = tempFile.getAbsolutePath().substring(tempFile.getAbsolutePath().lastIndexOf(".")).
                substring(1);
        
        for (String fileExtention : fileExtentions) {
            if(fileExtention.equals(tempfileExtention)){
                return true;
            }
        }
        return false;
    }

    public AbstractDocumentParser selectParser(String tempFilePath, HashMap<String, AbstractDocumentParser> activeParsers) {
        return selectParser(new File(tempFilePath), activeParsers);

    }

}
