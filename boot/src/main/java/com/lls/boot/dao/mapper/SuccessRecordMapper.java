package com.lls.boot.dao.mapper;

import com.lls.boot.dao.provider.SuccessRecordProvider;
import com.lls.boot.model.SuccessRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

/************************************
 * SuccessRecordMapper
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
@Repository
public interface SuccessRecordMapper {

    @Insert("INSERT IGNORE INTO `success_record`(`sec_kill_id`, `phone`, state) VALUES (#{seckillId}, #{phone}, 0)")
    int save(SuccessRecord successRecord);

    @SelectProvider(type = SuccessRecordProvider.class, method = "getItem")
    SuccessRecord getItem(@Param("secKillId") long secKillId, @Param("phone") String phone);

}
