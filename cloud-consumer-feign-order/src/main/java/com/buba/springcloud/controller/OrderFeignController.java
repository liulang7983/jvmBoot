package com.buba.springcloud.controller;


import com.buba.springcloud.pojo.CommonResult;
import com.buba.springcloud.pojo.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.buba.springcloud.service.PaymentService;

/**
 * @author ming.li
 * @date 2022/9/16 14:05
 */
@RestController
public class OrderFeignController {
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResult result = paymentService.queryById(id);
        return result;
    }

    @GetMapping("/consumer/feign/timeout")
    public String PaymentFeignTimeOut() throws InterruptedException{
        return paymentService.PaymentFeignTimeOut();
    }

}
