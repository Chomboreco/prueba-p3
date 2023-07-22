package com.p3.examenpractico.service;

import org.springframework.http.ResponseEntity;

import com.p3.examenpractico.model.Camioneta;

public interface CamionetaService {

  /**
   * The function getAllParams takes a parameter called available and returns a
   * ResponseEntity object.
   * 
   * @param available The "available" parameter is a string that represents the
   *                  availability status of
   *                  the parameters. It is used to filter the parameters that are
   *                  available or not.
   * @return The method is returning a ResponseEntity object that can hold any
   *         type of response body.
   */
  ResponseEntity<?> getAllParams(String available);

  /**
   * The function saves a Camioneta object and returns a ResponseEntity.
   * 
   * @param camioneta The parameter "camioneta" is an object of type "Camioneta".
   *                  It represents a
   *                  specific instance of a camioneta (a type of vehicle). The
   *                  method "saveCamioneta" is used to save
   *                  this camioneta object, typically to a database or some other
   *                  storage system
   * @return The return type of the method saveCamioneta is ResponseEntity<?>.
   */
  ResponseEntity<?> saveCamioneta(Camioneta camioneta);
}
