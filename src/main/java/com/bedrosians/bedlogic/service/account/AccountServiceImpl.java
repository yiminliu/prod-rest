package com.bedrosians.bedlogic.service.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.account.AccountBranchDao;
import com.bedrosians.bedlogic.dao.account.AccountBranchDetailDao;
import com.bedrosians.bedlogic.dao.account.AccountDao;
import com.bedrosians.bedlogic.dao.account.AccountDetailDao;
import com.bedrosians.bedlogic.dao.account.CheckPaymentDao;
import com.bedrosians.bedlogic.domain.account.Account;
import com.bedrosians.bedlogic.domain.account.AccountBranchDetail;
import com.bedrosians.bedlogic.domain.account.AccountDetail;
import com.bedrosians.bedlogic.domain.account.AccountBranch;
import com.bedrosians.bedlogic.domain.account.BranchPK;
import com.bedrosians.bedlogic.domain.account.CheckPayment;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;
import com.sun.jersey.core.util.MultivaluedMapImpl;


@Service("accountService")
//@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private SessionFactory sessionFactory;	
    @Autowired
	AccountDao accountDao;    
   // @Autowired
	//AccountDetailDao accountDetailDao;    
    @Autowired
	AccountBranchDao accountBranchDao;
   // @Autowired
	//AccountBranchDetailDao accountBranchDetailDao;
	
    @Autowired
	CheckPaymentDao checkPaymentDao;
	    	
    @Loggable(value = LogLevel.TRACE)
    @Override
	@Transactional(readOnly=true)
	public List<Account> getAccountsByCustomerCode(String customerCode) throws BedDAOBadParamException, BedDAOException {
    	List<Account> accounts = null;
    	
    	try{
    		accounts = accountDao.getAccountByCustomerCode(getSession(), customerCode);
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
	@Transactional(readOnly=true)
	public List<Account> getAccounts(MultivaluedMap<String, String> queryParams) throws BedDAOException{
		if(queryParams == null || queryParams.isEmpty()){
		   queryParams = new MultivaluedMapImpl();
		   queryParams.put("inactivecode", Arrays.asList(new String[]{"N"}));
		}
		List<Account> accounts = null;
		try{
			accounts = accountDao.getAccounts(queryParams);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during getProducts(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProducts(), due to: " +  hbe.getMessage());
		
		}
		List<Account> processedAccounts = new ArrayList<>();
		//for(Account account  : accounts){
			//processedAccounts.add(FormatUtil.process(account));			
		//}
		return accounts;//processedAccounts;
	}
	
    @Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional
	public String createAccount(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{
		return accountDao.createAccount(getSession(), inputJsonObj); 
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
	@Transactional
	public void updateAccount(Account account){
		//if(account instanceof AccountDetail)
		   accountDetailDao.updateAccount(account); 
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
	public List<AccountBranch> getAccountBranches(String accountId) {
		return accountBranchDao.getAccountBranches(accountId);
	}
	
	
	public List<CheckPayment> getCheckPaymentsForAccount(String custcd) {
		return checkPaymentDao.getCheckPaymentsForAccount(custcd);
	}
	
	private synchronized Session getSession(){
    	return sessionFactory.getCurrentSession();
}
}
