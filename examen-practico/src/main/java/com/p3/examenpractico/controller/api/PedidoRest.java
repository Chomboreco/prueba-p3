package com.p3.examenpractico.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.p3.examenpractico.controller.response.BaseResponse;
import com.p3.examenpractico.model.Pedido;
import com.p3.examenpractico.service.PedidoService;

import lombok.extern.slf4j.Slf4j;

/**
 * The class "PedidoRest" is a REST controller that handles requests related to
 * orders, such as retrieving all orders, saving a new order, and canceling an
 * existing order.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(path = "/pedidos")
public class PedidoRest {

  @Autowired
  private PedidoService pedidoService;

  /**
   * The above function is a GET request handler that retrieves all Pedido objects
   * and logs the action.
   * 
   * @return The getAll() method is returning a List of Pedido objects.
   */
  @GetMapping
  public ResponseEntity<?> getAll() {
    log.info("Performing... getAll()");
    return pedidoService.getAll();
  }

  /**
   * The function saves a Pedido object by calling the savePedido() method in the
   * pedidoService and returns the saved Pedido object.
   * 
   * @param request The "request" parameter is of type "Pedido" and is annotated
   *                with "@RequestBody". It contains the data to be stored.
   * @return The method is returning a Pedido object.
   */
  @PostMapping
  public ResponseEntity<?> savePedido(@RequestBody Pedido request) {
    log.info("Performing... savePedido()" + request.toString());
    return pedidoService.savePedido(request);
  }

  /**
   * The function cancels a pedido (order) with the specified ID and returns a
   * ResponseEntity
   * containing a BaseResponse.
   * 
   * @param id The "id" parameter is an Integer value that represents the
   *           identifier of a pedido (order).
   * @return The method is returning a ResponseEntity object with a generic type
   *         of BaseResponse.
   */
  @PostMapping(value = "/{id}/cancelar")
  public ResponseEntity<BaseResponse> cancelPedido(@RequestParam Integer id) {
    log.info("Performing... cancelPedido()");
    return pedidoService.cancelPedido(id);
  }

}
