package com.p3.examenpractico.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.p3.examenpractico.service.VendedorService;

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
@RequestMapping("/vendedores")
public class VendedorRest {

  @Autowired
  private VendedorService vendedorService;

  @GetMapping
  public ResponseEntity<?> getAllParams(@RequestParam(required = false) String email) {
    log.info("Performing... getAllParams()");
    return ResponseEntity.ok(vendedorService.getVendedorByEmail(email));
  }
}
