/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jga.swordle;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 280148
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SWordle sw = new SWordle(5, "es");
        logger.trace("Trace log message");
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.warn("Warn log message");
        logger.error("Error log message");
        try{
        String content = Utils.readURL("https://raw.githubusercontent.com/eymenefealtun/all-words-in-all-languages/main/Japanese/Japanese.txt");
            logger.info(content);
            List<String> words = Utils.splitByComma(content);
            for (String s: words){
                logger.info(s);    
            }
        } catch (IOException e) {
            
        }
        

        



    }     
}
