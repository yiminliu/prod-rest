package com.bedrosians.bedlogic.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
	private Class<T> type;
  
	@Autowired
	private SessionFactory sessionFactory;
		
	@SuppressWarnings("unchecked")
	public GenericDaoImpl(){
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public PK save(Session session, T newInstance) {
		session.saveOrUpdate(newInstance);
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T findById(Session session, final PK id) {
	   return (T)session.get(type, id, LockOptions.NONE);
	}
	
	//This method only gets a proxy of the persistent entity, without hitting the database
	@Override
	@SuppressWarnings("unchecked")
	public T loadById(Session session, final PK id) {
	   return (T)session.load(type, id, LockOptions.NONE);
	}
		
	@Override
	@SuppressWarnings("unchecked")		
	public List<T> findAll(Session session){
		return (List<T>)session.createCriteria(type).list();
	}
	
	@Override
	public synchronized void update(Session session, final T transientObject) {
		try{
            session.update(transientObject); 
		}
		catch(HibernateException e){
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
		catch(HibernateException e){
			throw e;
		}		   
   	}
	
	@Override
	public void delete(Session session, T persistentObject) {
		session.delete(persistentObject);
	}
	
	protected synchronized Session getSession(){
		Session session = sessionFactory.getCurrentSession();
		if (session == null)
		   session = sessionFactory.openSession();
	    return session;
    }
}
