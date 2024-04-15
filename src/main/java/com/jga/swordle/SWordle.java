/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jga.swordle;


public class SWordle {
    int size;
    String language;
    SQLiteDB db;

    public SWordle(int size, String language) {
        this.size = size;
        this.language = language;
        db = new SQLiteDB("jdbc:sqlite::memory:");
        
    }
    
}

