package de.ueberproduct.zettl.domain;

import java.io.Serializable;

public class Geodata implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private double latitude;
	private double longitude;
	
	Geodata() {
		
	}
	
	public Geodata(double lat, double lon) {
		this.latitude = lat;
		this.longitude = lon;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}

}
