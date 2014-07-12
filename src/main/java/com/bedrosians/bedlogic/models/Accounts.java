package com.bedrosians.bedlogic.models;

import java.io.StringWriter;
import java.io.Writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.exception.BedResException;

public class Accounts
{
    private JSONObject  data;
    private Object object;
    
    public Accounts(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
    
    public Accounts(Object object)
    {
        this.object = object;
    }
    
    public String toJSONStringWithJackson() throws BedResException
    {
       final ObjectMapper mapper = new ObjectMapper();
       final ObjectWriter writer = mapper.writer().withRootName("accounts");
       mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
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
    
    
    public String toJSONStringWithJacksonWithRootName() throws BedResException {
       final ObjectMapper mapper = new ObjectMapper();
       mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
       Writer strWriter = new StringWriter(); 
       try{
           mapper.writeValue(strWriter, object);
   	   }
       catch(Exception e)
       {
   	      e.printStackTrace();
   	      throw new BedResException(e);
   	   }
       return strWriter.toString();
    }
    
    /*
    public String toJSONStringWithJackson() throws BedResException
    {
       ObjectMapper mapper = new ObjectMapper();
       Writer strWriter = new StringWriter(); 
       try{
           mapper.writeValue(strWriter, object);
   	   }
       catch(Exception e)
       {
   	      e.printStackTrace();
   	      throw new BedResException(e);
   	   }
       return strWriter.toString();
    }
    */
     
}
