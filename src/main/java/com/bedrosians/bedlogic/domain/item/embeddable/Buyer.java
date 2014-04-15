package com.bedrosians.bedlogic.domain.item.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Buyer  implements java.io.Serializable {

	private static final long serialVersionUID = -338982221787L;
	
	private String productManager;
	private String buyer;

	@Column(name = "purchaser", length = 10)
	public String getProductManager() {
		return FormatUtil.process(this.productManager);
	}

	public void setProductManager(String productManager) {
		this.productManager = productManager;
	}

	@Column(name = "purchaser2", length = 10)
	public String getBuyer() {
		return FormatUtil.process(this.buyer);
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	
}
