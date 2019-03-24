package com.lls.seckill.dto;

import com.lls.seckill.enums.SecKillStateEnum;
import com.lls.seckill.model.SuccessRecord;

/************************************
 * Execution
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public class Execution {

    private long secKillId;
    private int state;
    private SuccessRecord successRecord;
    private String message;

    public Execution(long secKillId, SecKillStateEnum state) {
        this.secKillId = secKillId;
        this.state = state.getState();
        this.message = state.getMessage();
    }

    public Execution(long secKillId, SecKillStateEnum state, SuccessRecord successRecord) {
        this.secKillId = secKillId;
        this.state = state.getState();
        this.message = state.getMessage();
        this.successRecord = successRecord;
    }

    public long getSecKillId() {
        return secKillId;
    }

    public void setSecKillId(long secKillId) {
        this.secKillId = secKillId;
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

    public SuccessRecord getSuccessRecord() {
        return successRecord;
    }

    public void setSuccessRecord(SuccessRecord successRecord) {
        this.successRecord = successRecord;
    }
}
