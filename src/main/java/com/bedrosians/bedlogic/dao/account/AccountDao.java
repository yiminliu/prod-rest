package com.bedrosians.bedlogic.dao.account;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Session;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.account.Account;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;

public interface AccountDao extends GenericDao<Account, String>{

  public List<Account> getAccountByCustomerCode(Session session, String customerCode);  
  public List<Account> getAccounts(MultivaluedMap<String, String> queryParams);
  public String createAccount(Session session, JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException;
  public void updateAccount(Account account);
    
}