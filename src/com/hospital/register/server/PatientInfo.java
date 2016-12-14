package com.hospital.register.server;

public class PatientInfo {
	
	private String number, ID, name, gender, age, telephone, office, classification, price;
	
	public PatientInfo(String ID, String name, String gender, String age, String telephone, String office, String classification, String price) {
		this.ID = ID;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.telephone = telephone;
		this.office = office;
		this.classification = classification;
		this.price = price;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}

	public String getID() {
		return ID;
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
}
