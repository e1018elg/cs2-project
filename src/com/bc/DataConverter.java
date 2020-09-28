package com.bc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DataConverter {
	public static void main(String args[]) {

		ArrayList<Person> people = createPeopleArray("data/Persons.dat");
		writePeople("data/Persons.xml",people);
		
		ArrayList<Product> products = createProductArray("data/Products.dat");
		writeProducts("data/Products.xml",products);
		
		ArrayList<Customer> customer = createCustomerArray("data/Customers.dat");
		writeCustomers("data/Customers.xml",customer);
		
	}
	private static ArrayList<Person> createPeopleArray(String fileName){
		ArrayList<Person> people = new ArrayList<>();
		BufferedReader personReader = null;
		try {
			personReader = new BufferedReader(new FileReader(fileName));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String line = personReader.readLine();
			while((line = personReader.readLine()) != null) {
				people.add(createPerson(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return people;
	}
	private static Person createPerson(String line) {
		String personCode, firstName, lastName;
		Address address;
		ArrayList<String> emails = new ArrayList<>();
		int curIndex = 0;
		if(line.indexOf(';',curIndex)<0) {
			personCode = line.substring(curIndex);
			curIndex = line.length();
		}else {
			personCode = line.substring(curIndex,line.indexOf(';', curIndex));
			curIndex = line.indexOf(';',curIndex)+1;
		}
		if(line.indexOf(';',curIndex)<0) {
			lastName = line.substring(curIndex);
			curIndex = line.length();
		} else {
			lastName = line.substring(curIndex,line.indexOf(',', curIndex));
			curIndex = line.indexOf(',',curIndex)+1;
		}
		if(line.indexOf(';',curIndex)<0){
			firstName = line.substring(curIndex);
			curIndex = line.length();
		} else {
			firstName = line.substring(curIndex,line.indexOf(';',curIndex));
			curIndex = line.indexOf(';',curIndex)+1;
		}
		if(line.indexOf(';',curIndex)<0) {
			address = createAddress(line.substring(curIndex));
		} else {
			address = createAddress(line.substring(curIndex,line.indexOf(';', curIndex)));
			curIndex = line.indexOf(';',curIndex)+1;
		}
		for(int i=curIndex;i<line.length();i++) {
			try {
				emails.add(line.substring(i, line.indexOf(',', i)));
				i = line.indexOf(',',i);
			} catch (IndexOutOfBoundsException e) {
				emails.add(line.substring(i));
				i=line.length();
			}
		}
		return new Person(personCode, firstName, lastName, address, emails);
	}
	private static Address createAddress(String address) {
		String street, city, state, zip, country;
		int curIndex = 0;
		street = address.substring(curIndex,address.indexOf(',',curIndex));
		curIndex = address.indexOf(',',curIndex)+1;
		city = address.substring(curIndex,address.indexOf(',',curIndex));
		curIndex = address.indexOf(',',curIndex)+1;
		state = address.substring(curIndex,address.indexOf(',',curIndex));
		curIndex = address.indexOf(',',curIndex)+1;
		zip = address.substring(curIndex,address.indexOf(',',curIndex));
		curIndex = address.indexOf(',',curIndex)+1;
		country = address.substring(curIndex);
		return new Address(street,city,state,zip,country);
	}
	private static void writePeople(String filePath, ArrayList<Person> people) {
		String version = "<?xml version=\"1.0\"?>";
		String header = "<persons>";
		String closer = "</persons>";
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("person", com.bc.Person.class);
		try {
			PrintWriter out = new PrintWriter(new File(filePath));
			out.write(version+"\n"+header+"\n");
			for(Person input : people) {
				out.write(xstream.toXML(input));
				out.write("\n");
			}
			out.write(closer);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Product> createProductArray(String fileName){
		ArrayList<Product> products = new ArrayList<Product>();
		BufferedReader productReader = null;
		try {
			productReader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String line = productReader.readLine();
			while((line = productReader.readLine()) != null) {
				products.add(createProduct(line));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	private static Product createProduct(String line) {
		String productCode, productType, productLabel;
		double dailyCost,deposit,cleaningFee,partsCost,hourlyLaborCost,unitCost,costPerMile;
		int curIndex = 0;
		productCode = line.substring(curIndex,line.indexOf(';', curIndex));
		curIndex = line.indexOf(';',curIndex)+1;
		productType = line.substring(curIndex,line.indexOf(';', curIndex));
		curIndex = line.indexOf(';',curIndex)+1;
		if(productType.equals("R")) {
			productLabel = line.substring(curIndex,line.indexOf(';', curIndex));
			curIndex = line.indexOf(';',curIndex)+1;
			dailyCost = Double.parseDouble(line.substring(curIndex,line.indexOf(';', curIndex)));
			curIndex = line.indexOf(';',curIndex)+1;
			deposit = Double.parseDouble(line.substring(curIndex,line.indexOf(';', curIndex)));
			curIndex = line.indexOf(';',curIndex)+1;
			cleaningFee = Double.parseDouble(line.substring(curIndex));
			return new Rental(productCode,productType,productLabel,dailyCost,deposit,cleaningFee);
		} else if(productType.equals("F")) {
			productLabel = line.substring(curIndex,line.indexOf(';', curIndex));
			curIndex = line.indexOf(';',curIndex)+1;
			partsCost = Double.parseDouble(line.substring(curIndex,line.indexOf(';', curIndex)));
			curIndex = line.indexOf(';',curIndex)+1;
			if(line.substring(curIndex).contains(String.valueOf(';'))) {
				hourlyLaborCost = Double.parseDouble(line.substring(curIndex,line.indexOf(';',curIndex)));
			} else {
				hourlyLaborCost = Double.parseDouble(line.substring(curIndex));
			}
			return new Repair(productCode,productType,productLabel,partsCost,hourlyLaborCost);
		} else if(productType.equals("C")) {
			productLabel = line.substring(curIndex,line.indexOf(';', curIndex));
			curIndex = line.indexOf(';',curIndex)+1;
			unitCost = Double.parseDouble(line.substring(curIndex));
			return new Concession(productCode,productType,productLabel,unitCost);
		} else if(productType.equals("T")) {
			productLabel = line.substring(curIndex,line.indexOf(';', curIndex));
			curIndex = line.indexOf(';',curIndex)+1;
			costPerMile = Double.parseDouble(line.substring(curIndex));
			return new Towing(productCode,productType,productLabel,costPerMile);
		} else {
			System.out.println("Invalid ProductType");
			return null;
		}
	}
	private static void writeProducts(String filePath, ArrayList<Product> products) {
		String version = "<?xml version=\"1.0\"?>";
		String header = "<products>";
		String closer = "</products>";
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("product", com.bc.Product.class);
		xstream.alias("product", com.bc.Rental.class);
		xstream.alias("product", com.bc.Repair.class);
		xstream.alias("product", com.bc.Concession.class);
		xstream.alias("product", com.bc.Towing.class);
		try {
			PrintWriter out = new PrintWriter(new File(filePath));
			out.write(version+"\n"+header+"\n");
			for(Product input : products) {
				out.write(xstream.toXML(input));
				out.write("\n");
			}
			out.write(closer);
			out.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Customer> createCustomerArray(String fileName){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			String line = reader.readLine();
			while((line = reader.readLine()) != null) {
				customers.add(createCustomer(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return customers;
	}
	private static Customer createCustomer(String line) {
		String customerCode, customerType, customerName, contactCode;
		Address address;
		int curIndex = 0;
		customerCode = line.substring(curIndex,line.indexOf(';', curIndex));
		curIndex = line.indexOf(';',curIndex)+1;
		customerType = line.substring(curIndex,line.indexOf(';', curIndex)); 
		curIndex = line.indexOf(';',curIndex)+1;
		customerName = line.substring(curIndex,line.indexOf(';',curIndex));
		curIndex = line.indexOf(';',curIndex)+1;
		contactCode = line.substring(curIndex,line.indexOf(';',curIndex));
		curIndex = line.indexOf(';',curIndex)+1;
		address = createAddress(line.substring(curIndex));
		return new Customer(customerCode,customerType, customerName, contactCode, address);
	}
	private static void writeCustomers(String filePath, ArrayList<Customer> customers) {
		String version = "<?xml version=\"1.0\"?>";
		String header = "<customers>";
		String closer = "</customer>";
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("customer", com.bc.Customer.class);
		try {
			PrintWriter out = new PrintWriter(new File(filePath));
			out.write(version+"\n"+header+"\n");
			for(Customer input : customers) {
				out.write(xstream.toXML(input));
				out.write("\n");
			}
			out.write(closer);
			out.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
