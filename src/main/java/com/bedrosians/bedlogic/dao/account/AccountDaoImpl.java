package com.bedrosians.bedlogic.dao.account;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.account.Account;
import com.bedrosians.bedlogic.domain.account.AccountDetail;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;


@Repository("accountDao")
public class AccountDaoImpl extends GenericDaoImpl<Account, String> implements AccountDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Account> getAccountByCustomerCode(Session session, String customerCode) {
		//return findById(session, accountId);//branch info cannot be linked to the account.
		//List<AccountDetail> accounts =  findByParameter("accountId", accountId);
		Query query = session.createQuery("From Account where customerCode = :customerCode");
		query.setString("customerCode", customerCode);
		return (List<Account>)query.setCacheable(true).list();
	}
	
	public List<Account> getAccounts(MultivaluedMap<String, String> queryParams){
		List<Account> accounts = null;
		
		return accounts;
	}
	
	 @Override
	// @Transactional
	public String createAccount(Session session, JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{

		 //return save(sessionFactory, account);
		 return null;
	}
	 
	 @Override
	 public void updateAccount(Account account){
		 updateAccount(account);
	 }
	 
	 /*
		@Cacheable("accounts")
		@Override
		public List<SimplifiedAccount> getAccountsByStatus(String status){
			String queryString = "";
			if ("all".equalsIgnoreCase(status) || status == null || status.length() == 0)
				queryString = "from SimplifiedAccount";
			else if ("active".equalsIgnoreCase(status))
				queryString = "from SimplifiedAccount where status = ' '";
			else if ("inactive".equalsIgnoreCase(status))
			    queryString = "from SimplifiedAccount where status is not null";
			Session session = currentSession();
			Query query = session.createQuery(queryString);
			query.setReadOnly(true);
			return (List<SimplifiedAccount>)query.list();
		}
		
		

		@Override
		public List<Account> getAccountsByActivityStatus(String status){
			String propertyName = "activityStatus";
			Criteria criteria = currentSession().createCriteria(Account.class);
			if  ("all".equalsIgnoreCase(status)){
				//do nothing
			}
			else if  ("active".equalsIgnoreCase(status))
				//criteria.add(
				  //  Restrictions.or(Restrictions.isNull(propertyName),
	            //                    		Restrictions.eq(propertyName, " ")));
				//criteria.add(Restrictions.eq(propertyName, ""));
				criteria.add(Restrictions.isNull(propertyName));
			else if  ("inactive".equalsIgnoreCase(status))	
			   //criteria.add(Restrictions.eq(propertyName, "D").ignoreCase());
				criteria.add(Restrictions.isNotNull(propertyName));
			List<Account> list = (List<Account>)criteria.list();
			
			return list;
		}
		
		*/
		/*@Override
		public List<Account> getAccountsByActivityStatus(String status){
			String propertyName = "activityStatus";
		    List<Account> accounts = null;
			if  ("all".equalsIgnoreCase(status))
		    	accounts = readByParameter(propertyName, " ", RestrictionOperation.NONE);	
			else if  ("active".equalsIgnoreCase(status))
				accounts = readByParameter(propertyName, " ");
			else if  ("inactive".equalsIgnoreCase(status))	
				accounts = readByParameter(propertyName, " ", RestrictionOperation.NOTNULL);
			
			return accounts;
		}
		*/
			
		/*@Override
	    public List<Account> getAccountsByParameter(String parameterName, String value){
		    String queryString = "from Account where ".concat(parameterName.concat(" = :")).concat(parameterName);
			Session session = currentSession();
			Query query = session.createQuery(queryString);
			query.setParameter(parameterName, value.toUpperCase());
			//query.setMaxResults(10);
			List<Account> list = (List<Account>)query.list();
				
			return list;
			
			Criteria criteria = currentSession().createCriteria(Account.class);
		  	criteria.setReadOnly(true);
		  	System.out.printf("parameterName, value.toUpperCase() = %s, %s ", parameterName, value.toUpperCase());
			criteria.add(Restrictions.eq(parameterName, value.toUpperCase()));
			return (List<Account>)criteria.list();	
		 }
		
			
		 @Override
		 public List<SimplifiedAccount> getAccountsByParameters(String[] parameterNames, String[] values){
		    String condition = "";
		    List<SimplifiedAccount> accountList = null;
		    for(int i = 0; i < parameterNames.length; i++){
		    	if(i < parameterNames.length - 1)
		      	    condition.concat(parameterNames[i].concat(" = :")).concat(parameterNames[i]).concat(" AND ");
		    	else
		    		condition.concat(parameterNames[i].concat(" = :")).concat(parameterNames[i]);	
			}
			String queryString = "from Account where ".concat(condition);
			Session session = currentSession();
			Query query = session.createQuery(queryString);
			for(int i = 0; i < parameterNames.length; i++){
			    query.setParameter(parameterNames[i], values[i]);
			}			
			accountList = (List<SimplifiedAccount>)query.list();
			return accountList;
		  }
		  */ 
		 
}
