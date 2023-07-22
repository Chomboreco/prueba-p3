package com.p3.examenpractico.controller.response;

import java.util.Date;

import lombok.Data;

@Data
public class BaseResponse {
  private String message;
  private int code;
  private long date;

  public BaseResponse() {
    this.date = new Date().getTime();
    this.code = 0;
    this.message = "Operación correcta";
  }

  public BaseResponse(int code, String message) {
    this.code = code;
    this.message = message;
    this.date = new Date().getTime();
  }

  public BaseResponse(Integer code) {
    this.date = new Date().getTime();
    this.code = code;
  }

  public BaseResponse(String msg) {
    this.code = 1;
    this.date = new Date().getTime();
    this.message = msg;
  }

  public static BaseResponse ok() {
    return new BaseResponse(0, "Operación realizada con éxito");
  }

  public static BaseResponse error() {
    return new BaseResponse(1, "La operación no se pudo completar");
  }

  public static BaseResponse error(String msg) {
    return new BaseResponse(1, msg);
  }
}
