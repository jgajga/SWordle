/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jga.swordle.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author 280148
 */
public class LetterBox extends JButton implements ActionListener{

    JLabel label = new JLabel();
    private Color bg;
    private Color currentBg;
    
    
    public LetterBox() {
        


        label.setOpaque(true);

        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);    
        addActionListener(this);
        setMargin(new Insets(0, 0, 0, 0));
        label.setFont(new Font("Dialog", Font.PLAIN, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(Color.WHITE);
        bg = Color.WHITE;
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getBackground() == Color.YELLOW){
            this.setBackground(Color.GREEN);    
        }
        else if (this.getBackground() == Color.GREEN){
            this.setBackground(Color.GRAY);    
        }
        else if (this.getBackground() == Color.GRAY || this.getBackground() == Color.WHITE){
            this.setBackground(Color.YELLOW);    
        }        
    }
    
    @Override
    public void setBackground(Color bg) {
        this.bg = bg;
        if(label != null) {
            label.setBackground(bg);
        }
    }
    
    @Override
    public Color getBackground() {
        return this.bg;
    }    
    
    @Override
    public void setText(String text) {
        //super.setText(text);
        if(label != null){
            label.setText(text);
        }
    }    

    @Override
    public void setForeground(Color fg) {
        //super.setForeground(fg);
        if(label != null){
            label.setForeground(fg);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Rectangle rectangle = getBounds();
        rectangle.x = 0;
        rectangle.y = 0;
        rectangle.width -= rectangle.x * 2;
        rectangle.height -= rectangle.y * 2;

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(bg);
        g2d.fill(rectangle);
    }   
    
    @Override
    public void setEnabled(boolean b){
        //super.setEnabled(b);
        if (b){
            this.setBackground(currentBg);  
        }
        else{
            currentBg = this.getBackground();
            if (currentBg == Color.WHITE){
                this.setBackground(new Color(224, 224, 224));
            }
            else
            {
                this.setBackground(new Color(currentBg.getRed(), currentBg.getGreen(), currentBg.getBlue(), 40));
            }
        }
        
    }

    
}
