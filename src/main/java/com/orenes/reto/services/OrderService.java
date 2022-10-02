package com.orenes.reto.services;

import org.springframework.stereotype.Service;

import com.orenes.reto.exceptions.OrderIDAlreadyExistsException;
import com.orenes.reto.services.entities.Order;

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
	 * @param newOrder the new location order to be inserted
	 * @throws OrderIDAlreadyExistsException if the order ID already exists
	 * @return the inserted order
	 */
	Order insertOrder(final Order newOrder) throws OrderIDAlreadyExistsException;
}
