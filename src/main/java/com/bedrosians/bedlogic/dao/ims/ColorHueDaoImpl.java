package com.bedrosians.bedlogic.dao.ims;


import java.util.Set;
import java.util.HashSet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.ims.ColorHue;
import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;

@Repository("colorHueDao")
public class ColorHueDaoImpl extends GenericDaoImpl<ColorHue, String> implements ColorHueDao {
					
    @Autowired
	private SessionFactory sessionFactory;
	 
	@Override
	public Set<ColorHue> getAllColorHues() {
		return new HashSet<ColorHue>(findAll(sessionFactory.getCurrentSession()));
	}

	public Set<ColorHue> getItemColorHues(String itemCode){
		String sqlString = "from ColorHue where itemCode = :itemCode";
		Query query = sessionFactory.getCurrentSession().createQuery(sqlString);
		query.setString("itemCode", itemCode);
		return new HashSet<ColorHue>(query.list());
	
	}
	 
	@Override
	@Loggable(value = LogLevel.INFO)
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public String createColorHue(Session session, ColorHue colorHue){
		return (String)save(sessionFactory.getCurrentSession(), colorHue); 
	}
	
	@Override
	@Loggable(value = LogLevel.INFO)
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void createColorHues(Set<ColorHue> colorHues){
		Session session = sessionFactory.getCurrentSession();
		for(ColorHue colorHue : colorHues){
		    save(session, colorHue);
		}    
	}
	
	@Override
	@Loggable(value = LogLevel.INFO)
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateColorHue(ColorHue colorHue){
		update(sessionFactory.getCurrentSession(), colorHue);
	}
	
	@Override
	@Loggable(value = LogLevel.INFO)
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	public void deleteColorHue(ColorHue colorHue){
		delete(sessionFactory.getCurrentSession(), colorHue); 
	}

}
