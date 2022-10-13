/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.annotation;

/**
 *
 * @author fatihs
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import tr.com.bites.poc.documentparser.parser.generator.AbstractGenerator;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface ParserService {
    String [] fileExtention();
    String parserGroup() default "";
    Class<? extends AbstractGenerator> generator();
    
}
