package com.orenes.reto.repositories;

import org.springframework.data.repository.CrudRepository;

import com.orenes.reto.repositories.dao.OrderDAO;

public interface OrderRepository extends CrudRepository<OrderDAO, Long> { }
