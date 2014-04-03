package com.bedrosians.bedlogic.bedDataAccessDAO;

import javax.ws.rs.core.MultivaluedMap;
import net.minidev.json.JSONObject;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.ProductSeries;

public class ProductSeriesDAO
{
    public ProductSeriesDAO()
    {
    }
    
    public ProductSeries readProductSeries(String userType, String userCode, String seriesName, String resultType, MultivaluedMap<String,String> queryParams)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readProductSeries");
        rpcDAO.setMethodResultType(resultType);
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addStringParameter(seriesName);
        rpcDAO.addQueryParameters(queryParams);
       
        JSONObject  result = rpcDAO.call();
        
        return new ProductSeries(result);
    }
}
