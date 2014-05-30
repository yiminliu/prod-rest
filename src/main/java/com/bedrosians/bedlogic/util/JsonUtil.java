package com.bedrosians.bedlogic.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jettison.json.JSONObject;

import com.bedrosians.bedlogic.domain.item.ColorHue;
import com.bedrosians.bedlogic.domain.item.IconCollection;
import com.bedrosians.bedlogic.domain.item.ImsNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.ItemVendor;
import com.bedrosians.bedlogic.domain.item.embeddable.Applications;
import com.bedrosians.bedlogic.domain.item.embeddable.Cost;
import com.bedrosians.bedlogic.domain.item.embeddable.Dimensions;
import com.bedrosians.bedlogic.domain.item.embeddable.Material;
import com.bedrosians.bedlogic.domain.item.embeddable.Notes;
import com.bedrosians.bedlogic.domain.item.embeddable.PackagingInfo;
import com.bedrosians.bedlogic.domain.item.embeddable.Price;
import com.bedrosians.bedlogic.domain.item.embeddable.PriorVendor;
import com.bedrosians.bedlogic.domain.item.embeddable.Purchasers;
import com.bedrosians.bedlogic.domain.item.embeddable.Series;
import com.bedrosians.bedlogic.domain.item.embeddable.SimilarItemCode;
import com.bedrosians.bedlogic.domain.item.embeddable.TestSpecification;
import com.bedrosians.bedlogic.domain.item.embeddable.Units;
import com.bedrosians.bedlogic.domain.item.embeddable.VendorInfo;
import com.bedrosians.bedlogic.domain.item.enums.DesignLook;
import com.bedrosians.bedlogic.domain.item.enums.DesignStyle;
import com.bedrosians.bedlogic.domain.item.enums.Edge;
import com.bedrosians.bedlogic.domain.item.enums.Grade;
import com.bedrosians.bedlogic.domain.item.enums.Status;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceType;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
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
	
	public static String validateItemCode(JSONObject jsonObj) throws BedDAOBadParamException{
		String itemCode = getItemCode(jsonObj);
		if(itemCode == null || itemCode.trim().length() < 1)
		   throw new BedDAOBadParamException("Item code cannot be empty.");
		if(itemCode.length() > 18)
		   throw new BedDAOBadParamException("Item code cannot be longer that 18 characters.");
		return itemCode.toUpperCase();
	}
	
	
	public static void main(String[] args) {
		String fileName="/Users/yiminliu/Documents/workspace/bedlogic-hibernate/src/test/resources/item.json";

	    String jString = "{\"itemcode\":\"ASCATHASH22MO\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\",\"itemdesc2\":\"2x2 Mosaic\"},"
	    		+ "\"series\":{\"seriesname\":\"Athena\",\"seriescolor\":\"Ash\"},"
	    		+ "\"material\":{\"materialtype\":\"Porcelain\",\"materialcategory\":\"Trim\",\"materialclass\":\"CTSRC\",\"materialstyle\":\"FW\",\"materialfeature\":\"\"},"
	    		+ "\"shadevariation\":\"V2\",\"colorhues\":[\"BEIGE\"],"
	    		+ "\"dimensions\":{\"nominallength\":12.0,\"nominalwidth\":12.0,\"sizeunits\":\"E\",\"thickness\":\"3/8\",\"thicknessunit\":\"E\",\"length\":\"11-13/16\",\"width\":\"11-13/16\",\"nominalthickness\":0.0},"
	    		+ "\"price\":{\"listprice\":18.3800,\"sellprice\":18.3800,\"pricegroup\":\"\",\"priceunit\":\"SHT\",\"sellpricemarginpct\":2.0,\"sellpriceroundaccuracy\":2,\"listpricemarginpct\":0.0,\"minmarginpct\":15.0,\"futuresell\":0.0000,\"priorsellprice\":14.7000,\"tempprice\":0.0000,\"tempdatefrom\":null,\"tempdatethru\":null,\"priorlistprice\":0.0000},"
	    		+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"
	    		+ "\"testSpecification\":{\"waterabsorption\":0.0,\"scratchresistance\":0.0,\"frostresistance\":\"\",\"chemicalresistance\":\"\",\"peiabrasion\":0.0,\"scofwet\":0.0,\"scofdry\":0.0,\"breakingstrength\":0,\"scratchstandard\":\"\",\"breakingstandard\":\"\",\"restricted\":\"N\",\"warpage\":\" \",\"wedging\":\" \",\"dcof\":0.0,\"thermalshock\":\" \",\"bondstrength\":\"\",\"greenfriendly\":\"N\",\"moh\":0.0,\"leadpoint\":\"N\",\"preconsummer\":0.0,\"posconsummer\":0.0},"
	    		+ "\"relateditemcodes\":null,\"purchasers\":{\"purchaser\":\"ALICIAB\",\"purchaser2\":\"GFIL\"},"
	    		+ "\"packaginginfo\":{\"boxPieces\":4.0,\"boxSF\":0.0,\"boxWeight\":16.8,\"palletBox\":60.0,\"palletSF\":0.0,\"palletWeight\":1007.99994},"
	    		+ "\"notes\":null,\"newNoteSystem\":[],"
	    		+ "\"productline\":\"\",\"iconsystem\":\"NNNNNNNNNNNNNNNNNNNN\",\"newIconSystem\":null,"
	    		+ "\"applications\":{\"residential\":\"FR:WR:CR:SR:PR\",\"lightcommercial\":\"FL:WL:CL:SL:PL\",\"commercial\":\"FC:WC:CC:SC:PC\"},"
	    		+ "\"units\":{\"stdunit\":\"SHT\",\"stdratio\":1.0,\"ordunit\":\"SHT\",\"ordratio\":1.0,"
	    		            + "\"baseunit\":\"SHT\",\"baseisstdsell\":\"Y\",\"baseisstdord\":\"Y\","
	    		            + "\"baseisfractqty\":\"N\","
	    		            + "\"baseispackunit\":\"Y\","
	    		            + "\"baseupc\":0,"
	    		            + "\"baseupcdesc\":\"\","
	    				    + "\"basevolperunit\":0.0000,\"basewgtperunit\":4.2000,\"unit1unit\":\"CTN\",\"unit1ratio\":4.0,\"unit1isstdsell\":\"N\",\"unit1isstdord\":\"N\",\"unit1isfractqty\":\"N\",\"unit1ispackunit\":\"Y\",\"unit1upc\":0,\"unit1upcdesc\":\"\",\"unit1wgtperunit\":17.4000,\"unit2unit\":\"PLT\",\"unit2ratio\":240.0,\"unit2isstdsell\":\"N\",\"unit2isstdord\":\"N\",\"unit2isfractqty\":\"N\",\"unit2ispackunit\":\"N\",\"unit2upc\":0,\"unit2upcdesc\":\"\",\"unit2wgtperunit\":1070.0000,\"unit3unit\":\"\",\"unit3ratio\":0.0,\"unit3isstdsell\":\"N\",\"unit3isstdord\":\"N\",\"unit3isfractqty\":\"N\",\"unit3ispackunit\":\"N\",\"unit3upc\":0,\"unit3upcdesc\":\"\",\"unit3wgtperunit\":0.0000,\"unit4unit\":\"\",\"unit4ratio\":0.0,\"unit4isstdsell\":\"N\",\"unit4isstdord\":\"N\",\"unit4isfractqty\":\"N\",\"unit4ispackunit\":\"N\",\"unit4upc\":0,\"unit4upcdesc\":\"\",\"unit4wgtperunit\":0.0000},"
	    		+ "\"showonwebsite\":\"Y\",\"itemtypecd\":\"#\",\"abccd\":\"C\",\"itemcd2\":\"\",\"inventoryItemcd\":\"\",\"showonalysedwards\":\"N\",\"offshade\":\"N\",\"printlabel\":\" \",\"taxclass\":\"T\",\"lottype\":\"\",\"updatecd\":\"CERA-CRD\",\"directship\":\" \",\"dropdate\":null,\"itemgroupnbr\":0,\"priorlastupdated\":\"2014-03-31\",\"imsNewFeature\":null,\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134585}],\"vendors\":{\"vendornbr\":0,\"vendornbr1\":134585,\"vendornbr2\":0,\"vendornbr3\":0,\"vendorxrefcd\":\"ATM40\",\"vendorlistprice\":4.1500,\"vendorpriceunit\":\"SHT\",\"vendorfob\":\"\",\"vendordiscpct\":0.0,\"vendorroundaccuracy\":2,\"vendornetprice\":4.1500,\"vendormarkuppct\":0.0,\"vendorfreightratecwt\":0.0,\"dutypct\":0.0,\"leadtime\":60,\"vendorLandedBaseCost\":4.1500,\"vendordiscpct2\":0.0,\"vendordiscpct3\":0.0},"
	    		+ "\"cost\":{\"cost1\":0.0000,\"priorcost\":0.0000,"
	    		+ "\"priorcost1\":0.0000,\"futurecost\":0.0000,"
	    		+ "\"futurecost1\":0.0000,"
	    		+ "\"poincludeinvendorcost\":\"Y\","
	    		+ "\"nonstockcostpct\":0.0,\"costrangepct\":0.0},\"priorVendor\":null}";
	    
		//System.out.println(\"json string = \" + jString);
	    String jString2 = "{\"itemcode\":\"TEST1690\",\"itemcategory\":\"BRECCIA\",\"countryorigin\":\"\",\"inactivecode\":\"\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"\",\"itemdesc1\":\"This is a test\",\"itemdesc2\":\"\"},"
	    		+ "\"series\":{\"seriesname\":\"test\",\"seriescolor\":\"White\"},"
	    		+ "\"material\":{\"materialtype\":\"\",\"materialcategory\":\"\",\"materialclass\":\"\",\"materialstyle\":\"\",\"materialfeature\":\"\"},"
	    		+ "\"shadevariation\":\"\",\"colorhues\":[\"\"],"
	    		+ "\"dimensions\":{\"nominallength\":0.0,\"nominalwidth\":0.0,\"sizeunits\":\"\",\"thickness\":\"\",\"thicknessunit\":\"\",\"length\":\"\",\"width\":\"\",\"nominalthickness\":0.0},"
	    		+ "\"price\":{\"listprice\":0.0000,\"sellprice\":0.0000,\"pricegroup\":\"\",\"priceunit\":\"PCS\",\"sellpricemarginpct\":0.0,\"sellpriceroundaccuracy\":0,\"listpricemarginpct\":0.0,\"minmarginpct\":0.0,\"futuresell\":0.0000,\"priorsellprice\":0.0000,\"tempprice\":0.0000,\"tempdatefrom\":null,\"tempdatethru\":null,\"priorlistprice\":0.0000},"
	    		+ "\"usage\":[\"\"],"
	    		+ "\"testSpecification\":{\"waterabsorption\":0.0,\"scratchresistance\":0.0,\"frostresistance\":\"\",\"chemicalresistance\":\"\",\"peiabrasion\":0.0,\"scofwet\":0.0,\"scofdry\":0.0,\"breakingstrength\":0,\"scratchstandard\":\"\",\"breakingstandard\":\"\",\"restricted\":\" \",\"warpage\":\" \",\"wedging\":\" \",\"dcof\":0.0,\"thermalshock\":\" \",\"bondstrength\":\"\",\"greenfriendly\":\" \",\"moh\":0.0,\"leadpoint\":\"\",\"preconsummer\":0.0,\"posconsummer\":0.0},"
	    		+ "\"relateditemcodes\":{\"similaritemcd1\":\"\",\"similaritemcd2\":\"\",\"similaritemcd3\":\"\",\"similaritemcd4\":\"\",\"similaritemcd5\":\"\",\"similaritemcd6\":\"\",\"similaritemcd7\":\"\"},"
	    		+ "\"purchasers\":{\"purchaser\":\"\",\"purchaser2\":\"\"},"
	    		+ "\"packaginginfo\":{\"boxPieces\":0.0,\"boxSF\":0.0,\"boxWeight\":0.0,\"palletBox\":0.0,\"palletSF\":0.0,\"palletWeight\":0.0},"
	    		+ "\"notes\":{\"ponotes\":\"\",\"notes1\":\"\",\"notes2\":\"\",\"notes3\":\"\"},"
	    		+ "\"newNoteSystem\":[],"
	    		+ "\"productline\":\"\","
	    		+ "\"iconsystem\":\"\","
	    		+ "\"newIconSystem\":null,"
	    		+ "\"applications\":{\"residential\":\"\",\"lightcommercial\":\"\",\"commercial\":\"\"},"
	    		+ "\"units\":{\"stdunit\":\"PCS\",\"stdratio\":1.0,\"ordunit\":\"PCS\",\"ordratio\":1.0,\"baseunit\":\"PCS\",\"baseisstdsell\":\" \",\"baseisstdord\":\" \",\"baseisfractqty\":\" \",\"baseispackunit\":\" \",\"baseupc\":0,\"baseupcdesc\":\"\",\"basevolperunit\":0.0000,\"basewgtperunit\":0.0000,\"unit1unit\":\"\",\"unit1ratio\":0.0,\"unit1isstdsell\":\" \",\"unit1isstdord\":\" \",\"unit1isfractqty\":\" \",\"unit1ispackunit\":\" \",\"unit1upc\":0,\"unit1upcdesc\":\"\",\"unit1wgtperunit\":0.0000,\"unit2unit\":\"\",\"unit2ratio\":0.0,\"unit2isstdsell\":\" \",\"unit2isstdord\":\" \",\"unit2isfractqty\":\" \",\"unit2ispackunit\":\" \",\"unit2upc\":0,\"unit2upcdesc\":\"\",\"unit2wgtperunit\":0.0000,\"unit3unit\":\"\",\"unit3ratio\":0.0,\"unit3isstdsell\":\" \",\"unit3isstdord\":\" \",\"unit3isfractqty\":\" \",\"unit3ispackunit\":\" \",\"unit3upc\":0,\"unit3upcdesc\":\"\",\"unit3wgtperunit\":0.0000,\"unit4unit\":\"\",\"unit4ratio\":0.0,\"unit4isstdsell\":\" \",\"unit4isstdord\":\" \",\"unit4isfractqty\":\" \",\"unit4ispackunit\":\" \",\"unit4upc\":0,\"unit4upcdesc\":\"\",\"unit4wgtperunit\":0.0000},"
	    		+ "\"showonwebsite\":\" \",\"itemtypecd\":\" \",\"abccd\":\"\",\"itemcd2\":\"\",\"inventoryItemcd\":\"\",\"showonalysedwards\":\" \",\"offshade\":\" \",\"printlabel\":\" \",\"taxclass\":null,\"lottype\":\"\",\"updatecd\":\"\",\"directship\":\" \",\"dropdate\":null,\"itemgroupnbr\":0,\"priorlastupdated\":null,"
	    		+ "\"imsNewFeature\":{\"grade\":\"First\",\"status\":\"Good\",\"body\":\"Red_Body\",\"edge\":\"Tumbled\",\"mpsCode\":\"Drop\",\"designLook\":\"Wood\",\"designStyle\":\"Modern\",\"surfaceApplication\":\"Silk\",\"surfaceType\":\"Cross_Cut\",\"surfaceFinish\":\"Antiquated\",\"warranty\":3,\"recommendedGroutJointMin\":\"1\",\"recommendedGroutJointMax\":\"2\",\"createdDate\":\"2014-05-14\",\"launchedDate\":null,\"droppedDate\":null,\"version\":0},"
	    		+ "\"newVendorSystem\":[],"
	    		+ "\"vendors\":{\"vendornbr\":0,\"vendornbr1\":0,\"vendornbr2\":0,\"vendornbr3\":0,\"vendorxrefcd\":\"\",\"vendorlistprice\":0.0000,\"vendorpriceunit\":\"PCS\",\"vendorfob\":\"\",\"vendordiscpct\":0.0,\"vendorroundaccuracy\":1,\"vendornetprice\":0.0000,\"vendormarkuppct\":0.0,\"vendorfreightratecwt\":0.0,\"dutypct\":0.0,\"leadtime\":0,\"vendorLandedBaseCost\":0.0000,\"vendordiscpct2\":0.0,\"vendordiscpct3\":0.0},\"cost\":{\"cost1\":0.0000,\"priorcost\":0.0000,\"priorcost1\":0.0000,\"futurecost\":0.0000,\"futurecost1\":0.0000,\"poincludeinvendorcost\":\" \",\"nonstockcostpct\":0.0,\"costrangepct\":0.0},"
	    		+ "\"priorVendor\":{\"priorvendorpriceunit\":\"\",\"priorvendorfob\":\"\",\"priorvendorlistprice\":0.0000,\"priorvendordiscpct1\":0.0,\"priorvendorroundaccuracy\":0,\"priorvendornetprice\":0.0000,\"priorvendormarkuppct\":0.0,\"priorvendorfreightratecwt\":0.0,\"priorvendorlandedbasecost\":0.0000}"
	    		+ "}";
		JSONObject jsonObj = null;
		
		try{
			jsonObj = new JSONObject(jString2);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		Item item = (Item)jsonStringToPOJO(jString2, new Item());
		ImsNewFeature newFeature =   item.getImsNewFeature();
		System.out.println("units = "+ newFeature.toString());
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
        //System.out.println("items   = " + parsedJsonStr);
        
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

	/*String jString = "{ \"itemcode\" : \"TEST\", "
	+ "\"description\" : \"a test desc\", "
	+ "\"color\" : \"Red\",  "
	+ "\"category\" : \"Tool\",  "
	+ "\"seriesName\": \"test\", "
	+ "\"countryorigin\": \"USA\", "
	+ "\"type\" : \"Test\",  "
	+ "\"category\" : \"Tool\",  "
	+ "\"seriesname\": \"test\", "
	+ "\"countryorigin\": \"USA\", "
	+ "\"length\" : 14,  "
	+ "\"width\" : 4,  "
	+ "\"thickness\": \"4 3/4\", "
	+ "\"nominalLength\": 14, "
	+ "\"nominalwidth\" : 4,  "
	+ "\"nominalthickness\" : \"3.4\",  "
	+ "\"sizeunits\": \"E\", "
	+ "\"thicknessunit\": \"E\", "
	+ "\"materialtype\" : \"Ceramic\", " 
	+ "\"materialcategory\" : \"Allied\", " 
	//+ "\"materialStyle\": " + "\"" + MaterialStyle.Chair_Rail.getDescription() + "\", " 
	+ "\"materialfeature\": \"Test\", "
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

	+ "}";*/
}
