package com.orenes.reto.services.impl;

import org.springframework.stereotype.Service;

import com.orenes.reto.repositories.OrderRepository;
import com.orenes.reto.services.OrderService;
import com.orenes.reto.services.entities.Order;

/**
 * Class that implements the bussiness logic regarding the orders of the clients
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService{
	private final OrderRepository orderRepository;
	
	public OrderServiceImpl(final OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Order insertOrder(final Order newOrder) {
		// TODO Auto-generated method stub
		return null;
	}

}
