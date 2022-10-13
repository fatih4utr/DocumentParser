/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.com.bites.poc.documentparser.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import tr.com.bites.poc.documentparser.boot.BootStarter;
import tr.com.bites.poc.documentparser.document.TempDocument;
import tr.com.bites.poc.documentparser.parser.AbstractDocumentParser;

/**
 *
 * @author fatihs
 */
public class DocumentEditorPanel extends JPanel implements AbstractDocumentParser.DocumentParserListener {

    private File tempFile = null;
    private File targetFile = null;

    private TempFileInfoPanel pnlTempFileInfo;
    private CustomPanel pnlParsedElements;
    private CustomPanel pnlObjectElements;
    private CustomPanel pnlSelections;
    private CustomPanel pnlStatucBar;

    public DocumentEditorPanel() {
    }

    public void initPanel() {
        this.setLayout(new BorderLayout());
        this.setSize(500, 500);
        pnlObjectElements = new CustomPanel(Color.yellow);
        pnlParsedElements = new CustomPanel(Color.RED);
        pnlSelections = new CustomPanel(Color.BLUE);
        pnlTempFileInfo = new TempFileInfoPanel();
        pnlStatucBar = new CustomPanel(Color.GREEN);

        this.add(pnlTempFileInfo, BorderLayout.NORTH);
        this.add(pnlParsedElements, BorderLayout.WEST);
        this.add(pnlObjectElements, BorderLayout.CENTER);
        this.add(pnlSelections, BorderLayout.EAST);
        this.add(pnlStatucBar, BorderLayout.SOUTH);

        this.pnlTempFileInfo.getBtnOpenTempFile().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                File openSelectFile = DocumentEditorPanel.this.openSelectFile(BootStarter.getActiveParsersExtentions());
                
                if (openSelectFile != null) {
                    BootStarter.parse(openSelectFile);
                    AbstractDocumentParser currentParser = BootStarter.getCurrentParser();
                    if (currentParser != null) {
                        pnlTempFileInfo.setTempFile(currentParser.getTempDocument());
                    }
                }

            }
        });
    }

    private File openSelectFile(String ... fileExtentions) {
        //TODO remember last path;
        JFileChooser fileChooser = new JFileChooser();

        if (fileExtentions != null) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Temp Files", fileExtentions);
            fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
            fileChooser.addChoosableFileFilter(filter);
        }
        fileChooser.showOpenDialog(this);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setVisible(true);

        return fileChooser.getSelectedFile();
    }

    @Override
    public void onError(String errorMsg) {
    }

    @Override
    public void onParseSucces(TempDocument document) {
    }

    private static class CustomPanel extends JPanel {

        public CustomPanel(Color color) {
            this.setBorder(BorderFactory.createLineBorder(color, 5));
        }
    }
}
