package com.buba.springcloud.controller;


import com.buba.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming.li
 * @date 2022/9/19 14:15
 */
@RestController
@Slf4j
public class OrderHystirxController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id){
        return  paymentHystrixService.paymentInfo_OK(id);
    }


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        System.out.println("进入paymentInfo_TimeOut方法");
        long start = System.currentTimeMillis();
        String timeOut = paymentHystrixService.paymentInfo_TimeOut(id);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        return timeOut;
    }


}
