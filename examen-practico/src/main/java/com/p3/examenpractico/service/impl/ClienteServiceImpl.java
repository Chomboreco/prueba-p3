package com.p3.examenpractico.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p3.examenpractico.model.Cliente;
import com.p3.examenpractico.repository.ClienteRepository;
import com.p3.examenpractico.service.ClienteService;

/**
 * The class "ClienteServiceImpl" is a service implementation for the
 * "ClienteService" interface.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Service
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public List<Cliente> getAll() {
    return clienteRepository.findAll();
  }

}
