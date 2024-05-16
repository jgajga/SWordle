
package com.jga.swordle.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

public class Utils {
    // Método estático para leer el texto desde una URL
    public static String readURL(String urlString) throws IOException {
        StringBuilder content = new StringBuilder();
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    
    public static List<String> splitByComma(String input) {
        List<String> result = new ArrayList<>();
        int startIndex = 0;
        int commaIndex;
        while ((commaIndex = input.indexOf(',', startIndex)) != -1) {
            result.add(input.substring(startIndex, commaIndex));
            startIndex = commaIndex + 1;
        }
        // Agregar el substring final después de la última coma
        result.add(input.substring(startIndex));
        return result;
    }
    
    public static List<String> filterValidWords(List<String> words, int size){
        List<String> result = new ArrayList<>();
        for (String w: words)
        {
            if (w.length() == size){
                result.add(w);
            }
        }
        return result;
            
    }
    
    public static String getProperty(String name)
    {
        Properties prop = new Properties();
        String value = "";
        String fileName = "app.config";
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("app.config");
                try {
                    prop.load(is);
                    value = prop.getProperty(name);
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
        return value;
    }
}










