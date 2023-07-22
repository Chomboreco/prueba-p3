package com.p3.examenpractico.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p3.examenpractico.controller.request.AuthRequest;
import com.p3.examenpractico.model.Vendedor;
import com.p3.examenpractico.service.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

/**
 * The AuthenticationRest class handles authentication and user creation for a
 * REST API.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationRest {

  @Autowired
  private AuthenticationService authService;

  /**
   * The method is a POST request handler for the "/login" endpoint that
   * authenticates a user using the provided credentials.
   * 
   * @param request The "request" parameter is an object of type AuthRequest.
   *                The AuthRequest object contains the user's credentials,
   *                username and password, that are used for authentication.
   * @return The method is returning a ResponseEntity object.
   */
  @PostMapping("/login")
  public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
    log.info("Performing... authenticate()");
    return authService.login(request);
  }

  /**
   * The method creates a new user by encoding the password and saving the
   * user object to the database.
   * 
   * @param request The "request" parameter is an object of type "Vendedor". It
   *                contains the data needed to create a new vendedor.
   * @return The method is returning a ResponseEntity object.
   */
  @PostMapping("/create-user")
  public ResponseEntity<?> createUser(@RequestBody Vendedor request) {
    log.info("Performing... createUser()");
    return authService.createVendedor(request);
  }
}
