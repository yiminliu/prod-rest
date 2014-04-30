package com.bedrosians.bedlogic.models;

import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.exception.BedResException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.minidev.json.JSONObject;

public class Products
{
    private JSONObject  data;
    
    private Object obj;
    
    public Products(JSONObject inData)
    {
        this.data = inData;
    }
    
    public Products(Object obj)
    {
        this.obj = obj;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
    
    public String toJSONStringWithJackson() throws BedResException
    {
       ObjectMapper mapper = new ObjectMapper();
       //mapper.configure(Feature.WRAP_ROOT_VALUE, true);
       Writer strWriter = new StringWriter(); 
       try{
           mapper.writeValue(strWriter, obj);
   	   }
       catch(Exception e)
       {
   	      e.printStackTrace();
   	      throw new BedResException(e);
   	   }
       return strWriter.toString();
    }
}