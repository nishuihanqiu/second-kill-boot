package com.lls.boot.dto;

/************************************
 * Exposer
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public class Exposer {

  private boolean exposed;
  private String md5;
  private long secKillId;
  private long currentTime;
  private long startTime;
  private long endTime;

  public Exposer(boolean exposed, String md5, long secKillId) {
    this.exposed = exposed;
    this.md5 = md5;
    this.secKillId = secKillId;
  }

  public Exposer(boolean exposed, long secKillId, long currentTime, long startTime, long endTime) {
    this.exposed = exposed;
    this.secKillId = secKillId;
    this.currentTime = currentTime;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public Exposer(boolean exposed, long secKillId) {
    this.exposed = exposed;
    this.secKillId = secKillId;
  }

  public boolean isExposed() {
    return exposed;
  }

  public void setExposed(boolean exposed) {
    this.exposed = exposed;
  }

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public long getSecKillId() {
    return secKillId;
  }

  public void setSecKillId(long secKillId) {
    this.secKillId = secKillId;
  }

  public long getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(long currentTime) {
    this.currentTime = currentTime;
  }

  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  public long getEndTime() {
    return endTime;
  }

  public void setEndTime(long endTime) {
    this.endTime = endTime;
  }
}
