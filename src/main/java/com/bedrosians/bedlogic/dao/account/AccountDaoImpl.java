package com.bedrosians.bedlogic.dao.account;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.TermQuery;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.QueryParameterException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.account.Account;
import com.bedrosians.bedlogic.domain.product.Product;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.util.product.ImsQueryUtil;


@Repository("accountDao")
public class AccountDaoImpl extends GenericDaoImpl<Account, String> implements AccountDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional(readOnly=true)
	public List<Account> getAccountByCustomerCode(String customerCode) {
		//return findById(session, accountId);//branch info cannot be linked to the account.
		//List<AccountDetail> accounts =  findByParameter("accountId", accountId);
		Query query = getSession(sessionFactory).createQuery("From Account where customerCode = :customerCode");
		query.setString("customerCode", customerCode);
		return (List<Account>)query.setCacheable(true).list();
	}
	
	@Override
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<Account> getAccounts(MultivaluedMap<String, String> queryParams){
		if(queryParams == null) 
		   return null;

		List<Account> accounts = null; 
		boolean exactMatch = queryParams.containsKey("exactmatch")? true : queryParams.containsKey("exactMatch")? true : false;
		int maxResults = ImsQueryUtil.getMaxResults(queryParams);
		Set<Map.Entry<String, List<String>>> entrySet = queryParams.entrySet();
		Iterator<Map.Entry<String, List<String>>> it = entrySet.iterator();
		String key, value = null;
		List<String> values = null;
		String stringValue = null;
    	DetachedCriteria accountCriteria = DetachedCriteria.forClass(Account.class);
	  	while(it.hasNext()) {
		     Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		     key = normalizeKey((String)entry.getKey());
		   	 values = entry.getValue();	
		 	 if(values == null || values.isEmpty())
		 	    continue;
		 	 else if(values.size() == 1 && values.toString().contains(",")) { //the input contains multiple values for a key, but in String format
		 	   		stringValue = values.toString();
		 	   		stringValue =stringValue.substring(stringValue.indexOf("[")+1);
		 	     	stringValue =stringValue.substring(0, stringValue.lastIndexOf("]"));
		 	   		values = Arrays.asList(stringValue.split(","));  
		 	 }
		 	 value = values.get(0);
		     switch(key) {
		 	   case "customerCode": case "customerName": 
		        	if(values.size() > 1)	
		     		   accountCriteria = generateWildcardDisjunctionCriteria(accountCriteria, key, values);
		        	else if ("customerCode".equalsIgnoreCase(key))
		        	   accountCriteria.add(Restrictions.ilike(key, value, MatchMode.START));
		        	else if ("customerName".equalsIgnoreCase(key))
	        	       accountCriteria.add(Restrictions.ilike(key, value, MatchMode.ANYWHERE));
		       break;
	   	       default:     
	 	   		   accountCriteria.add(Restrictions.eq(key, value).ignoreCase());
	 	   		   break;  
		    }  
		
		}	
	  
		accountCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        //itemCriteria.addOrder(Order.asc("itemcode"));
        System.out.println("getItemsByQueryParameters() using criteria = " + accountCriteria.toString());
  
		try {
			if(maxResults > 0)
		       accounts =  (List<Account>)accountCriteria.getExecutableCriteria(getSession(sessionFactory)).setMaxResults(maxResults).setCacheable(true).list();//executeCriteria(itemCriteria);//(List<Product>)itemCriteria.list();			
			else
			   accounts =  (List<Account>)accountCriteria.getExecutableCriteria(getSession(sessionFactory)).setCacheable(true).list();	
		}
		catch(Exception e){
		   throw e;
		}
		
		System.out.println(accounts == null? 0 : accounts.size() + " items returned"); 
		return accounts;
	}
	
	
	@Override
	@Transactional(readOnly=true)
	//@SuppressWarnings("unchecked")
	public List<Account> getAccountsByLucene(MultivaluedMap<String, String> queryParams){
		if(queryParams == null) 
		   return null;

		List<Account> accounts = null; 
		
	    // create native Lucene query
	    //org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onField("customerCode").matching("26818     ").createQuery();
	    //org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().wildcard().onField("customerCode").matching("26818*").createQuery();
	    //org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().wildcard().onField("accountName").matching("S*").createQuery();
		int maxResults = ImsQueryUtil.getMaxResults(queryParams);
		Set<Map.Entry<String, List<String>>> entrySet = queryParams.entrySet();
		Iterator<Map.Entry<String, List<String>>> it = entrySet.iterator();
		String key, value = null;
		List<String> values = null;
		
		FullTextSession fullTextSession = Search.getFullTextSession(getSession(sessionFactory));
	    QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Account.class).get();	
	    org.hibernate.Query fullTextQuery = null;
	    //	org.apache.lucene.search.Query luceneQuery = null;//queryBuilder.bool();
		BooleanQuery booleanQuery = new BooleanQuery();
		PhraseQuery phraseQuery = null;
		FuzzyQuery fuzzyQuery = null; 
		TermQuery termQuery = null;  
		org.apache.lucene.search.Query luceneQuery = null;
		while(it.hasNext()) {
		     Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		     key = normalizeKey((String)entry.getKey());
		   	 values = entry.getValue();	
		 	 if(values == null || values.isEmpty())
		 	    continue;
	 		 switch(key){
	 		     case "customerName": case "customername": case "accountName": case "companyName":
		   	    	luceneQuery = queryBuilder.keyword().wildcard().onField(key).matching(values.get(0).toUpperCase()+"*").createQuery();
		   	    	luceneQuery.setBoost(2.0f);
		   	    	booleanQuery.add(new BooleanClause(luceneQuery, BooleanClause.Occur.MUST));
		            break;
		   	     case "customerCode": case "customercode":
		   	    	fuzzyQuery =  new FuzzyQuery(new Term(key, values.get(0)), 0.8f);
		   	    	fuzzyQuery.setBoost(2.0f);
		   	    	booleanQuery.add(new BooleanClause(fuzzyQuery, BooleanClause.Occur.MUST));
			        break;    
		   	     case "address.city": 
		   	    	fuzzyQuery =  new FuzzyQuery(new Term(key, values.get(0).toUpperCase()), 0.8f);
		   	    	booleanQuery.add(new BooleanClause(fuzzyQuery, BooleanClause.Occur.MUST));
			         break;   
		   	     case "accountManager": case "woner.ownerFirstName": case "woner.ownerLastName": case "woner.ownerDriverLicenseNo":
		   	    	fuzzyQuery =  new FuzzyQuery(new Term(key, values.get(0)), 0.8f);
		   	    	booleanQuery.add(new BooleanClause(fuzzyQuery, BooleanClause.Occur.MUST));
			         break;
		   	     case "maxResults":
		   	    	 break;
			     default:
			    	 fuzzyQuery =  new FuzzyQuery(new Term(key, values.get(0)), 0.99f);
			    	 //Term term = new Term(key, values.get(0));
			    	 //luceneQuery = queryBuilder.keyword().onField(key).matching(values.get(0)).createQuery();
			    	 booleanQuery.add(new BooleanClause(fuzzyQuery, BooleanClause.Occur.MUST));
			}
		 	//booleanQuery.add(new BooleanClause(termQuery, BooleanClause.Occur.MUST));
	   	 
		}
		//  luceneQuery = queryBuilder.bool()
		//	   	                                       .must(queryBuilder.keyword().onField(key).matching("N  ").createQuery())//ims table uses char type witch may need some padding to match index id 
		//  	                                           .must(queryBuilder.keyword().onField("showonwebsite").matching("Y").createQuery()).createQuery();
		       
			   // wrap Lucene query in a javax.persistence.Query
		      // org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Account.class).setCacheable(true);
		try{
		   if(maxResults != 0)
			   fullTextSession.createFullTextQuery(booleanQuery, Account.class).setCacheable(true).setCacheMode(CacheMode.NORMAL).setMaxResults(maxResults);
		   else
		       fullTextQuery = fullTextSession.createFullTextQuery(booleanQuery, Account.class).setCacheable(true).setCacheMode(CacheMode.NORMAL);
		   //fullTextQuery.setParameter("includeLower", true);
		   //fullTextQuery.setParameter("includeUpper", true);
		   System.out.println("fullTextQuery = " + fullTextQuery.toString());
		   accounts = (List<Account>)fullTextQuery.list();  
		}
		catch(Exception e){
			throw e;
		}
		System.out.println("query = " + fullTextQuery);
				
		return accounts;
	}	
	 @Override
	// @Transactional
	public String createAccount(Session session, JSONObject inputJsonObj){

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

	//------------------- internal helper methods ------------------//
		
		private String normalizeKey(String key){
	       switch(key) {
			  case "custcd": case "custCode": case "customerCd": 
		   		 key = "customerCode";
		   		 break;
			  case "coname": case "customername": case "accountname": case "accountName": 
			   	 key = "customerName";
			   	 break;	 
			  case "inactivecd":
				 key = "inactiveCode";
		    	 break;   
			  case "addressLine1": case "addressLine2": case "city": case "state": case "zip": case "country":
			   	 key = "address." + key;
			   	 break;
			  case "ownerFirstName": case "ownerLastName": case "ownerDriverLicenseNo":
		   		 key = "owner" + key; 
		         break;	
			  case "creditstatus":  case "creditStatus":
				 key = "credit.creditStatus";
			   	 break;
			  case "dba": case "Dba":
			   	 key = "companyName";
			   	 break;
	       }
	        return key;
		}  
		
		private DetachedCriteria generateWildcardDisjunctionCriteria(DetachedCriteria criteria, String name, List<String> values){
		    Disjunction or = Restrictions.disjunction();
			for(String value : values) {
				if("customerName".equalsIgnoreCase(name))
				   or.add(Restrictions.ilike(name, value, MatchMode.ANYWHERE));
				else
				   or.add(Restrictions.ilike(name, value, MatchMode.START));
		    }  
		   	criteria.add(or);
			return criteria;
	    }	

	    private DetachedCriteria generateEqualsDisjunctionCriteria(DetachedCriteria criteria, String name, List<String> values){
		    Disjunction or = Restrictions.disjunction();
			for(String value : values) {
				or.add(Restrictions.eq(name, value.trim()).ignoreCase());
		    }  
		   	criteria.add(or);
			return criteria;
	    }	
}
