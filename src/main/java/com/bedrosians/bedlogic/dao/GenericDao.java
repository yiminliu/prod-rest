package com.bedrosians.bedlogic.dao;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;


public interface GenericDao <T, PK extends Serializable>{
	
	T findById(PK id);
	T loadById(final PK id); 
	List<T> findAll();
	List<T> findByParameters(MultivaluedMap<String, String> queryParams);
	PK save(T newInstance);
	void update(T transientObject);
	void delete(T persistentObject);
	Long insertRecord(String insertStatement);

}
