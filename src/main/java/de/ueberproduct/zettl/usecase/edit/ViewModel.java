package de.ueberproduct.zettl.usecase.edit;

import org.springframework.web.multipart.MultipartFile;

public class ViewModel {
	
	private String headline;
	private String description;
	private String contactData;
	private MultipartFile image;	
	
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContactData() {
		return contactData;
	}
	public void setContactData(String contactData) {
		this.contactData = contactData;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
}
