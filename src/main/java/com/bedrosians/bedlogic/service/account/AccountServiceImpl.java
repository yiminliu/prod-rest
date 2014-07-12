package com.bedrosians.bedlogic.service.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.QueryParameterException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.account.AccountBranchDao;
import com.bedrosians.bedlogic.dao.account.AccountDao;
import com.bedrosians.bedlogic.dao.account.CheckPaymentDao;
import com.bedrosians.bedlogic.domain.account.Account;
import com.bedrosians.bedlogic.domain.account.Branch;
import com.bedrosians.bedlogic.domain.account.BranchPK;
import com.bedrosians.bedlogic.domain.account.CheckPayment;
import com.bedrosians.bedlogic.domain.product.Product;
import com.bedrosians.bedlogic.domain.product.enums.DBOperation;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.JsonUtil;
import com.bedrosians.bedlogic.util.JsonWrapper.AccountWrapper;
import com.bedrosians.bedlogic.util.account.AccountFormatUtil;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;
import com.bedrosians.bedlogic.util.product.ImsDataUtil;
import com.bedrosians.bedlogic.util.product.ImsValidator;
import com.sun.jersey.core.util.MultivaluedMapImpl;


@Service("accountService")
//@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private SessionFactory sessionFactory;	
    @Autowired
	AccountDao accountDao;    
    @Autowired
	AccountBranchDao accountBranchDao;	
    @Autowired
	CheckPaymentDao checkPaymentDao;
	    	
    @Loggable(value = LogLevel.TRACE)
    @Override
	public List<Account> getAccountsByCustomerCodeTest(String customerCode) throws BedDAOBadParamException, BedDAOException {
    	List<Account> accounts = null;
    	
    	try{
    		accounts = accountDao.getAccountByCustomerCode(customerCode);
    		//if(account != null){	         		
    		//	account.setCheckPayments(checkPaymentDao.getCheckPaymentsForAccount(id));
    		//}
    	}
    	catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  hbe.getMessage());	
		}
    	return accounts;
	}
	
    @Loggable(value = LogLevel.TRACE)
    @Override
	public List<AccountWrapper> getAccountsByCustomerCode(String customerCode) throws BedDAOBadParamException, BedDAOException {
    	List<Account> accounts = null;
    	
    	try{
    		accounts = accountDao.getAccountByCustomerCode(customerCode);
    		//if(account != null){	         		
    		//	account.setCheckPayments(checkPaymentDao.getCheckPaymentsForAccount(id));
    		//}
    	}
    	catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  hbe.getMessage());	
		}
    	List<AccountWrapper> accountWrapperList = new ArrayList<AccountWrapper>();
    	for(Account account : accounts){
    		//accountWrapperList.add(new AccountWrapper(account));
    		accountWrapperList.add(new AccountWrapper(AccountFormatUtil.process(account)));
    	}
    	return accountWrapperList;
	}
    
    @Loggable(value = LogLevel.TRACE)
	@Override
	public List<AccountWrapper> getAccounts(MultivaluedMap<String, String> queryParams) throws BedDAOException{
		if(queryParams == null || queryParams.isEmpty()){
		   queryParams = new MultivaluedMapImpl();
		   queryParams.put("inactivecode", Arrays.asList(new String[]{"N"}));
		}
		List<Account> accounts = null;
		try{
			accounts = accountDao.getAccounts(queryParams);
		}
		catch(QueryParameterException e){
			if(e.getCause() != null)
		       throw new BedDAOException("Error occured during getAccounts(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
    		else
	  		   throw new BedDAOException("Error occured during getAccounts(), due to: " +  e.getMessage());
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during getAccounts(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getAccounts(), due to: " +  hbe.getMessage());
		
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new BedDAOException("Error occured during getAccounts(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getAccounts(), due to: " +  e.getMessage());	
		}
		List<Account> processedAccounts = new ArrayList<>();
		 List<AccountWrapper> wrappedAccountList = new  ArrayList<AccountWrapper>();
		for(Account account  : accounts){
			processedAccounts.add(AccountFormatUtil.process(account));
			wrappedAccountList.add(new AccountWrapper(AccountFormatUtil.process(account)));
		}
		return wrappedAccountList;//processedAccounts;
	}
    
    @Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional
	public String createAccount(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException{
		return accountDao.createAccount(getSession(), inputJsonObj); 
	}
    
    @Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional
	public void updateAccount(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException{
    	//String itemCode = JsonUtil.validateItemCode(jsonObj);
		Account account = (Account)JsonUtil.jsonObjectToPOJO(jsonObj, new Account());
		Session session = getSession();
		
  	    //ImsValidator.validateNewItem(itemToUpdate);
    	try{
		      accountDao.updateAccount(account);
	 	}
    	catch(HibernateException hbe){
     	      if(hbe.getCause() != null)
 		         throw new BedDAOException("Error occured during createProduct(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
 		      else
 		  	     throw new BedDAOException("Error occured during createProduct(), due to: " +  hbe.getMessage());	
 	    }	
    	catch(Exception e){
			  if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			     throw new BedDAOBadParamException("Invalid vendor number (ID), since it cannot be found in the vendor table");
			  if(e.getCause() != null)
		         throw new BedDAOException("Error occured during updateProduct(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	  else
			     throw new BedDAOException("Error occured during updateProduct(), due to: " +  e.getMessage());	
		   }	   
	}
	
	
/*	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional
	public void updateAccount(String accountId, Account account){
		Account retrievedAccount = null;
		try{
		   retrievedAccount = getAccountById(accountId);
		   if(retrievedAccount == null)
			  throw new Exception("No data found"); 
		}
		catch(Exception e){
		//	throw e;
			
		}
		//if(account instanceof AccountDetail)
		   accountDetailDao.updateAccount(Account account); 
		//else if(account instanceof SimplifiedAccount)
		  // accountDao.update((SimplifiedAccount)account); 
	}
	
		
	
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(readOnly=true)
	public AccountBranchDetail getAccountBranchByBranchPK(BranchPK branchPK) {
		return accountBranchDetailDao.findById(getSession(), branchPK);
	}
	*/
    @Loggable(value = LogLevel.TRACE)
	@Override
	public Branch getBranch(String coustomerCode, String branchCode) {
		
		return accountBranchDao.getBranchByBranchPK(new BranchPK(coustomerCode, branchCode));
    	//return accountBranchDao.getAccountBranchById(coustomerCode, branchCode);
	}
    
	@Loggable(value = LogLevel.TRACE)
	@Override
	public List<Branch> getBranches(String accountId) {
		return accountBranchDao.getBranches(accountId);
	}
	
	
	public List<CheckPayment> getCheckPaymentsForAccount(String custcd) {
		return checkPaymentDao.getCheckPaymentsForAccount(custcd);
	}
	
	private synchronized Session getSession(){
    	return sessionFactory.getCurrentSession();
}
}
