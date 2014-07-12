package com.bedrosians.bedlogic.domain.account.embeddable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Phone implements Serializable {

	private static final long serialVersionUID = 7982351854896047883L;
	
	private Long phoneNumber;		
	private Integer extension;
	private Long cellPhoneNumber;	
	
	@Column(name="apPhone")
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name="apExt")
	public Integer getExtension() {
		return extension;
	}
	public void setExtension(Integer extension) {
		this.extension = extension;
	}
	
	@Column(name="apCellPhone")
	public Long getCellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setCellPhoneNumber(Long cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}
	
}
