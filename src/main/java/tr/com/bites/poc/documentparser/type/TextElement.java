/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.type;

import java.awt.Font;
import tr.com.bites.poc.documentparser.annotation.DocumentAttribute;
import tr.com.bites.poc.documentparser.annotation.DocumentElementType;

/**
 *
 * @author fatihs
 */

@DocumentElementType(parserGroup = "WORD", documentKey = "text",
        attributes = {@DocumentAttribute(attributeName = "font",defaultValue = "",targetType = Font.class)})
public class TextElement extends DocumentElement{
    
}
