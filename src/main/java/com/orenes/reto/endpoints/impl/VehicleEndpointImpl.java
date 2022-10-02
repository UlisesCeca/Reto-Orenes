package com.orenes.reto.endpoints.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orenes.reto.endpoints.VehicleEndpoint;
import com.orenes.reto.endpoints.dto.LocationDTO;
import com.orenes.reto.endpoints.dto.OrderDTO;
import com.orenes.reto.services.LocationService;
import com.orenes.reto.services.OrderService;
import com.orenes.reto.services.classes.Location;
import com.orenes.reto.services.classes.Order;


/**
 * Endpoint class to perform operations with the Vehicle entity.
 */
@RestController
public class VehicleEndpointImpl implements VehicleEndpoint{
	private static final String LOCATION_TOPIC = "location-topic";
	
	private final LocationService locationService;
	private final OrderService orderService;
	private final ModelMapper modelMapper;
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	public VehicleEndpointImpl(final LocationService locationService, final ModelMapper modelMapper,
			final OrderService orderService, @Qualifier("kafkaTemplate") KafkaTemplate<String, String> kafkaTemplate) {
		this.locationService = locationService;
		this.modelMapper = modelMapper;
		this.orderService = orderService;
		this.kafkaTemplate = kafkaTemplate;
	}

	/**
	 * Updates a vehicle's location by updating the location property of the entity and storing
	 * a new Location entry in the database for the locations history.
	 *
	 * @param plateNumber the vehicle plate number
	 * @param locationDto the current location of the vehicle
	 * @return the updated location of the vehicle with the current system's date-time
	 */
	@PutMapping("/vehicles/{plateNumber}/location")
	@Override
	public ResponseEntity<LocationDTO> updateVehicleLocation(@PathVariable final String plateNumber,
			@RequestBody final LocationDTO locationDto) {
		final Location newLocation = this.locationService.updateVehicleLocation(this.modelMapper.map(locationDto, Location.class), plateNumber);
		final LocationDTO savedLocationDto = this.modelMapper.map(newLocation, LocationDTO.class);
		
		this.kafkaTemplate.send(LOCATION_TOPIC, savedLocationDto.toString());
		
		return ResponseEntity.ok(savedLocationDto);
	}

	/**
	 * Retrieves the current location from a vehicle with the specified plate number.
	 * @param vehiclePlateNumber the vehicle plate number
	 * @return the current location from the specified vehicle
	 */
	@GetMapping("/vehicles/{plateNumber}/location")
	@Override
	public ResponseEntity<LocationDTO> getVehicleLocation(@PathVariable final String plateNumber) {
		final Location newLocation = this.locationService.getVehicleLocation(plateNumber);
		
		return ResponseEntity.ok(this.modelMapper.map(newLocation, LocationDTO.class));
	}

	/**
	 * Adds a new to order to an existing vehicle.
	 * @param plateNumber the vehicle plate number
	 * @return the created order
	 */
	@PostMapping("/vehicles/{plateNumber}/orders")
	@Override
	public ResponseEntity<OrderDTO> addOrderToVehicle(@PathVariable final String plateNumber, @RequestBody final OrderDTO newOrder) {
		final Order createdOrder = this.orderService.insertOrder(plateNumber, this.modelMapper.map(newOrder, Order.class));
		

		return new ResponseEntity<OrderDTO>(this.modelMapper.map(createdOrder, OrderDTO.class), HttpStatus.CREATED);
	}

	/**
	 * Deletes an existing order.
	 * @param orderId the id of the order to be deleted
	 */
	@DeleteMapping("/orders/{orderId}")
	@Override
	public ResponseEntity<Void> deleteOrder(@PathVariable final String orderId) {
		this.orderService.deleteOrder(orderId);
		

		return ResponseEntity.noContent().build();
	}
}
