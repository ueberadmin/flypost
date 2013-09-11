package de.ueberproduct.zettl.domain;

import java.io.Serializable;

public class Geodata implements Serializable {
	private static final long serialVersionUID = 2L;
	
	private String osmId;
	
	private double latitude;
	private double longitude;
	private String displayName;
	
	private String street;
	private String postcode;
	private String city;
	
	
	public String getOsmId() {
		return osmId;
	}
	public void setOsmId(String osmId) {
		this.osmId = osmId;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
