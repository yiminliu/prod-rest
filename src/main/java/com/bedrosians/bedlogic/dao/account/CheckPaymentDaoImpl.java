package com.bedrosians.bedlogic.dao.account;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bedrosians.bedlogic.domain.account.CheckPayment;

@Repository
public class CheckPaymentDaoImpl implements CheckPaymentDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session currentSession() {
	   // return sessionFactory.getCurrentSession();
	    return sessionFactory.openSession();
	}
	
	public List<CheckPayment> getCheckPaymentsForAccount(String custcd) {
		String sql = "SELECT obo.ordernbr, obo.shipmentnbr, nsf.seqnbr, nsf.companyname, nsf.checknbr, nsf.checkamt FROM obo INNER JOIN nsf ON obo.ordernbr=nsf.ordernbr AND obo.shipmentnbr=nsf.shipmentnbr WHERE obo.custcd='".concat(custcd).concat("'");
		SQLQuery query = currentSession().createSQLQuery(sql);
		query.addEntity(CheckPayment.class);
		return query.list();
	}
}
