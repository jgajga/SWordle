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
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args)  {
        
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
  
            /*
            SWordle sw = new SWRandom(5, 6, "Spanish");
            String resp = "";
            while (!resp.equals("====="))
            {
            try{
            logger.info("Evaluate: " + sw.getGuess());
            }
            catch(WordleException we){}
            Scanner s = new Scanner(System.in);
            System.out.println("Resp:");
            resp = s.next();
            sw.setGuessResult(resp);
            }
            sw.reset();
            */
            
            /*
            Frm frm = new Frm();
            frm.setVisible(true);
            frm.setSize(600, 600);
        */
        Setup setup = new Setup(); 
        setup.setSize(300, 300);
        setup.setLocationRelativeTo(null);
        
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classloader.getResourceAsStream("images/wordle_icon.jpg");
        ImageIcon icon;
        try {
            icon = new ImageIcon(ImageIO.read(stream));
            setup.setIconImage(icon.getImage());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        setup.setVisible(true);



/*
Properties prop = new Properties();
String fileName = "app.config";
ClassLoader classloader = Thread.currentThread().getContextClassLoader();
InputStream is = classloader.getResourceAsStream("app.config");
        try {
            prop.load(is);
            System.out.println(prop.getProperty("app.name"));
            System.out.println(prop.getProperty("app.version"));
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
}
*/
    }

}
