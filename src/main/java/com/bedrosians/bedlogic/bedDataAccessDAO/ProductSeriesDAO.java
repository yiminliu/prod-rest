package com.bedrosians.bedlogic.bedDataAccessDAO;

import org.springframework.stereotype.Service;

import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.ProductSeries;

@Service
public class ProductSeriesDAO
{
    public ProductSeriesDAO()
    {
    }
    
    public ProductSeries readProductSeries(String userType, String userCode)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readProductSeries");
        rpcDAO.setMethodResultType("productseries");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        
        JSONObject  result = rpcDAO.call();
        
        return new ProductSeries(result);
    }
}
