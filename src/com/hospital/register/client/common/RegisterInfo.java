package com.hospital.register.client.common;

public class RegisterInfo {
	
	private String ID, name, gender, age, telephone, office, classification, price;
	
	public RegisterInfo(String ID, String name, String gender, String age, String telephone, String office, String classification, String price) {
		this.ID = ID;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.telephone = telephone;
		this.office = office;
		this.classification = classification;
		this.price = price;
	}
	
	public RegisterInfo(String ID, String name, String gender, String age, String telephone) {
		this.ID = ID;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.telephone = telephone;
	}
	
	

	public void setOffice(String office) {
		this.office = office;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public void setPrice(String price) {
		this.price = price;
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
