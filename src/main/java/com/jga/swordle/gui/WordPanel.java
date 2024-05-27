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
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author 280148
 */
public class WordPanel extends javax.swing.JPanel /*implements ActionListener*/{

    private int BOX_WIDTH = 50;
    private int BOX_HEIGHT = 50;
    private int WIDTH_SEPARATOR = 5;
    private int HEIGHT_SEPARATOR = 5;    
    
    private List<LetterBox> llb;
    private JButton btnCheck;
    private JButton btnNotFound;
    private SWordle sw;
    private String word;
    private WordlePanel parent;
    
    /**
     * Creates new form WordPanel
     */
    public WordPanel(WordlePanel parent) {
        initComponents();
        this.sw = parent.getSWordle();
        int size = this.sw.getSize();
        //this.setBorder(BorderFactory.createLineBorder(Color.black));
        llb = new ArrayList<LetterBox>();
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
        btnCheck.setText("\u2714");
        btnCheck.setForeground(Color.GREEN);
        btnCheck.setFont(new Font("Dialog", Font.PLAIN, 40));
        btnCheck.setBounds(WIDTH_SEPARATOR + (size+1)*(BOX_WIDTH + WIDTH_SEPARATOR), HEIGHT_SEPARATOR, BOX_WIDTH, BOX_HEIGHT);
        this.add(btnCheck);
        btnCheck.setVisible(true);
        
        //NotFound - Button
        btnNotFound = new JButton();
        btnNotFound.setMargin(new Insets(0, 0, 0, 0));
        btnNotFound.setText("\u274C");
        btnNotFound.setForeground(Color.RED);
        btnNotFound.setFont(new Font("Dialog", Font.PLAIN, 40));
        btnNotFound.setBounds(WIDTH_SEPARATOR + (size+2)*(BOX_WIDTH + WIDTH_SEPARATOR), HEIGHT_SEPARATOR, BOX_WIDTH, BOX_HEIGHT);
        this.add(btnNotFound);
        btnNotFound.setVisible(true);
        
        
        btnCheck.addActionListener((ActionEvent e) -> {
            
            StringBuilder sb = new StringBuilder(); 
            boolean isCompleted = true;
            int greens = 0;
            for (LetterBox lb : llb){
                if (lb.getBackground() == Color.WHITE)
                {
                    isCompleted = false;
                    break;
                }
                if (lb.getBackground() == Color.GREEN)
                {
                    greens++;
                    sb.append("=");
                }
                else if (lb.getBackground() == Color.YELLOW)
                {
                    sb.append("?");
                }            
                else if (lb.getBackground() == Color.GRAY)
                {
                    sb.append("x");
                }                
            }
            
            if (isCompleted){
                sw.setGuessResult(sb.toString());
                if (greens == this.sw.getSize()){
                    JOptionPane.showMessageDialog(this, "Game won in " + parent.getCurrentTurn() + " turns", "Alert", JOptionPane.INFORMATION_MESSAGE);    
                }
                else{
                    if (parent.getCurrentTurn() == this.sw.getTurns()){
                        this.setEnabled(false);
                        JOptionPane.showMessageDialog(this, "Solution not found. Game lost.", "Alert", JOptionPane.INFORMATION_MESSAGE);    
                    }
                    else{
                        parent.newTurn();
                    }    
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Color all pieces, please.", "Alert", JOptionPane.INFORMATION_MESSAGE);
            }
            repaint();
            
            
        });
        
        btnNotFound.addActionListener((ActionEvent e) -> {
            int answer = JOptionPane.showConfirmDialog(this, "Are sure you want to ask for another word?","Alert",JOptionPane.YES_NO_OPTION);
            if (answer == 0){
                this.reset();
                this.sw.setGuessResult("#");
                try {
                    //Generate a new word
                    this.setWord(this.sw.getGuess());
                } catch (WordleException ex) {
                    Logger.getLogger(WordPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            repaint();
            
        });
    }
    
    public void reset(){
        for (LetterBox lb : llb){
            lb.setText("");
            lb.setBackground(Color.WHITE);
        }    
    }
    
    public void setWord(String word){
        this.word = word;
        for (int i=0; i<llb.size(); i++)
        {
            llb.get(i).setText(String.valueOf(word.charAt(i)));
        }
    }
    
    
    
    


    
    @Override
    public void setEnabled(boolean isEnabled){
        for (LetterBox lb : llb){
            lb.setEnabled(isEnabled);
        }
        super.setEnabled(isEnabled);
        btnCheck.setEnabled(isEnabled);
        btnNotFound.setEnabled(isEnabled);
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
