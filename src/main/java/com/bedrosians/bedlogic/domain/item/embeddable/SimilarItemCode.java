package com.bedrosians.bedlogic.domain.item.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;


@Embeddable
public class SimilarItemCode implements java.io.Serializable {
	
	private static final long serialVersionUID = -13582221787L;
	
	private String similarItemcd1;
	private String similarItemcd2;
	private String similarItemcd3;
	private String similarItemcd4;
	private String similarItemcd5;
	private String similarItemcd6;
	private String similarItemcd7;
	
	public SimilarItemCode(){}
	
	@Column(name = "similar_itemcd1", length = 18)
	public String getSimilarItemcd1() {
		return FormatUtil.process(this.similarItemcd1);
	}

	public void setSimilarItemcd1(String similarItemcd1) {
		this.similarItemcd1 = similarItemcd1;
	}

	@Column(name = "similar_itemcd2", length = 18)
	public String getSimilarItemcd2() {
		return FormatUtil.process(this.similarItemcd2);
	}

	public void setSimilarItemcd2(String similarItemcd2) {
		this.similarItemcd2 = similarItemcd2;
	}

	@Column(name = "similar_itemcd3", length = 18)
	public String getSimilarItemcd3() {
		return FormatUtil.process(this.similarItemcd3);
	}

	public void setSimilarItemcd3(String similarItemcd3) {
		this.similarItemcd3 = similarItemcd3;
	}

	@Column(name = "similar_itemcd4", length = 18)
	public String getSimilarItemcd4() {
		return FormatUtil.process(this.similarItemcd4);
	}

	public void setSimilarItemcd4(String similarItemcd4) {
		this.similarItemcd4 = similarItemcd4;
	}

	@Column(name = "similar_itemcd5", length = 18)
	public String getSimilarItemcd5() {
		return FormatUtil.process(this.similarItemcd5);
	}

	public void setSimilarItemcd5(String similarItemcd5) {
		this.similarItemcd5 = similarItemcd5;
	}

	@Column(name = "similar_itemcd6", length = 18)
	public String getSimilarItemcd6() {
		return FormatUtil.process(this.similarItemcd6);
	}

	public void setSimilarItemcd6(String similarItemcd6) {
		this.similarItemcd6 = similarItemcd6;
	}

	@Column(name = "similar_itemcd7", length = 18)
	public String getSimilarItemcd7() {
		return FormatUtil.process(this.similarItemcd7);
	}

	public void setSimilarItemcd7(String similarItemcd7) {
		this.similarItemcd7 = similarItemcd7;
	}

		
}	
