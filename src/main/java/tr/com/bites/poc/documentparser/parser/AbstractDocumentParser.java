/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.parser;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.com.bites.poc.documentparser.document.TargetDocument;
import tr.com.bites.poc.documentparser.document.TempDocument;
import tr.com.bites.poc.documentparser.element.AbstractDocumentElement;
import tr.com.bites.poc.documentparser.parser.generator.AbstractGenerator;

/**
 *
 * @author fatihs
 */
public abstract class AbstractDocumentParser {

    TargetDocument targetFile;
    List<DocumentParserListener> listeners = new ArrayList<>();
    HashMap<String, AbstractDocumentElement> elementKeyMap = new HashMap<>();
    TempDocument tempDocument = null;
    AbstractGenerator generator;
    Class<? extends AbstractGenerator> generatorClass;

    public void setGenerator(AbstractGenerator generator) {
        this.generator = generator;
    }

    public void setGeneratorClass(Class<? extends AbstractGenerator> generatorClass) {
        this.generatorClass = generatorClass;
    }

    public void setTempDocument(TempDocument tempDocument) {
        this.tempDocument = tempDocument;

    }

    public void setTempDocument(File tempDocumentFile) {
        if (this.generator == null) {
            try {
                this.generator = this.generatorClass.getConstructor().newInstance();
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(AbstractDocumentParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            this.tempDocument = (TempDocument) this.generator.getTempDocumentType().getDeclaredConstructor(String.class).newInstance(tempDocumentFile.getPath());
            this.tempDocument.initDocument();
            this.generator.setTempDocument(tempDocument);
            
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(AbstractDocumentParser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public TempDocument getTempDocument() {
        return tempDocument;
    }
    public AbstractDocumentElement getElementByKey(String key){
        if (!this.elementKeyMap.containsKey(key)) {
            return null;
        }
        return this.elementKeyMap.get(key);
    }
    public void addDocumentElement(AbstractDocumentElement element) {
        if (!elementKeyMap.containsKey(element.getDocumentKey())) {
            elementKeyMap.put(element.getDocumentKey(), element);
        }
        //TODO change;
    }

    public void setTargetFile(File targetFile) {

    }

    public void setTempFile(File tempFile) {
        this.setTempDocument(tempFile);
    }

    public abstract TempDocument parseDocument();

    public void addParserListener(DocumentParserListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(DocumentParserListener listener) {
        this.listeners.remove(listener);
    }

    protected void notifyOnParseSucces(TempDocument document) {
        for (DocumentParserListener listener : listeners) {
            listener.onParseSucces(document);
        }
    }

    protected void notifyOnError(String msg) {
        for (DocumentParserListener listener : listeners) {
            listener.onError(msg);
        }
    }

    public interface DocumentParserListener {

        void onError(String errorMsg);

        void onParseSucces(TempDocument document);
    }

}
