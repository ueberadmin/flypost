package de.ueberproduct.zettl.usecase.show;

public class ViewModel {
	
	private final String headline;
	private final String description;
	private final String contactData;
	private final String imageId;
	
	private ViewModel(Builder builder) {
		this.headline = builder.headline;
		this.description = builder.description;
		this.contactData = builder.contactData;
		this.imageId = builder.imageId;
	}
	
	public String getHeadline() {
		return headline;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getContactData() {
		return contactData;
	}
	
	public String getImageId() {
		return imageId;
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
		
		public ViewModel get() {
			return new ViewModel(this);
		}
	}

}
