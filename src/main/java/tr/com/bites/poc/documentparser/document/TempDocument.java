/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.document;

import tr.com.bites.poc.documentparser.element.ParsedElement;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tr.com.bites.poc.documentparser.parser.AbstractDocumentParser;

/**
 *
 * @author fatihs
 */
public abstract class TempDocument<TYPEOFDOCUMENT> extends AbstractDocument<TYPEOFDOCUMENT> implements Loadable {

    private final AbstractDocumentParser parser = null;
    private final Map<String, ? extends ParsedElement> parsedElementMap = new HashMap<>();

    public TempDocument(String path) {
        super(path);
    }

}
