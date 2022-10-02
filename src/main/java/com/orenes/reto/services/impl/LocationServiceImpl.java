package com.orenes.reto.services.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orenes.reto.exceptions.VehicleNotFoundException;
import com.orenes.reto.repositories.LocationRepository;
import com.orenes.reto.repositories.VehicleRepository;
import com.orenes.reto.repositories.dao.LocationDAO;
import com.orenes.reto.repositories.dao.VehicleDAO;
import com.orenes.reto.services.LocationService;
import com.orenes.reto.services.entities.Location;

/**
 * Class that implements the bussiness logic regarding the location of the vehicles
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
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
	
	
	/**
	 * Updates a vehicle's location by updating the location property of the entity and storing
	 * a new Location entry in the database for the locations history.
	 *
	 * @param newLocation the new location of the vehicle to be stored
	 * @param vehiclePlateNumber the vehicle plate number
	 * @return the location with the updated date-time of the system
	 */
	@Override
	@Transactional
	public Location updateVehicleLocation(final Location newLocation, final String vehiclePlateNumber) {
		final VehicleDAO vehicleDao = this.vehicleRepository.findByPlateNumber(vehiclePlateNumber)
				.orElseThrow(() -> new VehicleNotFoundException(vehiclePlateNumber));
		final LocationDAO newLocationDao = this.modelMapper.map(newLocation, LocationDAO.class);

		newLocationDao.setDateTime(LocalDateTime.now());
		vehicleDao.setLastLocation(newLocationDao);
		this.locationRepository.save(newLocationDao);
		this.vehicleRepository.save(vehicleDao);

		return this.modelMapper.map(newLocationDao, Location.class);
	}

	/**
	 * Retrieves the current location from a vehicle with the specified plate number.
	 * @param vehiclePlateNumber the vehicle plate number
	 * @return the current location from the specified vehicle
	 */
	@Override
	public Location getVehicleLocation(final String vehiclePlateNumber) {
		final VehicleDAO vehicleDao = this.vehicleRepository.findByPlateNumber(vehiclePlateNumber)
				.orElseThrow(() -> new VehicleNotFoundException(vehiclePlateNumber));
		final LocationDAO locationDao = Objects.isNull(vehicleDao.getLastLocation()) ? new LocationDAO() : this.modelMapper.map(vehicleDao.getLastLocation(), LocationDAO.class);
		return this.modelMapper.map(locationDao, Location.class);
	}

}
