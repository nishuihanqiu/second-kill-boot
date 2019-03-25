package com.lls.boot.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/************************************
 * DruidConfig
 * @author liliangshan
 * @date 2019-03-25
 ************************************/
@Configuration
@MapperScan(basePackages = {"com.lls.boot.dao.mapper"})
public class DruidConfig {

  private static final Logger logger = LoggerFactory.getLogger(DruidConfig.class);

  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.username}")
  private String userName;

  @Value("${spring.datasource.password}")
  private String password;

  @Value("${spring.datasource.driver-class-name}")
  private String driverClassName;

  // pool
  @Value("${spring.datasource-pool.initialSize}")
  private Integer initialSize;

  @Value("${spring.datasource-pool.minIdle}")
  private Integer minIdle;

  @Value("${spring.datasource-pool.maxActive}")
  private Integer maxActive;

  @Value("${spring.datasource-pool.maxWait}")
  private Integer maxWait;

  @Value("${spring.datasource-pool.timeBetweenEvictionRunsMillis}")
  private Long timeBetweenEvictionRunsMillis;

  @Value("${spring.datasource-pool.minEvictableIdleTimeMillis}")
  private Long minEvictableIdleTimeMillis;

  @Value("${spring.datasource-pool.validationQuery}")
  private String validationQuery;

  @Value("${spring.datasource-pool.testWhileIdle}")
  private Boolean testWhileIdle;

  @Value("${spring.datasource-pool.testOnBorrow}")
  private Boolean testOnBorrow;

  @Value("${spring.datasource-pool.testOnReturn}")
  private Boolean testOnReturn;

  @Value("${spring.datasource-pool.poolPreparedStatements}")
  private Boolean poolPreparedStatements;

  @Value("${spring.datasource-pool.maxPoolPreparedStatementPerConnectionSize}")
  private Integer maxPoolPreparedStatementPerConnectionSize;

  @Value("${spring.datasource-pool.filters}")
  private String filters;

  @Value("${spring.datasource-pool.connectionProperties}")
  private String connectionProperties;

  @Bean
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(this.url);
    dataSource.setUsername(this.userName);
    dataSource.setPassword(this.password);
    dataSource.setDriverClassName(this.driverClassName);
    dataSource.setInitialSize(this.initialSize);
    dataSource.setMinIdle(this.minIdle);
    dataSource.setMaxActive(this.maxActive);
    dataSource.setMaxWait(this.maxWait);
    dataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
    dataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
    dataSource.setValidationQuery(this.validationQuery);
    dataSource.setTestWhileIdle(this.testWhileIdle);
    dataSource.setTestOnBorrow(this.testOnBorrow);
    dataSource.setTestOnReturn(this.testOnReturn);
    dataSource.setPoolPreparedStatements(this.poolPreparedStatements);
    dataSource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);

    List<Filter> filters = new ArrayList<>();
    WallConfig wallConfig = new WallConfig();
    wallConfig.setMultiStatementAllow(true);
    WallFilter wallFilter = new WallFilter();
    wallFilter.setConfig(wallConfig);
    filters.add(wallFilter);
    dataSource.setProxyFilters(filters);
    dataSource.setConnectionProperties(this.connectionProperties);

    try {
      dataSource.setFilters(this.filters);
      logger.info("dataSource instance created...");
      dataSource.init();
      logger.info("dataSource instance inited...");
      return dataSource;
    } catch (SQLException e) {
      logger.error("druid configuration initialization filter:" + e.getMessage(), e);
      throw new RuntimeException("druid configuration initialization filter:" + e.getMessage(), e);
    }
  }

}
