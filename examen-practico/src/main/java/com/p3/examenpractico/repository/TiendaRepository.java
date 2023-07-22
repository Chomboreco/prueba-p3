package com.p3.examenpractico.repository;

import org.springframework.data.repository.CrudRepository;

import com.p3.examenpractico.model.Tienda;

/**
 * Repository para las operaciones de Tienda.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
public interface TiendaRepository extends CrudRepository<Tienda, Integer> {

}
