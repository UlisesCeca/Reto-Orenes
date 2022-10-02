package com.orenes.reto.repositories.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Class that represents the Order entity. This entity stores all the information
 * of an order made by a client. 
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
@Entity
@Table(name="orders")
public class OrderDAO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assigned_vehicle_id", referencedColumnName = "id", nullable = false)
    private VehicleDAO assignedVehicle;
	@Column(name = "order_id", updatable = false, unique = true, nullable = false)
	private String orderId;
	
	public Long getId() {
		return this.id;
	}
	
	public VehicleDAO getAssignedVehicle() {
		return this.assignedVehicle;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(final String orderId) {
		this.orderId = orderId;
	}

	public void setAssignedVehicle(final VehicleDAO assignedVehicle) {
		this.assignedVehicle = assignedVehicle;
	}

	@Override
	public String toString() {
		return "OrderDAO [id=" + this.id + ", assignedVehicle=" + this.assignedVehicle + ", orderId=" + this.orderId + "]";
	}
}
