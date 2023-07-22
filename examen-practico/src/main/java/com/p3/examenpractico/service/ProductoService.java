package com.p3.examenpractico.service;

import org.springframework.http.ResponseEntity;

import com.p3.examenpractico.model.Producto;

public interface ProductoService {

  /**
   * The function getAllParams returns all the parameters in the request as a
   * ResponseEntity.
   * 
   * @return The method getAllParams() is returning a ResponseEntity object. The
   *         "?" in the
   *         ResponseEntity<?> indicates that the response can be of any type.
   */
  ResponseEntity<?> getAllParams();

  /**
   * The function "saveProducto" saves a product and returns a ResponseEntity
   * object.
   * 
   * @param producto The "producto" parameter is an object of type "Producto" that
   *                 represents the
   *                 product to be saved.
   * @return The method is returning a ResponseEntity object.
   */
  ResponseEntity<?> saveProducto(Producto producto);

  /**
   * The function getByHawa takes a parameter hawa and returns a ResponseEntity
   * object.
   * 
   * @param hawa The parameter "hawa" is a string that represents a value used to
   *             retrieve data.
   * @return The return type of the method is `ResponseEntity<?>`.
   */
  ResponseEntity<?> getByHawa(String hawa);

}
