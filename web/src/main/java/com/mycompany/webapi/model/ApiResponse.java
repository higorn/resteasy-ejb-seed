/*
 * File:   ApiResponse.java
 *
 * Created on 28/05/18, 21:42
 */
package com.mycompany.webapi.model;

/**
 * @author higor
 */
public class ApiResponse<T> {
  private Integer code;
  private String status;
  private T data;

  public ApiResponse() {
  }

  public ApiResponse(final Integer code, final String status, final T data) {
    this.code = code;
    this.status = status;
    this.data = data;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}

