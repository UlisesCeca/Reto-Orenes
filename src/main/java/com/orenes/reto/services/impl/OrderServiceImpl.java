package com.orenes.reto.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.orenes.reto.exceptions.OrderIDAlreadyExistsException;
import com.orenes.reto.exceptions.OrderNotFoundException;
import com.orenes.reto.exceptions.VehicleNotFoundException;
import com.orenes.reto.repositories.OrderRepository;
import com.orenes.reto.repositories.VehicleRepository;
import com.orenes.reto.repositories.dao.OrderDAO;
import com.orenes.reto.repositories.dao.VehicleDAO;
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
	private final VehicleRepository vehicleRepository;
	private final ModelMapper modelMapper;
	
	public OrderServiceImpl(final OrderRepository orderRepository, final ModelMapper modelMapper,
			final VehicleRepository vehicleRepository) {
		this.orderRepository = orderRepository;
		this.vehicleRepository = vehicleRepository;
		this.modelMapper = modelMapper;
	}

	/**
	 * Inserts a new order into the database.
	 *
	 * @param plateNumber the plate number of the car where the order will travel
	 * @param newOrder the new location order to be inserted
	 * @throws OrderIDAlreadyExistsException if the order ID already exists
	 * @return the inserted order
	 */
	@Override
	public Order insertOrder(final String plateNumber, final Order newOrder) throws OrderIDAlreadyExistsException{
		final VehicleDAO assignedVehicle = this.vehicleRepository.findByPlateNumber(plateNumber)
				.orElseThrow(() -> new VehicleNotFoundException(plateNumber));
		final OrderDAO newOrderDao;
		
		if (this.orderRepository.existsByOrderId(newOrder.getOrderId())) {
			throw new OrderIDAlreadyExistsException(newOrder.getOrderId());
		} else {
			newOrderDao = this.modelMapper.map(newOrder, OrderDAO.class);
			newOrderDao.setAssignedVehicle(assignedVehicle);
			this.orderRepository.save(newOrderDao);
		}
		
		return this.modelMapper.map(newOrderDao, Order.class);
	}

	/**
	 * Deletes an order for the specified vehicle.
	 *
	 * @param orderId the public id of the order
	 */
	@Override
	public void deleteOrder(final String orderId) {
		final OrderDAO orderToDelete = this.orderRepository.findByOrderId(orderId)
				.orElseThrow(() -> new OrderNotFoundException(orderId));
		this.orderRepository.delete(orderToDelete);
	}

}
