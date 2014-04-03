package com.bedrosians.bedlogic.dao;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;


public interface GenericDao <T, PK extends Serializable>{
	
	T findById(final PK id);
	T loadById(final PK id); 
	List<T> findAll();
	PK save(T newInstance);
	void update(T transientObject);
	void delete(T persistentObject);
	
}
