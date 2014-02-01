package com.bedrosians.bedlogic.bedDataAccessDAO;

import java.net.*;
import java.util.*;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;
import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.bedDataAccessDAO.JSONRPCDAO;
import com.bedrosians.bedlogic.exception.BedDAOException;

public class LocationsDAO
{
    public LocationsDAO()
    {
    }
    
    public JSONObject getLocations()
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = new JSONRPCDAO();
        String      userType = "guest";
        String      userCode = "";
        String      locationCodes = "";
        String      locationRegion = "";
        String      branchName = "";
        
        rpcDAO.setMethodName("readLocations");
        rpcDAO.setMethodResultType("locations");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addIntListParameter(locationCodes);
        rpcDAO.addStringParameter(locationRegion);
        rpcDAO.addStringParameter(branchName);
        
        // TODO: Read URL from config
        JSONObject  result = rpcDAO.call("http://192.168.56.14:8888/api/dataaccess/v2/server.php");
        
        return result;
    }
}
