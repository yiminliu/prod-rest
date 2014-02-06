package com.bedrosians.bedlogic.bedDataAccessDAO;

import javax.ws.rs.core.MultivaluedMap;
import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.SlabCosts;

public class SlabCostsDAO
{
    public SlabCostsDAO()
    {
    }
    
    public SlabCosts getSlabCosts(String userType, String userCode, String itemCode, String locationCode, String serialNumber, MultivaluedMap<String,String> queryParams)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readSlabCosts");
        rpcDAO.setMethodResultType("slabcosts");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addStringParameter(itemCode);
        rpcDAO.addStringParameter(locationCode);
        rpcDAO.addStringParameter(serialNumber);
        rpcDAO.addQueryParameters(queryParams);
        
        JSONObject  result = rpcDAO.call();
        
        return new SlabCosts(result);
    }
}
