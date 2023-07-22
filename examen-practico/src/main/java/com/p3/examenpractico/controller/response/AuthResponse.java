package com.p3.examenpractico.controller.response;

import lombok.Data;

@Data
public class AuthResponse {
  private String email;
  private String accessToken;
}
