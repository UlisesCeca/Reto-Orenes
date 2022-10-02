package com.orenes.reto.endpoints;

import org.springframework.http.ResponseEntity;

import com.orenes.reto.endpoints.dto.LocationDTO;
/**
 * Interface that defines the operations that be performed with the Vehicle entity.
 */
public interface VehicleEndpoint {
	/**
	 * Updates a vehicle's location by updating the location property of the entity and storing
	 * a new Location entry in the database for the locations history.
	 *
	 * @param plateNumber the vehicle plate number
	 * @param locationDto the current location of the vehicle
	 * @return the updated location of the vehicle with the current system's date-time
	 */
	ResponseEntity<LocationDTO> updateVehicleLocation(final String plateNumber, final LocationDTO locationDto);

}
