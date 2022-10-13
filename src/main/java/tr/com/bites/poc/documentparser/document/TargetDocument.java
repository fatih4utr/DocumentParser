/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.document;

/**
 *
 * @author fatihs
 * 
 */
public  abstract  class TargetDocument <TYPE> extends AbstractDocument <TYPE> implements Saveable {

    public TargetDocument(String path) {
        super(path);
    }
    
}
