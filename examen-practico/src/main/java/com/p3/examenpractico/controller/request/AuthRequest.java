package com.p3.examenpractico.controller.request;

import lombok.Data;

@Data
public class AuthRequest {
  private String email;
  private String password;
}
