package com.bedrosians.bedlogic.bedDataAccessDAO;

import java.net.*;
import java.util.*;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;
import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.bedDataAccessDAO.JSONRPCDAO;

public class LocationsDAO
{
    public LocationsDAO()
    {
    }
    
    public JSONObject getLocations()
    {
        JSONRPCDAO  rpcDAO = new JSONRPCDAO();
        String      auth = new String("TODO-Use-User-Code");
        String      locationCodes = "";
        String      emptyStrParam = new String("");
        
        rpcDAO.setMethodName("readLocations");
        rpcDAO.setMethodResultType("locations");
        rpcDAO.addStringParameter(auth);
        rpcDAO.addIntListParameter(locationCodes);
        rpcDAO.addStringParameter(emptyStrParam);
        rpcDAO.addStringParameter(emptyStrParam);
        
        // TODO: Read URL from config
        JSONObject result = rpcDAO.call("http://192.168.56.14:8888/api/v2/server.php");
        
        return result;
    }
}
