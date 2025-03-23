package com.Assignment.entity;

import lombok.Data;

@Data
public class Location {
	public Location(String string, double d, double e) {
		// TODO Auto-generated constructor stub
	}
	private String id;
    private double latitude;
    private double longitude;
    
    public Location() {}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
    
    
    

}
