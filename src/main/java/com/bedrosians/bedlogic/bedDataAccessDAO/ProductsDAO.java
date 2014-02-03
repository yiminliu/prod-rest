package com.bedrosians.bedlogic.bedDataAccessDAO;

import javax.ws.rs.core.MultivaluedMap;
import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.exception.BedDAOException;

public class ProductsDAO
{
    public ProductsDAO()
    {
    }
    
    public JSONObject getProductsByQueryParams(String userType, String userCode, MultivaluedMap<String,String> queryParams)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readProductsByQuery");
        rpcDAO.setMethodResultType("products");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addQueryParameters(queryParams);
        
        JSONObject  result = rpcDAO.call();
        
        return result;
    }
}
