package com.p3.examenpractico.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p3.examenpractico.service.ClienteService;

import lombok.extern.slf4j.Slf4j;

/**
 * The ClienteRest class is a REST controller that handles GET and POST
 * requests for Cliente objects.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(path = "/clientes")
public class ClienteRest {

  @Autowired
  private ClienteService clienteService;

  /**
   * The above function is a GET request handler that retrieves all Pedido objects
   * and logs the action.
   * 
   * @return The getAll() method is returning a List of Pedido objects.
   */
  @GetMapping
  public ResponseEntity<?> getAll() {
    log.info("Performing... getAll()");
    return ResponseEntity.ok(clienteService.getAll());
  }
}
