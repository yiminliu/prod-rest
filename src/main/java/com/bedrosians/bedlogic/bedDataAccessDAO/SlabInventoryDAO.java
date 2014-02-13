package com.bedrosians.bedlogic.bedDataAccessDAO;

import org.springframework.stereotype.Service;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;

import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.bedDataAccessDAO.JSONRPCDAO;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.SlabInventory;

@Service
public class SlabInventoryDAO
{
    public SlabInventoryDAO()
    {
    }
        
    public SlabInventory readSlabInventory(String userType, String userCode, String itemCode, String locationCode, String unit)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readSlabInventory");
        rpcDAO.setMethodResultType("slabinventory");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addStringParameter(itemCode);
        rpcDAO.addStringParameter(locationCode);
        rpcDAO.addStringParameter(unit);
        JSONObject  result = rpcDAO.call();
        
        return new SlabInventory(result);
    }
}
