package com.lls.seckill.dao.mapper;

import com.lls.seckill.model.SecKill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/************************************
 * SecKillMapper
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public interface SecKillMapper {

    List<SecKill> getAll(@Param("offset") int offset, @Param("limit") int limit);

    SecKill getItem(@Param("id") long id);

    int updateCount(@Param("id") long id, @Param("executedTime") Date executedTime);

    void executeByProcedure(Map<String, Object> map);

}
