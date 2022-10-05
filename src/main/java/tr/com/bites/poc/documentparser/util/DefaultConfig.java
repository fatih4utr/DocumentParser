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

@ConfigService.SearchPathConfig(parserSearchPaths = ("tr.com.bites.poc.documentparser.parser"))
@ConfigService.Parser(activeParsers = "WORD")
public class DefaultConfig {
   public enum CONFIGS {
       PARSER_SEARCH_PATHS,
       ACTIVE_PARSERS
   }
   
}
