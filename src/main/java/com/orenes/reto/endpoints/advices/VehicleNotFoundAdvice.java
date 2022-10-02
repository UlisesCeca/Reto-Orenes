package com.orenes.reto.endpoints.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.orenes.reto.exceptions.VehicleNotFoundException;

/**
 * Advice for when a vehicle resource isn't found on the database.
 * @author Ulises Ceca
 * @version 1.0
 */
@RestControllerAdvice
public class VehicleNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(VehicleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String vehicleNotFoundHandler(VehicleNotFoundException ex) {
	  return ex.getMessage();
	}
}