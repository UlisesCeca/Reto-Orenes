package com.orenes.reto.exceptions;

/**
 * Exception that is thrown when an order isn't found on the database.
 * @author Ulises Ceca
 * @version 1.0
 */
public class OrderNotFoundException extends RuntimeException {
	public OrderNotFoundException(String orderId) {
	    super("Could not find order with id " + orderId);
	  }
}
