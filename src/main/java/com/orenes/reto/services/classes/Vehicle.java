package com.orenes.reto.services.classes;

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

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", plateNumber=" + plateNumber + ", lastLocation=" + lastLocation + "]";
	}

	
}
