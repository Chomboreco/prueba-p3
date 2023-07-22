package com.p3.examenpractico.service;

import org.springframework.http.ResponseEntity;

import com.p3.examenpractico.controller.response.BaseResponse;
import com.p3.examenpractico.model.Pedido;

public interface PedidoService {

  /**
   * The function "savePedido" saves a Pedido object and returns a ResponseEntity.
   * 
   * @param pedido The "pedido" parameter is an object of type "Pedido" which
   *               represents a request to
   *               save a pedido (order).
   * @return The method savePedido is returning a ResponseEntity object.
   */
  ResponseEntity<?> savePedido(Pedido pedido);

  /**
   * The getAll() function returns all entities.
   * 
   * @return The method is returning a ResponseEntity object, which can hold any
   *         type of response data.
   */
  ResponseEntity<?> getAll();

  /**
   * The function cancels a pedido (order) and returns a ResponseEntity with a
   * BaseResponse.
   * 
   * @param id The id parameter is an Integer that represents the unique
   *           identifier of the pedido
   *           (order) that needs to be canceled.
   * @return The method is returning a ResponseEntity object with a generic type
   *         of BaseResponse.
   */
  ResponseEntity<BaseResponse> cancelPedido(Integer id);

  /**
   * The function "cambiarStatusPedido" takes an integer id and a string status as
   * parameters and
   * returns a ResponseEntity object.
   * 
   * @param id     The id parameter is an Integer that represents the unique
   *               identifier of the pedido
   *               (order) that you want to update the status for.
   * @param status The status parameter is a String that represents the new status
   *               of the pedido
   *               (order).
   * @return The method is returning a ResponseEntity object, which can hold any
   *         type of response data.
   */
  ResponseEntity<?> cambiarStatusPedido(Integer id, String status);
}
