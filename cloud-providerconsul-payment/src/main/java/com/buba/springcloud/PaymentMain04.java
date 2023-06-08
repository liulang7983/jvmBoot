package com.buba.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ming.li
 * @date 2022/9/15 14:05
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain04 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain04.class,args);
    }
}
