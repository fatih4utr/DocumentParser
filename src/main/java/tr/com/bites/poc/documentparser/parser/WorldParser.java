/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.parser;

import tr.com.bites.poc.documentparser.annotation.ParserService;

/**
 *
 * @author fatihs
 */
@ParserService(fileExtention = {"doc","docx"},parserGroup = "WORD")
public class WorldParser extends AbstractDocumentParser {

    public WorldParser() {
    }

    @Override
    public boolean parseDocument() {

        return false;
    }

}
