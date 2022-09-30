package com.orenes.reto.repositories.dao;
/**
 * Class that represents the Location entity. This entity represents all the information needed to know 
 * where a vehicle is located at a given moment. 
 * 
 * @author Ulises Ceca
 * @version 1.0
 */

import java.time.LocalDateTime;

import javax.persistence.Column;
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
	@Column(name = "latitude")
	private Long latitude;
	@Column(name = "longitude")
	private Long longitude;
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	
	public Long getId() {
		return id;
	}
	
	public VehicleDAO getVehicle() {
		return this.vehicle;
	}
	
	public Long getLatitude() {
		return this.latitude;
	}
	
	public Long getLongitude() {
		return this.longitude;
	}
	
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}

	@Override
	public String toString() {
		return "LocationDAO [id=" + this.id + ", vehicle=" + this.vehicle + ", latitude=" + this.latitude + ", longitude=" + this.longitude
				+ ", dateTime=" + this.dateTime + "]";
	}
}
