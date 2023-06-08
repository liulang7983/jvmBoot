package com.buba.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author ming.li
 * @date 2022/9/15 14:10
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "payment/consul")
    public String paymentConsul() {
        System.out.println("调用了：paymentConsul");
        return "SpringCloud with consul:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
