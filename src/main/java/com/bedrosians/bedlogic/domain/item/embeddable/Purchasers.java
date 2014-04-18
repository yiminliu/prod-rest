package com.bedrosians.bedlogic.domain.item.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Purchasers  implements java.io.Serializable {

	private static final long serialVersionUID = -338982221787L;
	
	private String purchaser;
	private String purchaser2;

	@Column(name = "purchaser", length = 10)
	public String getPurchaser() {
		return FormatUtil.process(this.purchaser);
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	@Column(name = "purchaser2", length = 10)
	public String getPurchaser2() {
		return FormatUtil.process(this.purchaser2);
	}

	public void setPurchaser2(String purchaser2) {
		this.purchaser2 = purchaser2;
	}
	
}
