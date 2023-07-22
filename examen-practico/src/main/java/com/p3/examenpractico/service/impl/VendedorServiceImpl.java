package com.p3.examenpractico.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p3.examenpractico.model.Vendedor;
import com.p3.examenpractico.repository.VendedorRepository;
import com.p3.examenpractico.service.VendedorService;

/**
 * The VendedorServiceImpl class is a service implementation for the
 * VendedorService interface.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Service
public class VendedorServiceImpl implements VendedorService {

  @Autowired
  private VendedorRepository vendedorRepository;

  @Override
  public Vendedor getVendedorByEmail(String emial) {
    return vendedorRepository.findByEmail(emial).orElse(null);
  }

}
