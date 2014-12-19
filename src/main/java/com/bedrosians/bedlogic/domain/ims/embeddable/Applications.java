package com.bedrosians.bedlogic.domain.ims.embeddable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Applications  implements java.io.Serializable {

	private static final long serialVersionUID = -3342182221787L;
	
	private String residential;
	private String lightcommercial;
	private String commercial;

	private List<String> residentialList;
	private List<String> lightcommercialList;
	private List<String> commercialList;
	
	@Column(name = "residential", length = 20)
	public String getResidential() {
		return this.residential;
	}

	public void setResidential(String residential) {
		this.residential = residential;
	}

	@Column(name = "lightcommercial", length = 20)
	public String getLightcommercial() {
		return this.lightcommercial;
	}

	public void setLightcommercial(String lightcommercial) {
		this.lightcommercial = lightcommercial;
	}

	@Column(name = "commercial", length = 20)
	public String getCommercial() {
		return this.commercial;
	}

	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}
	
	
	
	@Transient
	public List<String> getResidentialList() {
		return this.residentialList;
	}

	public void setResidentialList(List<String> residentialList) {
		this.residentialList = residentialList;
	}

	@Transient
	public List<String> getLightcommercialList() {
		return this.lightcommercialList;
	}

	public void setLightcommercialList(List<String> lightcommercialList) {
		this.lightcommercialList = lightcommercialList;
	}

	@Transient
	public List<String> getCommercialList() {
		return this.commercialList;
	}

	public void setCommercialList(List<String> commercialList) {
		this.commercialList = commercialList;
	}
	
}
