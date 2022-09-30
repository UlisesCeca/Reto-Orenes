package com.orenes.reto.repositories.dao;
/**
 * Class that represents the Order entity. This entity stores all the information
 * of an order made by a client. 
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
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
		return this.id;
	}
	
	public VehicleDAO getAssignedVehicle() {
		return this.assignedVehicle;
	}

	@Override
	public String toString() {
		return "OrderDAO [id=" + this.id + ", assignedVehicle=" + this.assignedVehicle + "]";
	}
}
