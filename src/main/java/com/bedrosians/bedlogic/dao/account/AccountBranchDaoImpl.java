package com.bedrosians.bedlogic.dao.account;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.account.Branch;
import com.bedrosians.bedlogic.domain.account.BranchPK;

@Repository("accountBranchDao")
public class AccountBranchDaoImpl extends GenericDaoImpl<Branch, BranchPK> implements AccountBranchDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Branch> getBranches(String accountId){
		return null;//(List<Branch>)findByParameter("branchPK.accountId", accountId);
	}
	
	@Override
	@Transactional()
	public Branch getBranchById(String customerCode, String branchCode){
		BranchPK branchPK = new BranchPK(customerCode, branchCode);
		return findById(getSession(sessionFactory), branchPK);
		
	}
	
	@Override
	@Transactional()
	public Branch getBranchByBranchPK(BranchPK branchPK){
		return findById(getSession(sessionFactory), branchPK);
		
	}
   
	@Override
	public List<Branch> getBranchByName(String name){
        return null;
   	   		
   	 }
    
}
