package com.p3.examenpractico.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.p3.examenpractico.controller.response.BaseResponse;
import com.p3.examenpractico.model.Camioneta;
import com.p3.examenpractico.repository.CamionetaRepository;
import com.p3.examenpractico.service.CamionetaService;

@Service
/**
 * The CamionetaServiceImpl class is a Java implementation of the
 * CamionetaService interface that provides methods for retrieving and saving
 * Camioneta objects.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
public class CamionetaServiceImpl implements CamionetaService {

  @Autowired
  private CamionetaRepository camionetaRepository;

  /**
   * The function returns all parameters of a camioneta (truck) based on the
   * availability status.
   * 
   * @param available The "available" parameter is a String that represents the
   *                  availability status of
   *                  a camioneta (a type of vehicle).
   * @return The method is returning a ResponseEntity object.
   */
  @Override
  public ResponseEntity<?> getAllParams(String available) {
    if (!available.isEmpty())
      return ResponseEntity.ok(camionetaRepository.findByStatus(available));
    else
      return ResponseEntity.ok(camionetaRepository.findAll());
  }

  /**
   * The function saves a Camioneta object if it doesn't already exist in the
   * camionetaRepository,
   * otherwise it returns an error message.
   * 
   * @param camioneta The parameter "camioneta" is an object of type "Camioneta".
   * @return The method is returning a ResponseEntity object.
   */
  @Override
  public ResponseEntity<?> saveCamioneta(Camioneta camioneta) {
    if (!camionetaRepository.findByPlacas(camioneta.getPlacas()).isPresent())
      return ResponseEntity.ok(camionetaRepository.save(camioneta));

    return ResponseEntity.ok(BaseResponse.error("El registro ya existe"));
  }
}
