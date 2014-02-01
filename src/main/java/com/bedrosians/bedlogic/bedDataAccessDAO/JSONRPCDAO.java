package com.bedrosians.bedlogic.bedDataAccessDAO;

import java.net.*;
import java.util.*;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;
import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.exception.*;

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
        throws BedDAOException
    {
        JSONObject  returnResult = null;
        
		try
        {
			URL serverURL = new URL(inServerURLStr);
            returnResult = this.call(serverURL);
		}
        catch (MalformedURLException e)
        {
            throw new BedDAOInternalException(e);
		}
                
        return returnResult;
    }
    
    public JSONObject call(URL serverURL)
        throws BedDAOException
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
                    int         resultCode = ((Number) resultObject.get("resultcode")).intValue();
                    
                    if (resultCode == 0)
                    {
                        resultObject.remove("resultcode");
                        if (!this.methodResultType.isEmpty())
                        {
                            resultObject.put(this.methodResultType, resultObject.get("result"));
                            resultObject.remove("result");
                        }
                        returnResult = resultObject;
                    }
                    else if (resultCode == 1)
                    {
                        throw new BedDAOUnAuthorizedException();
                    }
                    else if (resultCode == 2)
                    {
                        throw new BedDAOBadResultException();
                    }
                    else
                    {
                        throw new BedDAOInternalException();
                    }
                }
                else
                {
                    throw new BedDAOInternalException();
                }
            }
            catch (JSONRPC2SessionException e)
            {
                throw new BedDAOInternalException(e);
            }
        }
        
        return returnResult;
    }    
}
