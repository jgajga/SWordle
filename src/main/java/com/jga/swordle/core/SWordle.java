/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jga.swordle.core;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class SWordle {
    
    protected final String  BASE = Utils.getProperty("words.source");
    protected static final Logger logger = LoggerFactory.getLogger(SWordle.class);
    protected int size;
    protected int turns;
    protected String language;
    protected SQLiteDB db;
    protected final List<String> words;
    
    protected String selectedWord;
    protected List<String> conditions;
    
    public int getSize() {
        return size;
    }

    public int getTurns() {
        return turns;
    }

    public String getLanguage() {
        return language;
    }

    public SWordle(int size, int turns, String language) {
        this.size = size;
        this.turns = turns;
        this.language = language;
        db = new SQLiteDB("jdbc:sqlite::memory:");
        this.createDB();

        String content;
        //logger.info(strURL());
        try{
            content = Utils.readURL(strURL());
        }catch (IOException e) {
            content = "Error";
        }        
        //logger.info(content);
        
        this.words = Utils.filterValidWords(Utils.splitByComma(content),this.size);
        
        this.loadDB();
        
        this.conditions = new ArrayList<String>();
        
    }
    
    protected void createDB(){

        //logger.info(strCreateTableWords());
        db.executeUpdate(strCreateTableWords());
        
    }
    
    protected void loadDB(){
        //logger.info("loadDB");
        for (String w: words){
            db.executeUpdate(strInsertWord(w));
            //logger.info("loading: " + w + "(" + strInsertWord(w) +")");
            //System.out.println(strInsertWord(w) + ";");
        }    
        
        
        ResultSet rs = db.executeQuery("SELECT COUNT(*) FROM Words");
        Integer count = 0;
        try {
        while (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        //logger.info("Loaded rows: " + count);
        } catch(SQLException e){
        }
    }
    
    public abstract String getGuess() throws WordleException;
    
    // X: Letter is not in the word
    // O: Letter in correct position
    // ?: Letter in word but at wrong position
    // #: Incorrect word (not found in dictionary)
    public abstract void setGuessResult(String s);
    
    public abstract void reset();
    
    protected String strNotInDictionary(String word){
        StringBuilder sb = new StringBuilder();    
        sb.append("W.Word <> \"");
        sb.append(word);
        sb.append("\"");
        return sb.toString();
    }
    
    protected String strCorrectPositionCondition(int index, String word){
        StringBuilder sb = new StringBuilder();    
        sb.append("W.Letter");
        sb.append(index);
        sb.append(" = \"");
        sb.append(word.substring(index-1, index));  
        sb.append("\"");
        return sb.toString();
    }
    
    protected String strIncorrectPositionCondition(int index, String word){
        StringBuilder sb = new StringBuilder();    
        sb.append("W.Letter");
        sb.append(index);
        sb.append(" <> \"");
        sb.append(word.substring(index-1, index));  
        sb.append("\"");
        sb.append(" AND ("); 
        
        String[] strOrs = new String[this.size-1];
        int count=0;
        for (int i=1; i<=this.size; i++){
            if (i!=index){
                StringBuilder sb2 = new StringBuilder(); 
                sb2.append("W.Letter");
                sb2.append(i);
                sb2.append(" = \"");
                sb2.append(word.substring(index-1, index));  
                sb2.append("\"");   
                strOrs[count] = sb2.toString();
                count++;
            }  
        }
        sb.append(String.join(" OR ", strOrs));
        sb.append(")");

        return sb.toString();
    }
        
        
    protected List<String> lettersInGreenOrYellow(String selected, String answer){
        List<String> lettersIn = new ArrayList<>();
        for (int i=0; i<this.size; i++){
            if (String.valueOf(answer.charAt(i)).equals("=") || String.valueOf(answer.charAt(i)).equals("?")){
                lettersIn.add(String.valueOf(selected.charAt(i)));
            }
        }
        return lettersIn;
    }

    //
    protected String strNotFoundCondition(int index, String word, List<String> lettersInGreenOrYellow){
        StringBuilder sb = new StringBuilder();    

        if (lettersInGreenOrYellow.contains(word.substring(index-1, index))){
            sb.append("W.Letter");    
            sb.append(index); 
            sb.append(" <> \"");  
            sb.append(word.substring(index-1, index));  
            sb.append("\"");  
        }
        else
        {
            String[] strAnds = new String[this.size];  
            for (int j=0; j<this.size; j++){
                StringBuilder sb2 = new StringBuilder(); 
                sb2.append("W.Letter");    
                sb2.append(j+1); 
                sb2.append(" <> \"");  
                sb2.append(word.substring(index-1, index));  
                sb2.append("\"");   
                strAnds[j] = sb2.toString();
            }
            sb.append("(");
            sb.append(String.join(" AND ", strAnds));
            sb.append(")");
        }
        return sb.toString();
        
    }
        
        


    
    protected String strCreateTableWords(){
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
    
    protected String strInsertWord(String word){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO Words VALUES (\"");
        sb.append(word.toUpperCase());
        sb.append("\", ");
        for (int i=1; i<this.size; i++){
            sb.append("\"");
            sb.append(String.valueOf(word.charAt(i-1)).toUpperCase());
            sb.append("\", ");
        }
        sb.append("\"");
        sb.append(String.valueOf(word.charAt(this.size-1)).toUpperCase());
        sb.append("\")");       
        
        return sb.toString();
    }
    
    protected String strURL(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.BASE);
        sb.append("/");
        sb.append(this.language);
        sb.append("/"); 
        sb.append(this.language);
        sb.append(".txt");
        return sb.toString();
    }
    
    protected String strEvaluationQuery()
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
        if (!this.conditions.isEmpty()){
            sb.append(" WHERE ");
            for (String s : this.conditions){
                sb.append(" AND ");    
                sb.append(s); 
            }            
        }
        //logger.info("conditions: " + this.conditions);
        
        
        sb.append(" ORDER BY ");
        
        for (int i=1; i<this.size; i++){
            sb.append("Frequency");
            sb.append(i);
            sb.append(" * ");
        }        
        sb.append("Frequency");
        sb.append(this.size);
        sb.append(" DESC");
        
        //logger.info("strEvaluationQuery():" + sb.toString());
        return sb.toString();
        
    }
    
    
    
    
}

