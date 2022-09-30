package com.orenes.reto.services.impl;

/**
 * Class that implements the bussiness logic regarding the location of the vehicles
 * 
 * @author Ulises Ceca
 * @version 1.0
 */

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orenes.reto.repositories.LocationRepository;
import com.orenes.reto.repositories.VehicleRepository;
import com.orenes.reto.repositories.dao.LocationDAO;
import com.orenes.reto.repositories.dao.VehicleDAO;
import com.orenes.reto.services.LocationService;
import com.orenes.reto.services.entities.Location;

@Service
public class LocationServiceImpl implements LocationService {
	private final LocationRepository locationRepository;
	private final VehicleRepository vehicleRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public LocationServiceImpl(final LocationRepository locationRepository, final VehicleRepository vehicleRepository,
			final ModelMapper modelMapper) {
		this.locationRepository = locationRepository;
		this.modelMapper = modelMapper;
		this.vehicleRepository = vehicleRepository;
	}
	
	@Override
	public Location updateVehicleLocation(final Location newLocation, final String vehiclePlateNumber) {
		final VehicleDAO vehicleDao = this.vehicleRepository.findByPlateNumber(vehiclePlateNumber).orElse(null);
		final LocationDAO newLocationDao = this.modelMapper.map(newLocation, LocationDAO.class);
		final Location insertedLocation;

		vehicleDao.setLastLocation(newLocationDao);
		
		this.vehicleRepository.save(vehicleDao);
		this.locationRepository.save(newLocationDao);
		
		insertedLocation = this.modelMapper.map(newLocationDao, Location.class);
		
		return insertedLocation;
	}

}
