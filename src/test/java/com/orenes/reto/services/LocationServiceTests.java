package com.orenes.reto.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.orenes.reto.exceptions.VehicleNotFoundException;
import com.orenes.reto.repositories.VehicleRepository;
import com.orenes.reto.repositories.dao.VehicleDAO;
import com.orenes.reto.services.entities.Location;

/**
 * Test class for the LocationService class
 * @author Ulises Ceca
 * @version 1.0
 * 
 */
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class LocationServiceTests {
	@Autowired
	private LocationService locationService;
	@Autowired
	private VehicleRepository vehicleRepository;
	
	private final String EXPECTED_NOT_FOUND_MESSAGE = "Could not find vehicle with plate number ";
	
	/**
	 * Test that checks if a car's new location has been inserted successfully.
	 */
	@Test
	public void updateVehicleLocation_0K01() {
		final Long VEHICLE_ID = 1L;
		final Location location = new Location(1234L, 4321L);
		VehicleDAO vehicleDAO = this.vehicleRepository.findById(VEHICLE_ID).get();
		
		assertThat(vehicleDAO.getLastLocation()).isNull();
		this.locationService.updateVehicleLocation(location, vehicleDAO.getPlateNumber());
		vehicleDAO = this.vehicleRepository.findById(VEHICLE_ID).get();
		assertThat(vehicleDAO.getLastLocation()).isNotNull();
	}
	
	/**
	 * Test that checks if an exception is thrown when the specified plate number of a car
	 * doesn't exist.
	 */
	@Test
	public void updateVehicleLocation_KO01() {
		final String VEHICLE_PLATE_NUMBER = "33333B";
		final Location location = new Location(1234L, 4321L);
		final Exception exception = assertThrows(VehicleNotFoundException.class, () -> {
			this.locationService.updateVehicleLocation(location, VEHICLE_PLATE_NUMBER);
	    });
		assertEquals(exception.getMessage(), EXPECTED_NOT_FOUND_MESSAGE + VEHICLE_PLATE_NUMBER);
	}

}
