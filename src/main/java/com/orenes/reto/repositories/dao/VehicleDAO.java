package com.orenes.reto.repositories.dao;

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
    @JoinColumn(name = "current_location", referencedColumnName = "id")
	private LocationDAO currentLocation;
	
	public Long getId() {
		return id;
	}
	
	public String getPlateNumber() {
		return plateNumber;
	}
	
	public LocationDAO getCurrentLocation() {
		return currentLocation;
	}
	
	@Override
	public String toString() {
		return "VehicleDAO [id=" + id + ", plateNumber=" + plateNumber + ", currentLocation=" + currentLocation + "]";
	}
	
}
