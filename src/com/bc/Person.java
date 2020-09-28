package com.bc;
import java.util.ArrayList;

public class Person {
	private String personCode, firstName, lastName;
	private Address address;
	private ArrayList<String> emails;
	public Person(String personCode, String firstName, String lastName, Address address, ArrayList<String> emails) {
		this.personCode = personCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.emails = emails;
	}
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public ArrayList<String> getEmails() {
		return emails;
	}
	public void setEmails(ArrayList<String> emails) {
		this.emails = emails;
	}
	public String toString() {
		return personCode + " " + firstName + "," + lastName + " | " + address + " | " + emails;
	}
}
