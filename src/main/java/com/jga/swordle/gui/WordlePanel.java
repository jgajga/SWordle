/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jga.swordle.gui;

import com.jga.swordle.core.SWordle;
import com.jga.swordle.core.WordleException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author 280148
 */
public class WordlePanel extends javax.swing.JPanel {

    private int BOX_WIDTH = 50;
    private int BOX_HEIGHT = 50;
    private int WIDTH_SEPARATOR = 5;
    private int HEIGHT_SEPARATOR = 5;    
    
    private List<WordPanel> lwp;
    private JButton btnStart;
    private SWordle sw;
    private int currentTurn;


    public SWordle getSWordle() {
        return sw;
    }

    public void setSWordle(SWordle sw) {
        this.sw = sw;
    }
    
    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }


    public WordlePanel(SWordle sw) {

        initComponents();
        this.sw = sw;
        int size = sw.getSize();
        int turns = sw.getTurns();
        currentTurn = 0;
        //this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBounds(10, 10, 500, 600);
        lwp = new ArrayList<>();
        for (int i=0; i<turns; i++)
        {
            WordPanel wp = new WordPanel(this);
            wp.setVisible(true);
            wp.setBounds(WIDTH_SEPARATOR, (i+1)*HEIGHT_SEPARATOR + i*(BOX_HEIGHT + HEIGHT_SEPARATOR) ,  (size+3) * (BOX_WIDTH + WIDTH_SEPARATOR) + WIDTH_SEPARATOR, BOX_HEIGHT + 2*HEIGHT_SEPARATOR);
            lwp.add(wp);
            this.add(wp);
        }        
        
        btnStart = new JButton();
        //btnStart.setMargin(new Insets(0, 0, 0, 0));
        btnStart.setFont(new Font("Dialog", Font.PLAIN, 20));
        btnStart.setText("Start");
        btnStart.setBounds(WIDTH_SEPARATOR, (turns+1)*HEIGHT_SEPARATOR + (turns)*(BOX_HEIGHT + HEIGHT_SEPARATOR), 100, 40);
        btnStart.setVisible(true);
        this.add(btnStart);
        
        //Disable all WordPanels
        for (WordPanel wp : lwp)
        {
            wp.setEnabled(false);
        }
        
        btnStart.addActionListener((ActionEvent e) -> {
          if ("Start".equals(btnStart.getText())){
            btnStart.setText("Reset"); 
            this.newTurn();
          }
          else
          {
            btnStart.setText("Start"); 
            for (WordPanel wp : lwp){
                wp.reset();
                wp.setEnabled(false);
                sw.reset();
                this.currentTurn = 0;
                        
            }
          }
        });
        
    }
    
    public void newTurn()
    {
        
        try {
            lwp.get(this.currentTurn).setWord(sw.getGuess());
        } catch (WordleException ex) {
            Logger.getLogger(WordlePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        lwp.get(this.currentTurn).setEnabled(true);    
        if (this.currentTurn > 0){
            lwp.get(this.currentTurn -1).setEnabled(false);   
        }
        this.currentTurn++;
        
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
