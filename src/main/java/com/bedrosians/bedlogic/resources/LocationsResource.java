package com.bedrosians.bedlogic.resources;

import java.net.*;
import java.util.*;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;
import net.minidev.json.JSONObject;


@Path("/locations")
public class LocationsResource
{
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLocations()
    {
		URL                         serverURL  = null;
        JSONRPC2Session             rpcSession = null;
        JSONRPC2Response            rpcResponse = null;
        Response.ResponseBuilder    responseBuilder = null;
       
        // Create new JSON-RPC 2.0 client session
		try
        {
            // TODO Get URL from config file
			serverURL = new URL("http://192.168.56.14:8888/api/v2/server.php");
            rpcSession = new JSONRPC2Session(serverURL);
		}
        catch (MalformedURLException e)
        {
            responseBuilder = Response.serverError();
		}

        // Send a request
        if (responseBuilder == null)
        {
            try
            {
                String          method = "readLocations";
                List<Object>    params = new ArrayList<Object>();
                String          auth = new String("TODO-Get-From-Header");
                List<Object>    locationCode = new ArrayList<Object>();
                int             requestID = 1;
                String          emptyStrParam = new String("");
                
                params.add(auth);
                params.add(locationCode);
                params.add(emptyStrParam);
                params.add(emptyStrParam);
                JSONRPC2Request request = new JSONRPC2Request(method, params, requestID);
                
                rpcSession.getOptions().enableCompression(true);
                rpcResponse = rpcSession.send(request);
            }
            catch (JSONRPC2SessionException e)
            {
                responseBuilder = Response.serverError();
            }
        }

		// Process the response
		if (responseBuilder == null && rpcResponse.indicatesSuccess())
        {
            JSONObject  resultObject = (JSONObject) rpcResponse.getResult();
            Number      resultCode = (Number) resultObject.get("resultcode");
            
            if (resultCode.intValue() == 0)
            {
                JSONObject responseJSONObject = new JSONObject();
                
                responseJSONObject.put("locations", resultObject.get("result"));
                String json = responseJSONObject.toString();
                responseBuilder = Response.ok(json, MediaType.APPLICATION_JSON);
            }
            else if (resultCode.intValue() == 1)
            {
                // TODO Return Unauthorized
                responseBuilder = Response.serverError();
            }
            else
            {
                // TODO Return Parameter Error
                responseBuilder = Response.serverError();
            }
        }
		else if (responseBuilder == null)
        {
            responseBuilder = Response.serverError();
        }
        
        return responseBuilder.build();
    }
}
