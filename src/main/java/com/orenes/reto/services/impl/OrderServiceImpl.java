package com.orenes.reto.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.orenes.reto.exceptions.OrderIDAlreadyExistsException;
import com.orenes.reto.repositories.OrderRepository;
import com.orenes.reto.repositories.dao.OrderDAO;
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
	private final ModelMapper modelMapper;
	
	public OrderServiceImpl(final OrderRepository orderRepository, final ModelMapper modelMapper) {
		this.orderRepository = orderRepository;
		this.modelMapper = modelMapper;
	}

	/**
	 * Inserts a new order into the database.
	 *
	 * @param newOrder the new location order to be inserted
	 * @throws OrderIDAlreadyExistsException if the order ID already exists
	 * @return the inserted order
	 */
	@Override
	public Order insertOrder(final Order newOrder) throws OrderIDAlreadyExistsException{
		final OrderDAO newOrderDao;
		
		if (this.orderRepository.existsByOrderId(newOrder.getOrderId())) {
			throw new OrderIDAlreadyExistsException(newOrder.getOrderId());
		} else {
			newOrderDao = this.modelMapper.map(newOrder, OrderDAO.class);
			this.orderRepository.save(newOrderDao);
		}
		
		return this.modelMapper.map(newOrderDao, Order.class);
	}

}
