package com.bedrosians.bedlogic.util.account;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.Query;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.domain.miscellaneous.Obmh;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;

@Scope("singleton")
public class AccountIdGenerator implements IdentifierGenerator{
  
  public AccountIdGenerator(){}
  
  @Loggable(value=LogLevel.TRACE)
  @Transactional
  public Serializable generate(SessionImplementor session,  Object object) throws DataAccessException, UnsupportedOperationException {
	 long newId = 0l;
	 Query query = session.getNamedQuery("findLastCustCd");
	 Obmh obmhRecord = (Obmh)query.uniqueResult();
	 if (obmhRecord != null)
	    newId = new AtomicLong((Long)obmhRecord.getLastCustId()).incrementAndGet(); 
	 obmhRecord.setLastCustId(newId);	 
	 return String.valueOf(newId);
 }  
  
}
