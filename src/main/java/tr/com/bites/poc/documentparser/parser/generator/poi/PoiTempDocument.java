/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.parser.generator.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import tr.com.bites.poc.documentparser.document.TempDocument;

/**
 *
 * @author fatihs
 */
public class PoiTempDocument extends TempDocument<XWPFDocument> {

    public PoiTempDocument(String path) {
        super(path);
    }

    @Override
    public void load() {
    }

    @Override
    public void initDocument() {
        try {

            this.generatorDocumentType = new XWPFDocument(new FileInputStream(this));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PoiTempDocument.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(PoiTempDocument.class.getName()).log(Level.SEVERE, null, ex);
            
        }

    }

}
