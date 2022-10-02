package com.orenes.reto.repositories.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * Composition class to store all the locations where a car has been 
 * 
 * @author Ulises Ceca
 * @version 1.0
 */

@Entity
@Table(name="locations_history")
public class LocationHistoryDAO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToOne
    @JoinColumn(name="vehicle_id")
    private VehicleDAO vehicle;
	@OneToOne
    @JoinColumn(name="location_id")
    private LocationDAO location;
	
	LocationHistoryDAO() {}
	
	public LocationHistoryDAO(final VehicleDAO vehicle, final LocationDAO location) {
		this.vehicle = vehicle;
		this.location = location;
	}
	
	public VehicleDAO getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(VehicleDAO vehicle) {
		this.vehicle = vehicle;
	}
	
	public LocationDAO getLocation() {
		return location;
	}
	
	public void setLocation(LocationDAO location) {
		this.location = location;
	}
}
