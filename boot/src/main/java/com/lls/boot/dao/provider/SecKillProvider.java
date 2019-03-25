package com.lls.boot.dao.provider;

import org.apache.ibatis.jdbc.SQL;

/************************************
 * SecKillProvider
 * @author liliangshan
 * @date 2019-03-25
 ************************************/
public class SecKillProvider {

  static class Constants {
    public static final String TABLE_NAME = "`sec_kill`";
    public static final String ID = "`id`";
    private static final String NAME = "`name`";
    private static final String COUNT = "`count`";
    private static final String START_TIME = "`start_time`";
    private static final String END_TIME = "`end_time`";
    private static final String CREATED_AT = "`created_at`";
    private static final String UPDATED_AT = "`updated_at`";
  }

  public String getAll() {
    return new SQL() {
      {
        SELECT(Constants.ID, Constants.NAME, Constants.COUNT, Constants.START_TIME, Constants.END_TIME,
          Constants.CREATED_AT, Constants.UPDATED_AT);
        FROM(Constants.TABLE_NAME);
        ORDER_BY(Constants.CREATED_AT + " DESC LIMIT #{offset}, #{limit}");
      }
    }.toString();
  }

  public String getItem() {
    return new SQL() {
      {
        SELECT(Constants.ID, Constants.NAME, Constants.COUNT, Constants.START_TIME, Constants.END_TIME,
          Constants.CREATED_AT, Constants.UPDATED_AT);
        FROM(Constants.TABLE_NAME);
        WHERE(Constants.ID + "= #{id}");
      }
    }.toString();
  }

  public String updateCount() {
    return new SQL() {
      {
        UPDATE(Constants.TABLE_NAME);
        SET(Constants.COUNT + "=" + Constants.COUNT + "-1");
        WHERE(Constants.ID + "= #{id}");
        WHERE(Constants.START_TIME + "<= #{executedTime}");
        WHERE(Constants.END_TIME + "<= #{executedTime}");
        WHERE(Constants.COUNT + ">= 0");
      }
    }.toString();
  }

  public String executeByProcedure() {
    return "call execute_sec_kill(" +
      "#{id,jdbcType=BIGINT,mode=IN}," +
      "#{phone,jdbcType=VARCHAR,mode=IN}," +
      "#{executedTime,jdbcType=TIMESTAMP,mode=IN}," +
      "#{result,jdbcType=INTEGER,mode=OUT})";
  }

}
