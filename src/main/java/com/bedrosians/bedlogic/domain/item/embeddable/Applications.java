package com.bedrosians.bedlogic.domain.item.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Applications  implements java.io.Serializable {

	private static final long serialVersionUID = -3342182221787L;
	
	private String residential;
	private String lightCommercial;
	private String commercial;
	
	@Column(name = "residential", length = 20)
	public String getResidential() {
		return FormatUtil.process(this.residential);
	}

	public void setResidential(String residential) {
		this.residential = residential;
	}

	@Column(name = "lightcommercial", length = 20)
	public String getLightCommercial() {
		return FormatUtil.process(this.lightCommercial);
	}

	public void setLightCommercial(String lightCommercial) {
		this.lightCommercial = lightCommercial;
	}

	@Column(name = "commercial", length = 20)
	public String getCommercial() {
		return FormatUtil.process(this.commercial);
	}

	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}
	
}
