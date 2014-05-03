package com.bedrosians.bedlogic.domain.item.embeddable;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Description implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private String fulldesc;
	private String itemdesc1;
	private String itemdesc2;
	
	public Description(){}
	
	@Column(name = "fulldesc", length = 70)
	public String getFulldesc() {
		return FormatUtil.process(this.fulldesc);
	}

	public void setFulldesc(String fulldesc) {
		this.fulldesc = fulldesc;
	}
	
	@Column(name = "itemdesc1", length = 35)
	public String getItemdesc1() {
		return FormatUtil.process(this.itemdesc1);
	}

	public void setItemdesc1(String itemdesc1) {
		this.itemdesc1 = itemdesc1;
	}

	@Column(name = "itemdesc2", length = 35)
	public String getItemdesc2() {
		return FormatUtil.process(this.itemdesc2);
	}

	public void setItemdesc2(String itemdesc2) {
		this.itemdesc2 = itemdesc2;
	}
}
