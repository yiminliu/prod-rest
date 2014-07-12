package com.bedrosians.bedlogic.domain.product.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SimilarItemCode implements java.io.Serializable {
	
	private static final long serialVersionUID = -13582221787L;
	
	private String similaritemcd1;
	private String similaritemcd2;
	private String similaritemcd3;
	private String similaritemcd4;
	private String similaritemcd5;
	private String similaritemcd6;
	private String similaritemcd7;
	
	public SimilarItemCode(){}
	
	@Column(name = "similar_itemcd1", length = 18)
	public String getSimilaritemcd1() {
		return this.similaritemcd1;
	}

	public void setSimilaritemcd1(String similaritemcd1) {
		this.similaritemcd1 = similaritemcd1;
	}

	@Column(name = "similar_itemcd2", length = 18)
	public String getSimilaritemcd2() {
		return this.similaritemcd2;
	}

	public void setSimilaritemcd2(String similaritemcd2) {
		this.similaritemcd2 = similaritemcd2;
	}

	@Column(name = "similar_itemcd3", length = 18)
	public String getSimilaritemcd3() {
		return this.similaritemcd3;
	}

	public void setSimilaritemcd3(String similaritemcd3) {
		this.similaritemcd3 = similaritemcd3;
	}

	@Column(name = "similar_itemcd4", length = 18)
	public String getSimilaritemcd4() {
		return this.similaritemcd4;
	}

	public void setSimilaritemcd4(String similaritemcd4) {
		this.similaritemcd4 = similaritemcd4;
	}

	@Column(name = "similar_itemcd5", length = 18)
	public String getSimilaritemcd5() {
		return this.similaritemcd5;
	}

	public void setSimilaritemcd5(String similaritemcd5) {
		this.similaritemcd5 = similaritemcd5;
	}

	@Column(name = "similar_itemcd6", length = 18)
	public String getSimilaritemcd6() {
		return this.similaritemcd6;
	}

	public void setSimilaritemcd6(String similaritemcd6) {
		this.similaritemcd6 = similaritemcd6;
	}

	@Column(name = "similar_itemcd7", length = 18)
	public String getSimilaritemcd7() {
		return this.similaritemcd7;
	}

	public void setSimilaritemcd7(String similaritemcd7) {
		this.similaritemcd7 = similaritemcd7;
	}
}	
