/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.element;

import java.awt.image.BufferedImage;
import tr.com.bites.poc.documentparser.annotation.DocumentAttribute;
import tr.com.bites.poc.documentparser.annotation.DocumentElementType;

/**
 *
 * @author fatihs
 */
@DocumentElementType(parserGroup = "WORD", documentKey = "image",
        attributes = {
            @DocumentAttribute(attributeName = "src",defaultValue = "",targetType = BufferedImage.class),
            @DocumentAttribute(attributeName = "width",defaultValue = "",targetType = Integer.class),
            
        })
public class ImageElement extends  AbstractDocumentElement{
    
}
