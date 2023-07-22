package com.p3.examenpractico.service;

import org.springframework.http.ResponseEntity;

import com.p3.examenpractico.controller.request.AuthRequest;
import com.p3.examenpractico.model.Vendedor;

public interface AuthenticationService {

  /**
   * The login function takes an AuthRequest object as input and returns a
   * ResponseEntity object.
   * 
   * @param request The request parameter is an object of type AuthRequest, which
   *                is used to pass the
   *                login credentials (such as username and password) to the login
   *                method.
   * @return The method is returning a ResponseEntity object, which can hold any
   *         type of response data.
   */
  ResponseEntity<?> login(AuthRequest request);

  /**
   * The function creates a new Vendedor object and returns a ResponseEntity.
   * 
   * @param request The "request" parameter is an object of type "Vendedor" which
   *                contains the
   *                information needed to create a new "Vendedor" entity.
   * @return The method createVendedor is returning a ResponseEntity object.
   */
  ResponseEntity<?> createVendedor(Vendedor request);
}
