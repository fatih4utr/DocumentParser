/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.parser.generator.poi;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import tr.com.bites.poc.documentparser.element.ParsedElement;
import tr.com.bites.poc.documentparser.parser.generator.AbstractGenerator;

/**
 *
 * @author fatihs
 */
public class ApachePoiWordGenerator extends AbstractGenerator<PoiTempDocument, PoiTargetDocument> {

    @Override
    public void start() {
        XWPFWordExtractor extractor = new XWPFWordExtractor(tempDocument.getGeneratorDocumentType());
        String text = extractor.getText();

        String startPattern = Pattern.quote("${");
        String endPattern = Pattern.quote("}");
        Pattern pattern = Pattern.compile(startPattern + "(.*?)" + endPattern);
        Matcher matcher = pattern.matcher(text);

        Pattern patternAtt = Pattern.compile(startPattern + "(.*?)" + endPattern);
        
        
        while (matcher.find()) {
            String group = matcher.group();
            System.out.println("grouo  : "+ group);
            ParsedElement parsedElement= ParsedElementFactory.createParsedElement(group);
            this.tempDocument.addParsedElement(parsedElement);
        }
        
    }

    private void editParagraphWithData(XWPFParagraph paragraph, String surroundedTag, String replacement) {
        List<Integer> runsToRemove = new LinkedList<Integer>();
        StringBuilder tmpText = new StringBuilder();
        int runCursor = 0;

        // Processing (in normal order) the all runs until I found my surroundedTag
        while (!tmpText.toString().contains(surroundedTag)) {
            tmpText.append(paragraph.getRuns().get(runCursor).text());
            runsToRemove.add(runCursor);
            runCursor++;
        }

        tmpText = new StringBuilder();
        // Processing back (in reverse order) to only keep the runs I need to edit/remove
        while (!tmpText.toString().contains(surroundedTag)) {
            runCursor--;
            tmpText.insert(0, paragraph.getRuns().get(runCursor).text());
        }

        // Edit the first run of the tag
        XWPFRun runToEdit = paragraph.getRuns().get(runCursor);
        runToEdit.setText(tmpText.toString().replaceAll(surroundedTag, replacement), 0);

        // Forget the runs I don't to remove
        while (runCursor >= 0) {
            runsToRemove.remove(0);
            runCursor--;
        }

        // Remove the unused runs
        Collections.reverse(runsToRemove);
        for (Integer runToRemove : runsToRemove) {
            paragraph.removeRun(runToRemove);
        }
    }

    @Override
    public Class<PoiTempDocument> getTempDocumentType() {
        return PoiTempDocument.class;
    }

    @Override
    public Class<PoiTargetDocument> getTargetDocumentType() {
        return PoiTargetDocument.class;
    }
}
