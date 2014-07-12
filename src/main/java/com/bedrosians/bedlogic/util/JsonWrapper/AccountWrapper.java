package com.bedrosians.bedlogic.util.JsonWrapper;

//import org.codehaus.jackson.annotate.JsonProperty;
//import org.codehaus.jackson.map.ObjectMapper;

import com.bedrosians.bedlogic.domain.account.Account;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class AccountWrapper {

	 private Account account;
	 
	 public AccountWrapper(){}
	 
	 public AccountWrapper(Account account){
		ObjectMapper mapper = new  ObjectMapper(); 
	    //mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true); //version2.0
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		this.account = account;
	 }
	 
	 public Account getAccount() {
	     return account;
	 }

	 public void setAccount(Account account) {
	     this.account = account;
	 }
	
}
