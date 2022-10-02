package com.orenes.reto.services.classes;

/**
 * This class represents all the information about an order. 
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
public class Order {
	private Long id;
    private Vehicle assignedVehicle;
	private String orderId;

	Order() {}
	
	public Order(String orderId) {
		this.orderId = orderId;
	}
	
	public Vehicle getAssignedVehicle() {
		return this.assignedVehicle;
	}
	public void setAssignedVehicle(final Vehicle assignedVehicle) {
		this.assignedVehicle = assignedVehicle;
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(final String orderId) {
		this.orderId = orderId;
	}
	public Long getId() {
		return this.id;
	}
}
