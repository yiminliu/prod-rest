package com.bedrosians.bedlogic.bedDataAccessDAO;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;
import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.bedDataAccessDAO.JSONRPCDAO;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.PromoSeries;

public class PromoSeriesDAO
{
    public PromoSeriesDAO()
    {
    }
        
    public PromoSeries getPromoSeries(String userType, String userCode, String promoCode, String promoRegion, String materialType)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readProductPromosSeries");
        rpcDAO.setMethodResultType("promoseries");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addStringParameter(promoCode);
        rpcDAO.addStringParameter(promoRegion);
        rpcDAO.addStringParameter(materialType);
        
        JSONObject  result = rpcDAO.call();
        
        return new PromoSeries(result);
    }
}
