package com.bedrosians.bedlogic.bedDataAccessDAO;

//import java.lang.Integer;
import java.net.*;
import java.util.*;
import javax.ws.rs.core.MultivaluedMap;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;
import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.exception.*;

public class JSONRPCDAO
{    
    private String          methodName;
    private String          methodResultType;
    private List<Object>    params;
    private URL             rpcServerURL;
        
    public JSONRPCDAO()
    {
        this.methodName = "";
        this.methodResultType = "";
        this.params = new ArrayList<Object>();
        this.rpcServerURL = null;
    }
    
    public static JSONRPCDAO Create()
        throws BedDAOException
    {
        JSONRPCDAO newDAO = new JSONRPCDAO();

        // TODO Read URL from config
       // newDAO.setRPCServerURL("http://192.168.56.14:8888/rpc/dataaccess/v2/index.php");
        newDAO.setRPCServerURL("http://192.168.56.10:8888/rpc/dataaccess/v2/index.php");
        return newDAO;
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
        throws BedDAOException
    {
        List<Number>    numberList = new ArrayList<Number>();
        
        if (!inParam.trim().isEmpty())
        {        
            String[]        strList = inParam.split(",");
            try
            {
                for (int index=0; index<strList.length; index++)
                {
                    String numberStr = strList[index].trim();
                    if (!numberStr.isEmpty())
                    {
                        numberList.add(Integer.parseInt(numberStr));
                    }
                    else
                    {
                        throw new BedDAOBadParamException();
                    }
                }
            }
            catch (NumberFormatException e)
            {
                throw new BedDAOBadParamException(e);
            }
        }
        
        this.params.add(numberList);
    }
    
    public void addQueryParameters(MultivaluedMap<String,String> queryParams)
    {
        Map<String,String>    queryParamsMap = new HashMap<String,String>();
        
        for (Map.Entry<String, List<String>> entry : queryParams.entrySet())
        {
            queryParamsMap.put(entry.getKey(), entry.getValue().get(0));
        }
        
        this.params.add(queryParamsMap);
    }
    
    public void addObjectParameter(Object inParam)
    {
        this.params.add(inParam);
    }
    
    public void setRPCServerURL(String inServerURLStr)
        throws BedDAOException
    {
        try
        {
            this.rpcServerURL = new URL(inServerURLStr);
        }
        catch (MalformedURLException e)
        {
            throw new BedDAOInternalException(e);
        }
    }
        
    public JSONObject call()
        throws BedDAOException
    {
        JSONObject  returnResult = null;
        
        try
        {
            // Check for valid method name and url
            if (this.methodName.isEmpty() || this.rpcServerURL == null)
            {
                throw new BedDAOInternalException();
            }

            // Configure session
            JSONRPC2Session rpcSession = new JSONRPC2Session(this.rpcServerURL);
            rpcSession.getOptions().enableCompression(true);
            
            // Configure request
            long             requestID = this.GetNextRequestID();
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
        
        return returnResult;
    }
    
    /*
     * Return the next requestID. Skip 0 as it is an invalid request ID
     */
    private static long nextRequestID = 0;
    private static synchronized long GetNextRequestID()
    {
        if (nextRequestID == Long.MAX_VALUE)
        {
            nextRequestID = 0;
        }
        
        return ++nextRequestID;
    }
}
