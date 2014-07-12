package com.bedrosians.bedlogic.service.account;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.domain.account.Account;
import com.bedrosians.bedlogic.domain.account.Branch;
import com.bedrosians.bedlogic.domain.account.CheckPayment;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.util.JsonWrapper.AccountWrapper;

@Service
public interface AccountService {
	
	//------------------- Account CRUD Operations ------------------//
	public List<Account> getAccountsByCustomerCodeTest(String customerCode) throws BedDAOBadParamException, BedDAOException;
	public List<AccountWrapper> getAccountsByCustomerCode(String customerCode) throws BedDAOBadParamException, BedDAOException;
	public  List<AccountWrapper> getAccounts(MultivaluedMap<String, String> queryParams)throws BedDAOException;
	public String createAccount(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException;
	public void updateAccount(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException;
	//public void updateAccount(String accountId, Account account) throws BedDAOException;
	
	//------------------- Branch CRUD Operations ------------------//
	public List<? extends Branch> getBranches(String customerCode);
	//AccountBranchDetail getAccountBranchById(String accountId, String branchId);
	//AccountBranchDetail getAccountBranchByBranchPK(BranchPK branchId);
	public Branch getBranch(String coustomerCode, String branchCode);
	List<CheckPayment> getCheckPaymentsForAccount(String custcd);
}
