package de.ueberproduct.zettl.usecase.show;

public class ViewModel {
	
	private final String headline;
	private final String description;
	private final String contactData;
	private final String street;
	private final String postcode;
	private final String city;
	private final String imageId;
	private final String editToken;
	private final String lon;
	private final String lat;
	private final Integer radius;

	
	private ViewModel(Builder builder) {
		this.headline = builder.headline;
		this.description = builder.description;
		this.contactData = builder.contactData;
		this.imageId = builder.imageId;
		this.editToken = builder.editToken;
		this.street = builder.street;
		this.postcode = builder.postcode;
		this.city = builder.city;
		this.lon = builder.lon;
		this.lat = builder.lat;
		this.radius = builder.radius;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public String getStreet() {
		return street;
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
	
	public String getEditToken() {
		return editToken;
	}
	
	public String getLat() {
		return lat;
	}
	
	public String getLon() {
		return lon;
	}
	
	public Integer getRadius() {
		return radius;
	}
	
	public static class Builder {
		private String headline;
		private String description;
		private String contactData;
		private String imageId;
		private String editToken;
		private String street;
		private String postcode;
		private String city;
		private String lon;
		private String lat;
		private Integer radius;
		
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
		
		public Builder editToken(String value) {
			this.editToken = value;
			return this;
		}
		
		public Builder street(String value) {
			this.street = value;
			return this;
		}
		
		public Builder postcode(String value) {
			this.postcode = value;
			return this;
		}
		
		public Builder city(String value) {
			this.city = value;
			return this;
		}
		
		public Builder lon(String value) {
			this.lon = value;
			return this;
		}
		
		public Builder lat(String value) {
			this.lat = value;
			return this;
		}
		
		public Builder radius(Integer value) {
			this.radius = value;
			return this;
		}
		
		public ViewModel get() {
			return new ViewModel(this);
		}
	}

}
