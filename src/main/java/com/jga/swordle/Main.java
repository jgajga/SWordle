/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jga.swordle;

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
        

    }     
}
