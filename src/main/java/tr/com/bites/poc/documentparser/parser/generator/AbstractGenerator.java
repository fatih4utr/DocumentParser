/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.parser.generator;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import tr.com.bites.poc.documentparser.document.TargetDocument;
import tr.com.bites.poc.documentparser.document.TempDocument;
import tr.com.bites.poc.documentparser.element.ParsedElement;

/**
 *
 * @author fatihs
 */
public abstract class AbstractGenerator<TYPE_OF_TEMP_DOCUMENT extends TempDocument, TYPE_OF_TARGET_DOCUMENT extends TargetDocument> {

    
    private List<String> errorList = null;

    private InputStream inStream = null;
    private OutputStream outStream = null;

    protected TYPE_OF_TARGET_DOCUMENT targetDocument;
    protected TYPE_OF_TEMP_DOCUMENT tempDocument;

    public abstract void start();

    public void setTempDocument(TYPE_OF_TEMP_DOCUMENT tempDocument) {
        this.tempDocument = tempDocument;
    }

    public void setTargetDocument(TYPE_OF_TARGET_DOCUMENT targetDocument) {
        this.targetDocument = targetDocument;
    }

    public TYPE_OF_TEMP_DOCUMENT getTempDocument() {
        return tempDocument;
    }

    public TYPE_OF_TARGET_DOCUMENT getTargetDocument() {
        return targetDocument;
    }

    

    public abstract Class<TYPE_OF_TEMP_DOCUMENT> getTempDocumentType();

    public abstract Class<TYPE_OF_TARGET_DOCUMENT> getTargetDocumentType();

    public List<String> getErrorList() {
        return errorList;
    }

    

}
