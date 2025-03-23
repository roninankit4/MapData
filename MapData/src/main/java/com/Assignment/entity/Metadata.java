package com.Assignment.entity;

import lombok.Data;

@Data
public class Metadata {

	public Metadata(String string, String string2, double d, int i) {
		// TODO Auto-generated constructor stub
	}
	private String id;
    private String type;
    private double rating;
    private int reviews;
    
    
    
	public Metadata() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getReviews() {
		return reviews;
	}
	public void setReviews(int reviews) {
		this.reviews = reviews;
	}
    
    
}
