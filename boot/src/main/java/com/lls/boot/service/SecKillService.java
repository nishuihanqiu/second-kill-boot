package com.lls.boot.service;

import com.lls.boot.dto.Execution;
import com.lls.boot.dto.Exposer;
import com.lls.boot.model.SecKill;

import java.util.List;

/************************************
 * SecKillService
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public interface SecKillService {

    List<SecKill> getSecKillList(int offset, int limit);

    SecKill getItem(long id);

    Exposer getSecKillExposer(long id);

    Execution execute(long id, String phone, String md5) throws RuntimeException;

    Execution executeProcedure(long id, String phone, String md5);


}
