package com.bedrosians.bedlogic.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;

//import com.bedrosians.bedlogic.util.PatternMatchMode;
//import com.bedrosians.bedlogic.util.RestrictionOperation;


public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
	private Class<T> type;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected synchronized Session currentSession() {
	   return sessionFactory.getCurrentSession();
	  //return sessionFactory.openSession();
	    
	}
		
	@SuppressWarnings("unchecked")
	public GenericDaoImpl(){
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	public PK save(T newInstance) {
		//Transaction tx = currentSession().beginTransaction();
		PK pk = (PK)currentSession().save(newInstance);
		//currentSession().flush();
		//tx.commit();
		return pk;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T findById(final PK id) {
	return (T)currentSession().get(type, id);
	}
			
	public List<T> findAll(){
		return currentSession().createCriteria(type).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public synchronized void update(final T transientObject) {
		try{
           Session session = currentSession();
          // if(session.contains(transientObject))
        	 session.update(transientObject);
          //session.saveOrUpdate(transientObject);
           //else
        	// session.merge(transientObject);
		}
		catch(DataException e){
			throw e;
		}		   
   	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void delete(T persistentObject) {
		
	}
	
	//@Override
	@SuppressWarnings("unchecked")
    public List<T> findByParameter(final String parameterName, String value){
		if(value != null && value.length() > 0)
		   value = value.toUpperCase();
	  	Criteria criteria = currentSession().createCriteria(type);
	  	criteria.setReadOnly(true);
	  	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq(parameterName, value).ignoreCase());
		return (List<T>)criteria.list();			
	}
	/*
	
	@Override
	@SuppressWarnings("unchecked")
    public List<T> findByParameter(final String parameterName, String value, final RestrictionOperation op){
		if(value != null && value.length() > 0)
		   value = value.toUpperCase();
	  	Criteria criteria = currentSession().createCriteria(type);
	  	criteria.setReadOnly(true);
	  	// need it? 
	  	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	  	switch(op) {
	  	  case EQ:
	  		   criteria.add(Restrictions.eq(parameterName, value));  	
	  	  case NE:
	  		   criteria.add(Restrictions.ne(parameterName, value));
	  	  case IN:
	  		   criteria.add(Restrictions.in(parameterName, value.split(",")));	   
	  		   break;
	  	  case NULL:
	  		   criteria.add(Restrictions.isNull(parameterName));	   
	  		   break; 		   
	      case NOTNULL:
	  		   criteria.add(Restrictions.isNotNull(parameterName));	   
	  		   break; 	   
	       case NONE:
	  		   break;	   
	  	  default:
	  		   break;
	  	}		   
		return (List<T>)criteria.list();			
	}
	
	@Override
	@SuppressWarnings("unchecked")
    public List<T> findByParameter(final String parameterName, Long value){
	  	Criteria criteria = currentSession().createCriteria(type);
	  	criteria.setReadOnly(true);
	  	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq(parameterName, value));
		return (List<T>)criteria.list();			
	}
	
	@Override
	@SuppressWarnings("unchecked")
    public List<T> findByParameterPattern(final String parameterName, String value, final PatternMatchMode matchMode){
	  	Criteria criteria = currentSession().createCriteria(type);
	  	switch(matchMode){
	  		case START:
	  			criteria.add(Restrictions.like(parameterName, value.toUpperCase(), MatchMode.START));
		        break;
	  		case END:
	  			criteria.add(Restrictions.like(parameterName, value.toUpperCase(), MatchMode.END));
			    break;
	  		case ANYWHERE:
	  			criteria.add(Restrictions.like(parameterName, value.toUpperCase(), MatchMode.ANYWHERE));
			    break;
			default:
				criteria.add(Restrictions.like(parameterName, value.toUpperCase(), MatchMode.EXACT));
	  	} 				
	  	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	  	return (List<T>)criteria.list();			
	 }
	*/
	
	//@Loggable(value=LogLevel.DEBUG)
	@Override
	@SuppressWarnings("unchecked")
    public List<T> findByParameters(MultivaluedMap<String, String> queryParams){
		
		if(queryParams == null) 
		   return null;
		
		Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
	    Iterator it = set.iterator();
	    Criteria criteria = currentSession().createCriteria(type);
	  	criteria.setReadOnly(true);
	  	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
   	   	String key, value = null;
	  	while(it.hasNext()) {
   	    	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
   		   	key = (String)entry.getKey();
   	    	value = ((List<String>)entry.getValue()).get(0);
   	    	if("activityStatus".equalsIgnoreCase(key)) {
   	            if ("active".equalsIgnoreCase(value))
   	 		        criteria.add(Restrictions.eq(key, ""));
   	 		    else if ("inactive".equalsIgnoreCase(value))
   	 		        criteria.add(Restrictions.in(key, new String[] {"F", "Y", "D", "I"})); 
   	    	}  
   	       	else {
   	    		//criteria.add(Restrictions.eq("activityStatus", "")); //return only active accounts
   	    		criteria.add(Restrictions.eq(key, value).ignoreCase());
   	    	}
   		
   	    }	  	
		return (List<T>)criteria.list();			
	}
	
	
	//@Loggable(value=LogLevel.DEBUG)
	@Override
	@SuppressWarnings("unchecked")
    public Long insertRecord(String insertStatement){
		//SQLQuery query = currentSession().createSQLQuery(insertStatement);
		
		SQLQuery query = currentSession().createSQLQuery("INSERT INTO Product (product_Id, color) VALUES('Test', 'Beige')");
        long value = query.executeUpdate();
        return Long.valueOf(value);
	}	   
	
}
