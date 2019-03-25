package com.lls.boot.dao.provider;

import org.apache.ibatis.jdbc.SQL;

/************************************
 * SuccessRecordProvider
 * @author liliangshan
 * @date 2019-03-25
 ************************************/
public class SuccessRecordProvider {

  private static class Constants {
    private static final String TABLE_NAME = "`success_record`";
    private static final String SEC_KILL_ID = "`sec_kill_id`";
    private static final String PHONE = "`phone`";
    private static final String STATE = "`state`";
    private static final String CREATED_AT = "`created_at`";


  }

  public String getItem() {
    return new SQL() {
      {
        SELECT(Constants.SEC_KILL_ID, Constants.PHONE, Constants.STATE, Constants.CREATED_AT);
        FROM(Constants.TABLE_NAME);
        JOIN(SecKillProvider.Constants.TABLE_NAME + " ON " + SecKillProvider.Constants.ID + " = " + Constants.SEC_KILL_ID);
        WHERE(SecKillProvider.Constants.ID + " = #{secKillId}");
        WHERE(Constants.PHONE + " = #{phone}");
      }
    }.toString();
  }

}
