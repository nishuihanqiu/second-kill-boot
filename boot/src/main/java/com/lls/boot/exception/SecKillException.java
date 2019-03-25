package com.lls.boot.exception;

/************************************
 * SecKillException
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public class SecKillException extends RuntimeException {

  public SecKillException(String message) {
    super(message);
  }

  public SecKillException(String message, Throwable cause) {
    super(message, cause);
  }

  public SecKillException(Throwable cause) {
    super(cause);
  }

}
