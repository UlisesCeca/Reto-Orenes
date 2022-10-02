package com.orenes.reto.endpoints.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.orenes.reto.exceptions.OrderIDAlreadyExistsException;

/**
 * Advice for when an order already exists on the database.
 * @author Ulises Ceca
 * @version 1.0
 */
@RestControllerAdvice
public class OrderIDAlreadyExistsAdvice {
	@ResponseBody
	@ExceptionHandler(OrderIDAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	String orderExistsHandler(OrderIDAlreadyExistsException ex) {
	  return ex.getMessage();
	}
}
