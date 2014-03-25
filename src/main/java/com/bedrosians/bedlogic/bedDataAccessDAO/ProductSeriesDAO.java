package com.bedrosians.bedlogic.bedDataAccessDAO;

import net.minidev.json.JSONObject;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.ProductSeries;

public class ProductSeriesDAO
{
    public ProductSeriesDAO()
    {
    }
    
    public ProductSeries readProductSeries(String userType, String userCode, String seriesName)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readProductSeries");
        rpcDAO.setMethodResultType("productseries");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addStringParameter(seriesName);
        
        JSONObject  result = rpcDAO.call();
        
        return new ProductSeries(result);
    }
}
