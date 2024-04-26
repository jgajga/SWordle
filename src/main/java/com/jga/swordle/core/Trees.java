/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jga.swordle.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class Trees {
  @JsonProperty("sha")
  public String sha;
  
  @JsonProperty("url")
  public String url;

  @JsonProperty("tree")
  public List<Tree> trees;    
  
  @JsonProperty("truncated")
  public boolean truncated;    
}
