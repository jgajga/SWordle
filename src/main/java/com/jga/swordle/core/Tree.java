/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jga.swordle.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 280148
 */
public class Tree {
  @JsonProperty("path") 
  public String path;
  
  @JsonProperty("mode") 
  public String mode;    
  
  @JsonProperty("type") 
  public String type;
  
  @JsonProperty("sha") 
  public String sha;
  
  @JsonProperty("size") 
  public String size;
  
  @JsonProperty("url") 
  public String url;   
}
