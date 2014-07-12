package com.bedrosians.bedlogic.util.JsonWrapper;

import java.io.StringWriter;
import java.io.Writer;

import com.bedrosians.bedlogic.exception.BedResException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class GenericJsonConverter
{
    private Object object;
    
    
    public GenericJsonConverter(Object object)
    {
        this.object = object;
    }
    
    public String toJSONStringWithJackson() throws BedResException
    {
       ObjectMapper mapper = new ObjectMapper();
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
