package com.bedrosians.bedlogic.models;

import java.lang.String;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hello
{
  private String first;
  private String last;
  
  public void setFirst(String first)
  {
    this.first = first;
  }
  
  public String getFirst()
  {
    return this.first;
  }
  
  public void setLast(String last)
  {
    this.last = last;
  }
  
  public String getLast()
  {
    return this.last;
  }
  
}