/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.util;

import tr.com.bites.poc.documentparser.annotation.ConfigService;

/**
 *
 * @author fatihs
 */
@ConfigService.SearchPathConfig(
        parserSearchPaths = ("tr.com.bites.poc.documentparser.parser"),
        documentElementsSearchPaths = ("tr.com.bites.poc.documentparser.element")
)
@ConfigService.Parser(activeParsers = "WORD")
public abstract class DefaultConfig {
    
}
