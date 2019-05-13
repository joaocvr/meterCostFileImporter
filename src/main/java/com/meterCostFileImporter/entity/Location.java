package com.meterCostFileImporter.entity;

public class Location {

	private Long id;
	private String country;
	private String state;
	private String city;
	private String neighborhood;
	private LocationCost locationCost;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public LocationCost getLocationCost() {
		return locationCost;
	}

	public void setLocationCost(LocationCost locationCost) {
		this.locationCost = locationCost;
	}

	@Override
	public String toString() {
		return this.country + ", " + this.state + ", " + this.city + ", " + this.neighborhood;
	}
	
}
