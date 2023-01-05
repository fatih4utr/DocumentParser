/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.boot;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JFrame;
import tr.com.bites.poc.documentparser.annotation.ConfigService;
import tr.com.bites.poc.documentparser.annotation.DocumentAttribute;
import tr.com.bites.poc.documentparser.annotation.DocumentElementType;
import tr.com.bites.poc.documentparser.annotation.ParserService;
import tr.com.bites.poc.documentparser.document.TempDocument;
import tr.com.bites.poc.documentparser.element.AbstractDocumentElement;
import tr.com.bites.poc.documentparser.gui.DocumentEditorPanel;
import tr.com.bites.poc.documentparser.parser.AbstractDocumentParser;
import tr.com.bites.poc.documentparser.parser.generator.AbstractGenerator;
import tr.com.bites.poc.documentparser.util.DefaultConfig;
import tr.com.bites.poc.documentparser.util.ParserFactory;

/**
 *
 * @author fatihs
 */
public class BootStarter {

    private static HashMap< CONFIGS, Object> configsMap = new HashMap<>();
    private static HashMap<String, AbstractDocumentParser> activeParsers = new HashMap<>();
    private static AbstractDocumentParser currentParser = null;

    //TODO check outter service 
    public static void prepare(Class argClass) {
        //Load default and custon Configs;
        loadConfig(argClass);
        reflectParsers();
        //  Reflection Parsers;
        generateDocumentElements();
    }

    public static HashMap<String, AbstractDocumentParser> getActiveParsers() {
        return activeParsers;
    }

    public static String[] getActiveParsersExtentions() {
        List<String> list = new ArrayList<>();

        for (Map.Entry<String, AbstractDocumentParser> entry : activeParsers.entrySet()) {
            String key = entry.getKey();
            AbstractDocumentParser value = entry.getValue();

            ParserService annotation = value.getClass().getAnnotation(ParserService.class);
            String[] fileExtention = annotation.fileExtention();
            List<String> asList = Arrays.asList(fileExtention);
            list.addAll(asList);
        }

        if (!list.isEmpty()) {
            String[] ext = null;
            ext = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ext[i] = list.get(i);
            }
            return ext;
        }

