package com.orenes.reto.endpoints;

import org.springframework.http.ResponseEntity;

import com.orenes.reto.endpoints.dto.LocationDTO;

public interface VehicleEndpoint {

	ResponseEntity<LocationDTO> updateVehicleLocation(final String plateNumber, final LocationDTO locationDto);

}
