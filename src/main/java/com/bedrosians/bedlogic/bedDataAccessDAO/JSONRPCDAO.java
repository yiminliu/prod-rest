package com.bedrosians.bedlogic.bedDataAccessDAO;

import java.net.*;
import java.util.*;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;
import net.minidev.json.JSONObject;

public class JSONRPCDAO
{
    private String          methodName;
    private String          methodResultType;
    private List<Object>    params;
    
    public JSONRPCDAO()
    {
        this.methodName = "";
        this.methodResultType = "";
        this.params = new ArrayList<Object>();
    }
    
    public void setMethodName(String inMethodName)
    {
        this.methodName = inMethodName;
    }
    
    public void setMethodResultType(String inMethodResultType)
    {
        this.methodResultType = inMethodResultType;
    }
    
    public void addStringParameter(String inParam)
    {
        // TODO: Do we need to make const into object
        this.params.add(inParam);
    }
    
    public void addIntListParameter(String inParam)
    {
        // TODO: Parse comma separated list. Can we pass list of int?
        List<Object>    intList = new ArrayList<Object>();
        this.params.add(intList);
    }
    
    public void addObjectParameter(Object inParam)
    {
        this.params.add(inParam);
    }
    
    public JSONObject call(String inServerURLStr)
    {
        URL         serverURL = null;
        JSONObject  returnResult = null;
        
		try
        {
			serverURL = new URL(inServerURLStr);
		}
        catch (MalformedURLException e)
        {
            ;
		}
        
        if (serverURL != null)
        {
            returnResult = this.call(serverURL);
        }
        
        return returnResult;
    }
    
    public JSONObject call(URL serverURL)
    {
        JSONObject  returnResult = null;
        
        if (!this.methodName.isEmpty())
        {
            try
            {
                // Configure session
                JSONRPC2Session rpcSession = new JSONRPC2Session(serverURL);
                rpcSession.getOptions().enableCompression(true);
                
                // Configure request
                int             requestID = 1;  // TODO: unique request ID
                JSONRPC2Request rpcRequest = new JSONRPC2Request(this.methodName, this.params, requestID);
                
                // Send the request
                JSONRPC2Response rpcResponse = rpcSession.send(rpcRequest);
                
                // Process the response
                if (rpcResponse.indicatesSuccess())
                {
                    JSONObject  resultObject = (JSONObject) rpcResponse.getResult();
                    Number      resultCode = (Number) resultObject.get("resultcode");
                    
                    if (resultCode.intValue() == 0)
                    {
                        resultObject.remove("resultcode");
                        if (!this.methodResultType.isEmpty())
                        {
                            resultObject.put(this.methodResultType, resultObject.get("result"));
                            resultObject.remove("result");
                        }
                        returnResult = resultObject;
                    }
                    else if (resultCode.intValue() == 1)
                    {
                        // TODO Return Unauthorized
                    }
                    else
                    {
                        // TODO Return Parameter Error
                    }
                }
                else
                {
                    // TODO Return internal error
                }
            }
            catch (JSONRPC2SessionException e)
            {
                // TODO;
            }
        }
        
        return returnResult;
    }    
}
