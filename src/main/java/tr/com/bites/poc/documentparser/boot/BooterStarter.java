/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.boot;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import tr.com.bites.poc.documentparser.annotation.ConfigService;
import tr.com.bites.poc.documentparser.annotation.ParserService;
import tr.com.bites.poc.documentparser.parser.AbstractDocumentParser;
import tr.com.bites.poc.documentparser.util.DefaultConfig;

/**
 *
 * @author fatihs
 */
public class BooterStarter {

    private static HashMap< DefaultConfig.CONFIGS, Object> configsMap = new HashMap<>();
    private static HashMap<String, AbstractDocumentParser> activeParsers = new HashMap<>();

    //TODO check outter service 
    public static void prepare(Class argClass) {
        //Load default and custon Configs;
        loadConfig(argClass);
        reflectParsers();
        //  Reflection Parsers;

    }

    private static void reflectParsers() {
        List<String> activeParserList = (List<String>) configsMap.get(DefaultConfig.CONFIGS.ACTIVE_PARSERS);
        List<String> searchPathList = (List<String>) configsMap.get(DefaultConfig.CONFIGS.PARSER_SEARCH_PATHS);

        if (activeParserList.isEmpty() || searchPathList.isEmpty()) {
            return;
        }

        for (String path : searchPathList) {
            System.out.println("tr.com.bites.poc.documentparser.boot.BooterStarter.reflectParsers() -> " + path);
            String f;
            String correctedPatg = path.replace(".", "/");
            List<String> searchedPathClassList = new ArrayList<>();

            try {
                f = new File(BooterStarter.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                System.out.println("tr.com.bites.poc.documentparser.boot.BooterStarter.reflectParsers() -> " + f + "/" + correctedPatg);
                try ( Stream<Path> walkStream = Files.walk(Paths.get(f + "/" + correctedPatg))) {
                    walkStream.filter(p -> p.toFile().isFile()).forEach(t -> {
                        if (t.toString().endsWith(".class")) {
                            Path x;
                            String classLoadName = t.getFileName().toString();
                            classLoadName = path + "." + classLoadName.substring(0, classLoadName.indexOf("."));
                            System.out.println("tr.com.bites.poc.documentparser.boot.BooterStarter.reflectParsers() Name-> " + classLoadName);
                            searchedPathClassList.add(classLoadName);
                        }
                    });
                } catch (Exception e) {

                }
                Class [] parametersTypes  = {File.class};
                
                for (String parserPath : searchedPathClassList) {
                    Class<?> parserClass = loadClassFromName(parserPath, AbstractDocumentParser.class, ParserService.class);
                    ParserService parserAnnotation = parserClass.getAnnotation(ParserService.class);
                    AbstractDocumentParser reflectFromClass = reflectFromClass(AbstractDocumentParser.class,null , null );
                    if(activeParserList.contains(parserAnnotation.parserGroup())){
                        BooterStarter.activeParsers.put(parserAnnotation.parserGroup(), reflectFromClass);
                    }   
                }
                
            } catch (URISyntaxException ex) {
                Logger.getLogger(BooterStarter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static HashMap<DefaultConfig.CONFIGS, Object> getConfigsMap() {
        return configsMap;
    }

    private static <TYPE> TYPE reflectFromClass(Class<TYPE> clazz, Class[] parameterTypes, Object... parameters) {
        try {
            TYPE newInstance = clazz.getConstructor(parameterTypes).newInstance(parameters);
            return newInstance;
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BooterStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 

    private static <TYPE> Class<?> loadClassFromName(String className, Class<TYPE> typeClass, Class<? extends Annotation> annotationClass) {

        try {
            Class<TYPE> loadedClass = (Class<TYPE>) ClassLoader.getSystemClassLoader().loadClass(className);
            if (annotationClass != null) {
                if (!loadedClass.isAnnotationPresent(annotationClass));
                return null;
            }
            return loadedClass;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BooterStarter.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SecurityException ex) {
            Logger.getLogger(BooterStarter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BooterStarter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static void loadConfig(Class argClass) {
        //SearchPaths
        if (DefaultConfig.class.isAnnotationPresent(ConfigService.SearchPathConfig.class)) {
            ConfigService.SearchPathConfig parseC = DefaultConfig.class.getAnnotation(ConfigService.SearchPathConfig.class);
            String[] searchPaths = parseC.parserSearchPaths();

            if (searchPaths.length != 0) {
                if (!configsMap.containsKey(DefaultConfig.CONFIGS.PARSER_SEARCH_PATHS)) {
                    configsMap.put(DefaultConfig.CONFIGS.PARSER_SEARCH_PATHS, new ArrayList<String>());
                }

                ArrayList<String> list = (ArrayList<String>) configsMap.get(DefaultConfig.CONFIGS.PARSER_SEARCH_PATHS);
                for (String searchPath : searchPaths) {
                    list.add(searchPath);
                }
            }
        }

        if (argClass.isAnnotationPresent(ConfigService.SearchPathConfig.class)) {
            ConfigService.SearchPathConfig parseC = (ConfigService.SearchPathConfig) argClass.getAnnotation(ConfigService.SearchPathConfig.class);
            String[] searchPaths = parseC.parserSearchPaths();

            if (searchPaths.length != 0) {
                if (!configsMap.containsKey(DefaultConfig.CONFIGS.PARSER_SEARCH_PATHS)) {
                    configsMap.put(DefaultConfig.CONFIGS.PARSER_SEARCH_PATHS, new ArrayList<String>());
                }

                ArrayList<String> list = (ArrayList<String>) configsMap.get(DefaultConfig.CONFIGS.PARSER_SEARCH_PATHS);
                for (String searchPath : searchPaths) {
                    list.add(searchPath);
                }
            }
        }
        // -- SearchPaths -- END --

        // -- Active Parsers -- 
        if (DefaultConfig.class.isAnnotationPresent(ConfigService.Parser.class)) {
            ConfigService.Parser parsersAnnotation = DefaultConfig.class.getAnnotation(ConfigService.Parser.class);
            String[] activeParsers = parsersAnnotation.activeParsers();

            if (activeParsers.length != 0) {
                if (!configsMap.containsKey(DefaultConfig.CONFIGS.ACTIVE_PARSERS)) {
                    configsMap.put(DefaultConfig.CONFIGS.ACTIVE_PARSERS, new ArrayList<String>());
                }

                ArrayList<String> list = (ArrayList<String>) configsMap.get(DefaultConfig.CONFIGS.ACTIVE_PARSERS);
                for (String searchPath : activeParsers) {
                    list.add(searchPath);
                }
            }
        }

        if (argClass.isAnnotationPresent(ConfigService.Parser.class)) {
            ConfigService.Parser parsersAnnotation = (ConfigService.Parser) argClass.getAnnotation(ConfigService.Parser.class);
            String[] activeParsers = parsersAnnotation.activeParsers();

            if (activeParsers.length != 0) {
                if (!configsMap.containsKey(DefaultConfig.CONFIGS.ACTIVE_PARSERS)) {
                    configsMap.put(DefaultConfig.CONFIGS.ACTIVE_PARSERS, new ArrayList<String>());
                }

                ArrayList<String> list = (ArrayList<String>) configsMap.get(DefaultConfig.CONFIGS.ACTIVE_PARSERS);
                for (String searchPath : activeParsers) {
                    list.add(searchPath);
                }
            }
        }
    }
}
