package com.orenes.reto.repositories.dao;
/**
 * Class that represents the Vehicle entity. This entity stores all the information
 * needed to locate a vehicle. 
 * 
 * @author Ulises Ceca
 * @version 1.0
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="vehicles")
public class VehicleDAO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "plate_number", updatable = false, unique = true)
	private String plateNumber;
	@OneToOne(mappedBy = "vehicle")
    @JoinColumn(name = "last_location", referencedColumnName = "id")
	private LocationDAO lastLocation;
	
	public Long getId() {
		return this.id;
	}
	
	public String getPlateNumber() {
		return this.plateNumber;
	}
	
	public void setPlateNumber(final String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	public LocationDAO getLastLocation() {
		return this.lastLocation;
	}

	public void setLastLocation(final LocationDAO lastLocation) {
		this.lastLocation = lastLocation;
	}

	@Override
	public String toString() {
		return "VehicleDAO [id=" + this.id + ", plateNumber=" + this.plateNumber + ", lastLocation=" + this.lastLocation + "]";
	}
	
}
