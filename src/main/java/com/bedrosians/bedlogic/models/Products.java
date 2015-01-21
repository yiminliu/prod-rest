package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.bedrosians.bedlogic.exception.BedResException;


public class Products
{
    private JSONObject  data;
    
    private Object object;
    
    public Products(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
    
    public Products(Object object)
    {
        this.object = object;
    }
    
    public String toJSONStringWithJackson(String rootName) throws BedResException
    {
       ObjectWriter writer = null;
       final ObjectMapper mapper = new ObjectMapper();
       if(rootName != null && !rootName.isEmpty())
          writer = mapper.writer().withRootName(rootName); 
       else
    	   writer = mapper.writer(); 
       String json = null;
       try{
            json = writer.writeValueAsString(object);
   	   }
       catch(Exception e)
       {
   	      e.printStackTrace();
   	      throw new BedResException(e);
   	   }
       return json;
    }
     
}
