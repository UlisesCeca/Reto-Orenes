package com.orenes.reto.repositories.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Class that represents the Vehicle entity. This entity stores all the information
 * needed to locate a vehicle. 
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
@Entity
@Table(name="vehicles")
public class VehicleDAO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "plate_number", updatable = false, unique = true)
	private String plateNumber;
	@OneToOne
    @JoinColumn(name = "last_location_id", referencedColumnName = "id")
	private LocationDAO lastLocation;
	@OneToMany(mappedBy = "assignedVehicle")
	private List<OrderDAO> orders;
	
	
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

	public List<OrderDAO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDAO> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "VehicleDAO [id=" + id + ", plateNumber=" + plateNumber + ", lastLocation=" + lastLocation + ", orders="
				+ orders + "]";
	}
	
	
	
}
