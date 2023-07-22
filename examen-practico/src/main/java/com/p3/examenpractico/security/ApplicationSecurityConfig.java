package com.p3.examenpractico.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The `ApplicationSecurityConfig` class is a configuration class in Java that
 * sets up security measures for an application, including password encryption,
 * authentication manager, security filters, and CORS configuration.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Configuration
public class ApplicationSecurityConfig {
  @Autowired
  private AuthenticationTokenFilter authenticationTokenFilter;

  /**
   * Bean para en cifrado de contraseñas y datos sensibles.
   * 
   * @return Un codificador configurado.
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Bean para el método de autenticación.
   * 
   * @param authConfig
   * @return Un {@link AuthenticationManager} configurado.
   * @throws Exception Error en caso de no poder configurarse.
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  /**
   * Bean de seguridad para la configuración de los accesos permitidos a las rutas
   * configuradas.
   * 
   * @param http Clase {@link HttpSecurity} par ala configuracion de rutas y roles
   *             permitidos a las mismas.
   * @return FilterChain con toda la configuración aplicada.
   * @throws Exception Error en caso de no poder configurarse.
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf()
        .disable()
        .authorizeHttpRequests()
        // .antMatchers("/auth/**", "/departamento/get-all")
        .antMatchers("/auth/**")
        .permitAll()
        // .antMatchers("/departamento/save")
        // .hasRole("ADMIN")
        .antMatchers("/v3/**", "/swagger-ui/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.exceptionHandling()
        .authenticationEntryPoint(
            (request, response, ex) -> {
              response.sendError(
                  HttpServletResponse.SC_UNAUTHORIZED,
                  ex.getMessage());
            });

    // Filtro para la validación del token.
    http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    // Construccion de toda la configuración agregada.
    return http.build();
  }

  /**
   * Bean de configuración MVC para habilitar CORS.
   * 
   * @return Una instancia {@link WebMvcConfigurer} con CORS habilitado.
   */
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
      }
    };
  }
}
