package com.p3.examenpractico.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.p3.examenpractico.model.Pedido;

/**
 * Repository para las operaciones de Pedido.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

  /**
   * The function "findAll" returns a list of all "Pedido" objects.
   * 
   * @return The method is returning a List of objects of type Pedido.
   */
  public List<Pedido> findAll();
}
