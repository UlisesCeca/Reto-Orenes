package com.orenes.reto.services;

import org.springframework.stereotype.Service;

import com.orenes.reto.services.entities.Location;

@Service
public interface LocationService {
	Location updateVehicleLocation(final Location newLocation, final String vehiclePlateNumber);
}
