package com.orenes.reto.endpoints.dto;

/**
 * DTO for the Order entity. This class represents all the information related
 * to an order.
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
public class OrderDTO {
	private String orderId;
	
	OrderDTO() {}
	
	
	
	public OrderDTO(String orderId) {
		super();
		this.orderId = orderId;
	}



	public String getOrderId() {
		return orderId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + "]";
	}
}
