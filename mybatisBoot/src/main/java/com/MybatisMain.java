package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author ming.li
 * @Date 2024/7/11 8:59
 * @Version 1.0
 */
@SpringBootApplication
@EnableTransactionManagement
public class MybatisMain {
    public static void main(String[] args) {
        SpringApplication.run(MybatisMain.class,args);
    }
}
