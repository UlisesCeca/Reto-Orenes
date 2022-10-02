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
	Location updateVehicleLocation(final Location newLocation, final String vehiclePlateNumber);
}
