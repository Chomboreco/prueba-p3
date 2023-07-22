package com.p3.examenpractico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.p3.examenpractico.model.Producto;

/**
 * Repository para las operaciones de Producto.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

  /**
   * The function "findAll" returns a list of all products.
   * 
   * @return The method `findAll()` returns a list of `Producto` objects.
   */
  List<Producto> findAll();

  /**
   * The function findByHawa searches for a Producto object by its "hawa"
   * attribute.
   * 
   * @param hawa A string representing the value to search for in the "hawa" field
   *             of the "Producto"
   *             object.
   * @return The method is returning an Optional object that contains a Producto
   *         object.
   */
  Optional<Producto> findByHawa(String hawa);
}
