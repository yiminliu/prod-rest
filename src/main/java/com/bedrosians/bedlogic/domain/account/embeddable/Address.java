package com.bedrosians.bedlogic.domain.account.embeddable;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import com.bedrosians.bedlogic.util.FormatUtil;


@Embeddable
public class Address{
	
	protected String streeLine1;	
	protected String streeLine2;	
	protected String city;	
	protected String state;
	protected String zip;			
	protected String country;
	 
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Column(name = "CoAddr1")
	public String getStreeLine1() {
		return streeLine1;
	}

	public void setStreeLine1(String streeLine1) {
		this.streeLine1 = streeLine1;
	}

	@Column(name = "CoAddr2")
	public String getStreeLine2() {
		return streeLine2;
	}

	public void setStreeLine2(String streeLine2) {
		this.streeLine2 = streeLine2;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(1.5f)
	@Column(name = "CoCity")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(1.5f)
	@Column(name = "CoStateCd")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(1.5f)
	@Column(name = "CoZip")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "CoCountryCd")
	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}
	
	public Address() {
	}
	
	public Address(String streeLine1, String streeLine2,
			       String addressCity, String addressState, 
			       String addressZip) {
		this.streeLine1 = streeLine1;
		this.streeLine2 = streeLine2;
		this.city = addressCity;
		this.state = addressState;
		this.zip = addressZip;
	}
	

	@Override
	public String toString() {
		return "Address ["
				+ "streeLine1=" + streeLine1 
				+ ", streeLine2=" + streeLine2 
				+ ", city=" + city 
				+ ", state=" + state 
				+ ", zip="
				+ zip + "]";
	}
	
}
