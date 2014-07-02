package com.bedrosians.bedlogic.dao.account;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.account.AccountBranchDetail;
import com.bedrosians.bedlogic.domain.account.AccountBranch;
import com.bedrosians.bedlogic.domain.account.BranchPK;

@Repository
public class AccountBranchDaoImpl extends GenericDaoImpl<AccountBranch, BranchPK> implements AccountBranchDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<AccountBranch> getAccountBranches(String accountId){
		return null;//(List<AccountBranch>)findByParameter("branchPK.accountId", accountId);
	}
	
	@Override
	public AccountBranch getAccountBranchById(String customerCode, String branchId){
		if(branchId != null && branchId.length() > 0)
			branchId.toUpperCase();	
		BranchPK branchPK = new BranchPK(customerCode, branchId);
		return null;//findById(getSession(sessionFactory), branchPK);
		
	}
	
	@Override
	public AccountBranch getAccountBranchByBranchPK(BranchPK branchPK){
		return null;//findById(getSession(sessionFactory), branchPK);
		
	}
   
	@Override
	public List<AccountBranch> getAccountBranchByName(String name){
        return null;
   	   		
   	 }
    
}
