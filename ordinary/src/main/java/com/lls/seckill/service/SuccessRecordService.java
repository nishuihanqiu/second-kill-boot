package com.lls.seckill.service;

import com.lls.seckill.model.SuccessRecord;

/************************************
 * SuccessRecordService
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public interface SuccessRecordService {

    int save(long secKillId, String phone);

    SuccessRecord generateItem(long secKillId, String phone);

    SuccessRecord getItem(long secKillId, String phone);

}
