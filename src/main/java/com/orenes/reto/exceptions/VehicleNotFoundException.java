package com.orenes.reto.exceptions;

/**
 * Exception that is thrown when a vehicle with the specified plate number isn't found in the database
 * @author Ulises Ceca
 * @version 1.0
 */
public class VehicleNotFoundException extends RuntimeException {

	public VehicleNotFoundException(String plateNumber) {
	    super("Could not find vehicle with plate number " + plateNumber);
	  }
}
