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
    
    public JSONObject getLocations(String userType, String userCode, String locationCodes, String locationRegion, String branchName)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readLocations");
        rpcDAO.setMethodResultType("locations");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addIntListParameter(locationCodes);
        rpcDAO.addStringParameter(locationRegion);
        rpcDAO.addStringParameter(branchName);
        
        JSONObject  result = rpcDAO.call();
        
        return result;
    }
}
