package de.ueberproduct.zettl.osm.domain;


public class Place {
	
	private double lat;
	private double lon;
	private String display_name;
	private String osm_id;
	private String osm_type;
	private Address address;
	
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	public String getDisplayName() {
		return display_name;
	}
	
	public String getOsmId() {
		return osm_id;
	}
	
	public String getOsmType() {
		return osm_type;
	}
	
	public Address getAddress() {
		return address;
	} 
	

}
