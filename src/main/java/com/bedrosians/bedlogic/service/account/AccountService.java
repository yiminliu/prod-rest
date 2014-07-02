package com.bedrosians.bedlogic.service.account;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.domain.account.Account;
import com.bedrosians.bedlogic.domain.account.AccountBranch;
import com.bedrosians.bedlogic.domain.account.AccountBranchDetail;
import com.bedrosians.bedlogic.domain.account.AccountDetail;
import com.bedrosians.bedlogic.domain.account.BranchPK;
import com.bedrosians.bedlogic.domain.account.CheckPayment;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;

@Service
public interface AccountService {
	
	public List<Account> getAccountsByCustomerCode(String customerCode) throws BedDAOBadParamException, BedDAOException;
	public List<Account> getAccounts(MultivaluedMap<String, String> queryParams)throws BedDAOException;
	public String createAccount(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException;
	//public void updateAccount(Account account);
	//public void updateAccount(String accountId, Account account) throws BedDAOException;
	public List<? extends AccountBranch> getAccountBranches(String customerCode);
	//AccountBranchDetail getAccountBranchById(String accountId, String branchId);
	//AccountBranchDetail getAccountBranchByBranchPK(BranchPK branchId);
	List<CheckPayment> getCheckPaymentsForAccount(String custcd);
}
