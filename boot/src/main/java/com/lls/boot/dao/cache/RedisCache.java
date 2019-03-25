package com.lls.boot.dao.cache;

import com.lls.boot.model.SecKill;
import com.lls.boot.util.SerializerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/************************************
 * RedisCache
 * @author liliangshan
 * @date 2019-03-22
 ************************************/
public class RedisCache {

    private final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private JedisPool jedisPool;

    public RedisCache(String ip, int port) {
        this.jedisPool = new JedisPool(ip, port);
    }

    private <T> T getObject(String key, Class<T> clz) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                byte[] data = jedis.get(key.getBytes());
                if (data == null) {
                    return null;
                }
                return SerializerUtils.deserialize(data, clz);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public SecKill getSecKill(long secKillId) {
        String key = "secKill:" + secKillId;
        return this.getObject(key, SecKill.class);
    }

    public Object getObject(String key) {
        return this.getObject(key, Object.class);
    }

    public String putSecKill(SecKill secKill) {
        String key = "secKill:" + secKill.getId();
        return this.putObject(key, secKill);
    }

    private <T> String putObject(String key, T object) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                byte[] data = SerializerUtils.serialize(object);
                //超时缓存
                int timeout = 60 * 60;
                //返回OK 一致性建立在超时的基础上
                return jedis.setex(key.getBytes(), timeout, data);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
