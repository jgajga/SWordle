/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jga.swordle.core;

import com.jga.swordle.engines.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jga.swordle.gui.Frm;
import com.jga.swordle.gui.Setup;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author 280148
 */
public class MainCLI {
    private static Logger logger = LoggerFactory.getLogger(MainCLI.class);

    public static void main(String[] args)  {
        
    Scanner s = new Scanner(System.in);
    System.out.println("Select Language:");
    String language = s.next();
    
    System.out.println("Select Size:");
    int size = s.nextInt();
    
    System.out.println("Select Turns:");
    int turns = s.nextInt();
    
    System.out.println("Select Engine: (1:Direct-Random, 2:Direct-Stat)");
    String engine = s.next();    
    SWordle sw;
    sw = switch (engine) {
            case "1" -> new SWRandom(size, turns, language);
            default -> new SWDirect(size, turns, language);
        };
  
    String resp = "";
    int hints = 0;
    while (!resp.equals("=".repeat(size)) )
    {
        hints++;
        try{
        System.out.println("Guess: " + sw.getGuess());
        }
        catch(WordleException we){}

        System.out.println("Response:");
        resp = s.next();
        sw.setGuessResult(resp);
        if (resp.equals("=".repeat(size))){
            System.out.println("Correct!! Won in " + hints + " turns");  
            break;
        }
        if (hints == 6){
            System.out.println("Game over. Solution not found");    
        }
    }
    
            
            

    }

}
