/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.parser.generator;

import java.util.ArrayList;
import java.util.List;
import tr.com.bites.poc.documentparser.document.TargetDocument;
import tr.com.bites.poc.documentparser.document.TempDocument;
import tr.com.bites.poc.documentparser.element.ParsedElement;

/**
 *
 * @author fatihs
 */
public abstract class AbstractGenerator<TYPE_OF_TEMP_DOCUMENT extends TempDocument,TYPE_OF_TARGET_DOCUMENT extends TargetDocument> {

    private List<ParsedElement> pasedElementList = null;
    private List<String> errorList = null;

    
    public abstract void start();

    public abstract Class<TYPE_OF_TEMP_DOCUMENT> getTempDocumentType();
    public abstract Class<TYPE_OF_TARGET_DOCUMENT> getTargetDocumentType();
    
    public List<String> getErrorList() {
        return errorList;
    }
    
    public List<ParsedElement> getPasedElementList() {
        return pasedElementList;
    }

}
