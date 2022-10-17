/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package tr.com.bites.poc.documentparser.gui;

import javax.swing.JButton;
import tr.com.bites.poc.documentparser.document.TempDocument;

/**
 *
 * @author fatihs
 */
public class TempFileInfoPanel extends javax.swing.JPanel {

    /**
     * Creates new form FileInfoPanel
     */
    public TempFileInfoPanel() {
        initComponents();

    }

    public void setTempFile(TempDocument document) {
        this.lblPathValue.setText(document.getAbsolutePath());
        
        long size =  (document.length() / 1024);
        
        this.lblSize.setText("Size :" + size + " Kb");
    }

    public JButton getBtnOpenTempFile() {
        return btnOpenTempFile;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFileSelect = new javax.swing.JPanel();
        lblFilePath = new javax.swing.JLabel();
        lblPathValue = new javax.swing.JLabel();
        btnOpenTempFile = new javax.swing.JButton();
        pnlInfo = new javax.swing.JPanel();
        lblSize = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        pnlFileSelect.setLayout(new java.awt.BorderLayout());

        lblFilePath.setText("File Path : ");
        pnlFileSelect.add(lblFilePath, java.awt.BorderLayout.WEST);

        lblPathValue.setText("...");
        pnlFileSelect.add(lblPathValue, java.awt.BorderLayout.CENTER);

        btnOpenTempFile.setText("Open Temp File");
        pnlFileSelect.add(btnOpenTempFile, java.awt.BorderLayout.EAST);

        add(pnlFileSelect, java.awt.BorderLayout.NORTH);

        lblSize.setText("Size :");

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addComponent(lblSize, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 568, Short.MAX_VALUE))
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSize)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        add(pnlInfo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpenTempFile;
    private javax.swing.JLabel lblFilePath;
    private javax.swing.JLabel lblPathValue;
    private javax.swing.JLabel lblSize;
    private javax.swing.JPanel pnlFileSelect;
    private javax.swing.JPanel pnlInfo;
    // End of variables declaration//GEN-END:variables
}
