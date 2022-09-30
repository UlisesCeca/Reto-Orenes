package com.orenes.reto.repositories;

import org.springframework.data.repository.CrudRepository;

import com.orenes.reto.dao.LocationDAO;

public interface LocationRepository extends CrudRepository<LocationDAO, Long> { }