package com.meterCostFileImporter.entity;

import java.math.BigDecimal;

public class Location {

	private Long id;
	private String country;
	private String state;
	private String city;
	private String neighborhood;
	private BigDecimal cost;

	public Location() {
		super();
	}
	
	public Location(Long id, String country, String state, String city, String neighborhood,  BigDecimal cost) {
		this.id = id;
		this.country = country;
		this.state = state;
		this.city = city;
		this.neighborhood = neighborhood;
		this.cost = cost;
	}

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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return this.country + ", " + this.state + ", " + this.city + ", " + this.neighborhood + ", " + this.cost;
	}
	
}
