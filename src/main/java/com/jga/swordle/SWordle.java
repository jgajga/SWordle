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
    
    private final String  BASE = "https://raw.githubusercontent.com/eymenefealtun/all-words-in-all-languages/main";
    private static final Logger logger = LoggerFactory.getLogger(SWordle.class);
    private int size;
    private String language;
    private SQLiteDB db;
    private final List<String> words;

    public SWordle(int size, String language) {
        this.size = size;
        this.language = language;
        db = new SQLiteDB("jdbc:sqlite::memory:");
        this.createDB();

        String content;
        logger.info(strURL());
        try{
            content = Utils.readURL(strURL());
        }catch (IOException e) {
            content = "Error";
        }        
        logger.info(content);
        
        this.words = Utils.filterValidWords(Utils.splitByComma(content),this.size);
        
        this.loadDB();
        
        logger.info(this.strEvaluationQuery());
        
    }
    
    private void createDB(){

        logger.info(strCreateTableWords());
        db.executeUpdate(strCreateTableWords());
        

        
    }
    
    private void loadDB(){
        logger.info("loadDB");
        for (String w: words){
            db.executeUpdate(strInsertWord(w));
            //logger.info("loading: " + w + "(" + strInsertWord(w) +")");
            System.out.println(strInsertWord(w) + ";");
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
    
    private String strURL(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.BASE);
        sb.append("/");
        sb.append(this.language);
        sb.append("/"); 
        sb.append(this.language);
        sb.append(".txt");
        return sb.toString();
    }
    
    private String strEvaluationQuery()
    {
        StringBuilder sb = new StringBuilder(); 
        sb.append("SELECT Word, ");
        for (int i=1; i<=this.size; i++){
            sb.append("Frequency");
            sb.append(i);
            sb.append(", ");
        }
        for (int i=1; i<this.size; i++){
            sb.append("Frequency");
            sb.append(i);
            sb.append(" * ");
        }        
        sb.append("Frequency");
        sb.append(this.size);
        
        sb.append(" FROM Words W");
        
        for (int i=1; i<=this.size; i++){
            sb.append(" LEFT JOIN ");
            sb.append("(SELECT Letter");
            sb.append(i);
            sb.append(", COUNT(Letter");
            sb.append(i);
            sb.append(") AS Frequency");
            sb.append(i);
            sb.append(" FROM Words GROUP BY Letter");
            sb.append(i);
            sb.append(") AS T");
            sb.append(i);
            sb.append(" ON W.Letter");
            sb.append(i);
            sb.append(" = T");
            sb.append(i);
            sb.append(".Letter");
            sb.append(i);           
        }  
        //Where conditions here
        sb.append(" WHERE 1=1");
        sb.append(" ORDER BY ");
        
        for (int i=1; i<this.size; i++){
            sb.append("Frequency");
            sb.append(i);
            sb.append(" * ");
        }        
        sb.append("Frequency");
        sb.append(this.size);
        sb.append(" DESC");
        return sb.toString();
        
    }
    
    
    
    
}

