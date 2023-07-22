package com.p3.examenpractico.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.p3.examenpractico.controller.response.BaseResponse;
import com.p3.examenpractico.model.Producto;
import com.p3.examenpractico.repository.ProductoRepository;
import com.p3.examenpractico.service.ProductoService;

/**
 * The `ProductoServiceImpl` class is a service implementation that provides
 * methods for retrieving and
 * saving `Producto` objects using a `ProductoRepository`.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Service
public class ProductoServiceImpl implements ProductoService {

  @Autowired
  private ProductoRepository productoRepository;

  /**
   * The function returns all the parameters stored in the productoRepository as a
   * ResponseEntity.
   * 
   * @return The method is returning a ResponseEntity object.
   */
  @Override
  public ResponseEntity<?> getAllParams() {
    return ResponseEntity.ok(productoRepository.findAll());
  }

  /**
   * The function saves a product if it doesn't already exist in the database,
   * otherwise it returns an
   * error message.
   * 
   * @param producto The "producto" parameter is an object of type "Producto"
   *                 which represents a
   *                 product.
   * @return The method is returning a ResponseEntity object.
   */
  @Override
  public ResponseEntity<?> saveProducto(Producto producto) {
    if (!productoRepository.findByHawa(producto.getHawa()).isPresent())
      return ResponseEntity.ok(productoRepository.save(producto));

    return ResponseEntity.ok(BaseResponse.error("El registro ya exsite"));
  }

  /**
   * The function returns a ResponseEntity containing the result of a database
   * query based on the input
   * parameter "hawa", or a bad request response if the parameter is empty.
   * 
   * @param hawa The parameter "hawa" is a string that is used to search for a
   *             specific value in the
   *             "productoRepository". The method checks if the "hawa" string is
   *             not empty. If it is not empty, it
   *             returns a ResponseEntity with the result of the search using the
   *             "findByHawa
   * @return The method is returning a ResponseEntity object.
   */
  @Override
  public ResponseEntity<?> getByHawa(String hawa) {
    if (!hawa.isEmpty())
      return ResponseEntity.ok(productoRepository.findByHawa(hawa));

    return ResponseEntity.badRequest().body(BaseResponse.error("Datos no válidos"));
  }
}
