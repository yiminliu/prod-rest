package com.bedrosians.bedlogic.dao.account;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Session;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.account.Account;

public interface AccountDao extends GenericDao<Account, String>{

  public List<Account> getAccountByCustomerCode(String customerCode);  
  public List<Account> getAccounts(MultivaluedMap<String, String> queryParams);
  public List<Account> getAccountsByLucene(MultivaluedMap<String, String> queryParams);
  public String createAccount(Session session, JSONObject inputJsonObj);
  public void updateAccount(Account account);
    
}