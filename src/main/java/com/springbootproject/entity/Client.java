package com.springbootproject.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {
	private Integer id;
	private String lastName;
	private String name;
	private String numberPassport;
	private String address;
	private String phone;

	public Client(int id, String surName, String name, String passport, String homeAddress, String telephone) {
		this.id = id;
		this.lastName = surName;
		this.name = name;
		this.numberPassport = passport;
		this.address = homeAddress;
		this.phone = telephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumberPassport() {
		return numberPassport;
	}

	public void setNumberPassport(String numberPassport) {
		this.numberPassport = numberPassport;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
