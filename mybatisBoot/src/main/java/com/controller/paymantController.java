package com.controller;

import com.bean.Payment;
import com.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author ming.li
 * @Date 2024/7/11 11:30
 * @Version 1.0
 */
@RestController
@RequestMapping("payment")
public class paymantController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping("create")
    public String create(){
        paymentService.create();
        return "成功";
    }
    @RequestMapping("selectList")
    public void selectList(){
        paymentService.selectList();
    }

    @RequestMapping("selectListSerial")
    public List<Payment> selectListSerial(){
       return paymentService.selectListSerial();
    }

    @RequestMapping("selectListConcurrent")
    public List<Payment> selectListConcurrent(){
       return paymentService.selectListConcurrent();
    }
}
