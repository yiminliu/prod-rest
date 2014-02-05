package com.bedrosians.bedlogic.bedDataAccessDAO;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;
import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.bedDataAccessDAO.JSONRPCDAO;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.MaterialOrders;

public class MaterialOrdersDAO
{
    public MaterialOrdersDAO()
    {
    }
    
    public MaterialOrders getMaterialOrders(String userType, String userCode, String itemCode, String locationCode, String openCode)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readMaterialOrders");
        rpcDAO.setMethodResultType("materialorders");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addStringParameter(itemCode);
        rpcDAO.addStringParameter(locationCode);
        rpcDAO.addStringParameter(openCode);
        JSONObject  result = rpcDAO.call();
        
        return new MaterialOrders(result);
    }
}
