/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.util;

import tr.com.bites.poc.documentparser.parser.AbstractDocumentParser;

/**
 *
 * @author fatihs
 */
public class DocumentElementFactory {
    private AbstractDocumentParser parser;
    
    

    public AbstractDocumentParser getParser() {
        return parser;
    }

    public void setParser(AbstractDocumentParser parser) {
        this.parser = parser;
    }

   
    
}
