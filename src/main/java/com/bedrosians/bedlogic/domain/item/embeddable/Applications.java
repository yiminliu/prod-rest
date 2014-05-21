package com.bedrosians.bedlogic.domain.item.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Applications  implements java.io.Serializable {

	private static final long serialVersionUID = -3342182221787L;
	
	private String residential;
	private String lightcommercial;
	private String commercial;
	
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
	
}
