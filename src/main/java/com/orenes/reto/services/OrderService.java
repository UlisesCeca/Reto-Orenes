package com.orenes.reto.services;

import org.springframework.stereotype.Service;

import com.orenes.reto.exceptions.OrderIDAlreadyExistsException;
import com.orenes.reto.services.classes.Order;

/**
 * Interface that defines the bussiness logic regarding the orders of the clients.
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
@Service
public interface OrderService {
	/**
	 * Inserts a new order into the database.
	 *
	 * @param plateNumber the plate number of the car where the order will travel
	 * @param newOrder the new location order to be inserted
	 * @throws OrderIDAlreadyExistsException if the order ID already exists
	 * @return the inserted order
	 */
	Order insertOrder(final String plateNumber, final Order newOrder) throws OrderIDAlreadyExistsException;
	
	/**
	 * Deletes an order for the specified vehicle.
	 *
	 * @param orderId the public id of the order
	 */
	void deleteOrder(final String orderId);
}
