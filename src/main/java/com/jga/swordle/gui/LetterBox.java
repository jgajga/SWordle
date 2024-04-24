/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jga.swordle.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author 280148
 */
public class LetterBox extends JButton implements ActionListener{

    
    public LetterBox() {
         this.setBackground(Color.GRAY);
         addActionListener(this);
         setMargin(new Insets(0, 0, 0, 0));
         this.setText("윤");
         this.setFont(new Font("Dialog", Font.PLAIN, 40));
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getBackground() == Color.YELLOW){
            this.setBackground(Color.GREEN);    
        }
        else if (this.getBackground() == Color.GREEN){
            this.setBackground(Color.GRAY);    
        }
        else if (this.getBackground() == Color.GRAY){
            this.setBackground(Color.YELLOW);    
        }        
    }
    
}
