package com.bedrosians.bedlogic.bedDataAccessDAO;

import com.thetransactioncompany.jsonrpc2.client.*;
import com.thetransactioncompany.jsonrpc2.*;
import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.bedDataAccessDAO.JSONRPCDAO;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.Accounts;

public class AccountsDAO
{
    public AccountsDAO()
    {
    }
        
    public Accounts readAccounts(String userType, String userCode, String customerCode, String branchCode, String customerName, String creditStatus)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readAccounts");
        rpcDAO.setMethodResultType("accounts");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addStringParameter(customerCode);
        rpcDAO.addStringParameter(branchCode);
        rpcDAO.addStringParameter(customerName);
        rpcDAO.addStringParameter(creditStatus);
        JSONObject  result = rpcDAO.call();
        
        return new Accounts(result);
    }
}
