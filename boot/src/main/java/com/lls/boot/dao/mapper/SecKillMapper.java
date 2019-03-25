package com.lls.boot.dao.mapper;

import com.lls.boot.dao.provider.SecKillProvider;
import com.lls.boot.model.SecKill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/************************************
 * SecKillMapper
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
@Repository
public interface SecKillMapper {

    @SelectProvider(type = SecKillProvider.class, method = "getAll")
    List<SecKill> getAll(@Param("offset") int offset, @Param("limit") int limit);

    @SelectProvider(type = SecKillProvider.class, method = "getItem")
    SecKill getItem(@Param("id") long id);

    @UpdateProvider(type = SecKillProvider.class, method = "updateCount")
    int updateCount(@Param("id") long id, @Param("executedTime") Date executedTime);

    @SelectProvider(type = SecKillProvider.class, method = "executeByProcedure")
    void executeByProcedure(Map<String, Object> map);

}
