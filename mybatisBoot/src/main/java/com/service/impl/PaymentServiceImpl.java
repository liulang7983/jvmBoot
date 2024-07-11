package com.service.impl;

import com.bean.Payment;
import com.dao.PaymentMapper;
import com.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2024/7/11 11:32
 * @Version 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public void create() {
        for (int i = 0; i <2000 ; i++) {
            Payment payment = new Payment();
            payment.setSerial("张三"+i);
            paymentMapper.insert(payment);
        }
    }

    @Override
    public void selectList() {
        List<Payment> payments = paymentMapper.selectList();
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <payments.size() ; i++) {
            list.add(payments.get(i).getId());
        }
        List<Payment> payments1 = paymentMapper.selectListByid(list);
        System.out.println(payments1.size());
    }
}
