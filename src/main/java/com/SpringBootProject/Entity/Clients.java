package com.SpringBootProject.Entity;

public class Clients {
	private int id;
	private String SurName;
	private String Name;
	private String Passport;
	private String HomeAddress;
	private String Telephone;

	public Clients(int id, String surName, String name, String passport, String homeAddress, String telephone) {
		super();
		this.id = id;
		SurName = surName;
		Name = name;
		Passport = passport;
		HomeAddress = homeAddress;
		Telephone = telephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSurName() {
		return SurName;
	}

	public void setSurName(String surName) {
		SurName = surName;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassport() {
		return Passport;
	}

	public void setPassport(String passport) {
		Passport = passport;
	}

	public String getHomeAddress() {
		return HomeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		HomeAddress = homeAddress;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	
	
}
