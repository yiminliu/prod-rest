package com.bedrosians.bedlogic.util.account;


import com.bedrosians.bedlogic.domain.account.Account;

public class AccountDataUtil {

	public static boolean determineIsDefaultBranch(Account account, String branchCode){
		return branchCode == null? false : branchCode.equals(account.getDefaultBranchCode());
	}
	
	public static boolean determineIsTreatedAsStore(Account account){
		return account == null? false : account.getTreatAsStore() == "Y"? true : false;	
	}
	 
    public static String getCreditStatus(Account account){
    	return account == null? null : account.getCredit().getCreditStatus();
    }
}
