package tr.com.bites.poc.documentparser.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface DocumentedField {

	boolean isusedWord() default false;
	FIELD_TYPE fieldType() default FIELD_TYPE.NONE;
        Class type() ;
        String documentKey() default "";
        
	public enum FIELD_TYPE{
		TEXT,
		IMAGE,
                TABLE,
		NONE
	}

}
