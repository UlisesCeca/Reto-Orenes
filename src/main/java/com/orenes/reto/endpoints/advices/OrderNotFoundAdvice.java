package com.orenes.reto.endpoints.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.orenes.reto.exceptions.OrderNotFoundException;

/**
 * Advice for when an order resource isn't found on the database.
 * @author Ulises Ceca
 * @version 1.0
 */
@RestControllerAdvice
public class OrderNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String orderNotFoundHandler(OrderNotFoundException ex) {
	  return ex.getMessage();
	}
}