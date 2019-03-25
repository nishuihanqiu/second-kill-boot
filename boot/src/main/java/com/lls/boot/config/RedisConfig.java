package com.lls.boot.config;

import com.lls.boot.dao.cache.RedisCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/************************************
 * RedisConfig
 * @author liliangshan
 * @date 2019-03-25
 ************************************/
@Configuration
public class RedisConfig {

  @Value("${redis.host}")
  private String ip;

  @Value("${redis.port}")
  private Integer port;

  @Bean
  public RedisCache redisCache() {
    return new RedisCache(this.ip, this.port);
  }

}
