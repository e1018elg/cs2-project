package com.bc;

public class Customer {
	private String customerCode,customerType, customerName, contactCode;
	private Address address;
	

	public Customer(String customerCode, String customerType, String customerName, String contactCode, Address address) 
	{
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.address = address;
		this.customerType = customerType;
		this.contactCode= contactCode;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactCode() {
		return contactCode;
	}
	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

}
