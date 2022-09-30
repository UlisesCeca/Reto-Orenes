package com.orenes.reto.repositories.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class OrderDAO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToOne
    @JoinColumn(name = "assigned_vehicle", referencedColumnName = "id")
    private VehicleDAO assignedVehicle;
	
	public Long getId() {
		return id;
	}
	
	public VehicleDAO getAssignedVehicle() {
		return assignedVehicle;
	}

	@Override
	public String toString() {
		return "OrderDAO [id=" + id + ", assignedVehicle=" + assignedVehicle + "]";
	}
}
