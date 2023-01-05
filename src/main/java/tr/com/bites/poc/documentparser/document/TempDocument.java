/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.document;

import tr.com.bites.poc.documentparser.element.ParsedElement;
import java.util.HashMap;
import java.util.Map;
import tr.com.bites.poc.documentparser.parser.AbstractDocumentParser;

/**
 *
 * @author fatihs
 */
public abstract class TempDocument<TYPEOFDOCUMENT> extends AbstractDocument<TYPEOFDOCUMENT> implements Loadable {

    private final AbstractDocumentParser parser = null;
    //parsed element name object map;
    private final Map<String, ParsedElement> parsedElementMap = new HashMap<>();

    public abstract void initDocument();

    public void addParsedElement(ParsedElement parsedElement) {
        this.parsedElementMap.put(parsedElement.getElementAttributeByName("name").toString(), parsedElement);
    }
    
    public TempDocument(String path) {
        super(path);
    }

}
