package com.p3.examenpractico.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.p3.examenpractico.controller.request.AuthRequest;
import com.p3.examenpractico.controller.response.AuthResponse;
import com.p3.examenpractico.controller.response.BaseResponse;
import com.p3.examenpractico.model.Vendedor;
import com.p3.examenpractico.repository.VendedorRepository;
import com.p3.examenpractico.security.JwtUtils;
import com.p3.examenpractico.service.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
/**
 * The `AuthenticationServiceImpl` class is responsible for handling user
 * authentication and creating new user accounts.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
public class AuthenticationServiceImpl implements AuthenticationService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private VendedorRepository vendedorRepository;

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  /**
   * The function handles the login process by authenticating the user's
   * credentials and generating an
   * access token.
   * 
   * @param request The request parameter is an object of type AuthRequest, which
   *                contains the email
   *                and password for authentication.
   * @return The method is returning a ResponseEntity object.
   */
  @Override
  public ResponseEntity<?> login(AuthRequest request) {
    AuthResponse response = new AuthResponse();

    try {
      if (!request.getEmail().isEmpty() || !request.getPassword().isEmpty()) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        Vendedor user = (Vendedor) authentication.getPrincipal();
        response.setEmail(user.getUsername());
        response.setAccessToken(jwtUtils.generateAccessToken(user));
      } else
        return ResponseEntity.badRequest().body(BaseResponse.error("Datos no válidos"));
    } catch (AuthenticationException e) {
      log.error("Error de autenticacion", e);
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(BaseResponse.error("Datos no válidos"));
    }

    return ResponseEntity.ok(response);
  }

  /**
   * The function creates a new "Vendedor" object, encrypts the password, checks
   * if the user already
   * exists, and saves the object in the repository.
   * 
   * @param request The "request" parameter is an object of type "Vendedor" which
   *                contains the
   *                information of the vendedor (seller) that needs to be created.
   * @return The method is returning a ResponseEntity object.
   */
  @Override
  public ResponseEntity<?> createVendedor(Vendedor request) {
    String password = bCryptPasswordEncoder.encode(request.getPassword());
    request.setPassword(password);

    if (vendedorRepository.findByEmail(request.getEmail()).isPresent())
      return ResponseEntity.ok(BaseResponse.error("El usuario ya existe"));

    return ResponseEntity.ok(vendedorRepository.save(request));
  }
}
