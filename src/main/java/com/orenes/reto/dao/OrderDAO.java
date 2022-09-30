package com.orenes.reto.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class OrderDAO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String address;
	
	protected OrderDAO() {}
	
	public OrderDAO(final String address) {
		this.address = address;
	}
	
	public Long getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", address=" + address + "]";
	}		
	
}
