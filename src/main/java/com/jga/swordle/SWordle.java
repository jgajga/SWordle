/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jga.swordle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SWordle {
    
    private static final String  BASE = "https://raw.githubusercontent.com/eymenefealtun/all-words-in-all-languages/main";
    private static final Logger logger = LoggerFactory.getLogger(SWordle.class);
    int size;
    String language;
    SQLiteDB db;
    List<String> words;

    public SWordle(int size, String language) {
        this.size = size;
        this.language = language;
        db = new SQLiteDB("jdbc:sqlite::memory:");
        this.createDB();

        String content;
        logger.info(strURL(BASE, language));
        try{
            content = Utils.readURL(strURL(BASE, language));
        }catch (IOException e) {
            content = "Error";
        }        
        logger.info(content);
        
        this.words = Utils.filterValidWords(Utils.splitByComma(content),this.size);
        
        this.loadDB();
        
    }
    
    private void createDB(){

        logger.info(strCreateTableWords());
        db.executeUpdate(strCreateTableWords());
        

        
    }
    
    private void loadDB(){
        logger.info("loadDB");
        for (String w: words){
            db.executeUpdate(strInsertWord(w));
            logger.info("loading: " + w + "(" + strInsertWord(w) +")");
        }    
        
        
        ResultSet rs = db.executeQuery("SELECT COUNT(*) FROM Words");
        Integer count = 0;
        try {
        while (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        logger.info("Loaded rows: " + count);
        } catch(SQLException e){
        }
    }
    

    
    private String strCreateTableWords(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE Words ( Word TEXT(100), ");
        
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
    
    private String strInsertWord(String word){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO Words VALUES (\"");
        sb.append(word);
        sb.append("\", ");
        for (int i=1; i<this.size; i++){
            sb.append("\"");
            sb.append(String.valueOf(word.charAt(i-1)));
            sb.append("\", ");
        }
        sb.append("\"");
        sb.append(String.valueOf(word.charAt(this.size-1)));
        sb.append("\")");       
        
        return sb.toString();
    }
    
    private String strURL(String base, String language){
        StringBuilder sb = new StringBuilder();
        sb.append(base);
        sb.append("/");
        sb.append(language);
        sb.append("/"); 
        sb.append(language);
        sb.append(".txt");
        return sb.toString();
    }
    
    
    
    
}

