package com.p3.examenpractico.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.p3.examenpractico.model.Camioneta;
import com.p3.examenpractico.service.CamionetaService;

import lombok.extern.slf4j.Slf4j;

/**
 * The CamionetaRest class is a REST controller that handles GET and POST
 * requests for Camioneta objects.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(path = "/camionetas")
public class CamionetaRest {

  @Autowired
  private CamionetaService camionetaService;

  /**
   * The function getAllParams() returns a list of Camioneta objects based on an
   * optional "available"
   * parameter.
   * 
   * @param available The "available" parameter is an optional query parameter
   *                  that can be passed to the API endpoint.
   * @return The method is returning a List of Camioneta objects.
   */
  @GetMapping
  public ResponseEntity<?> getAllParams(@RequestParam(required = false) String available) {
    log.info("Performing... getAllParams()");
    return camionetaService.getAllParams(available);
  }

  /**
   * The above function is a POST method that saves a Camioneta object and returns
   * the saved object.
   * 
   * @param request The parameter "request" is of type "Camioneta". It contains
   *                the data to be stored.
   * @return The method is returning an object of type "Camioneta".
   */
  @PostMapping()
  public ResponseEntity<?> saveCamioneta(@RequestBody Camioneta request) {
    log.info("Performing... saveCamioneta()");
    return camionetaService.saveCamioneta(request);
  }

}
