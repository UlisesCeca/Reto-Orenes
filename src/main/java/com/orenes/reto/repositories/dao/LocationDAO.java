package com.orenes.reto.repositories.dao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="locations")
public class LocationDAO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToOne
    @JoinColumn(name = "vehicle", referencedColumnName = "id", updatable = false)
    private VehicleDAO vehicle;
	private Long latitude;
	private Long longitude;
	private LocalDateTime dateTime;
	
	public Long getId() {
		return id;
	}
	
	public VehicleDAO getVehicle() {
		return vehicle;
	}
	
	public Long getLatitude() {
		return latitude;
	}
	
	public Long getLongitude() {
		return longitude;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	@Override
	public String toString() {
		return "LocationDAO [id=" + id + ", vehicle=" + vehicle + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", dateTime=" + dateTime + "]";
	}
}
