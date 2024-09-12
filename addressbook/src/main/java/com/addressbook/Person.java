package com.addressbook;

import java.util.Date;

public class Person {
	private String NAME;
	private PhoneNumber phoneNumber;
	private Date date;
	
	public Person(String name, PhoneNumber phoneNumber) {
		this.NAME = name;
	}

	public Person(String name, String sphone) {
		this.NAME = name;
		PhoneNumber phone = new PhoneNumber(sphone);
		this.phoneNumber = phone;
	}

	public String getName() {
		return NAME;
	}

	public void setName(String name) {
		NAME = name;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
