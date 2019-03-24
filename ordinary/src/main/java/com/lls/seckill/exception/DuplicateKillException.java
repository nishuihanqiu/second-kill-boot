package com.lls.seckill.exception;

/************************************
 * DuplicateKillException
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public class DuplicateKillException extends RuntimeException {

  public DuplicateKillException(String message) {
    super(message);
  }

  public DuplicateKillException(String message, Throwable cause) {
    super(message, cause);
  }

  public DuplicateKillException(Throwable cause) {
    super(cause);
  }

}
