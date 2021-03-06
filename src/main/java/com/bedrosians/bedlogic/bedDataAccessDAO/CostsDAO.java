package com.bedrosians.bedlogic.bedDataAccessDAO;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;
import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.bedDataAccessDAO.JSONRPCDAO;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.Costs;

public class CostsDAO
{
    public CostsDAO()
    {
    }
    
    public Costs readCosts(String userType, String userCode, String itemCode, String locationCode, String unit)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readCosts");
        rpcDAO.setMethodResultType("costs");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addStringParameter(itemCode);
        rpcDAO.addStringParameter(locationCode);
        rpcDAO.addStringParameter(unit);
        JSONObject  result = rpcDAO.call();
        
        return new Costs(result);
    }
}
