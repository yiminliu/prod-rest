package com.bedrosians.bedlogic.util.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.bedrosians.bedlogic.domain.account.Account;
import com.bedrosians.bedlogic.domain.account.Branch;
import com.bedrosians.bedlogic.domain.account.embeddable.Address;
import com.bedrosians.bedlogic.domain.account.embeddable.Contact;
import com.bedrosians.bedlogic.domain.account.embeddable.Owner;
import com.bedrosians.bedlogic.domain.account.embeddable.Phone;
import com.bedrosians.bedlogic.domain.account.embeddable.Statement;
import com.bedrosians.bedlogic.domain.product.ProductVendor;
import com.bedrosians.bedlogic.domain.product.Product;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.product.ImsDataUtil;


public class AccountFormatUtil extends FormatUtil{
  
	public static synchronized Account process(Account account){
		if(account == null)
		   return null;	
		account.setCustomerCode(process(account.getCustomerCode()));	
		account.setCustomerName(process(account.getCustomerName()));
		account.setInactiveCode(process(account.getInactiveCode()));
		account.setAddress(process(account.getAddress()));
		account.setPhysicalAddress(process(account.getPhysicalAddress()));
		account.setDefaultBranchCode(process(account.getDefaultBranchCode()));
		account.setAccountManager(process(account.getAccountManager()));
		account.setObNotes(process(account.getObNotes()));
		if(account.getApContact() != null){
			account.setApContact(process(account.getApContact()));	
		}
		if(account.getCeoContact() != null){
			account.setCeoContact(process(account.getCeoContact()));	
		}
		if(account.getCfoContact() != null){
			account.setCfoContact(process(account.getCfoContact()));	
		}
		if(account.getSalesContact() != null){
			account.setSalesContact(process(account.getSalesContact()));	
		}
		if(account.getPurchaserContact() != null){
			account.setPurchaserContact(process(account.getPurchaserContact()));	
		}
		if(account.getCredit() != null)
			account.getCredit().setCreditStatNote(process(account.getCredit().getCreditStatNote()));
		if(account.getStatement() != null)
			account.setStatement(process(account.getStatement()));
		if(account.getOwner() != null)
			account.setOwner(process(account.getOwner()));
		//if(account.getBranches() != null)
		//	account.setBranches(process(account.getBranches()));
		return account;
	}	
	
	public static synchronized Contact process(Contact contact){
		if(contact == null)
			return null;
		contact.setName(process(contact.getName()));
		contact.setEmail(process(contact.getEmail()));
		contact.setFax(process(contact.getFax()));
		contact.setNotes(process(contact.getNotes()));
		//if(contact.getPhone() != null)
		//   contact.setPhone(process(contact.getPhone()));
			
		return contact;
	}
	
	public static synchronized Address process(Address address){
		if(address == null)
			return null;
		address.setStreeLine1(process(address.getStreeLine1()));
		address.setStreeLine2(process(address.getStreeLine2()));
		address.setCity(process(address.getCity()));
		address.setZip(process(address.getZip()));
		address.setCountry(process(address.getCountry()));
		return address;
	}
	
	public static synchronized Phone process(Phone phone){
		if(phone == null)
			return null;
		phone.setPhoneNumber(process(phone.getPhoneNumber()));
		phone.setExtension(process(phone.getExtension()));
		if(phone.getCellPhoneNumber() != null)
		   phone.setCellPhoneNumber((process(phone.getCellPhoneNumber())));
			
		return phone;
	}
	
	public static synchronized Owner process(Owner owner){
		if(owner == null)
			return null;
		owner.setFirstName(process(owner.getFirstName()));
		owner.setLastName(process(owner.getLastName()));
		owner.setDriverLicenseNo(process(owner.getDriverLicenseNo()));

		return owner;
	}
	
	public static synchronized Statement process(Statement statement){
		if(statement == null)
			return null;
		statement.setStatementType(process(statement.getStatementType()));
		statement.setInvoiceType(process(statement.getInvoiceType()));
		statement.setStatementType(process(statement.getStatementType()));
		statement.setEmailStatementMailingFrequency(process(statement.getEmailStatementMailingFrequency()));
		statement.setPaperStatementMailingFrequency(process(statement.getPaperStatementMailingFrequency()));
		return statement;
	}	
	
	public static synchronized List<Branch> process(List<Branch> branches){
		if(branches == null)
		   return null;	
		for(Branch branch : branches){
			//branch.getCustomerCode();
			//branch.getBranchCode();
		    branch.setUserCode(process(branch.getUserCode()));
		    branch.setBranchName(process(branch.getBranchName()));
		    branch.setInactiveCode(process(branch.getInactiveCode()));
		    branch.setAddress(process(branch.getAddress()));
		    branch.setPriceGroup(process(branch.getPriceGroup()));
		    branch.setContractLicenseNo(process(branch.getContractLicenseNo()));
		    branch.setResaleNo(process(branch.getResaleNo()));
		    branch.setAccountManager(process(branch.getAccountManager()));
		    if(branch.getApContact() != null){
			   branch.setApContact(process(branch.getApContact()));	
		    }
		    if(branch.getManagerContact() != null){
			   branch.setManagerContact(process(branch.getManagerContact()));	
		    }
		    if(branch.getSalesContact() != null){
			   branch.setSalesContact(process(branch.getSalesContact()));	
		    }
		    if(branch.getPurchaserContact() != null){
			   branch.setPurchaserContact(process(branch.getPurchaserContact()));	
		    }
		}
		return branches;
	}	
}