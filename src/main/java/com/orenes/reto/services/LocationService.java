package com.orenes.reto.services;



import org.springframework.stereotype.Service;

import com.orenes.reto.services.entities.Location;

/**
 * Interface that defines the bussiness logic regarding the location of the vehicles
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
@Service
public interface LocationService {
	/**
	 * Updates a vehicle's location by updating the location property of the entity and storing
	 * a new Location entry in the database for the locations history.
	 *
	 * @param newLocation the new location of the vehicle to be stored
	 * @param vehiclePlateNumber the vehicle plate number
	 * @return the location with the updated date-time of the system
	 */
	Location updateVehicleLocation(final Location newLocation, final String vehiclePlateNumber);
	
	/**
	 * Retrieves the current location from a vehicle with the specified plate number
	 * @param vehiclePlateNumber the vehicle plate number
	 * @returnt he current location from the specified vehicle
	 */
	Location getVehicleLocation(final String vehiclePlateNumber);
}
