package de.ueberproduct.flypost.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flyposts")
public class Flypost {
	
	@Id
	private String id;
	private String headline;
	private String description;
	private String contactData;
	private String imageId;
	
	private String owner;
	
	Flypost() {
		
	}
	
	public Flypost(String owner) {
		this.owner = owner;
	}
	
	public String getId() {
		return id;
	}
	
	public String getContactData() {
		return contactData;
	}
	public String getDescription() {
		return description;
	}
	public String getHeadline() {
		return headline;
	}
	public String getImageId() {
		return imageId;
	}

	public String getOwner() {
		return owner;
	}
	
	public void setContactData(String contactData) {
		this.contactData = contactData;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
