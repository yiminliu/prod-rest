package com.bedrosians.bedlogic.domain.account.embeddable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Contact implements Serializable {
	
	private static final long serialVersionUID = -127451862418201850L;
	
	private String name;	
	private String email;
	private Long fax;
	private String notes;
	private Phone phone;
		
	@Column(name="Contact")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//@OneToMany(fetch=FetchType.EAGER, mappedBy="user")
	//@Cascade(CascadeType.ALL)
	@Embedded
	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}
			
	@Column(name="Email")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="Fax")
	public Long getFax() {
		return fax;
	}

	public void setFax(Long fax) {
		this.fax = fax;
	}
	
	@Column(name="Notes")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
			
	//private String createdBy;		
	//private Date createdDate;		
	//private String lastModifiedBy;		
	//private Date lastModifiedDate;	
	
	//private int userId;
	//private Account account;
}
