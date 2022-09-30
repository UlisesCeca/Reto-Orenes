package com.orenes.reto.repositories;
/**
 * Interface to interact with the Vehicle entity and its persistence
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
import org.springframework.data.repository.CrudRepository;

import com.orenes.reto.repositories.dao.VehicleDAO;

public interface VehicleRepository extends CrudRepository<VehicleDAO, Long> { }