package com.lls.boot.dto;

/************************************
 * Result
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public class Result<T> {

  private boolean status;
  private T data;
  private String message;

  public Result(boolean status, T data) {
    this.status = status;
    this.data = data;
  }

  public Result(boolean status, String message) {
    this.status = status;
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
