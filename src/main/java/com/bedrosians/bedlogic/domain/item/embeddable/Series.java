package com.bedrosians.bedlogic.domain.item.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

@Embeddable
public class Series implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private String seriesname;
	private String seriescolor;
	
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(1.5f)
	@Column(name = "seriesname", length = 40)
	public String getSeriesname() {
		return this.seriesname;
	}

	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}
	
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Column(name = "color", length = 30)
	public String getSeriescolor() {
		return this.seriescolor;
	}

	public void setSeriescolor(String seriescolor) {
		this.seriescolor = seriescolor;
	}

}
