package com.p3.examenpractico.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.p3.examenpractico.model.Vendedor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * The JwtUtils class is responsible for generating and validating JWT tokens in
 * a Java application.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Slf4j
@Component
public class JwtUtils {
  @Autowired
  private Environment environment;

  /**
   * Genera el token a partir de los datos de usuario. Agregándole el
   * username(email) en el payload.
   * 
   * @param user Usuario identificado.
   * @return String del token generado.
   */
  public String generateAccessToken(Vendedor user) {
    log.info("Generating Token");
    int exp = Integer.parseInt(environment.getProperty("config.secutiry.oauth.jwt.expiration"));

    log.info("Vendedor user: " + user.toString());
    // log.info("Vendedor roles: " + user.getAuthorities().toString());

    return Jwts.builder()
        .setSubject(user.getEmail())
        .setIssuer("BMore")
        // .setClaims(claims)
        // .claim("roles", user.getAuthorities())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + exp))
        .signWith(SignatureAlgorithm.HS512, environment.getProperty("config.security.oauth.jwt.key"))
        .compact();
  }

  /**
   * Obtiene el username(email) del token.
   * 
   * @param token String del token en cuestión.
   * @return String del username(email).
   */
  String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(environment.getProperty("config.security.oauth.jwt.key")).parseClaimsJws(token)
        .getBody().getSubject();
  }

  /**
   * Verifíca si es un token válido o no.
   * 
   * @param token String del token en cuestión.
   * @return <code>true</code> si y solo si es un token válido, o
   *         <code>false</code> en cualquier otro caso.
   */
  public boolean validateAccessToken(String token) {
    try {
      Jwts.parser().setSigningKey(environment.getProperty("config.security.oauth.jwt.key")).parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException ex) {
      log.error("JWT expired", ex.getMessage());
    } catch (IllegalArgumentException ex) {
      log.error("Token is null, empty or only whitespace", ex.getMessage());
    } catch (MalformedJwtException ex) {
      log.error("JWT is invalid", ex);
    } catch (UnsupportedJwtException ex) {
      log.error("JWT is not supported", ex);
    } catch (SignatureException ex) {
      log.error("Signature validation failed");
    }

    return false;
  }

  /**
   * Obtenemos el Subject del token.
   * 
   * @param token String del token en cuestión.
   * @return String del Subject.
   */
  public String getSubject(String token) {
    return parseClaims(token).getSubject();
  }

  /**
   * Parseo de los claims.
   * 
   * @param token String del token en cuestión.
   * @return <code>Claims</code> encontrados.
   */
  Claims parseClaims(String token) {
    return Jwts.parser()
        .setSigningKey(environment.getProperty("config.security.oauth.jwt.key"))
        .parseClaimsJws(token)
        .getBody();
  }

  /**
   * Obtiene todos los claims del token.
   * 
   * @param token String del token en cuestión.
   * @return <code>Claims</code> encontrados.
   */
  Claims getAllClaimsFromToken(String token) {
    try {
      return Jwts.parser().setSigningKey(environment.getProperty("config.security.oauth.jwt.key")).parseClaimsJws(token)
          .getBody();
    } catch (Exception e) {
      log.error("Error al obtener claims", e);
      return null;
    }
  }
}
