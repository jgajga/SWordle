/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jga.swordle.engines;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jga.swordle.core.*;

public class SWRandom extends SWordle{
    
    

    public SWRandom(int size, int turns, String language) {
        super(size, turns, language);
    }
    

    @Override
    public String getGuess() throws WordleException{
        ResultSet rs = db.executeQuery(this.strEvaluationQuery());
        String word = "";
        try {
            if (rs.next()) {
                word = rs.getString(1);
            }
            else
            {
                throw new WordleException("EVALUATION_ERROR", "The evaluation function did not suggest any words");
            }
        rs.close();
        logger.info("SelectedWord: " + word);
        } catch(SQLException e){
        }
        this.selectedWord = word;
        return word;
        
    }
    
    // X: Letter is not in the word
    // O: Letter in correct position
    // ?: Letter in word but at wrong position
    // #: Incorrect word (not found in dictionary)
    @Override
    public void setGuessResult(String s){
        if (s.equals("#"))
        {
            conditions.add(strNotInDictionary(this.selectedWord));
        }
        else
        {
            for (int i=1; i<=this.size; i++)
            {
                switch(s.substring(i-1, i)){
                    case "=" -> conditions.add(strCorrectPositionCondition(i,this.selectedWord));
                    case "?" -> conditions.add(strIncorrectPositionCondition(i,this.selectedWord));
                    default -> {
                        List<String> l = lettersInGreenOrYellow(this.selectedWord, s);
                        conditions.add(strNotFoundCondition(i, this.selectedWord, l));
                    }
                }
            }
        }
        
    }
    
    @Override
    public void reset(){
        selectedWord = "";
        conditions  = new ArrayList<String>();
    }

    
    @Override
    protected String strEvaluationQuery()
    {
        StringBuilder sb = new StringBuilder(); 
        sb.append(" SELECT Word FROM Words W");
        //Where conditions here
        if (!this.conditions.isEmpty()){
            sb.append(" WHERE 1=1 ");
            for (String s : this.conditions){
                
                sb.append(" AND ");    
                sb.append(s); 
            }            
        }
        logger.info("conditions: " + this.conditions);
        
        sb.append(" ORDER BY RANDOM()");
        
        logger.info("strEvaluationQuery():" + sb.toString());
        return sb.toString();
        
    }
    
    
    
    
}

