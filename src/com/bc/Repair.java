package com.bc;

public class Repair extends Product {
	protected String productType,productLabel;
	protected double partsCost,hourlyLaborCost;
	
	public Repair(String productCode, String productType, String productLabel, double partsCost, double hourlyLaborCost) {
		super(productCode);
		this.productType = productType;
		this.productLabel = productLabel;
		this.partsCost = partsCost;
		this.hourlyLaborCost = hourlyLaborCost;
	}

	public String getProductType() {
		return productType;
	}
	public String getProductLabel() {
		return productLabel;
	}
	public double getPartsCost() {
		return partsCost;
	}
	public void setPartsCost(double partsCost) {
		this.partsCost = partsCost;
	}
	public double getHourlyLaborCost() {
		return hourlyLaborCost;
	}
	public void setHourlyLaborCost(double hourlyLaborCost) {
		this.hourlyLaborCost = hourlyLaborCost;
	}
}
