package com.p3.examenpractico.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.p3.examenpractico.model.Vendedor;

/**
 * Repository para las operaciones de Vendedor.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
public interface VendedorRepository extends CrudRepository<Vendedor, Integer> {

  /**
   * The function findByEmail searches for a Vendedor (salesperson) object by
   * their email address and
   * returns an Optional object.
   * 
   * @param email A string representing the email address of the vendedor
   *              (salesperson) to search for.
   * @return The method findByEmail is returning an Optional object that contains
   *         an instance of the
   *         class Vendedor.
   */
  Optional<Vendedor> findByEmail(String email);

}
