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

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.enums.DesignLook;
import com.bedrosians.bedlogic.domain.item.enums.DesignStyle;
import com.bedrosians.bedlogic.domain.item.enums.Edge;
import com.bedrosians.bedlogic.domain.item.enums.Grade;
import com.bedrosians.bedlogic.domain.item.enums.Status;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceType;
import com.bedrosians.bedlogic.models.Products;
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
	
	public static Object jsonObjectToPOJO(JSONObject jsonObj, Object obj){
		Object object = null; 
		ObjectMapper mapper = new ObjectMapper();
		try{
			//object = mapper.readValue(jsonObj.toString(), obj.getClass());
			object = mapper.readValue(mapper.writeValueAsString(jsonObj), obj.getClass());
		}
        catch (JsonGenerationException e) {
	      	e.printStackTrace();
        } 
		catch (JsonMappingException e) { 
		    e.printStackTrace();
        }
		catch (JsonParseException e) { 
		    e.printStackTrace();
        }
		catch(IOException e){
			e.printStackTrace();
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
	/*
	public static Object PojoToJsonObject(JSONObject jsonObj, Object obj){
		Object object = null; 
		ObjectMapper mapper = new ObjectMapper();
		try{
			object = mapper.writeValue(jsonObj, obj.getClass());
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
		return object;
	}
    */
	
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
	
	public static void mapToJsonFile(Map map, String fileName){
	    
		ObjectMapper mapper = new ObjectMapper();
		try{
			mapper.writeValue(new File(fileName), map);
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
	
	public static void main(String[] args) {
		String fileName="/Users/yiminliu/Documents/workspace/bedlogic-hibernate/src/test/resources/item.json";
		String jString = "{ \"itemCode\" : \"TEST\", "
				+ "\"description\" : \"a test desc\", "
				+ "\"color\" : \"Red\",  "
				+ "\"category\" : \"Tool\",  "
				+ "\"seriesName\": \"test\", "
				+ "\"countryorigin\": \"USA\", "
				+ "\"type\" : \"Test\",  "
				+ "\"category\" : \"Tool\",  "
				+ "\"seriesName\": \"test\", "
				+ "\"countryorigin\": \"USA\", "
				+ "\"length\" : 14,  "
				+ "\"width\" : 4,  "
				+ "\"thickness\": \"4 3/4\", "
				+ "\"nominalLength\": 14, "
				+ "\"nominalWidth\" : 4,  "
				+ "\"nominalThickness\" : \"3.4\",  "
				+ "\"sizeUnits\": \"E\", "
				+ "\"thicknessUnit\": \"E\", "
				+ "\"materialType\" : \"Ceramic\", " 
				+ "\"materialCategory\" : \"Allied\", " 
				//+ "\"materialStyle\": " + "\"" + MaterialStyle.Chair_Rail.getDescription() + "\", " 
				+ "\"materialFeature\": \"Test\", "
				//+ "\"materialClassCode\" : " + "\"" + MaterialClass.Ceramic_Tile_Sourced.getDescription() + "\", " 
						
				//+ "\"taxClass\" : " + "\"" + TaxClass.N.getDescription()+ "\"" +", "
				+ "\"price\" :{\"price\" : 22.4,"
				              +"\"futurePrice\" : 22.4," 
				              +"\"priorPrice\" : 22.4,"
				              +"\"tempPrice\" : 22.4,"
				              //+"\"tempdatefrom\" : new Date()).toString()," 
				             // +"\"tempdatethru\" : new Date()).toString()," 
				              +"\"priceMarginPct\" : 22.4,"
				              +"\"priceRoundAccuracy\" : 4"
				+ "}, "
				+ "\"imsNewFeature\" :{\"grade\" : " + "\"" + Grade.First.getDescription() + "\"" 
				                  + ", \"status\" : " + "\"" + Status.Better.getDescription() + "\"" 
				                  + ", \"designStyle\" : " + "\"" + DesignStyle.Asian.getDescription() + "\"" 
				                  + ", \"designLook\" : " + "\"" + DesignLook.Wood.getDescription() + "\"" 
				                  //+ ", \"body\" : " + "\"" + Body.Double_Loaded.getDescription() + "\"" 
				                  + ", \"edge\" : " + "\"" + Edge.Chiseled.getDescription() + "\"" 
				                  + ", \"surfaceApplication\" : " + "\"" + SurfaceApplication.Silk.getDescription() + "\"" 
				                  + ", \"surfaceType\" : " + "\"" + SurfaceType.Abrasive.getDescription() + "\"" 
				                  + ", \"surfaceFinish\" : " + "\"" + SurfaceFinish.Antiquated.getDescription() + "\"" 
				                  + ", \"warranty\" :  3" 
				                  + ", \"recommendedGroutJointMin\" : 1" 
				                  + ", \"recommendedGroutJointMax\" : 2" 
				                   +" }, "
				+ "\"buyer\" :{ \"productManager\" : \"Joe\", \"buyer\" : \"Tom\" }, "
				+ "\"test\" :{\"waterAbsorption\" : 0.05,"
	                         +"\"scratchResistance\" : 0.05," 
	                         + "\"frostResistance\": \"Y\", "
	                         + "\"chemicalResistance\": \"Y\", "
	                         +"\"peiAbrasion\" : 0.05,"
	                         +"\"scofWet\" : 0.05,"
	                         +"\"breakingStrength\" : 5,"
	                         +"\"scofDry\" : 0.05"
	               +" }, "
				
				+ "\"applications\" :{ \"residential\" : \"FR:WR:CR\", \"commercial\" : \"FC:WC:CC\" } "
			
				+ "}";
		//System.out.println("json string = " + jString);
		JSONObject jsonObj = null;
		
		try{
			jsonObj = new JSONObject(jString);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		Item item = (Item)jsonStringToPOJO(jString, new Item());
		
		//Item item = (Item)jsonObjectToPOJO(jsonObj, new Item());
		//Item item = (Item)jsonFileToPOJO(fileName, new Item());
		
		String parsedJsonStr = null;
        
        Products result = new Products(item);
        try{
        	parsedJsonStr = result.toJSONStringWithJackson();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        System.out.println("items   = " + parsedJsonStr);
        
		//System.out.println("Item = " + item);
		/*
	StringBuilder sb = new StringBuilder();
	sb.append("{
		  \"itemCode\" : \"TEST\",
		  \"Buyer\" { \"productManager\" : \"Joe\", \"buyer\" : \"Tom\" },
		  \"color\" : \"Red\",
		  \"showOnWebsite\" : false,
		  \"price\" : 22.4
		}");
*/
	}

}
