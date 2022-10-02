package com.orenes.reto.repositories;
import java.util.Optional;


import org.springframework.data.repository.CrudRepository;

import com.orenes.reto.repositories.dao.VehicleDAO;

/**
 * Interface to interact with the Vehicle entity and its persistence
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
public interface VehicleRepository extends CrudRepository<VehicleDAO, Long> { 
	Optional<VehicleDAO> findByPlateNumber(final String plateNumber);
}