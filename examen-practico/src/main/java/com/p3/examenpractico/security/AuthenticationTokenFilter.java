package com.p3.examenpractico.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.p3.examenpractico.model.Vendedor;
import com.p3.examenpractico.repository.VendedorRepository;

/**
 * The `AuthenticationTokenFilter` class is a Java filter that handles
 * authentication and authorization by validating and extracting a JWT token
 * from the request header, setting the authentication context, and excluding
 * certain URI patterns from filtering.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtils jwtUtil;

  @Autowired
  private VendedorRepository vendedorRepository;

  private static String tokenPrefix = "Bearer ";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    if (!hasAuthorizationBearer(request)) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = getAccessToken(request);

    // Verifíca si es un Token válido.
    if (!jwtUtil.validateAccessToken(token)) {
      filterChain.doFilter(request, response);
      return;
    }

    setAuthenticationContext(token, request);
    filterChain.doFilter(request, response);
  }

  /**
   * Valida si contiene un token en el header de Authorization y si comienza con
   * el prefijo indicado.
   * 
   * @param request Solicitud {@link HttpServletRequest} original.
   * @return <code>true</code> si y solo si comple las condiciones de validacion,
   *         o <code>false</code> en cualqueir otro caso.
   */
  private boolean hasAuthorizationBearer(HttpServletRequest request) {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (ObjectUtils.isEmpty(header) || !header.startsWith(AuthenticationTokenFilter.tokenPrefix)) {
      return false;
    }

    return true;
  }

  /**
   * Elimina el prefijo: Bearer del token.
   * 
   * @param request Solicitud {@link HttpServletRequest} original.
   * @return Un {@link String} del token en cuestión.
   */
  private String getAccessToken(HttpServletRequest request) {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    return header.split(" ")[1].trim();
  }

  /**
   * Configura el modo de autenticación y los parámetros que se concideran para
   * ello. Además de obtener los datos del usuario a partir del Subject contenido
   * en el token.
   * 
   * @param token   String que contiene el token.
   * @param request Solicitud {@link HttpServletRequest} original.
   */
  private void setAuthenticationContext(String token, HttpServletRequest request) {
    UserDetails userDetails = getUserDetails(token);

    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
        userDetails.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  /**
   * Obtiene el detalle del usuario, a partir del Subject contenido en el token.
   * 
   * @param token Token en cuestión.
   * @return Una instacia {@link Vendedor} con los datos obtenidos si los
   *         encontró.
   */
  private UserDetails getUserDetails(String token) {
    return vendedorRepository.findByEmail(jwtUtil.getSubject(token)).get();
  }

  /**
   * The function checks if the requested servlet path matches any of the excluded
   * URI patterns.
   * 
   * @param request The `request` parameter is an instance of the
   *                `HttpServletRequest` class, which
   *                represents an HTTP request made by a client to a server. It
   *                contains information such as the
   *                request method, headers, parameters, and the requested URI.
   * @return The method is returning a boolean value.
   */
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    List<String> excludeUriPatterns = new ArrayList<>();
    excludeUriPatterns.add("/auth/login");
    AntPathMatcher pathMatcher = new AntPathMatcher();

    return excludeUriPatterns.stream()
        .anyMatch(p -> {
          return pathMatcher.match(p, request.getServletPath());
        });
  }
}
