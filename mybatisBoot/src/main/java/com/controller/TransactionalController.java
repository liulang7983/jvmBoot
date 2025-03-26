package com.controller;

import com.bean.Payment;
import com.service.PaymentService;
import com.service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ming.li
 * @Date 2025/3/26 10:07
 * @Version 1.0
 */
@RestController
@RequestMapping("transactional")
public class TransactionalController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TransactionalService transactionalService;

    @RequestMapping("requiredNew")
    public String requiredNew(@RequestBody Payment payment){
        transactionalService.requiredNew(payment);
        return "成功";
    }
    @RequestMapping("required")
    public String required(@RequestBody Payment payment){
        transactionalService.required(payment);
        return "成功";
    }
    @RequestMapping("notSupported")
    public String notSupported(@RequestBody Payment payment){
        transactionalService.notSupported(payment);
        return "成功";
    }
    @RequestMapping("never")
    public String never(@RequestBody Payment payment){
        transactionalService.never(payment);
        return "成功";
    }
    @RequestMapping("selectById")
    public Payment selectById(Integer id){
        return paymentService.selectById(id);
    }
}
