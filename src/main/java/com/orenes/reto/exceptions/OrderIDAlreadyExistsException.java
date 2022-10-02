package com.orenes.reto.exceptions;

/**
 * Exception that is thrown when an order that already exists is being attempted to be inserted again.
 * @author Ulises Ceca
 * @version 1.0
 */
public class OrderIDAlreadyExistsException extends RuntimeException {
	public OrderIDAlreadyExistsException(String orderId) {
	    super("There is already an order with the specified ID " + orderId);
	 }
}
