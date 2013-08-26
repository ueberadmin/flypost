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

	@Override
	public String toString() {
		return "FlyPost [id=" + id + ", headline=" + headline
				+ ", description=" + description + ", contactData="
				+ contactData + "]";
	}
	
	

}
