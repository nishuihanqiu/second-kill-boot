package com.lls.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/************************************
 * BootApplication
 * @author liliangshan
 * @date 2019-03-25
 ************************************/
@SpringBootApplication
@EnableTransactionManagement
public class BootApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootApplication.class, args);
  }

}
