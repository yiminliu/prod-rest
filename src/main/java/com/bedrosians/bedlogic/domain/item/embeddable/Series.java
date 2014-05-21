package com.bedrosians.bedlogic.domain.item.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Series implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private String seriesname;
	private String seriescolor;
	
	@Column(name = "seriesname", length = 40)
	public String getSeriesname() {
		return this.seriesname;
	}

	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}
	
	@Column(name = "color", length = 30)
	public String getSeriescolor() {
		return this.seriescolor;
	}

	public void setSeriescolor(String seriescolor) {
		this.seriescolor = seriescolor;
	}

}
