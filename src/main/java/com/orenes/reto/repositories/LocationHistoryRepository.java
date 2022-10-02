package com.orenes.reto.repositories;

import org.springframework.data.repository.CrudRepository;

import com.orenes.reto.repositories.dao.LocationHistoryDAO;

/**
 * Interface to interact with the Location history entity and its persistence
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
public interface LocationHistoryRepository extends CrudRepository<LocationHistoryDAO, Long> {

}
