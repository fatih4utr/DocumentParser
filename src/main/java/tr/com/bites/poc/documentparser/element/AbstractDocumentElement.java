/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.element;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author fatihs
 */
public abstract class AbstractDocumentElement {

    String documentKey = "";
    
    HashMap<String, ElementAttribute> attributeMap = new HashMap<>();

    public AbstractDocumentElement() {
        ElementAttribute attribute = new ElementAttribute("name", "", String.class);
        attributeMap.put(attribute.key,attribute);
    }
    
    public void addAttribute(String key, ElementAttribute attribute) {
        //TODO:  check if duplaceted key change value;
        attributeMap.put(key, attribute);
    }

    public void addAttribute(ElementAttribute attribute) {
        attributeMap.put(attribute.getKey(), attribute);
    }

    public void addAttribute(String key, String value) {
        //TODO:  check if duplaceted key change value;
        attributeMap.put(key, new ElementAttribute(key, value));
    }

    public void removeAttribute(String key) {
        if (attributeMap.containsKey(key)) {
            attributeMap.remove(key);
        }
    }

    public String getDocumentKey() {
        return documentKey;
    }

    public void setDocumentKey(String documentKey) {
        this.documentKey = documentKey;
    }

    public AbstractDocumentElement(String documentElementKey) {

        this.documentKey = documentElementKey;
    }

    @Override
    public String toString() {
        String value = "- "+ this.documentKey + "\n";
        for (Map.Entry<String, ElementAttribute> entry : attributeMap.entrySet()) {
            Object key = entry.getKey();
            Object att = entry.getValue();
            value+=att.toString() + "\n";
        }
        return value;
    }
    
    public static class ElementAttribute {

        String key;
        String value;
        Class type;

        public ElementAttribute() {
        }

        public ElementAttribute(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public ElementAttribute(String key, String value, Class type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }

        public Class getType() {
            return type;
        }

        public void setType(Class type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "-- " + this.key + " - " + this.value + " - " + this.type.toString();
        }
        
    }

}
