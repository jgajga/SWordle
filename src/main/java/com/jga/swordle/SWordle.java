/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jga.swordle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SWordle {
    
    private static Logger logger = LoggerFactory.getLogger(SWordle.class);
    int size;
    String language;
    SQLiteDB db;

    public SWordle(int size, String language) {
        this.size = size;
        this.language = language;
        db = new SQLiteDB("jdbc:sqlite::memory:");
        this.createDB();
        
        
    }
    
    private void createDB(){

        logger.info(stringCreateTableWords());
        db.executeUpdate(stringCreateTableWords());
        
        logger.info(stringCreateTableWordsN());
        db.executeUpdate(stringCreateTableWordsN());
        

    }
    
    private void loadDB(){
        
    }
    
    private String stringCreateTableWords(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE Words ( Word TEXT(100))");
        return sb.toString();
    }
    
    private String stringCreateTableWordsN(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE WordsN ( Word TEXT(100), ");
        
        for (int i=1; i<this.size; i++){
            sb.append("Letter");
            sb.append(i);
            sb.append(" TEXT(1), ");
        }
        sb.append("Letter");
        sb.append(this.size);
        sb.append(" TEXT(1))");  
        return sb.toString();
    }
    
}

