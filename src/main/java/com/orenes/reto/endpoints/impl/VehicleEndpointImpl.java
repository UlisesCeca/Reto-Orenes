package com.orenes.reto.endpoints.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orenes.reto.endpoints.VehicleEndpoint;
import com.orenes.reto.endpoints.dto.LocationDTO;
import com.orenes.reto.services.LocationService;
import com.orenes.reto.services.entities.Location;

@RestController
public class VehicleEndpointImpl implements VehicleEndpoint{
	private final LocationService locationService;
	private final ModelMapper modelMapper;
	
	@Autowired
	public VehicleEndpointImpl(final LocationService locationService, final ModelMapper modelMapper) {
		this.locationService = locationService;
		this.modelMapper = modelMapper;
	}

	@PutMapping("/vehicles/{plateNumber}/locations")
	@Override
	public ResponseEntity<LocationDTO> updateVehicleLocation(@PathVariable final String plateNumber,
			@RequestBody final LocationDTO locationDto) {
		final Location newLocation = this.locationService.updateVehicleLocation(this.modelMapper.map(locationDto, Location.class), plateNumber);
		final LocationDTO savedLocation = this.modelMapper.map(newLocation, LocationDTO.class);
		
		return ResponseEntity.ok(savedLocation);
	}
}
