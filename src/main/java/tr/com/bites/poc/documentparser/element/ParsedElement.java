/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.element;

import java.util.HashMap;
import tr.com.bites.poc.documentparser.element.AbstractDocumentElement;

/**
 *
 * @author fatihs
 */
public class ParsedElement {

    private Class<? extends AbstractDocumentElement> elementType = null;
    private HashMap<String, ElementAttributeValuePair> pairMap = new HashMap<>();
    // rawMarkupString = ${ #image# name=”key” width=”123” height=123214 sdeflşmpgnhtbşlp};
    
    private String rawMarkupString;
    private AbstractDocumentElement documentElement = null;

    public void setDocumentElement(AbstractDocumentElement documentElement) {
        this.documentElement = documentElement;
    }
    
    
    
    public String getRawMarkupString() {
        return rawMarkupString;
    }
    
    public void setRawMarkupString(String rawMarkupString) {
        this.rawMarkupString = rawMarkupString;
    }

    public <TYPE> void  addElementAttributeValuePair(AbstractDocumentElement.ElementAttribute elementAttribute,Class<TYPE> valueType, TYPE value ){
        if(!this.pairMap.containsKey(elementAttribute.getKey())){
            this.pairMap.put(elementAttribute.getKey(), new ElementAttributeValuePair<TYPE>(elementAttribute, valueType, value) );
        }
    }
    
    public <TYPE> TYPE getElementAttributeByName(String name){
        if(!this.pairMap.containsKey(name)){
            return null;
        }
        return (TYPE) this.pairMap.get(name).value;
    }
    
    public static class ElementAttributeValuePair<TYPE> {

        AbstractDocumentElement.ElementAttribute attribute;
        TYPE value;
        Class valueType;

        public ElementAttributeValuePair(AbstractDocumentElement.ElementAttribute attribute, Class<TYPE> valueType, TYPE value) {
            this.attribute = attribute;
            this.value = value;
            this.valueType = this.valueType;
        }

        public TYPE getValue() {
            return value;
        }

        public AbstractDocumentElement.ElementAttribute getAttribute() {
            return attribute;
        }

        public Class getValueType() {
            return valueType;
        }
        
    }
}
