package com.orenes.reto.repositories;

import org.springframework.data.repository.CrudRepository;

import com.orenes.reto.repositories.dao.VehicleDAO;

public interface VehicleRepository extends CrudRepository<VehicleDAO, Long> { }