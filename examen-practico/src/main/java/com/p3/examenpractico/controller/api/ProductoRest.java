package com.p3.examenpractico.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p3.examenpractico.model.Producto;
import com.p3.examenpractico.service.ProductoService;

import lombok.extern.slf4j.Slf4j;

/**
 * The above class is a REST controller that handles GET and POST requests for a
 * product resource.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(path = "/productos")
public class ProductoRest {

  @Autowired
  private ProductoService productoService;

  /**
   * The above function is a GET request handler that retrieves all parameters and
   * returns a list of Producto objects.
   * 
   * @return The method is returning a List of Producto objects.
   */
  @GetMapping
  public ResponseEntity<?> getAllParams() {
    log.info("Performing... getAllParams()");
    return productoService.getAllParams();
  }

  /**
   * The above function saves a Producto object by calling the saveProducto()
   * method in the productoService and returns the saved Producto object.
   * 
   * @param request The "request" parameter is of type "Producto" and it
   *                represents the data that is* sent in the request body. It
   *                contains the data to be stored.
   * @return The method is returning an instance of the `Producto` class.
   */
  @PostMapping
  public ResponseEntity<?> saveProducto(@RequestBody Producto request) {
    log.info("Performing... saveProducto()");
    return productoService.saveProducto(request);
  }
}
