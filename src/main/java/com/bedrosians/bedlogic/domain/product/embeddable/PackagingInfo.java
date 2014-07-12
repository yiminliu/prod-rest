package com.bedrosians.bedlogic.domain.product.embeddable;


public class PackagingInfo implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	float boxPieces, boxSF, boxWeight = 0f;
	float palletBox, palletSF, palletWeight = 0f;
	
	public float getBoxPieces() {
		return boxPieces;
	}
	public void setBoxPieces(float boxPieces) {
		this.boxPieces = boxPieces;
	}
	public float getBoxSF() {
		return boxSF;
	}
	public void setBoxSF(float boxSF) {
		this.boxSF = boxSF;
	}
	public float getBoxWeight() {
		return boxWeight;
	}
	public void setBoxWeight(float boxWeight) {
		this.boxWeight = boxWeight;
	}
	public float getPalletBox() {
		return palletBox;
	}
	public void setPalletBox(float palletBox) {
		this.palletBox = palletBox;
	}
	public float getPalletSF() {
		return palletSF;
	}
	public void setPalletSF(float palletSF) {
		this.palletSF = palletSF;
	}
	public float getPalletWeight() {
		return palletWeight;
	}
	public void setPalletWeight(float palletWeight) {
		this.palletWeight = palletWeight;
	}
	
	
}
