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
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;


public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
	private Class<T> type;
	
	@SuppressWarnings("unchecked")
	public GenericDaoImpl(){
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	public PK save(Session session, T newInstance) {
		PK pk = (PK)session.save(newInstance);
		return pk;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T findById(Session session, final PK id) {
	return (T)session.get(type, id);
	}
	
	//This method only gets a proxy of the persistent entity, without hitting the database
	@Override
	@SuppressWarnings("unchecked")
	public T loadById(Session session, final PK id) {
	return (T)session.load(type, id);
	}
		
			
	public List<T> findAll(Session session){
		return session.createCriteria(type).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public synchronized void update(Session session, final T transientObject) {
		try{
        	session.update(transientObject);
          //session.saveOrUpdate(transientObject);      
		}
		catch(DataException e){
			throw e;
		}		   
   	}
	
	@Override
	@SuppressWarnings("unchecked")
	public synchronized void update(Session session, final String id, final T transientObject) {
		try{
           T t = (T)session.load(type, id);
           if(t == null)
        	  throw new HibernateException("No DB object found for the given Id " + id);  
           session.update(transientObject);  
		}
		catch(DataException e){
			throw e;
		}		   
   	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public void delete(Session session, T persistentObject) {
		
	}
	
	/*
	//@Override
	@SuppressWarnings("unchecked")
    public List<T> findByParameter(final String parameterName, String value){
		if(value != null && value.length() > 0)
		   value = value.toUpperCase();
	  	Criteria criteria = currentSession().createCriteria(type);
	  	criteria.setReadOnly(true);
	  	//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq(parameterName, value).ignoreCase());
		return (List<T>)criteria.list();			
	}
	
	
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
	
}
