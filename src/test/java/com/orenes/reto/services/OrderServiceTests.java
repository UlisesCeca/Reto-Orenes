package com.orenes.reto.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
/**
 * Test class for the OrderService class
 * @author Ulises Ceca
 * @version 1.0
 * 
 */

import com.orenes.reto.exceptions.OrderIDAlreadyExistsException;
import com.orenes.reto.exceptions.OrderNotFoundException;
import com.orenes.reto.exceptions.VehicleNotFoundException;
import com.orenes.reto.repositories.OrderRepository;
import com.orenes.reto.repositories.dao.OrderDAO;
import com.orenes.reto.services.classes.Order;
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderServiceTests {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderRepository orderRepository;

	private final String EXPECTED_NOT_FOUND_VEHICLE_MESSAGE = "Could not find vehicle with plate number ";
	private final String EXPECTED_NOT_FOUND_ORDER_MESSAGE = "Could not find order with id ";
	private final String EXPECTED_ORDER_EXISTS_MESSAGE = "There is already an order with the specified ID ";
	
	/**
	 * Test that checks if an order has been inserted successfully.
	 * Everything must go fine.
	 */
	@Test
	public void insertOrder_0K01() {
		final String VEHICLE_PLATE_NUMBER = "111111B";
		final Order order = new Order("123456A");
		final Order insertedOrder = this.orderService.insertOrder(VEHICLE_PLATE_NUMBER, order);

		assertThat(insertedOrder).isNotNull();
		assertEquals(insertedOrder.getAssignedVehicle().getPlateNumber(), VEHICLE_PLATE_NUMBER);
	}

	/**
	 * Test that checks if an order has been inserted successfully.
	 * The inserted plate number doesn't exist so it must return an error.
	 */
	@Test
	public void insertOrder_KO01() {
		final String VEHICLE_PLATE_NUMBER = "1";
		final Order order = new Order("123456A");
		final Exception exception = assertThrows(VehicleNotFoundException.class, () -> {
			this.orderService.insertOrder(VEHICLE_PLATE_NUMBER, order);
	    });
		assertEquals(exception.getMessage(), EXPECTED_NOT_FOUND_VEHICLE_MESSAGE + VEHICLE_PLATE_NUMBER);
	}
	
	/**
	 * Test that checks if an order has been inserted successfully.
	 * The inserted order ID already exists so it must return an error.
	 */
	@Test
	public void insertOrder_KO02() {
		final String VEHICLE_PLATE_NUMBER = "111111B";
		final Order order = new Order("123456A");
		this.orderService.insertOrder(VEHICLE_PLATE_NUMBER, order);
		final Exception exception = assertThrows(OrderIDAlreadyExistsException.class, () -> {
			this.orderService.insertOrder(VEHICLE_PLATE_NUMBER, order);
	    });
		assertEquals(exception.getMessage(), EXPECTED_ORDER_EXISTS_MESSAGE + order.getOrderId());
	}
	
	/**
	 * Test that checks if an order has been deleted successfully.
	 * Everything must go fine and the deleted order must be null not existing in the db.
	 */
	@Test
	public void deleteOrder_0K01() {
		final String VEHICLE_PLATE_NUMBER = "111111B";
		final Order order = new Order("123456A");
		final Order insertedOrder = this.orderService.insertOrder(VEHICLE_PLATE_NUMBER, order);
		final OrderDAO deletedOrder;

		assertThat(insertedOrder).isNotNull();
		this.orderService.deleteOrder(order.getOrderId());
		deletedOrder = this.orderRepository.findByOrderId(order.getOrderId()).orElse(null);

		assertThat(deletedOrder).isNull();
	}

	/**
	 * Test that checks if an order has been deleted successfully.
	 * The inserted order id doesn't exist so it must return an error.
	 */
	@Test
	public void deletetOrder_KO01() {
		final String ORDER_ID = "123456A";
		final Exception exception = assertThrows(OrderNotFoundException.class, () -> {
			this.orderService.deleteOrder(ORDER_ID);
	    });
		assertEquals(exception.getMessage(), EXPECTED_NOT_FOUND_ORDER_MESSAGE + ORDER_ID);
	}
}
