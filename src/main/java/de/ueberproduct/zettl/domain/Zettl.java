package de.ueberproduct.zettl.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "zettls")
public class Zettl {
	
	@Id
	private String id;
	private String description;
	private String emailAddress;
	private String street;
	private String postCode;
	private String city;
	private String radius;
	private String imageId;
	
	private String headline;
	private String contactData;
	
	private String editToken;
	
	Zettl() {
		
	}
	
	public Zettl(String editToken) {
		this.editToken = editToken;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getEditToken() {
		return editToken;
	}

	public void setEditToken(String editToken) {
		this.editToken = editToken;
	}
	
	public String getContactData() {
		return contactData;
	}
	
	public void setContactData(String contactData) {
		this.contactData = contactData;
	}
	
	public String getHeadline() {
		return headline;
	}
	
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	
	

}
