package com.bc;

public class Towing extends Product {
	protected String productType,productLabel;
	protected double costPerMile;
	public Towing(String productCode, String productType, String productLabel, double costPerMile) {
		super(productCode);
		this.productType = productType;
		this.productLabel = productLabel;
		this.costPerMile = costPerMile;
	}

	public String getProductType() {
		return productType;
	}
	public String getProductLabel() {
		return productLabel;
	}
	public double getCostPerMile() {
		return costPerMile;
	}
	public void setCostPerMile(double costPerMile) {
		this.costPerMile = costPerMile;
	}
}
