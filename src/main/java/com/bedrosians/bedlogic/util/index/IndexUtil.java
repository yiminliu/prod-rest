package com.bedrosians.bedlogic.util.index;

import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("indexUtil")
public class IndexUtil {

	@Autowired
    private SessionFactory sessionFactory;
	
	 /* This method is used to create Lucene indexes for the existing data in database */
    public  boolean initializeIndex(){
    	FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.openSession());
    	try{
    		fullTextSession.createIndexer().startAndWait();
    		return true;
    	}
    	catch(InterruptedException e){
    		e.printStackTrace();
    		return false;
    	}
    }
}
