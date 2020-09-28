package com.bc;

public abstract class Product {
	protected String productCode;
	public Product(String productCode) {
		this.productCode = productCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public abstract String getProductType();
	public abstract String getProductLabel();
}
