package com.bedrosians.bedlogic.domain.item.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Applications {

	private String residential;
	private String lightcommercial;
	private String commercial;
	
	@Column(name = "residential", length = 20)
	public String getResidential() {
		return FormatUtil.process(this.residential);
	}

	public void setResidential(String residential) {
		this.residential = residential;
	}

	@Column(name = "lightcommercial", length = 20)
	public String getLightcommercial() {
		return FormatUtil.process(this.lightcommercial);
	}

	public void setLightcommercial(String lightcommercial) {
		this.lightcommercial = lightcommercial;
	}

	@Column(name = "commercial", length = 20)
	public String getCommercial() {
		return FormatUtil.process(this.commercial);
	}

	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}
	
}
