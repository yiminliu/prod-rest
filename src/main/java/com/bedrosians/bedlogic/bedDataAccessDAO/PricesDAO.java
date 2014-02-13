package com.bedrosians.bedlogic.bedDataAccessDAO;

import org.springframework.stereotype.Service;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;

import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.bedDataAccessDAO.JSONRPCDAO;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.Prices;

@Service
public class PricesDAO
{
    public PricesDAO()
    {
    }
    
    public Prices getPrices(String userType, String userCode, String itemCode, String customerCode, String branchCode, String locationCode, String unit)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readPrices");
        rpcDAO.setMethodResultType("prices");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addStringParameter(itemCode);
        rpcDAO.addStringParameter(customerCode);
        rpcDAO.addStringParameter(branchCode);
        rpcDAO.addStringParameter(locationCode);
        rpcDAO.addStringParameter(unit);
        JSONObject  result = rpcDAO.call();
        
        return new Prices(result);
    }
}
