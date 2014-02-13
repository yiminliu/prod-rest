package com.bedrosians.bedlogic.bedDataAccessDAO;

import org.springframework.stereotype.Service;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;

import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.bedDataAccessDAO.JSONRPCDAO;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.Inventory;

@Service
public class InventoryDAO
{
    public InventoryDAO()
    {
    }
        
    public Inventory readInventory(String userType, String userCode, String itemCode, String unit)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readInventory");
        rpcDAO.setMethodResultType("inventory");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addStringParameter(itemCode);
        rpcDAO.addStringParameter(unit);
        JSONObject  result = rpcDAO.call();
        
        return new Inventory(result);
    }
}
