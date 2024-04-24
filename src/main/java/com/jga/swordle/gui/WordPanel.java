/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jga.swordle.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author 280148
 */
public class WordPanel extends javax.swing.JPanel {

    private int BOX_WIDTH = 50;
    private int BOX_HEIGHT = 100;
    private int WIDTH_SEPARATOR = 5;
    private int HEIGHT_SEPARATOR = 10;    
    
    private List<JButton> llb;
    private JButton btnCheck;
    private JButton btnNotFound;
    /**
     * Creates new form WordPanel
     */
    public WordPanel(int size) {
        initComponents();
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        llb = new ArrayList<JButton>();
        for (int i=0; i<size; i++)
        {
            LetterBox lbox = new LetterBox();
            lbox.setVisible(true);
            lbox.setBounds(WIDTH_SEPARATOR + i*(BOX_WIDTH + WIDTH_SEPARATOR), HEIGHT_SEPARATOR, BOX_WIDTH, BOX_HEIGHT);
            llb.add(lbox);
            this.add(lbox);
        }
        //OK - Button
        btnCheck = new JButton();
        btnCheck.setMargin(new Insets(0, 0, 0, 0));
        btnCheck.setText("O");
        btnCheck.setFont(new Font("Dialog", Font.PLAIN, 40));
        btnCheck.setBounds(WIDTH_SEPARATOR + (size+1)*(BOX_WIDTH + WIDTH_SEPARATOR), HEIGHT_SEPARATOR, BOX_WIDTH, BOX_HEIGHT);
        llb.add(btnCheck);
        this.add(btnCheck);
        btnCheck.setVisible(true);
        
        //NotFound - Button
        btnNotFound = new JButton();
        btnNotFound.setMargin(new Insets(0, 0, 0, 0));
        btnNotFound.setText("X");
        btnNotFound.setFont(new Font("Dialog", Font.PLAIN, 40));
        btnNotFound.setBounds(WIDTH_SEPARATOR + (size+2)*(BOX_WIDTH + WIDTH_SEPARATOR), HEIGHT_SEPARATOR, BOX_WIDTH, BOX_HEIGHT);
        llb.add(btnNotFound);
        this.add(btnNotFound);
        btnNotFound.setVisible(true);
    }
    
    @Override
    public void setEnabled(boolean isEnabled){
        for (JButton lb : llb){
            lb.setEnabled(isEnabled);
        }
        super.setEnabled(isEnabled);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
