package com.bedrosians.bedlogic.util.JsonWrapper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountListWrapper {

	 private List<AccountWrapper> list;
	 
	 public AccountListWrapper(){}
	 
	 public AccountListWrapper(List<AccountWrapper> list){
		 this.list = list;
	 }
	 
	 @JsonProperty("accounts")
	 public List<AccountWrapper> getList() {
	     return list;
	 }

	 public void setList(List<AccountWrapper> list) {
	     this.list = list;
	 }
	
}
