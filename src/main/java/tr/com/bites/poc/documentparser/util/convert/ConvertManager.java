/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.util.convert;

import java.util.HashMap;
import tr.com.bites.poc.documentparser.util.*;

/**
 *
 * @author fatihs
 */
public class ConvertManager {

    private static HashMap<Class, AbstractConverter> stringConverterMap = new HashMap<>();

    static {
        stringConverterMap.put(Integer.class, new IntegerConverter());
    }

    public static <TYPE> TYPE convertString(Class<TYPE> type, String value) {
        if (ConvertManager.stringConverterMap.containsKey(type)) {

            return (TYPE) stringConverterMap.get(type).convert(value);
        }
        return null;
    }

    public static abstract class AbstractConverter<TYPE_IN, TYPE_OUT> {

        protected abstract TYPE_OUT convert(TYPE_IN value);
    }

    private static class StringConverter extends AbstractConverter<String, String> {

        @Override
        public String convert(String value) {
            return value;
        }

    }

    private static class IntegerConverter extends AbstractConverter<String, Integer> {

        @Override
        public Integer convert(String value) {
            return Integer.valueOf(value);
        }

    }
}
