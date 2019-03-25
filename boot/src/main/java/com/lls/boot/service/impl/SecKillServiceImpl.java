package com.lls.boot.service.impl;

import com.lls.boot.dao.cache.RedisCache;
import com.lls.boot.dao.mapper.SecKillMapper;
import com.lls.boot.dto.Execution;
import com.lls.boot.dto.Exposer;
import com.lls.boot.enums.SecKillStateEnum;
import com.lls.boot.exception.ClosedKillException;
import com.lls.boot.exception.DuplicateKillException;
import com.lls.boot.exception.SecKillException;
import com.lls.boot.model.SecKill;
import com.lls.boot.model.SuccessRecord;
import com.lls.boot.service.SecKillService;
import com.lls.boot.service.SuccessRecordService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/************************************
 * SecKillServiceImpl
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
@Service
public class SecKillServiceImpl implements SecKillService {

    private static final Logger logger = LoggerFactory.getLogger(SecKillService.class);
    private static final String SALT = "&*(()@#$%%^1234567890)(*&^%$#@!";

    @Autowired
    private SecKillMapper secKillMapper;
    @Autowired
    private SuccessRecordService successRecordService;
    @Autowired
    private RedisCache redisCache;

    @Override
    public List<SecKill> getSecKillList(int offset, int limit) {
        return secKillMapper.getAll(offset, limit);
    }

    @Override
    public SecKill getItem(long id) {
        return secKillMapper.getItem(id);
    }

    @Override
    public Exposer getSecKillExposer(long id) {
        SecKill secKill = redisCache.getSecKill(id);
        if (secKill == null) {
            secKill = this.getItem(id);
            if (secKill == null) {
                return new Exposer(false, id);
            }
            redisCache.putSecKill(secKill);
        }

        long startTime = secKill.getStartTime().getTime();
        long endTime = secKill.getEndTime().getTime();
        long currentTime = System.currentTimeMillis();
        if (currentTime < startTime || currentTime > endTime) {
            return new Exposer(false, id, currentTime, startTime, endTime);
        }

        String md5 = this.getMD5(id);
        return new Exposer(true, md5, id);
    }

    @Override
    public Execution execute(long id, String phone, String md5) throws RuntimeException {
        if (phone == null || phone.trim().length() != 11) {
            return new Execution(id, SecKillStateEnum.UN_INIT);
        }
        if (md5 == null || !this.getMD5(id).equals(md5)) {
            throw new SecKillException("second kill data rewrite.");
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            // 先记录购买明细
            int savedCount = successRecordService.save(id, phone);
            if (savedCount <= 0) {
                throw new DuplicateKillException("second kill repeated.");
            }
            //后减库存
            int updatedCount = secKillMapper.updateCount(id, new Date(currentTimeMillis));
            if (updatedCount <= 0) {
                throw new ClosedKillException("second kill closed.");
            }

            SuccessRecord successRecord = successRecordService.getItem(id, phone);
            return new Execution(id, SecKillStateEnum.SUCCESS, successRecord);
        } catch (DuplicateKillException | ClosedKillException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SecKillException("second kill error:" + e.getMessage(), e);
        }
    }

    @Override
    public Execution executeProcedure(long id, String phone, String md5) {
        if (phone == null || phone.trim().length() != 11) {
            return new Execution(id, SecKillStateEnum.UN_INIT);
        }
        if (md5 == null || !this.getMD5(id).equals(md5)) {
            return new Execution(id, SecKillStateEnum.DATA_REWRITE);
        }
        Map<String, Object> map= new HashMap<>();
        map.put("id", id);
        map.put("phone", phone);
        map.put("executedTime", new Date());
        map.put("result", null);

        try {
            secKillMapper.executeByProcedure(map);
            int result = MapUtils.getInteger(map, "result", -2);
            if (result == 1) {
                SuccessRecord record = successRecordService.getItem(id, phone);
                return new Execution(id, SecKillStateEnum.SUCCESS, record);
            }
            return new Execution(id, SecKillStateEnum.stateOf(result));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Execution(id, SecKillStateEnum.INNER_ERROR);
        }
    }

    private String getMD5(long secKillId) {
        String base = secKillId + "/" + SALT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
