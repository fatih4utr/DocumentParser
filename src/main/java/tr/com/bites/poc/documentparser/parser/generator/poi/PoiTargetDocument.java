/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.parser.generator.poi;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import tr.com.bites.poc.documentparser.document.TargetDocument;
import tr.com.bites.poc.documentparser.document.TempDocument;

/**
 *
 * @author fatihs
 */
public class PoiTargetDocument extends TargetDocument<XWPFDocument> {

    public PoiTargetDocument(String path) {
        super(path);
    }
    
    @Override
    public void save() {
    }
    
    
}
