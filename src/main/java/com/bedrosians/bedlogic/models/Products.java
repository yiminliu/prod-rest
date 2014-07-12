package com.bedrosians.bedlogic.models;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import net.minidev.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.bedrosians.bedlogic.domain.product.Product;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.util.JsonWrapper.ProductWrapper;


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
    
    public String toJSONStringWithJackson() throws BedResException
    {
       final ObjectMapper mapper = new ObjectMapper();
       //mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
       //mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
       final ObjectWriter writer = mapper.writer().withRootName("products"); 
       String json = null;
       try{
    	   //System.out.println("canSerialize(Product.class)? = " + writer.withType(Product.class).canSerialize(Product.class));
    	   //writer.with(SerializationFeature.WRAP_ROOT_VALUE).withFeatures(SerializationFeature.WRAP_ROOT_VALUE).withView(Product.class).withRootName("product");
           json = writer.writeValueAsString(object);
   	   }
       catch(Exception e)
       {
   	      e.printStackTrace();
   	      throw new BedResException(e);
   	   }
       return json;
    }
    
    public String toJSONStringWithJacksonWithRootName() throws BedResException
    {
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
    
    
     
}
