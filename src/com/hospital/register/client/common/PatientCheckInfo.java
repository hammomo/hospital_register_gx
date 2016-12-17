package com.hospital.register.client.common;

public class PatientCheckInfo {
	
	private String number, name, gender, age, telephone, office, classification, price, date, type, username;
	
	public PatientCheckInfo(String number, String name, String gender, String age, String telephone, String office, String classification, String price, String date, String type, String username) {
		this.number = number;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.telephone = telephone;
		this.office = office;
		this.classification = classification;
		this.price = price;
		this.date = date;
		this.type = type;
		this.username = username;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getAge() {
		return age;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getOffice() {
		return office;
	}

	public String getClassification() {
		return classification;
	}

	public String getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public String getUsername() {
		return username;
	}
	
	public String getDate() {
		return date;
	}
}
