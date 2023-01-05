/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.parser;

import tr.com.bites.poc.documentparser.annotation.ParserService;
import tr.com.bites.poc.documentparser.document.TempDocument;
import tr.com.bites.poc.documentparser.parser.generator.poi.ApachePoiWordGenerator;

/**
 *
 * @author fatihs
 */
@ParserService(fileExtention = {"doc", "docx"}, parserGroup = "WORD", generator = ApachePoiWordGenerator.class)
public class WorldParser extends AbstractDocumentParser {

    

    @Override
    public TempDocument parseDocument() {
        if (this.generator != null) {
            generator.start();
        } else {
            System.out.println("tr.com.bites.poc.documentparser.parser.WorldParser.parseDocument() " + null);
        }

        if (tempDocument == null) {
            this.notifyOnParseSucces(this.tempDocument);
            return null;
        }
        return tempDocument;
    }
}
