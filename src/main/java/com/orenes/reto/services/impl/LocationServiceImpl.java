package com.orenes.reto.services.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orenes.reto.exceptions.VehicleNotFoundException;
import com.orenes.reto.repositories.LocationHistoryRepository;
import com.orenes.reto.repositories.LocationRepository;
import com.orenes.reto.repositories.VehicleRepository;
import com.orenes.reto.repositories.dao.LocationDAO;
import com.orenes.reto.repositories.dao.LocationHistoryDAO;
import com.orenes.reto.repositories.dao.VehicleDAO;
import com.orenes.reto.services.LocationService;
import com.orenes.reto.services.classes.Location;

/**
 * Class that implements the bussiness logic regarding the location of the vehicles
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
@Service
public class LocationServiceImpl implements LocationService {
	private static final String LOCATION_TOPIC = "location-topic";
	
	private final LocationRepository locationRepository;
	private final VehicleRepository vehicleRepository;
	private final LocationHistoryRepository locationHistoryRepository;
	private final ModelMapper modelMapper;
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	public LocationServiceImpl(final LocationRepository locationRepository, final VehicleRepository vehicleRepository,
			final ModelMapper modelMapper, final LocationHistoryRepository locationHistoryRepository, @Qualifier("kafkaTemplate") KafkaTemplate<String, String> kafkaTemplate) {
		this.locationRepository = locationRepository;
		this.modelMapper = modelMapper;
		this.vehicleRepository = vehicleRepository;
		this.locationHistoryRepository = locationHistoryRepository;
		this.kafkaTemplate = kafkaTemplate;
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
		final Location location;
		final LocationHistoryDAO locationHistory;

		newLocationDao.setDateTime(LocalDateTime.now());
		vehicleDao.setLastLocation(newLocationDao);
		this.locationRepository.save(newLocationDao);
		this.vehicleRepository.save(vehicleDao);
		locationHistory = new LocationHistoryDAO(vehicleDao, newLocationDao);
		this.locationHistoryRepository.save(locationHistory);
		location = this.modelMapper.map(newLocationDao, Location.class);
		this.kafkaTemplate.send(LOCATION_TOPIC, location.toString());

		return location;
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
