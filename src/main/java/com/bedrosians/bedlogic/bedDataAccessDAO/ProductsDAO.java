package com.bedrosians.bedlogic.bedDataAccessDAO;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Service;

import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.bedDataAccessDAO.JSONRPCDAO;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.Products;

@Service
public class ProductsDAO
{
    public ProductsDAO()
    {
    }
    
    public Products readProductsByQueryParams(String userType, String userCode, MultivaluedMap<String,String> queryParams)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readProductsByQuery");
        rpcDAO.setMethodResultType("products");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addQueryParameters(queryParams);
        
        JSONObject  result = rpcDAO.call();
        
        return new Products(result);
    }
    
    public Products readSimplifiedProducts(String userType, String userCode)
            throws BedDAOException
    {        
            JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
            
            rpcDAO.setMethodName("readSimplifiedProducts");
            rpcDAO.setMethodResultType("products");
            rpcDAO.addStringParameter(userType);
            rpcDAO.addStringParameter(userCode);
                   
            JSONObject  result = rpcDAO.call();
            
            return new Products(result);
    }
    
    public Products createProduct(String userType, String userCode, MultivaluedMap<String,String> sqlParams)
           throws BedDAOException
    {        
            JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
            
            rpcDAO.setMethodName("createProduct");
            rpcDAO.setMethodResultType("products");
            rpcDAO.addStringParameter(userType);
            rpcDAO.addStringParameter(userCode);
            rpcDAO.addStringParameter("ims");
            rpcDAO.addQueryParameters(sqlParams);
               
            JSONObject  result = rpcDAO.call();
            
            return new Products(result);
    }
    
    public Products updateProduct(String userType, String userCode, MultivaluedMap<String,String> sqlParams, MultivaluedMap<String,String> condition)
            throws BedDAOException
        {        
            JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
            
            rpcDAO.setMethodName("updateProduct");
            rpcDAO.setMethodResultType("products");
            rpcDAO.addStringParameter(userType);
            rpcDAO.addStringParameter(userCode);
            rpcDAO.addStringParameter("ims");
            rpcDAO.addQueryParameters(sqlParams);
            rpcDAO.addQueryParameters(condition);
            
            JSONObject  result = rpcDAO.call();
            
            return new Products(result);
        }
     
}
