/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.type;

import java.util.HashMap;

/**
 *
 * @author fatihs
 */
public abstract class DocumentElement<TYPE> {

    String documentKey = "";
    TYPE value = null;
    HashMap<String, ElementAttribute> attributeMap = new HashMap<>();

    public DocumentElement() {

    }

    public void addAttribute(String key, ElementAttribute attribute) {
        //TODO:  check if duplaceted key change value;
        attributeMap.put(key, attribute);
    }
    
    public void addAttribute(String key,String value) {
        //TODO:  check if duplaceted key change value;
        attributeMap.put(key,new ElementAttribute(key, value));
    }
    
    public void removeAttribute(String key){
        if(attributeMap.containsKey(key)) {
            attributeMap.remove(key);
        }
    }
    
    public DocumentElement(String documentKey, TYPE value) {
        this.value = value;
        this.documentKey = documentKey;
    }

    public class ElementAttribute {

        String key;
        String value;

        public ElementAttribute() {
        }

        public ElementAttribute(String key, String value) {
            this.key = key;
            this.value = value;
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
    }
}
