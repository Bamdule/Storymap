package com.storymap.network.dto;

public class Point {
	private double latitude;
	private double longtitude;
	
	public Point(double latitude, double longtitude) {
		super();
		this.latitude = latitude;
		this.longtitude = longtitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	@Override
	public String toString() {
		return "Point [latitude=" + latitude + ", longtitude=" + longtitude + "]";
	}
	
	
	
	


	

}
