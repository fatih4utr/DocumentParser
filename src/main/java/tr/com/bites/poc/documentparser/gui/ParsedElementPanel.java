/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package tr.com.bites.poc.documentparser.gui;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;

/**
 *
 * @author fatihs
 */
public class ParsedElementPanel extends javax.swing.JPanel {

    /**
     * Creates new form ParsedElementPanle
     */
    public ParsedElementPanel() {
        initComponents();
        this.jList1.setCellRenderer(new ParsedElementItemRenderer());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        pnlCenter.setLayout(new java.awt.BorderLayout());

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        pnlCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(pnlCenter, java.awt.BorderLayout.CENTER);

        jButton2.setText("B1");
        jPanel1.add(jButton2);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private static class ParsedElementItemRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

            return new JButton("asdsa");
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlCenter;
    // End of variables declaration//GEN-END:variables
}