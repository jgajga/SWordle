/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jga.swordle.core;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jga.swordle.gui.Frm;

/**
 *
 * @author 280148
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        /*
        SWordle sw = new SWordle(5, "Spanish");
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
        Frm frm = new Frm(); 
        frm.setVisible(true);
        frm.setSize(600, 600);
    }     
}
