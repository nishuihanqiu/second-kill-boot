package com.lls.boot.model;

import java.io.Serializable;
import java.util.Date;

/************************************
 * SecKill
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public class SecKill implements Serializable {

  private static final long serialVersionUID = -1849490099322L;

  private Long id;
  private String name; // 名称
  private int count; // 剩余数量
  private Date startTime; // 开始时间
  private Date endTime;  // 结束时间
  private Date createdAt;  // 创建时间
  private Date updatedAt;  // 更新时间

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}
