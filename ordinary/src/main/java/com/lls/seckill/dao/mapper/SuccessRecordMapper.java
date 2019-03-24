package com.lls.seckill.dao.mapper;

import com.lls.seckill.model.SuccessRecord;
import org.apache.ibatis.annotations.Param;

/************************************
 * SuccessRecordMapper
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public interface SuccessRecordMapper {

    int save(SuccessRecord successRecord);

    SuccessRecord getItem(@Param("secKillId") long secKillId, @Param("phone") String phone);

}
