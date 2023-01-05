/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.parser.generator.poi;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tr.com.bites.poc.documentparser.boot.BootStarter;
import tr.com.bites.poc.documentparser.element.AbstractDocumentElement;
import tr.com.bites.poc.documentparser.element.ParsedElement;
import tr.com.bites.poc.documentparser.util.convert.ConvertManager;

/**
 *
 * @author fatihs
 */
public class ParsedElementFactory {

    public static ParsedElement createParsedElement(String rawMarkupString) {
        //get group 2 az element type;    
        String regex = ".(?>#)(?<name>.*)(?>#).";
        Pattern pCompile = Pattern.compile(regex);
        Matcher matcher = pCompile.matcher(rawMarkupString);
        matcher.find();

        String elementKey = rawMarkupString.substring(matcher.start("name"), matcher.end("name"));
        AbstractDocumentElement documentElement = BootStarter.getCurrentParser().getElementByKey(elementKey);

        if (documentElement == null) {
            return null;
        }
        //(?<=\btitle=")[^"]*

        ParsedElement parsedElement = new ParsedElement();
        parsedElement.setRawMarkupString(rawMarkupString);
        parsedElement.setDocumentElement(documentElement);
        for (Map.Entry<String, AbstractDocumentElement.ElementAttribute> att : documentElement.getAttributeMap().entrySet()) {
            String key = att.getKey();
            AbstractDocumentElement.ElementAttribute value = att.getValue();
            System.out.println("looking for key : " + key);

            //Pattern attPattern = Pattern.compile("(?<=\\b"+key+ "=\")[^\"]*");
            
            // TODO: Check Correct patter or not
            Pattern attPattern = Pattern.compile("(?<=\\b" + key + "=[\"”])[^\"”]*");
            Matcher aatMatcher = attPattern.matcher(rawMarkupString);
            if (aatMatcher.find()) {
                String val = aatMatcher.group();
                
                System.out.println("aatMatcher Found : " + val);
                
                Class type = value.getType();
                
                Object castedValue = ConvertManager.convertString(type, val);
                
                if (castedValue != null) {
                    parsedElement.addElementAttributeValuePair(value, value.getType(), castedValue);
                }
            }
        }

        System.out.println("tr.com.bites.poc.documentparser.parser.generator.poi.ParsedElementFactory.createParsedElement() -> " + documentElement.toString());
        return parsedElement;
        
    }
}
