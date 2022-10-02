package com.orenes.reto.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.orenes.reto.endpoints.advices.OrderIDAlreadyExistsAdvice;
import com.orenes.reto.endpoints.advices.VehicleNotFoundAdvice;
import com.orenes.reto.endpoints.dto.LocationDTO;
import com.orenes.reto.endpoints.dto.OrderDTO;

/**
 * Test class for the VehicleEndpointTests class
 * @author Ulises Ceca
 * @version 1.0
 * 
 */
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class VehicleEndpointTests {
	private MockMvc mvc;
	
	@Autowired
	private VehicleEndpoint vehicleEndpoint;

	private final Long LATITUDE = 1234566L;
	private final Long LONGITUDE = 1234567L;
	private final String LOCATIONS_URL = "/vehicles/{plate}/location";
	private final String ORDERS_URL = "/vehicles/{plate}/orders";
	
	@BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(vehicleEndpoint)
                .setControllerAdvice(new VehicleNotFoundAdvice(), new OrderIDAlreadyExistsAdvice())
                .build();
    }
	
	/**
	 * Test that checks the response of request to update the location of a vehicle.
	 * The response must have a 200 code which means the location has been updated.
	 */
	@Test
	public void updateVehicleLocation_0K01() throws JsonProcessingException, Exception {
		final String VEHICLE_PLATE_NUMBER = "111111A";
		final LocationDTO locationDto = new LocationDTO(LATITUDE, LONGITUDE);
		final String url = LOCATIONS_URL.replace("{plate}", VEHICLE_PLATE_NUMBER);
		final LocationDTO locationDtoResponse;
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		MockHttpServletResponse response = mvc.perform(
				put(url)
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(locationDto)))
				.andReturn().getResponse();
		locationDtoResponse = mapper.readValue(response.getContentAsString(), LocationDTO.class);
		
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(locationDtoResponse.getDateTime()).isNotNull();
		assertThat(locationDtoResponse.getLatitude()).isEqualTo(LATITUDE);
		assertThat(locationDtoResponse.getLongitude()).isEqualTo(LONGITUDE);
	}
	
	/**
	 * Test that checks the response of request to update the location of a vehicle.
	 * The response must have a 404 code which means the vehicle isn't in our database so the location
	 * cannot be updated.
	 */
	@Test
	public void updateVehicleLocation_KO01() throws Exception {
		final String VEHICLE_PLATE_NUMBER = "222222A";
		final String url = LOCATIONS_URL.replace("{plate}", VEHICLE_PLATE_NUMBER);
		final LocationDTO locationDto = new LocationDTO(LATITUDE, LONGITUDE);
		MockHttpServletResponse response = mvc.perform(
				put(url)
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(locationDto)))
				.andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

	
	/**
	 * Test that checks the response of request to obtain the location of a vehicle.
	 * The response must have a 200 code which means the location has been retrieved.
	 */
	@Test
	public void getVehicleLocation_0K01() throws JsonProcessingException, Exception {
		final String VEHICLE_PLATE_NUMBER = "111111A";
		final String url = LOCATIONS_URL.replace("{plate}", VEHICLE_PLATE_NUMBER);
		final LocationDTO locationDtoResponse;
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		MockHttpServletResponse response = mvc.perform(
				get(url)
				.accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
		locationDtoResponse = mapper.readValue(response.getContentAsString(), LocationDTO.class);
		
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(locationDtoResponse.getDateTime()).isNotNull();
		assertThat(locationDtoResponse.getLatitude()).isEqualTo(LATITUDE);
		assertThat(locationDtoResponse.getLongitude()).isEqualTo(LONGITUDE);
	}
	
	/**
	 * Test that checks the response of request to retrieve the location of a vehicle.
	 * The response must have a 404 code which means the vehicle isn't in our database so the location
	 * cannot be retrieved.
	 */
	@Test
	public void getVehicleLocation_KO01() throws Exception {
		final String VEHICLE_PLATE_NUMBER = "222222A";
		final String url = LOCATIONS_URL.replace("{plate}", VEHICLE_PLATE_NUMBER);
		MockHttpServletResponse response = mvc.perform(
				get(url)
				.accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();;
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}
	
	/**
	 * Test that checks the response of request to insert a new order for a vehicle.
	 * The response must have a 201 code which means the location has been updated.
	 */
	@Test
	public void addOrderToVehicle_0K01() throws JsonProcessingException, Exception {
		final String VEHICLE_PLATE_NUMBER = "111111B";
		final String ORDER_ID = "123456A";
		final OrderDTO orderDto = new OrderDTO(ORDER_ID);
		final String url = ORDERS_URL.replace("{plate}", VEHICLE_PLATE_NUMBER);
		final OrderDTO orderDtoResponse;
		ObjectMapper mapper = new ObjectMapper();
		MockHttpServletResponse response = mvc.perform(
				post(url)
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(orderDto)))
				.andReturn().getResponse();
		orderDtoResponse = mapper.readValue(response.getContentAsString(), OrderDTO.class);
		
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		assertThat(orderDtoResponse.getOrderId()).isEqualTo(ORDER_ID);
	}
	
	/**
	 * Test that checks the response of request to insert a new order for a vehicle.
	 * The response must have a 404 code which means the vehicle isn't in our database so the order
	 * cannot be placed
	 */
	@Test
	public void addOrderToVehicle_KO01() throws Exception {
		final String VEHICLE_PLATE_NUMBER = "222222B";
		final String ORDER_ID = "123456A";
		final OrderDTO orderDto = new OrderDTO(ORDER_ID);
		final String url = ORDERS_URL.replace("{plate}", VEHICLE_PLATE_NUMBER);
		ObjectMapper mapper = new ObjectMapper();
		MockHttpServletResponse response = mvc.perform(
				post(url)
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(orderDto)))
				.andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}
	
	/**
	 * Test that checks the response of request to insert a new order for a vehicle.
	 * The response must have a 409 code which means there is already an order with that id.
	 */
	@Test
	public void addOrderToVehicle_KO02() throws Exception {

		final String VEHICLE_PLATE_NUMBER = "111111B";
		final String ORDER_ID = "123456A";
		final OrderDTO orderDto = new OrderDTO(ORDER_ID);
		final String url = ORDERS_URL.replace("{plate}", VEHICLE_PLATE_NUMBER);
		ObjectMapper mapper = new ObjectMapper();
		mvc.perform(
				post(url)
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(orderDto)))
				.andReturn().getResponse();
		MockHttpServletResponse repeatedResponse = mvc.perform(
				post(url)
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(orderDto)))
				.andReturn().getResponse();
		
		assertThat(repeatedResponse.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
	}

}
