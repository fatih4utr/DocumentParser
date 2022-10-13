/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.document;

import java.io.File;

/**
 *
 * @author fatihs
 * @param <TYPE>
 */
public abstract class AbstractDocument<TYPE> extends File {

    private TYPE generatorDocumentType = null;

    public AbstractDocument(String path) {
        super(path);
        
    }

    public TYPE getGeneratorDocumentType() {
        return generatorDocumentType;
    }

    public void setGeneratorDocumentType(TYPE generatorDocumentType) {
        this.generatorDocumentType = generatorDocumentType;
    }
    

}
