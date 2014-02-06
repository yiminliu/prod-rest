package com.bedrosians.bedlogic.bedDataAccessDAO;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;
import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.bedDataAccessDAO.JSONRPCDAO;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.Locations;

public class LocationsDAO
{
    public LocationsDAO()
    {
    }
    
    public Locations getLocations(String userType, String userCode, String locationCodes, String locationRegion, String branchName)
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
        
        return new Locations(result);
    }
}
