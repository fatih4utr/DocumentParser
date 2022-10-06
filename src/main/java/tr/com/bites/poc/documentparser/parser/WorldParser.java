/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.parser;

import java.io.File;
import java.util.HashMap;
import tr.com.bites.poc.documentparser.annotation.ParserService;
import tr.com.bites.poc.documentparser.element.DocumentElement;

/**
 *
 * @author fatihs
 */
@ParserService(fileExtention = {".doc",".docx"},parserGroup = "WORD")
public class WorldParser extends AbstractDocumentParser {

    public WorldParser() {
    }

    @Override
    public boolean parseDocument() {

        return false;
    }

}
