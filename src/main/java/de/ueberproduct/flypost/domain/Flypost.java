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
	
	Flypost() {
		// for spring
	}
	
	private Flypost(Builder builder) {
		this.headline = builder.headline;
		this.description = builder.description;
		this.contactData = builder.contactData;
		this.imageId = builder.imageId;
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

	@Override
	public String toString() {
		return "Flypost [id=" + id + ", headline=" + headline
				+ ", description=" + description + ", contactData="
				+ contactData + ", imageId=" + imageId + "]";
	}
	
	public static class Builder {
		private String headline;
		private String description;
		private String contactData;
		
		private String imageId;
		
		public Builder headline(String value) {
			this.headline = value;
			return this;
		}
		
		public Builder description(String value) {
			this.description = value;
			return this;
		}
		
		public Builder contactData(String value) {
			this.contactData = value;
			return this;
		}
		
		public Builder imageId(String value) {
			this.imageId = value;
			return this;
		}
		
		public Flypost get() {
			return new Flypost(this);
		}
	}

}
