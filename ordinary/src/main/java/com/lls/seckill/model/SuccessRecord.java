package com.lls.seckill.model;

import java.io.Serializable;
import java.util.Date;

/************************************
 * SuccessRecord
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public class SuccessRecord implements Serializable {

  private static final long serialVersionUID = -344288828339494L;

  private Long secKillId; // 秒杀商品id
  private String phone; // 用户手机号
  private short state;  // 状态标识（-1:无效，0:成功,1:已付款）
  private Date createdAt; // 创建时间
  private SecKill secKill;


  public Long getSecKillId() {
    return secKillId;
  }

  public void setSecKillId(Long secKillId) {
    this.secKillId = secKillId;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public short getState() {
    return state;
  }

  public void setState(short state) {
    this.state = state;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public void setSecKill(SecKill secKill) {
    this.secKill = secKill;
  }

  public SecKill getSecKill() {
    return secKill;
  }

}
