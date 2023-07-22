package com.p3.examenpractico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.p3.examenpractico.model.Camioneta;

/**
 * Repository para las operaciones de Camioneta.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
public interface CamionetaRepository extends CrudRepository<Camioneta, Integer> {

  /**
   * The function findByStatus returns a list of Camioneta objects based on their
   * status.
   * 
   * @param status The status parameter is a String that represents the status of
   *               the camionetas
   *               (trucks). It is used to search for camionetas that have a
   *               specific status.
   * @return The method findByStatus is returning a list of objects of type
   *         Camioneta.
   */
  List<Camioneta> findByStatus(String status);

  /**
   * The function `findAll()` returns a list of all `Camioneta` objects.
   * 
   * @return The method `findAll()` returns a list of objects of type `Camioneta`.
   */
  List<Camioneta> findAll();

  /**
   * The function findByPlacas searches for a Camioneta object based on its
   * license plate number.
   * 
   * @param placas A string representing the license plate number of a vehicle.
   * @return The method findByPlacas is returning an Optional object that contains
   *         a Camioneta object.
   */
  Optional<Camioneta> findByPlacas(String placas);
}
