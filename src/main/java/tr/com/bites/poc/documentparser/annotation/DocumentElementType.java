/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author fatihs
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)


public @interface DocumentElementType {
    String parserGroup() default "";
    String documentKey() default "";
    DocumentAttribute [] attributes() default {};
    
}
