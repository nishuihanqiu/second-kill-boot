package com.lls.seckill.service.impl;

import com.lls.seckill.dao.mapper.SuccessRecordMapper;
import com.lls.seckill.model.SuccessRecord;
import com.lls.seckill.service.SuccessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/************************************
 * SuccessRecordServiceImpl
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
@Service
public class SuccessRecordServiceImpl implements SuccessRecordService {

    @Autowired
    private SuccessRecordMapper successRecordMapper;

    private int save(SuccessRecord record) {
        if (record.getSecKillId() == null) {
            throw new IllegalArgumentException("secKillId不能为空");
        }
        if (record.getPhone() == null || record.getPhone().trim().length() == 0) {
            throw new IllegalArgumentException("phone不能为空");
        }
        return successRecordMapper.save(record);
    }

    @Override
    public int save(long secKillId, String phone) {
        SuccessRecord record = this.generateItem(secKillId, phone);
        return this.save(record);
    }

    @Override
    public SuccessRecord generateItem(long secKillId, String phone) {
        SuccessRecord record = new SuccessRecord();
        record.setSecKillId(secKillId);
        record.setPhone(phone);
        return record;
    }

    @Override
    public SuccessRecord getItem(long secKillId, String phone) {
        if (phone == null || phone.trim().length() == 0) {
            throw new IllegalArgumentException("phone不能为空");
        }
        return successRecordMapper.getItem(secKillId, phone);
    }

}
