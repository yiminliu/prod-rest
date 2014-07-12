package com.bedrosians.bedlogic.dao.account;

import java.util.List;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.account.Branch;
import com.bedrosians.bedlogic.domain.account.BranchPK;


public interface AccountBranchDao extends GenericDao<Branch, BranchPK> {
	
	public Branch getBranchById(String CustomerCode, String branchId); 
	public Branch getBranchByBranchPK(BranchPK branchPK);
    public List<Branch> getBranchByName(String name);
    public List<Branch> getBranches(String accountId);

}
