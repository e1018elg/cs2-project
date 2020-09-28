package com.bc;

public class Concession extends Product {
	protected String productType,productLabel;
	protected double unitCost;
	public Concession(String productCode, String productType, String productLabel, double unitCost) {
		super(productCode);
		this.productType = productType;
		this.productLabel = productLabel;
		this.unitCost = unitCost;
	}
	
	public String getProductType() {
		return productType;
	}
	public String getProductLabel() {
		return productLabel;
	}
	public double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
}
