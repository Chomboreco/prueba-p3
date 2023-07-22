package com.p3.examenpractico.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.p3.examenpractico.model.Vendedor;
import com.p3.examenpractico.repository.VendedorRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Service que carga la información del vendedor dado un email.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private VendedorRepository vendedorRepository;

  /**
   * The function loads a user by their username and returns a Vendedor object,
   * throwing an exception
   * if the user is not found.
   * 
   * @param username The "username" parameter is the username of the user that
   *                 needs to be loaded from
   *                 the database.
   * @return The method is returning an instance of the "Vendedor" class.
   */
  @Override
  public Vendedor loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("Loading user: " + username);
    return vendedorRepository.findByEmail(username).orElseThrow(
        () -> new UsernameNotFoundException("User " + username + " not found"));
  }
}
