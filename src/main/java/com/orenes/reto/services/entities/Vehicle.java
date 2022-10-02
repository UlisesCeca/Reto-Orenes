package com.orenes.reto.services.entities;

import java.util.List;

/**
 * Class that represents a Vehicle. This class stores all the information
 * needed to locate a vehicle. 
 * 
 * @author Ulises Ceca
 * @version 1.0
 */

public class Vehicle {
	private Long id;
	private String plateNumber;
	private Location lastLocation;
	private List<Order> orders;
	
	public Long getId() {
		return this.id;
	}
	
	public String getPlateNumber() {
		return this.plateNumber;
	}
	
	public void setPlateNumber(final String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	public Location getLastLocation() {
		return this.lastLocation;
	}

	public void setLastLocation(final Location lastLocation) {
		this.lastLocation = lastLocation;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(final List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", plateNumber=" + plateNumber + ", lastLocation=" + lastLocation + ", orders="
				+ orders + "]";
	}
}
