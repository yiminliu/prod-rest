package com.bedrosians.bedlogic.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.exception.DataMappingException;
import com.bedrosians.bedlogic.exception.InputParamException;
import com.bedrosians.bedlogic.resources.config.JacksonObjectMapperProvider;


public class JsonUtil {

	public static Object jsonStringToPOJO(String jsonString, Object object){
		ObjectMapper mapper = new JacksonObjectMapperProvider().getContext(Object.class); 
		try{
			object = mapper.readValue(jsonString, Object.class);
		}
		catch (JsonProcessingException e) {
	      	throw new DataMappingException("JsonGenerationException occured during jsonStringToPOJO: " + e.getMessage(), e);
        } 
		catch(IOException e){
			throw new InputParamException("IOException occured during jsonStringToPOJO: " + e.getMessage(), e);
		}
		return object;
	}
	
	public static Object jsonObjectToPOJO(ObjectNode jsonObj, Object obj) {
		Object object = null; 
 		ObjectMapper mapper = new ObjectMapper();
		try{
			object = mapper.readValue(jsonObj.toString(), obj.getClass());
			//object = mapper.readValue(mapper.writeValueAsString(jsonObj), obj.getClass());
		}
        catch (JsonProcessingException e) {
	      	throw new DataMappingException("JsonGenerationException occured during jsonObjectToPOJO(): " + e.getMessage(), e);
        } 
		catch(IOException e){
			throw new InputParamException("IOException occured during jsonObjectToPOJO(): " + e.getMessage(), e);
		}
		return object;
	}
	
	public static Object jsonFileToPOJO(String fileName, Object obj){
		Object object = null; 
		ObjectMapper mapper = new ObjectMapper();
		try{
			object = mapper.readValue(new File(fileName), obj.getClass());
		}
		catch (JsonProcessingException e) { 
		    throw new DataMappingException("JsonGenerationException occured during jsonObjectToPOJO(): " + e.getMessage(), e);
	   	} 
		catch (IOException e) {
    		e.printStackTrace();
     	}
		return object;
	}
	
	public static String pojoToJsonString(Object obj){
		String jsonString = null; 
		ObjectMapper mapper = new ObjectMapper();
	    try{
		    jsonString = mapper.writeValueAsString(obj);
		}
        catch (JsonProcessingException e) { 
		    throw new DataMappingException("JsonGenerationException occured during jsonObjectToPOJO(): " + e.getMessage(), e);
        }
		return jsonString;
	}
	
	public static void pojoToJsonFile(Object obj, String fileName){
		
		ObjectMapper mapper = new ObjectMapper();
		try{
			mapper.writeValue(new File(fileName), obj);
		}
	    catch (JsonProcessingException e) { 
		    throw new DataMappingException("JsonGenerationException occured during jsonObjectToPOJO(): " + e.getMessage(), e);
	    }
		catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	public static String getItemCode(ObjectNode inputJsonObj){
    	String itemCode = null;
    	try {
    		itemCode = inputJsonObj.get("itemcode").textValue() != ""?  inputJsonObj.get("itemcode").textValue() : 
    		         inputJsonObj.get("itemcode").textValue() != ""?  inputJsonObj.get("itemcode").textValue() : 
    		         inputJsonObj.get("itemcode").textValue() != ""?  inputJsonObj.get("itemcode").textValue() :  	
    		         inputJsonObj.get("itemcode").textValue();	 
    	}
    	catch(Exception e){
    		e.printStackTrace(); // just swallow it
    	}
    	return itemCode;	         
    }
	
	public static String validateItemCode(ObjectNode jsonObj){
		String itemCode = getItemCode(jsonObj);
		if(itemCode == null || itemCode.trim().length() < 1)
		   throw new InputParamException("Item code cannot be empty.");
		if(itemCode.length() > 18)
		   throw new InputParamException("Item code cannot be longer that 18 characters.");
		return itemCode.toUpperCase();
	}

	public static JsonNode jsonStringToJsonNode(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = null;
		try{
	       jsonNode = mapper.readTree(jsonString);
		}
		catch(JsonProcessingException e){
		   //throw e;
		}
	    catch(IOException e){
		   //throw e;
	    }
	    return jsonNode;
	}
	
	public static JsonNode getSubJsonNode(String jsonString, String fieldName) {
		JsonNode rootNode = jsonStringToJsonNode(jsonString);
		JsonNode subNode = rootNode.get(fieldName);
		return subNode;
	}	
		
}
