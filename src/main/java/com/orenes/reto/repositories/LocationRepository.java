package com.orenes.reto.repositories;
/**
 * Interface to interact with the Location entity and its persistence
 * 
 * @author Ulises Ceca
 * @version 1.0
 */

import org.springframework.data.repository.CrudRepository;

import com.orenes.reto.repositories.dao.LocationDAO;

public interface LocationRepository extends CrudRepository<LocationDAO, Long> { }