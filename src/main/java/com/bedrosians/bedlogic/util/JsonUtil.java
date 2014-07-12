package com.bedrosians.bedlogic.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jettison.json.JSONObject;

import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class JsonUtil {

	public static Object jsonStringToPOJO(String jsonString, Object obj){
		Object object = null; 
		ObjectMapper mapper = new ObjectMapper(); 
		try{
			object = mapper.readValue(jsonString, obj.getClass());
		}
	    catch (JsonGenerationException e) {
			e.printStackTrace();
	    } catch (JsonMappingException e) { 
   		    e.printStackTrace();
   		}
		catch(Exception e){
			e.printStackTrace();
		}
		return object;
	}
	
	public static Object jsonObjectToPOJO(JSONObject jsonObj, Object obj) throws BedDAOBadParamException {
		Object object = null; 
 		ObjectMapper mapper = new ObjectMapper();
		try{
			object = mapper.readValue(jsonObj.toString(), obj.getClass());
			//object = mapper.readValue(mapper.writeValueAsString(jsonObj), obj.getClass());
		}
        catch (JsonGenerationException e) {
	      	e.printStackTrace();
	      	throw new BedDAOBadParamException("JsonGenerationException occured while jsonObjectToPOJO(): " + e.getMessage());
        } 
		catch (JsonMappingException e) { 
		    e.printStackTrace();
		    throw new BedDAOBadParamException("JsonMappingException occured while jsonObjectToPOJO(): " + e.getMessage());
        }
		catch (JsonParseException e) { 
		    e.printStackTrace();
		    throw new BedDAOBadParamException("JsonParseException occured while jsonObjectToPOJO(): " + e.getMessage());
        }
		catch(IOException e){
			e.printStackTrace();
			throw new BedDAOBadParamException("IOException occured while jsonObjectToPOJO(): " + e.getMessage());
		}
		return object;
	}
	
	public static Object jsonFileToPOJO(String fileName, Object obj){
		Object object = null; 
		ObjectMapper mapper = new ObjectMapper();
		try{
			object = mapper.readValue(new File(fileName), obj.getClass());
		}
	    catch (JsonGenerationException e) {
			e.printStackTrace();
	    } catch (JsonMappingException e) { 
   		    e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
     	}
		return object;
	}
	
	public static String PojoToJsonString(Object obj){
		String jsonString = null; 
		ObjectMapper mapper = new ObjectMapper();
		try{
			jsonString = mapper.writeValueAsString(obj.getClass());
		}
        catch (JsonGenerationException e) {
	      	e.printStackTrace();
        } 
		catch (JsonMappingException e) { 
		    e.printStackTrace();
        }
		catch(IOException e){
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public static void PojoToJsonFile(Object obj, String fileName){
		
		ObjectMapper mapper = new ObjectMapper();
		try{
			mapper.writeValue(new File(fileName), obj.getClass());
		}
        catch (JsonGenerationException e) {
	      	e.printStackTrace();
        } 
		catch (JsonMappingException e) { 
		    e.printStackTrace();
        }
		catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	public static Map<String, String> JsonStringToMap(String jsonString){
		Map<String, String> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		try{
			map = mapper.readValue(jsonString, new TypeReference<HashMap<String, String>>(){});
		}
        catch (JsonGenerationException e) {
	      	e.printStackTrace();
        } 
		catch (JsonMappingException e) { 
		    e.printStackTrace();
        }
		catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	public static MultivaluedMap<String, String> JsonStringToMultivaluedMap(String jsonString){
		MultivaluedMap<String, String> map = new  MultivaluedMapImpl();
		ObjectMapper mapper = new ObjectMapper();
		try{
			map = mapper.readValue(jsonString, new TypeReference<MultivaluedMapImpl>(){});
		}
        catch (JsonGenerationException e) {
	      	e.printStackTrace();
        } 
		catch (JsonMappingException e) { 
		    e.printStackTrace();
        }
		catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	public static String mapToJsonString(Map map){
	    String jsonString = null;
		ObjectMapper mapper = new ObjectMapper();
		try{
			jsonString = mapper.writeValueAsString(map);
		}
        catch (JsonGenerationException e) {
	      	e.printStackTrace();
        } 
		catch (JsonMappingException e) { 
		    e.printStackTrace();
        }
		catch(IOException e){
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public static String getItemCode(JSONObject inputJsonObj){
    	String itemCode = null;
    	try {
    		itemCode = inputJsonObj.optString("itemcode") != ""?  inputJsonObj.optString("itemcode") : 
    		         inputJsonObj.optString("itemCode") != ""?  inputJsonObj.optString("itemCode") : 
    		         inputJsonObj.optString("itemcd") != ""?  inputJsonObj.optString("itemcd") :  	
    		         inputJsonObj.optString("itemId");	 
    	}
    	catch(Exception e){
    		e.printStackTrace(); // just swallow it
    	}
    	return itemCode;	         
    }
	
	public static String validateItemCode(JSONObject jsonObj) throws BedDAOBadParamException{
		String itemCode = getItemCode(jsonObj);
		if(itemCode == null || itemCode.trim().length() < 1)
		   throw new BedDAOBadParamException("Product code cannot be empty.");
		if(itemCode.length() > 18)
		   throw new BedDAOBadParamException("Product code cannot be longer that 18 characters.");
		return itemCode.toUpperCase();
	}
	
}
