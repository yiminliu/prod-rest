package com.bedrosians.bedlogic.service.system;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("indexUtil")
@Scope("singleton")
public class IndexService {

	@Autowired
    private SessionFactory sessionFactory;
	
   /** This method is used to initialize Lucene indexes for the existing data in database.
	 * The @PostConstruct annotation ensures the indexes are initialized when the app starts up
	 */
	//@PostConstruct
    public  boolean initializeIndex(){
    	FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.openSession());
    	try{
    		fullTextSession.createIndexer().purgeAllOnStart(true).batchSizeToLoadObjects(100).optimizeOnFinish(true).startAndWait();
    		System.out.println("Lucene indeses have been initialized.");
    		return true;
    	}
    	catch(InterruptedException e){
    		System.out.println("Lucene indeses initilization failed, due to " + e.getMessage());
    		return false;
    	}
    }
}
