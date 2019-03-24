package com.lls.seckill.enums;

/************************************
 * SecKillStateEnum
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public enum SecKillStateEnum {

  UN_INIT(2, "未注册"),
  SUCCESS(1, "秒杀成功"),
  FINISHED(0, "秒杀结束"),
  REPEAT_KILLED(-1, "重复秒杀"),
  INNER_ERROR(-2, "系统异常"),
  DATA_REWRITE(-3, "数据篡改");

  public static SecKillStateEnum stateOf(int index) {
    for (SecKillStateEnum state : values()) {
      if (state.getState() == index)
        return state;
    }
    return null;
  }

  private SecKillStateEnum(int state, String message) {
    this.state = state;
    this.message = message;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  private int state;
  private String message;

}
