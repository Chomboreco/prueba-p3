package com.p3.examenpractico.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.p3.examenpractico.model.Cliente;

/**
 * Repository para las operaciones de Cliente.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

  List<Cliente> findAll();
}