        return null;
    }

    public static void parse(File tempFile) {
        currentParser = ParserFactory.getDefault().selectParser(tempFile, activeParsers);
        if (currentParser == null) {
            //TODO Fire error
            return;
        }
        TempDocument tempDocument = currentParser.parseDocument();

    }

    public static AbstractDocumentParser getCurrentParser() {
        return currentParser;
    }

    public static void start() {
        JFrame frame = new JFrame();
        frame.setSize(700, 700);
        DocumentEditorPanel panel = new DocumentEditorPanel();
        panel.initPanel();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void save(File targetFile) {

    }

    private static void generateDocumentElements() {

        List<String> elementSearchList = (List<String>) configsMap.get(CONFIGS.DOCUMENT_ELEMENTS_SEARCH_PATHS);
        HashMap<Class<?>, DocumentElementType> elementAnnotationMap = new HashMap<>();

        for (String path : elementSearchList) {
            List<String> classListFromSearchPath = getClassListFromSearchPath(path);
            for (String elementPath : classListFromSearchPath) {
                Class<?> loadedClass = loadClassFromName(elementPath, AbstractDocumentElement.class, DocumentElementType.class);
                if (loadedClass == null) {
                    continue;
                }

                if (!loadedClass.isAnnotationPresent(DocumentElementType.class)
                        || !AbstractDocumentElement.class.isAssignableFrom(loadedClass)) {
                    continue;
                }
                DocumentElementType annotation = loadedClass.getAnnotation(DocumentElementType.class);
                elementAnnotationMap.put(loadedClass, annotation);
            }
        }

        for (Map.Entry<String, AbstractDocumentParser> entry : activeParsers.entrySet()) {
            String key = entry.getKey();
            AbstractDocumentParser parser = entry.getValue();

            for (Map.Entry<Class<?>, DocumentElementType> documentElementEntry : elementAnnotationMap.entrySet()) {
                Class<?> elementClass = documentElementEntry.getKey();
                DocumentElementType annotation = documentElementEntry.getValue();
                if (key.equals(annotation.parserGroup())) {
                    AbstractDocumentElement element = generateElementAttributes(annotation, elementClass);
                    System.out.println(element);

                    parser.addDocumentElement(element);

                }
            }
        }

    }

    private static AbstractDocumentElement generateElementAttributes(DocumentElementType annotation, Class documentElementType) {
        DocumentAttribute[] attributes = annotation.attributes();

        AbstractDocumentElement object = (AbstractDocumentElement) reflectFromClass(documentElementType, null, null);
        object.setDocumentKey(annotation.documentKey());

        if (attributes == null || attributes.length == 0) {
            return object;
        }

        for (DocumentAttribute attribute : attributes) {
            AbstractDocumentElement.ElementAttribute att
                    = new AbstractDocumentElement.ElementAttribute(attribute.attributeName(), attribute.targetType());
            object.addAttribute(att);
        }

        return object;
    }

    private static List<String> getClassListFromSearchPath(String searchPath) {
        List<String> searchedPathClassList = new ArrayList<>();
        String f;
        try {
            f = new File(BootStarter.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            String correctedPatg = searchPath.replace(".", "/");
            try ( Stream<Path> walkStream = Files.walk(Paths.get(f + "/" + correctedPatg))) {
                walkStream.filter(p -> p.toFile().isFile()).forEach(t -> {
                    if (t.toString().endsWith(".class")) {
                        Path x;
                        String classLoadName = t.getFileName().toString();
                        classLoadName = searchPath + "." + classLoadName.substring(0, classLoadName.indexOf("."));
                        searchedPathClassList.add(classLoadName);
                    }
                });
            } catch (Exception e) {

            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(BootStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return searchedPathClassList;
    }

    private static void reflectParsers() {
        List<String> activeParserList = (List<String>) configsMap.get(CONFIGS.ACTIVE_PARSERS);
        List<String> searchPathList = (List<String>) configsMap.get(CONFIGS.PARSER_SEARCH_PATHS);

        if (activeParserList.isEmpty() || searchPathList.isEmpty()) {
            return;
        }

        for (String path : searchPathList) {

            List<String> searchedPathClassList = getClassListFromSearchPath(path);
            for (String parserPath : searchedPathClassList) {
                Class<?> parserClass = loadClassFromName(parserPath, AbstractDocumentParser.class, ParserService.class);
                if (parserClass == null) {
                    //TODO ERROR fire
                    continue;
                }
                ParserService parserAnnotation = parserClass.getAnnotation(ParserService.class);
                AbstractDocumentParser reflectedParser = (AbstractDocumentParser) reflectFromClass(parserClass, null, null);
                if (activeParserList.contains(parserAnnotation.parserGroup())) {
                    Class<? extends AbstractGenerator> generatorClass = parserAnnotation.generator();
                    
                    reflectedParser.setGeneratorClass(generatorClass);
                    BootStarter.activeParsers.put(parserAnnotation.parserGroup(), reflectedParser);

                }
            }

        }

    }

    public static HashMap<CONFIGS, Object> getConfigsMap() {
        return configsMap;
    }

    private static Object reflectFromClass(Class<?> clazz, Class[] parameterTypes, Object... parameters) {
        try {
            Object newInstance = clazz.getConstructor(parameterTypes).newInstance(parameters);
            return newInstance;
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BootStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static <TYPE> Class<?> loadClassFromName(String className, Class<TYPE> typeClass, Class<? extends Annotation> annotationClass) {

        try {
            Class<TYPE> loadedClass = (Class<TYPE>) ClassLoader.getSystemClassLoader().loadClass(className);
            if (annotationClass != null) {
                if (!loadedClass.isAnnotationPresent(annotationClass)) {
                    return null;
                }
            }

            if (!typeClass.isAssignableFrom(loadedClass)) {
                //TODO extends error not correct 
                return null;
            }
            return loadedClass;
        } catch (ClassNotFoundException | SecurityException | IllegalArgumentException ex) {
            //Logger.getLogger(BootStarter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        
    }

    private static void fillSearchPathFromConfig(Class argClass, CONFIGS pathConfig) {

        if (argClass.isAnnotationPresent(ConfigService.SearchPathConfig.class)) {
            ConfigService.SearchPathConfig parseC = (ConfigService.SearchPathConfig) argClass.getAnnotation(ConfigService.SearchPathConfig.class);
            String[] searchPaths = null;
            if (pathConfig == CONFIGS.DOCUMENT_ELEMENTS_SEARCH_PATHS) {
                searchPaths = parseC.documentElementsSearchPaths();
            } else if (pathConfig == CONFIGS.PARSER_SEARCH_PATHS) {
                searchPaths = parseC.parserSearchPaths();
            }

            if (searchPaths.length != 0) {
                if (!configsMap.containsKey(pathConfig)) {
                    configsMap.put(pathConfig, new ArrayList<String>());
                }

                ArrayList<String> list = (ArrayList<String>) configsMap.get(pathConfig);
                for (String searchPath : searchPaths) {
                    list.add(searchPath);
                }
            }
        }
    }

    private static void loadConfig(Class argClass) {
        //SearchPaths
        fillSearchPathFromConfig(DefaultConfig.class, CONFIGS.PARSER_SEARCH_PATHS);
        fillSearchPathFromConfig(DefaultConfig.class, CONFIGS.DOCUMENT_ELEMENTS_SEARCH_PATHS);

        fillSearchPathFromConfig(argClass, CONFIGS.PARSER_SEARCH_PATHS);
        fillSearchPathFromConfig(argClass, CONFIGS.DOCUMENT_ELEMENTS_SEARCH_PATHS);

        // -- SearchPaths -- END --
        // -- Active Parsers -- 
        if (DefaultConfig.class.isAnnotationPresent(ConfigService.Parser.class)) {
            ConfigService.Parser parsersAnnotation = (ConfigService.Parser) DefaultConfig.class.getAnnotation(ConfigService.Parser.class);
            String[] activeParsers = parsersAnnotation.activeParsers();

            if (activeParsers.length != 0) {
                if (!configsMap.containsKey(CONFIGS.ACTIVE_PARSERS)) {
                    configsMap.put(CONFIGS.ACTIVE_PARSERS, new ArrayList<String>());
                }

                ArrayList<String> list = (ArrayList<String>) configsMap.get(CONFIGS.ACTIVE_PARSERS);
                for (String searchPath : activeParsers) {
                    list.add(searchPath);
                }
            }
        }

        if (argClass.isAnnotationPresent(ConfigService.Parser.class)) {
            ConfigService.Parser parsersAnnotation = (ConfigService.Parser) argClass.getAnnotation(ConfigService.Parser.class);
            String[] activeParsers = parsersAnnotation.activeParsers();

            if (activeParsers.length != 0) {
                if (!configsMap.containsKey(CONFIGS.ACTIVE_PARSERS)) {
                    configsMap.put(CONFIGS.ACTIVE_PARSERS, new ArrayList<String>());
                }

                ArrayList<String> list = (ArrayList<String>) configsMap.get(CONFIGS.ACTIVE_PARSERS);
                for (String searchPath : activeParsers) {
                    list.add(searchPath);
                }
            }
        }
    }
}
