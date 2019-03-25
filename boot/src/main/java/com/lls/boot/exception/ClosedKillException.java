package com.lls.boot.exception;

/************************************
 * ClosedKillException
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public class ClosedKillException extends RuntimeException {

  public ClosedKillException(String message) {
    super(message);
  }

  public ClosedKillException(String message, Throwable cause) {
    super(message, cause);
  }

  public ClosedKillException(Throwable cause) {
    super(cause);
  }

}
