package com.orenes.reto.services;

/**
 * Interface that defines the bussiness logic regarding the location of the vehicles
 * 
 * @author Ulises Ceca
 * @version 1.0
 */

import org.springframework.stereotype.Service;

import com.orenes.reto.services.entities.Location;

@Service
public interface LocationService {
	Location updateVehicleLocation(final Location newLocation, final String vehiclePlateNumber);
}
