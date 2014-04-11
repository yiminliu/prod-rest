package com.bedrosians.bedlogic.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

public interface GenericDao <T, PK extends Serializable>{
	
	T findById(Session session, final PK id);
	T loadById(Session session, final PK id); 
	List<T> findAll(Session session);
	PK save(Session session, T newInstance);
	void update(Session session, T transientObject);
	void update(Session session, String id, T transientObject);
	void delete(Session session, T persistentObject);	
}
