package com.bc;

public class Rental extends Product {
	protected String productType,productLabel;
	protected double dailyCost,deposit,cleaningFee;
	public Rental(String productCode,String productType,String productLabel,double dailyCost,double deposit, double cleaningFee) {
		super(productCode);
		this.productType = productType;
		this.productLabel = productLabel;
		this.dailyCost = dailyCost;
		this.deposit = deposit;
		this.cleaningFee = cleaningFee;
	}
	
	public String getProductType() {
		return productType;
	}
	public String getProductLabel() {
		return productLabel;
	}
	public double getDailyCost() {
		return dailyCost;
	}
	public void setDailyCost(double dailyCost) {
		this.dailyCost = dailyCost;
	}
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public double getCleaningFee() {
		return cleaningFee;
	}
	public void setCleaningFee(double cleaningFee) {
		this.cleaningFee = cleaningFee;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
}
